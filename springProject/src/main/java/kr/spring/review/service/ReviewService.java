package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.order.vo.OrderListVO;
import kr.spring.review.vo.ReviewListVO;
import kr.spring.review.vo.ReviewVO;

public interface ReviewService {
	public List<OrderListVO> selectReviewAvaliable(Map<String, Object> map);
	public List<ReviewListVO> selectReviewWritten(Map<String, Object> map);
	
	public int selectReviewAvaliableCount(int mem_num);
	public int selectReviewWrittenCount(int mem_num);
	
	public void insertReview(ReviewVO review);
	public ReviewVO selectReview(int review_no);
	public void deleteReview(int review_no);
	public void updateReview(ReviewVO reviewVO);
	public List<ReviewVO> selectProductReview(int p_no);
	public int selectProductReviewCount(int p_no);
}
