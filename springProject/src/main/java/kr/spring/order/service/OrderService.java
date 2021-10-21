package kr.spring.order.service;

import java.util.List;


import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderListVO;
import kr.spring.order.vo.OrderVO;

public interface OrderService {
	 public void insertOrder(OrderVO order);
	  public List<OrderListVO> selectAllOrder(int mem_num);
	  public List<OrderAllVO> countOrderProduct(int mem_num);
	  public List<OrderListVO> selectCancelOrder(int mem_num);
	  public List<OrderListVO> selectRefundOrder(int mem_num);
	  public List<OrderListVO> selectExchageOrder(int mem_num);
	  public List<OrderListVO> selectConfirmOrder(int mem_num);
	  public List<OrderAllVO> selectOrderDetailProduct(String order_no);
	  public OrderAllVO selectOrderDetailInfo(String order_no);
}
