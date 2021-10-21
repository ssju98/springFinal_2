package kr.spring.review.service;

import java.util.List;

import kr.spring.order.vo.OrderListVO;

public interface ReviewService {
	public List<OrderListVO> selectReviewAvaliavle(int mem_num);
	public List<OrderListVO> selectReviewWritten(int mem_num);
}
