package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cart.dao.CartMapper;
import kr.spring.delivery.dao.DeliveryMapper;
import kr.spring.order.dao.OrderMapper;
import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderListVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.orderDetail.dao.OrderDetailMapper;
import kr.spring.orderDetail.vo.OrderDetailVO;
import kr.spring.product.dao.ProductMapper;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderDetailMapper orderDetailMapper;
	@Autowired
	DeliveryMapper deliveryMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	CartMapper cartMapper;

	////장바구니에서 주문
	@Override
	public void insertCartOrder(OrderVO orderVO,OrderDetailVO orderDetailVO,String orderNo) {
		//주문테이블, 주문상세테이블, 배송테이블 insert
		orderMapper.insertOrder(orderVO);
		orderDetailMapper.insertOrderDetail(orderDetailVO);
		deliveryMapper.insertOrderDelivery(orderNo);
		
		//주문 완료 후, 카트에 담긴 상품을 삭제하기위해 orderDetail목록 가져옴
		List<OrderDetailVO> orderList = orderDetailMapper.selectOrderDetail(orderNo);
		
		//상품 수량 변경
		for(OrderDetailVO vo : orderList) {
			  productMapper.productAmountUpdate(vo);
		  }
		
		//장바구니 목록 삭제
		cartMapper.deleteAllCart(orderVO.getMem_num());		
	}
	
	//상품상세페이지에서 주문
	@Override
	public void insertDirectOrder(OrderVO orderVO, OrderDetailVO orderDetailVO) {
		//주문테이블, 주문상세테이블, 배송테이블 insert
		orderMapper.insertOrder(orderVO);
		orderDetailMapper.insertDirectOrderDetail(orderDetailVO);
		deliveryMapper.insertOrderDelivery(orderVO.getOrder_no());
		
		//상품 수량 update
		productMapper.productAmountUpdate(orderDetailVO);
	}

	@Override
	public List<OrderAllVO> countOrderProduct(int mem_num) {
		return orderMapper.countOrderProduct(mem_num);
	}

	@Override
	public List<OrderListVO> selectRefundOrder(Map<String,Object> map) {
		return orderMapper.selectRefundOrder(map);
	}

	@Override
	public List<OrderListVO> selectConfirmOrder(Map<String,Object> map) {
		return orderMapper.selectConfirmOrder(map);
	}

	@Override
	public List<OrderListVO> selectExchageOrder(Map<String,Object> map) {
		return orderMapper.selectExchageOrder(map);
	}

	@Override
	public List<OrderAllVO> selectOrderDetailProduct(String orderno) {
		return orderMapper.selectOrderDetailProduct(orderno);
	}

	@Override
	public OrderAllVO selectOrderDetailInfo(String orderno) {
		return orderMapper.selectOrderDetailInfo(orderno);
	}

	@Override
	public List<OrderListVO> selectCancelOrder(Map<String,Object> map) {
		return orderMapper.selectCancelOrder(map);
	}

	@Override
	public int selectCancelOrderCount(int mem_num) {
		return orderMapper.selectCancelOrderCount(mem_num);
	}

	@Override
	public List<OrderListVO> selectAllOrder(Map<String, Object> map) {
		return orderMapper.selectAllOrder(map);
	}

	@Override
	public int selectAllOrderCount(int mem_num) {
		return orderMapper.selectAllOrderCount(mem_num);
	}

	@Override
	public int selectRefundOrderCount(int mem_num) {
		return orderMapper.selectRefundOrderCount(mem_num);
	}

	@Override
	public int selectExchageOrderCount(int mem_num) {
		return orderMapper.selectExchageOrderCount(mem_num);
	}

	@Override
	public int selectConfirmOrderCount(int mem_num) {
		return orderMapper.selectConfirmOrderCount(mem_num);
	}

}
