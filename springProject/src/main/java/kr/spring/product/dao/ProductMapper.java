package kr.spring.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.category_sub.vo.Category_subVO;
import kr.spring.orderDetail.vo.OrderDetailVO;
import kr.spring.product.vo.ProductVO;

public interface ProductMapper {

	//해당 카테고리의 상품 갯수
	@Select("select count(*) from product where c_top_no = #{c_top_no} AND c_sub_no = #{c_sub_no}")
	public int productCategorySelectCount(Category_subVO category_subVO);
	
	//해당 카테고리 정렬 : 기본순
	public List<ProductVO> productCategorySelectAll(Category_subVO category_subVO);
	
	//해당 카테고리 정렬 : 가격 높은 순
	public List<ProductVO> selectPriceHigh(Category_subVO category_subVO);
	
	//해당 카테고리 정렬 : 가격 낮은 순
	public  List<ProductVO> selectPriceRow(Category_subVO category_subVO);
	
	////해당 카테고리 정렬 : 판매순
	public  List<ProductVO> selectPriceBest(Category_subVO category_subVO);
	
	//상품 검색 목록 갯수
	public int selectCountSearchProduct(Map<String, Object> map);
	
	//상품 검색 정렬 : 기본순
	public List<ProductVO> selectSearchProduct(Map<String, Object> map);
	
	//상품 검색 정렬 : 높은 가격순
	public List<ProductVO> selectSearchPriceHigh(Map<String, Object> map);
	
	//상품 검색 정렬 : 낮은 가격순
	public List<ProductVO> selectSearchPriceRow(Map<String, Object> map);
	
	//상품 검색 정렬 : 판매순
	public List<ProductVO> selectSearchPriceBest(Map<String, Object> map);
	
	//상품 존재 여부 확인
	@Select("select count(*) from product where p_no = #{p_no}")
	public int countProduct(int p_no);
	
	//총 상품 갯수
	@Select("SELECT COUNT(*) FROM product")
	public int selectRowCount();
	
	//특정 상품 조회
	@Select("Select * FROM product WHERE p_no=#{p_no}")
	public ProductVO productSelect(int p_no);
	
	//상품 재고 수정
	@Update("update product set p_amount=p_amount-#{order_d_amount} where p_no=#{p_no}")
	public void productAmountUpdate(OrderDetailVO orderDetailVO);
		
	//상품 재고 추가(반품 시)
	@Update("update product set p_amount=p_amount+#{param1} where p_no=#{param2}")
	public void productAmountPlusUpdate(int param1, int param2);
	
	//특정 상품 조회 + 리뷰 평점, 리뷰 갯수
	public ProductVO selectProduct(int p_no);
	
	//모든 상품 조회 + 리뷰 평점, 리뷰 갯수
	public List<ProductVO> selectAllProduct();

	
	
	
	//--------------------관리자 관리 : 상품 관리--------------------------------------
	public List<ProductVO> selectProductAll(Map<String,Object>map);
	
	@Delete("DELETE FROM product WHERE p_no=#{p_no}")
	public void deleteProduct(int p_no);
	
	@Insert("INSERT INTO product (p_no, p_name, p_price, p_amount, p_image_name, p_image, p_discount, p_sub_text, c_top_no, c_sub_no) "
			+ "VALUES (product_seq.nextval, #{p_name}, #{p_price}, #{p_amount}, #{p_image_name}, #{p_image}, #{p_discount}, #{p_sub_text}, #{c_top_no}, #{c_sub_no})")
	public void insertProduct(ProductVO product);
	
	public void updateProduct(ProductVO productVO);
	


	
}

