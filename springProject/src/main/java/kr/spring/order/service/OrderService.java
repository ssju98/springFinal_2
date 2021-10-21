package kr.spring.order.service;

import java.util.List;


import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderListVO;
import kr.spring.order.vo.OrderVO;

public interface OrderService {
	 public void insertOrder(OrderVO order);
	  public List<OrderAllVO> selectAllOrder(int mem_num);
	  public List<OrderAllVO> countOrderProduct(int mem_num);
	  public List<OrderAllVO> selectCancelOrder(int mem_num);
	  public List<OrderAllVO> selectRefundOrder(int mem_num);
	  public List<OrderAllVO> selectExchageOrder(int mem_num);
	  public List<OrderAllVO> selectConfirmOrder(int mem_num);
	  public List<OrderAllVO> selectOrderDetailProduct(String order_no);
	  public OrderAllVO selectOrderDetailInfo(String order_no);
}
