package kr.spring.order.controller;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.adminOrder.vo.AdminOrderVO;
import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.ProductCartVO;
import kr.spring.delivery.service.DeliveryService;
import kr.spring.delivery.vo.DeliveryVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.orderDetail.service.OrderDetailService;
import kr.spring.orderDetail.vo.OrderDetailVO;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;

@Controller
public class OrderController {
	
		private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
		@Autowired
		private CartService cartService;		
		@Autowired
		private OrderService orderService;		
		@Autowired
		private OrderDetailService orderDetailService;		
		@Autowired
		private MemberService memberService;
		@Autowired
		private DeliveryService deliveryService;
		@Autowired
		private ProductService productService;
		
	  //자바빈(VO) 초기화
	  @ModelAttribute public AdminOrderVO initCommand() { 
		  return new AdminOrderVO(); 
	  }
	 
	  //주문페이지	호출 
	  @GetMapping("/shop/order") 
	  public ModelAndView insertOrderForm(HttpSession session) { 
		  
		  Integer mem_num = (Integer)session.getAttribute("mem_num");
		  List<ProductCartVO> list = cartService.selectCart(mem_num);
		  MemberVO member = memberService.selectMember(mem_num);
		  ModelAndView mav = new ModelAndView();
		  mav.setViewName("shopOrder");
		  mav.addObject("cartList",list);
		  mav.addObject("member",member);
		  return mav;
	  }
	  
	  //주문 insert
	  @PostMapping("/shop/order")
	  public String insertOrder(HttpSession session, OrderVO orderVO, OrderDetailVO orderDetailVO,DeliveryVO deliveryVO) {
		  
		  Integer mem_num = (Integer)session.getAttribute("mem_num");
		  
		  //주문번호 세팅 (yyyymmdd_랜덤6자리)
		  Calendar cal = Calendar.getInstance();
		  int year = cal.get(Calendar.YEAR);
		  String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		  String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
		  String subNum = "";
			 
		  for(int i = 1; i <= 6; i ++) {
			  subNum += (int)(Math.random() * 10);
		  }
			 
		  String orderNo = ymd + "_" + subNum;

		  orderVO.setOrder_no(orderNo); //주문번호
		  orderVO.setMem_num(mem_num); //회원번호
		  
		  orderDetailVO.setOrder_no(orderNo);
		  orderDetailVO.setMem_num(mem_num);
		  
		  deliveryVO.setOrder_no(orderNo);	  
	
		  orderService.insertOrder(orderVO); 					//주문테이블 insert		  
		  orderDetailService.insertOrderDetail(orderDetailVO); 	//주문상세테이블 insert
		  deliveryService.insertOrderDelivery(orderNo);			//배송테이블 insert
		  
		  //주문 완료 후, 카트에 담긴 상품을 삭제하기위해 orderDetail목록 가져옴
		  List<OrderDetailVO> orderList = orderDetailService.selectOrderDetail(orderNo);
		  
		  //상품 수량 변경
		  for(OrderDetailVO vo : orderList) {
			  productService.productAmountUpdate(vo.getOrder_d_amount(), vo.getP_no());
		  }
		  
		  //장바구니 목록 삭제
		  cartService.deleteAllCart(mem_num);		
		  
		  return "redirect:/shop/orderResult.do";
	  }
	 
	  
	  //주문완료페이지
	  @RequestMapping("/shop/orderResult")
	  public String resultOrder() {
		  return "orderResult";
	  }
	  
	  //주문내역페이지
	  @RequestMapping("/shop/orderList") 
	  public ModelAndView listOrder(HttpSession session) {
		  Integer mem_num = (Integer)session.getAttribute("mem_num");
		  List<OrderAllVO> list = orderService.selectAllOrder(mem_num);
		 
		  ModelAndView mav = new ModelAndView();
		  mav.setViewName("orderList");
		  mav.addObject("list",list);
		  
		  return mav; 
	  }
		 
	  
	  //주문취소페이지
	  @RequestMapping("/shop/orderCancel")
	  public ModelAndView cancelOrder(HttpSession session) {
		  Integer mem_num = (Integer)session.getAttribute("mem_num");
		  List<OrderAllVO> list = orderService.selectCancelOrder(mem_num);
		 
		  ModelAndView mav = new ModelAndView();
		  mav.setViewName("orderCancel");
		  mav.addObject("list",list);
		  return mav;
	  }
	  
	  //주문교환페이지
	  @RequestMapping("/shop/orderExchange")
	  public ModelAndView exchangeOrder(HttpSession session) {
		  Integer mem_num = (Integer)session.getAttribute("mem_num");
		  List<OrderAllVO> list = orderService.selectComplateOrder(mem_num);
		 
		  ModelAndView mav = new ModelAndView();
		  mav.setViewName("orderExchange");
		  mav.addObject("list",list);
		  
		  return mav;
	  }
	  
	  //주문반품페이지
	  @RequestMapping("/shop/orderRefund")
	  public ModelAndView refundOrder(HttpSession session) {
		  Integer mem_num = (Integer)session.getAttribute("mem_num");
		  List<OrderAllVO> list = orderService.selectComplateOrder(mem_num);
		 
		  ModelAndView mav = new ModelAndView();
		  mav.setViewName("refundExchange");
		  mav.addObject("list",list);
		  return mav;
	  }
	  
	  //주문상세내역페이지
	  @RequestMapping("/shop/orderDetail")
	  public String detailOrder() {
		  return "orderDetail";
	  }
	  
	  
	 
	 
}
