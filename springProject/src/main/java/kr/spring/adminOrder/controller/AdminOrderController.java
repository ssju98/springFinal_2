package kr.spring.adminOrder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.adminOrder.service.AdminOrderService;
import kr.spring.adminOrder.vo.AdminOrderVO;
import kr.spring.util.PagingUtil;

@Controller
public class AdminOrderController {
	private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
	private int rowCount = 10;
	private int pageCount = 10;

	@Autowired
	private AdminOrderService adminOrderService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public AdminOrderVO initCommand() {
		return new AdminOrderVO();
	}
	
	//주문 목록
	@RequestMapping("/admin/orderList.do")
	public ModelAndView adminOrderList(@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
		logger.debug("<<adminOrderList 호출 - currentPage>> : " + currentPage);
		
		int count = adminOrderService.getOrderCount();
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "orderList.do");
		
		List<AdminOrderVO> list = null;
		if(count > 0) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			list = adminOrderService.getOrderList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminOrderList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//주문 상세
	@RequestMapping("/admin/orderDetail.do")
	public String adminOrderDetail(@RequestParam int order_no, Model model) {
		logger.debug("<<adminOrderDetail 호출 - order_no>> : " + order_no);
		
		AdminOrderVO adminOrderVO = adminOrderService.selectOrder(order_no);
		model.addAttribute("adminOrderVO", adminOrderVO);
		
		logger.debug("<<adminOrderDetail 호출 - adminOrderVO>> : " + adminOrderVO);
		
		return "adminOrderDetail";
	}
	
	//주문 취소
	@RequestMapping("/admin/orderCancel.do")
	public String adminOrderCancel() {
		return "adminOrderCancel";
	}
}
