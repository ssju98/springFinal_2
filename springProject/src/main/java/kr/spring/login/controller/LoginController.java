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

	
	  //자바빈(VO) 초기화
	  @ModelAttribute 
	  public MemberVO initCommand() { 
		  return new MemberVO(); 
	  }
	
	//로그인 폼
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
	
	//로그인 처리
	@PostMapping("/login/loginAction.do")
	public String loginAction(@Valid LoginVO loginVO, HttpSession session, HttpServletResponse response ) {
		logger.debug("============로그인처리 Controller===========" +loginVO);
		
		LoginVO vo = loginService.loginAction(loginVO.getMem_id());
		
		boolean passwdCheck = false;
		
		if(vo!=null) {
			passwdCheck = vo.isCheckPassword(loginVO.getMem_passwd()); //비밀번호 일치여부 체크
		}
		
		
		if(passwdCheck) { //아이디가 존재,비밀번호 일치-> 로그인 성공 처리
			System.out.println("로그인 성공!");
			session.setAttribute("mem_num", vo.getMem_num()); //시퀀스
			session.setAttribute("mem_id", vo.getMem_id()); //아이디
			session.setAttribute("mem_name", vo.getMem_name()); //이름
			session.setAttribute("mem_auth", vo.getMem_auth()); //0:탈퇴 1:정지 2:일반 3:일반관리자 4:최고관리자
			
			return "redirect:/shop/main.do";
		}
		
		return "login/loginFail";
		
	}
	
	//카카오 로그인처리
	@RequestMapping(value = "/login/kakaoLogin.do", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception { 
		ModelAndView mav = new ModelAndView();
		JsonNode node = kakaoController.getAccessToken(code); // accessToken에 사용자의 로그인한 모든 정보가 들어있음 
		JsonNode accessToken = node.get("access_token"); // 사용자의 정보 
		JsonNode userInfo = kakaoController.getKakaoUserInfo(accessToken); 
		String kemail = null; 
		String kname = null; 
		String kid = null;
		String ktoken = null;
	
		// 유저정보 카카오에서 가져오기 Get properties 
		JsonNode properties = userInfo.path("properties"); 
		JsonNode kakao_account = userInfo.path("kakao_account"); 
		kid = userInfo.path("id").asText();
		kemail = kakao_account.path("email").asText(); 
		kname = properties.path("nickname").asText(); 
		ktoken = node.get("access_token").asText(); 
		
		//이메일 중복 확인
		int CheckEmail = memberService.selectCheckMemberEmail(kemail);
		
		//아이디로 db에 이미 저장된 회원정보인지 확인(이전에 로그인했는지 확인)
		int count = memberService.selectMemberCount(kid);

		MemberVO member = new MemberVO();
		
		//이메일이 중복되면 에러
		if(CheckEmail == 1) {
			mav.setViewName("common/resultView"); 
			mav.addObject("message","이미 가입된 이메일입니다."); 
			mav.addObject("url", request.getContextPath() + "/login/loginForm.do"); 
			
			return mav;
		}
			
		//이메일이 중복되지 않고 db에 존재하지 않는다면 db에 저장 + 세션 저장
		if(CheckEmail == 0 && count == 0) {
			member.setMem_id(kid);
			member.setMem_name(kname);
			member.setMem_email(kemail);
			member.setMem_token(ktoken);
			memberService.insertMemberKakao(member);
			member.setMem_num(loginService.loginMem_num(kid));
		}
		
		//db에 존재하면 세션에만 저장
		int mem_num = loginService.loginMem_num(kid);
		session.setAttribute("mem_id", kid); 
		session.setAttribute("mem_name", kname);
		session.setAttribute("mem_auth", 2);
		session.setAttribute("mem_num", mem_num);
		
		mav.setViewName("common/redirectView"); 
		mav.addObject("url", request.getContextPath() + "/shop/main.do"); 
		
		return mav;
	}
	
	//네이버 로그인 처리
	@RequestMapping(value = "/login/naverLogin.do", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView naverLogin(@RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request) throws IOException, ParseException {
		OAuth2AccessToken oauthToken;
	    oauthToken = naverLoginBO.getAccessToken(session, code, state);
	    
	    //1. 로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
	    
	    //2. String형식인 apiResult를 json형태로 바꿈
	    JSONParser parser = new JSONParser();
	    Object obj = null;
	    try {
	        obj = parser.parse(apiResult);
	    } catch (org.json.simple.parser.ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    JSONObject jsonObj = (JSONObject) obj;
	    
	    //3. 데이터 파싱
	    //Top레벨 단계 _response 파싱
	    JSONObject response_obj = (JSONObject)jsonObj.get("response");
	    
	    //response에 담겨있는 정보 파싱
	    String nid = (String) response_obj.get("id");
	    String nname = (String) response_obj.get("name");
	    String nemail = (String)response_obj.get("email");
	    String nphone =(String)response_obj.get("mobile");
	    String ntoken = (String)oauthToken.getAccessToken();
	    
	  //이메일 중복 확인
	  int CheckEmail = memberService.selectCheckMemberEmail(nemail);
	    
	  //아이디로 db에 이미 저장된 회원정보인지 확인(이전에 로그인했는지 확인)
	    int count = memberService.selectMemberCount(nid);

		MemberVO member = new MemberVO();
		ModelAndView mav = new ModelAndView();
		
		//이메일이 중복되면 에러
		if(CheckEmail == 1) {
			mav.setViewName("common/resultView"); 
			mav.addObject("message","이미 가입된 이메일입니다."); 
			mav.addObject("url", request.getContextPath() + "/login/loginForm.do"); 
					
			return mav;
		}
		
		//이메일이 중복되지 않고 db에 존재하지 않는다면 db에 저장 + 세션 저장
		if(CheckEmail == 0 && count == 0) {
			member.setMem_id(nid);
			member.setMem_name(nname);
			member.setMem_email(nemail);
			member.setMem_phone(nphone);
			member.setMem_token(ntoken);
			memberService.insertMemberNaver(member);
			member.setMem_num(loginService.loginMem_num(nid));
		}
	    
	    //로그인 & 로그아웃을 위해 세션값 부여
		int mem_num = loginService.loginMem_num(nid);
	    session.setAttribute("mem_name", nname);
	    session.setAttribute("mem_id", nid);
	    session.setAttribute("mem_auth", 2);
	    session.setAttribute("mem_num", mem_num);
	    
	    //로그인 성공시 redirect
	    mav.setViewName("common/redirectView"); 
		mav.addObject("url", request.getContextPath() + "/shop/main.do"); 
		
		return mav;
	}

	
	//로그아웃 처리
	@RequestMapping("/login/logout.do")
	public String logout(HttpSession session, HttpServletRequest request) {
		
		session.invalidate();
		request.getSession(true);
		
		return "logout"; //타일스 식별자
		
	}
	
	//아이디 찾기 폼
	@RequestMapping("login/findIdForm.do")
	public String findInfoForm() {
		
		return "findIdForm";
	}
	
	//아이디 찾기
	  @PostMapping("login/findId.do") 
	  public ModelAndView findId(MemberVO memberVO, HttpServletRequest request) { 
		  ModelAndView mav = new ModelAndView();
		  MemberVO member = new MemberVO();
		  
		  //이름과 이메일이 일치하는 계정이 있는지 체크
		  	int count = memberService.memberCount(memberVO); 
		  	
			if(count == 0) {	//일치하는 계정이 없으면
				mav.setViewName("common/resultView"); 
				mav.addObject("message","정보와 일치하는 계정이 없습니다."); 
				mav.addObject("url", request.getContextPath() + "/login/findIdForm.do"); 
			}else {				//일치하는 계정이 있으면
				member = memberService.findIdMember(memberVO); 
				if(member.getMem_token().equals("0")) { 	//SNS 계정이 아닌 일반계정이면
					mav.addObject("id",member.getMem_id());
					mav.setViewName("findIdResult"); 
				}else {										//SNS계정이면
					mav.setViewName("common/resultView"); 
					mav.addObject("message","SNS 로그인 계정은 아이디 찾기를 할 수 없습니다."); 
					mav.addObject("url", request.getContextPath() + "/login/findIdForm.do"); 
				}
			}
	  return mav; 
	  }
	 
	//비밀번호 찾기 폼
	@RequestMapping("login/findPasswdForm.do")
	public String findPasswdForm() {
		
		return "findPasswdForm";
	}
	
	//비밀번호 찾기 : 이메일 전송 & 비밀번호 변경
	@PostMapping("login/findPasswd.do")
	public ModelAndView findPasswd(MemberVO memberVO,HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		
		//랜덤함수를 이용하여 임시비밀번호 10자리 생성
		String randomPasswd = RandamPasswordUtil.randomPassword(10);
		
		//이메일 발송에 필요한 변수들 세팅
		String subject = "[내일의 집]비밀번호 찾기 이메일 입니다.";
		String to = memberVO.getMem_email();
		String content = "내일의집 홈페이지를 방문해주셔서 감사합니다."+
						"<br><br>"+"회원님의 계정 비밀번호는 임시 비밀번호인"+
						"<b>"+randomPasswd+"</b>" + " 로 변경되었습니다."+"<br>"+
						"로그인 후 비밀번호를 변경하여 이용해주세요.";
		
		//해당 이메일의 회원 정보가 있는지 확인
		int count = memberService.memberPasswdCount(memberVO.getMem_email());

		//회원 정보가 없으면
		if(count == 0) {
			mav.setViewName("common/resultView"); 
			mav.addObject("message","정보와 일치하는 계정이 없습니다."); 
			mav.addObject("url", request.getContextPath() + "/login/findPasswdForm.do"); 
		//회원 정보가 있으면
		}else { 	 
			String mem_token = memberService.selectmemberPasswdToken(memberVO.getMem_email());
			//SNS계정이 아닌 일반계정이면 이메일 발송 & 비밀번호 변경
			if(mem_token.equals("0")) {		
				try {
						MimeMessage mail = mailSender.createMimeMessage();
						MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");

			            mailHelper.setTo(to);
			            mailHelper.setSubject(subject);
			            mailHelper.setText(content, true);
			            
			            mailSender.send(mail);						//이메일 발송
			            memberVO.setMem_passwd(randomPasswd);		//vo에 임시비밀번호 저장
			            memberService.updateMemberPasswd(memberVO);	//비밀번호 변경
			            
			            mav.setViewName("findPasswd");
			            
				 } catch(Exception e) {
					 e.printStackTrace();
				 }
			//SNS계정이면
			}else {									
				mav.setViewName("common/resultView"); 
				mav.addObject("message","SNS 로그인 계정은 비밀번호가 없습니다."); 
				mav.addObject("url", request.getContextPath() + "/login/findIdForm.do"); 
			}
			
		}
		 return mav;
	}
}
