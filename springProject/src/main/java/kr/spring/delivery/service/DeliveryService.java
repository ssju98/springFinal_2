package kr.spring.delivery.service;

import kr.spring.delivery.vo.DeliveryVO;

public interface DeliveryService {
	public void insertOrderDelivery(String order_no);
	public void updateOrderDeilveryCancel(String order_no);
	public void updateOrderDeilveryConfirm(String order_no);
	public void updateOrderDeilveryRefund(String order_no);
	public void updateOrderDeilveryExchange(String order_no);
	public DeliveryVO selectOrderDelivery(String order_no);
}
