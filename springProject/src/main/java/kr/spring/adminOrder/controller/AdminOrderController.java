package kr.spring.adminOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminOrderController {
	
	//자바빈(VO) 초기화

	
	//주문 목록
	@RequestMapping("/adminOrder/adminOrderList.do")
	public String adminOrderList() {
		return "adminOrderList";
	}
	
	//주문 상세
	@RequestMapping("/adminOrder/adminOrderDetail.do")
	public String adminOrderDetail() {
		return "adminOrderDetail";
	}
	
	//주문 취소
	@RequestMapping("/adminOrder/adminOrderCancel.do")
	public String adminOrderCancel() {
		return "adminOrderCancel";
	}
	
	//배송 관리
	@RequestMapping("/adminOrder/adminDeliveryList.do")
	public String adminDeliveryList() {
		return "adminDeliveryList";
	}
	
	//송장 관리
	@RequestMapping("/adminOrder/adminDeliveryTrack.do")
	public String adminDeliveryTrack() {
		return "adminDeliveryTrack";
	}
	
}
