package kr.spring.adminMember.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.adminMember.service.AdminMemberService;
import kr.spring.adminMember.vo.AdminMemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class AdminMemberController {
	private static final Logger logger = LoggerFactory.getLogger(AdminMemberController.class);
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public AdminMemberVO initCommand() {
		return new AdminMemberVO();
	}
	
	//회원 목록
	@RequestMapping("/admin/memberList.do")
	public ModelAndView adminMemberList(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
										@RequestParam(value="auth_num", defaultValue="") String auth_num,
										@RequestParam(value="keyfield", defaultValue="") String keyfield,
										@RequestParam(value="keyword", defaultValue="") String keyword) {
		
		logger.debug("##### adminMemberList 호출 - currentPage : " + currentPage + "/ auth_num : " + auth_num  + "/ keyfield : " + keyfield +" / keyword : " + keyword);
		
		//검색 조건
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auth_num", auth_num);
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//데이터 개수
		int count = adminMemberService.getMemberCount(map);
		
		logger.debug("***** count : " + count);
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage,count,rowCount,pageCount,"memberList.do");
		
		List<AdminMemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			list = adminMemberService.getMemberList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminMemberList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//회원 상세
	@RequestMapping("/admin/memberDetail.do")
	public ModelAndView adminMemberDetail(@RequestParam int mem_num) {
		logger.debug("##### adminMemberDetail 호출 - mem_num : " + mem_num);
		
		AdminMemberVO adminMemberVO = adminMemberService.selectMember(mem_num);
		
		return new ModelAndView("adminMemberDetail", "adminMemberVO", adminMemberVO);
	}
	
	//회원 수정 폼
	@GetMapping("/admin/memberUpdate.do")
	public String adminMemberUpdateForm(@RequestParam int mem_num, Model model) {
		logger.debug("##### adminMemberUpdateForm 호출 - mem_num : " + mem_num);
		
		//탈퇴회원 체크
		AdminMemberVO member = adminMemberService.selectMember(mem_num);
		if(member.getMem_auth()==0) {
			return "redirect:/admin/memberList.do";
		}
		
		AdminMemberVO adminMemberVO = adminMemberService.selectMember(mem_num);
		model.addAttribute("adminMemberVO", adminMemberVO);
		
		return "adminMemberUpdate";
	}
	
	//회원 수정 처리
	@PostMapping("/admin/memberUpdate.do")
	public String adminMemberUpdateSubmit(@Valid AdminMemberVO adminMemberVO, BindingResult result) {
		logger.debug("##### adminMemberUpdateForm 호출 - adminMemberVO : " + adminMemberVO);
		
		//유효성 체크
		if(result.hasErrors()) { 
			logger.debug("***** ERROR : " + result.getAllErrors());
			return "adminMemberUpdate";
		}
		
		adminMemberService.updateMember(adminMemberVO);
		
		return "redirect:/admin/memberList.do";
	}

	//회원 삭제 폼
	@GetMapping("/admin/memberDelete.do")
	public String adminMemberDeleteForm(@RequestParam int mem_num, Model model, HttpSession session) {
		logger.debug("##### adminMemberDelete[GET] 호출 - mem_num : " + mem_num);
		
		//탈퇴회원 체크
		AdminMemberVO member = adminMemberService.selectMember(mem_num);
		if(member.getMem_auth()==0) {
			return "redirect:/admin/memberList.do";
		}
		
		//최고관리자 체크
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		logger.debug("***** mem_auth : " + mem_auth);
		if(mem_auth != 4) {
			return "redirect:/admin/memberList.do";
		}
		
		//회원번호 저장
		AdminMemberVO adminMemberVO = new AdminMemberVO();
		adminMemberVO.setManage_num(mem_num);
		model.addAttribute("adminMemberVO", adminMemberVO);
		
		return "adminMemberDelete";
	}
	
	//회원 삭제 처리
	@PostMapping("/admin/memberDelete.do")
	public String adminMemberDelete(AdminMemberVO adminMemberVO, HttpSession session) {
		logger.debug("##### adminMemberDelete[POST] 호출");
		logger.debug("***** mem_id : " + adminMemberVO.getMem_id() + ", mem_passwd : " + adminMemberVO.getMem_passwd() + ", manage_num : " + adminMemberVO.getManage_num());
		
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		String mem_id = (String)session.getAttribute("mem_id");
		logger.debug("***** mem_auth : " + mem_auth + " / mem_id : " + mem_id);
		
		AdminMemberVO dbMember = adminMemberService.selectCheckMember(adminMemberVO.getMem_id());
		boolean check = false;
		
		//최고관리자 아이디, 비밀번호 체크
		if(mem_auth == 4 && dbMember!=null && dbMember.getMem_id().equals(mem_id)) {
			check = dbMember.isCheckedPassword(adminMemberVO.getMem_passwd());
		}
		
		//최고관리자 인증 성공시 회원 삭제
		if(check) {
			adminMemberService.deleteMember(adminMemberVO.getManage_num());			
			return "redirect:/admin/memberList.do";
		}else {
			return "login/loginFail";
		}
	}
}
