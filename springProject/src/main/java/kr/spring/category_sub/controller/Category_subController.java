package kr.spring.category_sub.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.category_sub.service.Category_subService;
import kr.spring.category_sub.vo.Category_subVO;

public class Category_subController {
	
	private static final Logger logger = LoggerFactory.getLogger(Category_subController.class);
	
	@Autowired
	private Category_subService category_subService;
	
	//자바빈(VO)초기화
	@ModelAttribute
	public Category_subVO initCommand() {
		return new Category_subVO();
	}
	
	//하위카테고리 상세정보
	@RequestMapping("/category_sub/detail.do")
	public String process(HttpSession session, Model model) {
		Integer c_sub_no = (Integer)session.getAttribute("c_sub_no");
		
		Category_subVO category_sub = category_subService.selectCategory_sub(c_sub_no);
		
		logger.debug("하위 카테고리 상세정보 : " + category_sub);
		
		model.addAttribute("category_sub", category_sub);
		
		return "category_subView"; //타일스 식별자
	}
	
	//하위카테고리 정보 수정 - 수정 폼
	@GetMapping("/category_sub/update.do")
	public String formUpdate(HttpSession session, Model model) {
		Integer c_sub_no = (Integer)session.getAttribute("c_sub_no");
		
		Category_subVO category_subVO = category_subService.selectCategory_sub(c_sub_no);
		
		model.addAttribute("category_subVO", category_subVO);
		
		return "category_subModify"; //타일스 설정
	}
	
	//하위카테고리 정보 수정 - 수정 데이터 처리
	@PostMapping("/category_sub/update.do")
	public String submitUpdate(@Valid Category_subVO category_subVO, BindingResult result, 
			                   HttpSession session) {
		
		logger.debug("대분류 카테고리 정보수정 : " + category_subVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "category_subModify";
		}
		
		Integer c_sub_no = (Integer)session.getAttribute("c_sub_no");
		category_subVO.setC_sub_no(c_sub_no);
		
		//소분류 카테고리 정보수정
		category_subService.updateCategory_sub(category_subVO);
		
		return "redirect:/category_sub/myPage.do";
	}
	
}












