package kr.spring.order.service;

import java.util.List;
import java.util.Map;

import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderListVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.orderDetail.vo.OrderDetailVO;

public interface OrderService {
	public void insertCartOrder(OrderVO orderVO, OrderDetailVO orderDetailVO, String order_no); 
	public void insertDirectOrder(OrderVO orderVO, OrderDetailVO orderDetailVO);
	 
	public List<OrderAllVO> countOrderProduct(int mem_num);
	public List<OrderAllVO> selectOrderDetailProduct(String order_no);
	public OrderAllVO selectOrderDetailInfo(String order_no);
	  
	public List<OrderListVO> selectAllOrder(Map<String,Object> map);
	public List<OrderListVO> selectCancelOrder(Map<String,Object> map);
	public List<OrderListVO> selectRefundOrder(Map<String,Object> map);
	public List<OrderListVO> selectExchageOrder(Map<String,Object> map);
	public List<OrderListVO> selectConfirmOrder(Map<String,Object> map);
	  
	public int selectCancelOrderCount(int mem_num);
	public int selectAllOrderCount(int mem_num);
	public int selectRefundOrderCount(int mem_num);
	public int selectExchageOrderCount(int mem_num);
	public int selectConfirmOrderCount(int mem_num);
}
