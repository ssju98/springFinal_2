package kr.spring.category_top.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.spring.category_top.service.Category_topService;
import kr.spring.category_top.vo.Category_topVO;

@Controller
public class Category_topController {
	
	private static final Logger logger = LoggerFactory.getLogger(Category_topController.class);
	
	@Autowired
	private Category_topService category_topService;
	
	//자바빈(VO)초기화
	@ModelAttribute
	public Category_topVO initCommand() {
		return new Category_topVO();
	}
	
	//
	
}










