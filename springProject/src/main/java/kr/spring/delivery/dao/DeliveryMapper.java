package kr.spring.delivery.dao;

import org.apache.ibatis.annotations.Insert;

import kr.spring.delivery.vo.DeliveryVO;

public interface DeliveryMapper {
	@Insert("insert into delivery(delivery_no,order_no) "
			+ "VALUES(delivery_no_seq.nextval,#{order_no})")
	public void insertOrderDelivery(String order_no);
}
