package kr.spring.orderDetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.orderDetail.dao.OrderDetailMapper;
import kr.spring.orderDetail.vo.OrderDetailVO;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	OrderDetailMapper orderDetailMapper;

	@Override
	public List<OrderDetailVO> selectOrderDetail(String order_no) {
		return orderDetailMapper.selectOrderDetail(order_no);
	}
}
