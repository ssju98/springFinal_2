package kr.spring.adminRevenue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.adminInfo.controller.AdminInfoController;
import kr.spring.adminRevenue.service.AdminRevenueService;

/**
  * @FileName : AdminRevenueController.java
  * @Date : 2021. 10. 12. 
  * @Author : 최유정
  * @Description : term기간별, goods상품별 매출관리 Controller
  */

@RestController //json을 리턴하는 method가 있는 경우
@Controller
public class AdminRevenueController {

	private static final Logger logger = LoggerFactory.getLogger(AdminInfoController.class);

	private AdminRevenueService adminRevenueService;
	
//	@RequestMapping("/admin/termRevenue.do")
//	public String termRevenue() {
//		
//		return "termRevenue";
//	}

	@RequestMapping("/admin/goodsRevenue.do")
	public String goodsRevenue() {
		
		return "goodsRevenue";
	}
	
	@RequestMapping("/admin/termRevenue.do")
	public ModelAndView termChartRevenue() {
		return new ModelAndView("termRevenue");
	}
	
}
