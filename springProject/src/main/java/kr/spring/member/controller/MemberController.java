package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;



@Controller
public class MemberController {
	
	 private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	 
	 @Autowired 
	 private MemberService memberService;
	 
	  //자바빈(VO) 초기화
	  @ModelAttribute 
	  public MemberVO initCommand() { 
		  return new MemberVO(); 
	  }
	 
	
	  //회원가입 - 아이디 중복 체크
	  @RequestMapping("/member/confirmId.do")
	  @ResponseBody 
	  public Map<String,String> process(@RequestParam String mem_id){
	  
	  logger.debug("<<id>> : " + mem_id);
	  
	  Map<String,String> map = new HashMap<String,String>();
	  
	  MemberVO member = memberService.selectCheckMember(mem_id); 
	  if(member!=null) {
	  //아이디 중복 
		  map.put("result", "idDuplicated"); 
	  }else {
	  if(!Pattern.matches("^[A-Za-z0-9]{4,12}$", mem_id)) { 
		  //패턴 불일치
		  map.put("result", "notMatchPattern"); 
	  }else { 
		  //아이디 미중복 
		  map.put("result","idNotFound"); 
	  } 
	}
	  return map; 
	}
	 
	  //회원가입 - 회원가입 폼 호출
	  @GetMapping("/member/registerUser.do") 
	  public String form() {
		  return "memberRegister"; 
	  }
	 
	 //회원가입 - 회원가입 처리
	  @PostMapping("/member/registerUser.do") 
	  public String submit(@Valid MemberVO memberVO, BindingResult result) {
	  
	  logger.debug("<<회원 정보>> : " + memberVO);
	  
	  //유효성 검사 결과 오류가 있으면 폼 호출 
	  if(result.hasErrors()) { 
		  return form(); 
	  }
	  
	  //회원가입 
	  memberService.insertMember(memberVO);
	  
	  return "redirect:/main/main.do"; }
	 
}
