package kr.spring.adminOrder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import kr.spring.adminOrder.vo.AdminOrderVO;

public interface AdminOrderMapper {

	//============== 주문관리 ==============
	public int getOrderCount(Map<String,Object> map);
	
	public List<AdminOrderVO> getOrderList(Map<String,Object> map);
	
	public AdminOrderVO selectOrder(String order_no);
	
	@Delete("DELETE FROM sorder_detail WHERE order_no=#{order_no}")
	public void deleteOrderDetail(String order_no);
	@Delete("DELETE FROM delivery WHERE order_no=#{order_no}")
	public void deleteOrderDelivery(String order_no);
	@Delete("DELETE FROM sorder WHERE order_no=#{order_no}")
	public void deleteOrder(String order_no);
}