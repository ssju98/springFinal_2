package kr.spring.adminMember.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMemberController {
	
	//자바빈(VO) 초기화

	
	//회원 목록
	@RequestMapping("/adminMember/adminMemberList.do")
	public String adminMemberList() {
		return "adminMemberList";
	}
	
	//회원 상세
	@RequestMapping("/adminMember/adminMemberDetail.do")
	public String adminMemberDetail() {
		return "adminMemberDetail";
	}
	
	//회원 수정
	@RequestMapping("/adminMember/adminMemberUpdate.do")
	public String adminMemberUpdate() {
		return "adminMemberUpdate";
	}
	
	//회원 삭제
	@RequestMapping("/adminMember/adminMemberDelete.do")
	public String adminMemberDelete() {
		return "adminMemberDelete";
	}
	
}
