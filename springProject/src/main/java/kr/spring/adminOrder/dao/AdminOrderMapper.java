package kr.spring.adminOrder.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import kr.spring.adminOrder.vo.AdminOrderVO;

public interface AdminOrderMapper {

	//============== 주문관리 ==============
	@Select("SELECT COUNT(*) FROM sorder")
	public int getOrderCount();
	
	public List<AdminOrderVO> getOrderList(Map<String,Object> map);
	
	public AdminOrderVO selectOrder(int order_no);
	
	@Delete("DELETE FROM sorder_detail WHERE order_no=#{order_no}")
	public void deleteOrderDetail(int order_no);
	@Delete("DELETE FROM delivery WHERE order_no=#{order_no}")
	public void deleteOrderDelivery(int order_no);
	@Delete("DELETE FROM sorder WHERE order_no=#{order_no}")
	public void deleteOrder(int order_no);
	
}
