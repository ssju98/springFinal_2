package kr.spring.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.order.vo.OrderListVO;
import kr.spring.review.vo.ReviewListVO;
import kr.spring.review.vo.ReviewVO;

public interface ReviewMapper {
	
	//작성 가능한 후기
	public List<OrderListVO> selectReviewAvaliable(Map<String, Object> map);
	
	//작성 가능한 후기 갯수
	public int selectReviewAvaliableCount(int mem_num);
	
	//이미 작성한 후기
	public List<ReviewListVO> selectReviewWritten(Map<String, Object> map);
	
	//이미 작성한 후기 갯수
	public int selectReviewWrittenCount(int mem_num);
	
	//리뷰 작성
	@Insert("INSERT INTO review (review_no,p_no,order_no,review_content,review_image,review_image_name,mem_num,review_rating) VALUES (review_seq.nextval,#{p_no},#{order_no},#{review_content},#{review_image, jdbcType=VARCHAR},#{review_image_name, jdbcType=VARCHAR},#{mem_num},#{review_rating})")
	public void insertReview(ReviewVO review);

	//특정 리뷰 출력
	@Select("select * from review r, product p where r.review_no=#{review_no} and p.p_no = r.p_no")
	public ReviewVO selectReview(int review_no);
	
	//리뷰 삭제
	@Delete("delete from review where review_no = #{review_no}")
	public void deleteReview(int review_no);
	
	//리뷰 수정
	public void updateReview(ReviewVO reviewVO);
	
	//특정 상품 전체 리뷰 정보
	public List<ReviewVO> selectProductReview(int p_no);
	
	//특정 상품 리뷰 갯수
	@Select("select count(*) from review where p_no=#{p_no}")
	public int selectProductReviewCount(int p_no);

}
