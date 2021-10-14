package kr.spring.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.delivery.vo.DeliveryVO;

@Controller
public class DeliveryController {
	
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public DeliveryVO initCommand() {
		return new DeliveryVO();
	}
	
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
