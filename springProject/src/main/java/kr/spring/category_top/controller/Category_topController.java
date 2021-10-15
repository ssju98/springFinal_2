package kr.spring.category_top.controller;

import java.util.ArrayList;
import java.util.List;

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

import kr.spring.category_top.service.Category_topService;
import kr.spring.category_top.vo.Category_topVO;

@Controller
public class Category_topController {

	private static final Logger logger = LoggerFactory.getLogger(Category_topController.class);

	@Autowired
	private Category_topService category_topService;

	// 자바빈(VO)초기화
	@ModelAttribute
	public Category_topVO initCommand() {
		return new Category_topVO();
	}

	// 상위카테고리 목록
	@RequestMapping("/category_top/main.do")
	public ModelAndView category_topList(@RequestParam(value = "c_top_no", defaultValue = "") int c_top_no,
			@RequestParam(value = "c_top_name", defaultValue = "") String c_top_name) {
		logger.debug(c_top_name);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("category_topList");

		return mav;
	}

	// 상위카테고리 상세정보
	@RequestMapping("/category_top/detail.do")
	public List selectCategory_top(HttpSession session, Model model) {
		/*
		 * Integer c_top_no = (Integer)session.getAttribute("c_top_no"); int c_top_no =
		 * 200;
		 * 
		 * Category_topVO category_top =
		 * category_topService.selectCategory_top(c_top_no);
		 * 
		 * logger.debug("상위 카테고리 상세정보 : " + category_top);
		 * 
		 * model.addAttribute("category_top", category_top);
		 */
		List<Category_topVO> list = new ArrayList<Category_topVO>();
		
		list = (List)session.getAttribute("category_top");
		
		session.setAttribute("category_top", list);
		
		Category_topVO category_top = category_topService.selectCategory_top();
				
		logger.debug("상위카테고리 정보 : " + list);
		
		return list; // 타일스 식별자
	}

	// 상위카테고리 정보 수정 - 수정 폼
	@GetMapping("/category_top/update.do")
	public String formUpdate(HttpSession session, Model model) {
		Integer c_top_no = (Integer) session.getAttribute("c_top_no");

		Category_topVO category_topVO = category_topService.selectCategory_top(c_top_no);

		model.addAttribute("category_topVO", category_topVO);

		return "category_topModify"; // 타일스 설정
	}

	// 상위카테고리 정보 수정 - 수정 데이터 처리
	@PostMapping("/category_top/update.do")
	public String submitUpdate(@Valid Category_topVO category_topVO, BindingResult result, HttpSession session) {

		logger.debug("대분류 카테고리 정보수정 : " + category_topVO);

		// 유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return "category_topModify";
		}

		Integer c_top_no = (Integer) session.getAttribute("c_top_no");
		category_topVO.setC_top_no(c_top_no);

		// 대분류 카테고리 정보수정
		category_topService.updateCategory_top(category_topVO);

		return "redirect:/category_top/main.do";
	}

}
