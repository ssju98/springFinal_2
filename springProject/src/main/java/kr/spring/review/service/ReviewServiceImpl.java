package kr.spring.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.order.vo.OrderListVO;
import kr.spring.review.dao.ReviewMapper;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewMapper reviewMapper;

	@Override
	public List<OrderListVO> selectReviewAvaliavle(int mem_num) {
		return reviewMapper.selectReviewAvaliavle(mem_num);
	}

	@Override
	public List<OrderListVO> selectReviewWritten(int mem_num) {
		return reviewMapper.selectReviewWritten(mem_num);
	}

}
