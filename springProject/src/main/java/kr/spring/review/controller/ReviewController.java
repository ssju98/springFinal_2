package kr.spring.review.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.order.vo.OrderListVO;
import kr.spring.review.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/review/reviewAvaliable.do")
	public ModelAndView reviewAvaliavleForm(HttpSession session) {
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		List<OrderListVO> list = reviewService.selectReviewAvaliavle(mem_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewAvaliable");
		mav.addObject("list",list);
		return mav;
	}
	
	@GetMapping("/review/reviewWritten.do")
	public ModelAndView reviewWrittenForm(HttpSession session) {
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		List<OrderListVO> list = reviewService.selectReviewWritten(mem_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewWritten");
		mav.addObject("list",list);
		return mav;
	}
	
}
