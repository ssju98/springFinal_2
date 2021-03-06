package kr.spring.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

import kr.spring.login.service.LoginService;
import kr.spring.login.vo.LoginVO;
import kr.spring.login.vo.NaverLoginBO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.RandamPasswordUtil;

import java.io.IOException;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	/* NaverLoginBO */ 
	private NaverLoginBO naverLoginBO; 
	private String apiResult = null;
	@Autowired 
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) { 
		this.naverLoginBO = naverLoginBO; 
	}

	
	  //?????????(VO) ?????????
	  @ModelAttribute 
	  public MemberVO initCommand() { 
		  return new MemberVO(); 
	  }
	
	//????????? ???
	@RequestMapping("/login/loginForm.do")
	public ModelAndView loginForm(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		String kakaoUrl = kakaoController.getAuthorizationUrl(session);
		String naverUrl = naverLoginBO.getAuthorizationUrl(session);


		mav.addObject("kakao_url",kakaoUrl);
		mav.addObject("naver_url",naverUrl);
		mav.setViewName("loginForm");
		return mav; 
	}
	
	//????????? ??????
	@PostMapping("/login/loginAction.do")
	public String loginAction(@Valid LoginVO loginVO, HttpSession session, HttpServletResponse response ) {
		logger.debug("============??????????????? Controller===========" +loginVO);
		
		LoginVO vo = loginService.loginAction(loginVO.getMem_id());
		
		boolean passwdCheck = false;
		
		if(vo!=null) {
			passwdCheck = vo.isCheckPassword(loginVO.getMem_passwd()); //???????????? ???????????? ??????
		}
		
		
		if(passwdCheck) { //???????????? ??????,???????????? ??????-> ????????? ?????? ??????
			System.out.println("????????? ??????!");
			session.setAttribute("mem_num", vo.getMem_num()); //?????????
			session.setAttribute("mem_id", vo.getMem_id()); //?????????
			session.setAttribute("mem_name", vo.getMem_name()); //??????
			session.setAttribute("mem_auth", vo.getMem_auth()); //0:?????? 1:?????? 2:?????? 3:??????????????? 4:???????????????
			
			return "redirect:/shop/main.do";
		}
		
		return "login/loginFail";
		
	}
	
	//????????? ???????????????
	@RequestMapping(value = "/login/kakaoLogin.do", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception { 
		ModelAndView mav = new ModelAndView();
		JsonNode node = kakaoController.getAccessToken(code); // accessToken??? ???????????? ???????????? ?????? ????????? ???????????? 
		JsonNode accessToken = node.get("access_token"); // ???????????? ?????? 
		JsonNode userInfo = kakaoController.getKakaoUserInfo(accessToken); 
		String kemail = null; 
		String kname = null; 
		String kid = null;
		String ktoken = null;
	
		// ???????????? ??????????????? ???????????? Get properties 
		JsonNode properties = userInfo.path("properties"); 
		JsonNode kakao_account = userInfo.path("kakao_account"); 
		kid = userInfo.path("id").asText();
		kemail = kakao_account.path("email").asText(); 
		kname = properties.path("nickname").asText(); 
		ktoken = node.get("access_token").asText(); 
		
		//????????? ?????? ??????
		int CheckEmail = memberService.selectCheckMemberEmail(kemail);
		
		//???????????? db??? ?????? ????????? ?????????????????? ??????(????????? ?????????????????? ??????)
		int count = memberService.selectMemberCount(kid);

		MemberVO member = new MemberVO();
		
		//???????????? ???????????? ??????
		if(CheckEmail == 1) {
			mav.setViewName("common/resultView"); 
			mav.addObject("message","?????? ????????? ??????????????????."); 
			mav.addObject("url", request.getContextPath() + "/login/loginForm.do"); 
			
			return mav;
		}
			
		//???????????? ???????????? ?????? db??? ???????????? ???????????? db??? ?????? + ?????? ??????
		if(CheckEmail == 0 && count == 0) {
			member.setMem_id(kid);
			member.setMem_name(kname);
			member.setMem_email(kemail);
			member.setMem_token(ktoken);
			memberService.insertMemberKakao(member);
			member.setMem_num(loginService.loginMem_num(kid));
		}
		
		//db??? ???????????? ???????????? ??????
		int mem_num = loginService.loginMem_num(kid);
		session.setAttribute("mem_id", kid); 
		session.setAttribute("mem_name", kname);
		session.setAttribute("mem_auth", 2);
		session.setAttribute("mem_num", mem_num);
		
		mav.setViewName("common/redirectView"); 
		mav.addObject("url", request.getContextPath() + "/shop/main.do"); 
		
		return mav;
	}
	
	//????????? ????????? ??????
	@RequestMapping(value = "/login/naverLogin.do", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView naverLogin(@RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request) throws IOException, ParseException {
		OAuth2AccessToken oauthToken;
	    oauthToken = naverLoginBO.getAccessToken(session, code, state);
	    
	    //1. ????????? ????????? ????????? ????????????.
	    apiResult = naverLoginBO.getUserProfile(oauthToken); //String????????? json?????????
	    
	    //2. String????????? apiResult??? json????????? ??????
	    JSONParser parser = new JSONParser();
	    Object obj = null;
	    try {
	        obj = parser.parse(apiResult);
	    } catch (org.json.simple.parser.ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    JSONObject jsonObj = (JSONObject) obj;
	    
	    //3. ????????? ??????
	    //Top?????? ?????? _response ??????
	    JSONObject response_obj = (JSONObject)jsonObj.get("response");
	    
	    //response??? ???????????? ?????? ??????
	    String nid = (String) response_obj.get("id");
	    String nname = (String) response_obj.get("name");
	    String nemail = (String)response_obj.get("email");
	    String nphone =(String)response_obj.get("mobile");
	    String ntoken = (String)oauthToken.getAccessToken();
	    
	  //????????? ?????? ??????
	  int CheckEmail = memberService.selectCheckMemberEmail(nemail);
	    
	  //???????????? db??? ?????? ????????? ?????????????????? ??????(????????? ?????????????????? ??????)
	    int count = memberService.selectMemberCount(nid);

		MemberVO member = new MemberVO();
		ModelAndView mav = new ModelAndView();
		
		//???????????? ???????????? ??????
		if(CheckEmail == 1) {
			mav.setViewName("common/resultView"); 
			mav.addObject("message","?????? ????????? ??????????????????."); 
			mav.addObject("url", request.getContextPath() + "/login/loginForm.do"); 
					
			return mav;
		}
		
		//???????????? ???????????? ?????? db??? ???????????? ???????????? db??? ?????? + ?????? ??????
		if(CheckEmail == 0 && count == 0) {
			member.setMem_id(nid);
			member.setMem_name(nname);
			member.setMem_email(nemail);
			member.setMem_phone(nphone);
			member.setMem_token(ntoken);
			memberService.insertMemberNaver(member);
			member.setMem_num(loginService.loginMem_num(nid));
		}
	    
	    //????????? & ??????????????? ?????? ????????? ??????
		int mem_num = loginService.loginMem_num(nid);
	    session.setAttribute("mem_name", nname);
	    session.setAttribute("mem_id", nid);
	    session.setAttribute("mem_auth", 2);
	    session.setAttribute("mem_num", mem_num);
	    
	    //????????? ????????? redirect
	    mav.setViewName("common/redirectView"); 
		mav.addObject("url", request.getContextPath() + "/shop/main.do"); 
		
		return mav;
	}

	
	//???????????? ??????
	@RequestMapping("/login/logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		
		session.invalidate();
		request.getSession(true);
		
		return "logout"; //????????? ?????????
		
	}
	
	//????????? ?????? ???
	@RequestMapping("login/findIdForm.do")
	public String findInfoForm() {
		
		return "findIdForm";
	}
	
	//????????? ??????
	  @PostMapping("login/findId.do") 
	  public ModelAndView findId(MemberVO memberVO, HttpServletRequest request) { 
		  ModelAndView mav = new ModelAndView();
		  MemberVO member = new MemberVO();
		  
		  //????????? ???????????? ???????????? ????????? ????????? ??????
		  	int count = memberService.memberCount(memberVO); 
		  	
			if(count == 0) {	//???????????? ????????? ?????????
				mav.setViewName("common/resultView"); 
				mav.addObject("message","????????? ???????????? ????????? ????????????."); 
				mav.addObject("url", request.getContextPath() + "/login/findIdForm.do"); 
			}else {				//???????????? ????????? ?????????
				member = memberService.findIdMember(memberVO); 
				if(member.getMem_token().equals("0")) { 	//SNS ????????? ?????? ??????????????????
					mav.addObject("id",member.getMem_id());
					mav.setViewName("findIdResult"); 
				}else {										//SNS????????????
					mav.setViewName("common/resultView"); 
					mav.addObject("message","SNS ????????? ????????? ????????? ????????? ??? ??? ????????????."); 
					mav.addObject("url", request.getContextPath() + "/login/findIdForm.do"); 
				}
			}
	  return mav; 
	  }
	 
	//???????????? ?????? ???
	@RequestMapping("login/findPasswdForm.do")
	public String findPasswdForm() {
		
		return "findPasswdForm";
	}
	
	//???????????? ?????? : ????????? ?????? & ???????????? ??????
	@PostMapping("login/findPasswd.do")
	public ModelAndView findPasswd(MemberVO memberVO,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		
		//??????????????? ???????????? ?????????????????? 10?????? ??????
		String randomPasswd = RandamPasswordUtil.randomPassword(10);
		
		//????????? ????????? ????????? ????????? ??????
		String subject = "[????????? ???]???????????? ?????? ????????? ?????????.";
		String to = memberVO.getMem_email();
		String content = "???????????? ??????????????? ?????????????????? ???????????????."+
						"<br><br>"+"???????????? ?????? ??????????????? ?????? ???????????????"+
						"<b>"+randomPasswd+"</b>" + " ??? ?????????????????????."+"<br>"+
						"????????? ??? ??????????????? ???????????? ??????????????????.";
		
		//?????? ???????????? ?????? ????????? ????????? ??????
		int count = memberService.memberPasswdCount(memberVO.getMem_email());

		//?????? ????????? ?????????
		if(count == 0) {
			mav.setViewName("common/resultView"); 
			mav.addObject("message","????????? ???????????? ????????? ????????????."); 
			mav.addObject("url", request.getContextPath() + "/login/findPasswdForm.do"); 
		//?????? ????????? ?????????
		}else { 	 
			String mem_token = memberService.selectmemberPasswdToken(memberVO.getMem_email());
			//SNS????????? ?????? ?????????????????? ????????? ?????? & ???????????? ??????
			if(mem_token.equals("0")) {		
				try {
						MimeMessage mail = mailSender.createMimeMessage();
						MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");

			            mailHelper.setTo(to);
			            mailHelper.setSubject(subject);
			            mailHelper.setText(content, true);
			            
			            mailSender.send(mail);						//????????? ??????
			            memberVO.setMem_passwd(randomPasswd);		//vo??? ?????????????????? ??????
			            memberService.updateMemberPasswd(memberVO);	//???????????? ??????
			            
			            mav.setViewName("findPasswd");
			            
				 } catch(Exception e) {
					 e.printStackTrace();
				 }
			//SNS????????????
			}else {									
				mav.setViewName("common/resultView"); 
				mav.addObject("message","SNS ????????? ????????? ??????????????? ????????????."); 
				mav.addObject("url", request.getContextPath() + "/login/findIdForm.do"); 
			}
			
		}
		 return mav;
	}
}
