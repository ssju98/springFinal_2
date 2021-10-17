package kr.spring.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.delivery.dao.DeliveryMapper;
import kr.spring.delivery.vo.DeliveryVO;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	DeliveryMapper deliveryMapper;

	@Override
	public void insertOrderDelivery(String order_no) {
		deliveryMapper.insertOrderDelivery(order_no);
		
	}

}
