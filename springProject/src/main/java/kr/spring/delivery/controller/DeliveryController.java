package kr.spring.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeliveryController {

	//자바빈(VO) 초기화
	
	//배송관리 메인
	@RequestMapping("/admin/deliveryList.do")
	public String adminDeliveryMain() {
		return "adminDeliveryList";
	}
	
	//송장관리
	@RequestMapping("/admin/deliveryTrack.do")
	public String adminDeliveryTracking() {
		return "adminDeliveryTrack";
	}
}
