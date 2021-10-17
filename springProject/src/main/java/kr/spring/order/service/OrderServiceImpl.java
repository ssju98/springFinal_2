package kr.spring.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.adminOrder.vo.AdminOrderVO;
import kr.spring.order.dao.OrderMapper;
import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderVO;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderMapper orderMapper;

	@Override
	public void insertOrder(OrderVO order) {
		orderMapper.insertOrder(order);
	}

	@Override
	public List<OrderAllVO> selectAllOrder(int mem_num) {
		return orderMapper.selectAllOrder(mem_num);
	}

	@Override
	public List<OrderAllVO> countOrderProduct(int mem_num) {
		return orderMapper.countOrderProduct(mem_num);
	}

	@Override
	public List<OrderAllVO> selectCancelOrder(int mem_num) {
		return orderMapper.selectCancelOrder(mem_num);
	}

	@Override
	public List<OrderAllVO> selectComplateOrder(int mem_num) {
		return orderMapper.selectComplateOrder(mem_num);
	}

	
}