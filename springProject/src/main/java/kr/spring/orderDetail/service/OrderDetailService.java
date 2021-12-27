package kr.spring.orderDetail.service;

import java.util.List;

import kr.spring.orderDetail.vo.OrderDetailVO;

public interface OrderDetailService {
	 public List<OrderDetailVO> selectOrderDetail(String order_no);
}