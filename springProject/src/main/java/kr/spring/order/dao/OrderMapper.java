package kr.spring.order.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.orderDetail.vo.OrderDetailVO;

public interface OrderMapper {
	
	  @Insert("Insert into sorder(order_no,mem_num,order_zipcode,order_address1,order_address2,order_name, order_phone,order_method, order_pay, delivery_pay) "
	  		+ "VALUES(#{order_no},#{mem_num}, #{order_zipcode}, #{order_address1}, #{order_address2},#{order_name},#{order_phone}, #{order_method},#{order_pay},#{delivery_pay})")
	  public void insertOrder(OrderVO order);
	 
	  @Select("select * from sorder s, delivery d, delivery_status e "
	  		+ "where s.order_no=d.order_no and d.d_status_num=e.d_status_num "
	  		+ "and s.mem_num = #{mem_num} ORDER BY s.order_date desc")
	  public List<OrderAllVO> selectAllOrder(int mem_num);
	  
	  @Select("select * from sorder s, delivery d, delivery_status e "
	  		+ "where s.order_no=d.order_no and d.d_status_num=e.d_status_num "
	  		+ "and s.mem_num = #{mem_num} and d.d_status_num IN(0,7) ORDER BY s.order_date desc")
	  public List<OrderAllVO> selectCancelOrder(int mem_num);
	  
	  @Select("select * from sorder s, delivery d, delivery_status e "
		  		+ "where s.order_no=d.order_no and d.d_status_num=e.d_status_num "
		  		+ "and s.mem_num = #{mem_num} and d.d_status_num IN(3,5) ORDER BY s.order_date desc")
	  public List<OrderAllVO> selectRefundOrder(int mem_num);
	  
	  @Select("select * from sorder s, delivery d, delivery_status e "
		  		+ "where s.order_no=d.order_no and d.d_status_num=e.d_status_num "
		  		+ "and s.mem_num = #{mem_num} and d.d_status_num IN(3,6) ORDER BY s.order_date desc")
	  public List<OrderAllVO> selectExchageOrder(int mem_num);
	  
	  @Select("select * from sorder s, delivery d, delivery_status e "
		  		+ "where s.order_no=d.order_no and d.d_status_num=e.d_status_num "
		  		+ "and s.mem_num = #{mem_num} and d.d_status_num IN(3,4) ORDER BY s.order_date desc")
	  public List<OrderAllVO> selectConfirmOrder(int mem_num);
	  
	  @Select("select count(*) FROM sorder s, sorder_detail d WHERE s.order_no=d.order_no and s.mem_num=#{mem_num} GROUP BY s.order_no,s.order_date ORDER BY s.order_date desc")
	  public List<OrderAllVO> countOrderProduct(int mem_num);
}
