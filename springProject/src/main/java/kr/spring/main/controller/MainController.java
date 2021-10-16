package kr.spring.main.controller;

import java.text.DecimalFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/shop/main.do")
	public String main() {
		
		return "shopMain";//타일스 식별자
	}
	
	@RequestMapping("/admin/main.do")
	public String adminMain() {
		return "adminMain";
	}
}

