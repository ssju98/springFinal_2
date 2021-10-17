package kr.spring.order.service;

import java.util.List;

import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderVO;

public interface OrderService {
	 public void insertOrder(OrderVO order);
	  public List<OrderAllVO> selectAllOrder(int mem_num);
	  public List<OrderAllVO> countOrderProduct(int mem_num);
	  public List<OrderAllVO> selectCancelOrder(int mem_num);
	  public List<OrderAllVO> selectComplateOrder(int mem_num);
}