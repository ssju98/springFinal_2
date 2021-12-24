package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.order.vo.OrderListVO;
import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.vo.ReviewListVO;
import kr.spring.review.vo.ReviewVO;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewMapper reviewMapper;

	@Override
	public List<OrderListVO> selectReviewAvaliable(Map<String, Object> map) {
		return reviewMapper.selectReviewAvaliable(map);
	}

	@Override
	public List<ReviewListVO> selectReviewWritten(Map<String, Object> map) {
		return reviewMapper.selectReviewWritten(map);
	}
	
	@Override
	public int selectReviewAvaliableCount(int mem_num) {
		return reviewMapper.selectReviewAvaliableCount(mem_num);
	}
	
	@Override
	public int selectReviewWrittenCount(int mem_num) {
		return reviewMapper.selectReviewWrittenCount(mem_num);
	}

	@Override
	public void insertReview(ReviewVO review) {
		reviewMapper.insertReview(review);
		
	}

	@Override
	public ReviewVO selectReview(int review_no) {
		return reviewMapper.selectReview(review_no);
	}

	@Override
	public void deleteReview(int review_no) {
		reviewMapper.deleteReview(review_no);
	}

	@Override
	public void updateReview(ReviewVO reviewVO) {
		reviewMapper.updateReview(reviewVO);
		
	}

	@Override
	public List<ReviewVO> selectProductReview(int p_no) {
		return reviewMapper.selectProductReview(p_no);
	}

	@Override
	public int selectProductReviewCount(int p_no) {
		return reviewMapper.selectProductReviewCount(p_no);
	}
}
