package kr.spring.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.cart.vo.CartVO;
import kr.spring.cart.vo.ProductCartVO;

public interface CartMapper {
	//카트 상품 등록
	@Insert ("INSERT INTO cart (cart_no,mem_num,p_no,cart_amount) "
			+ "VALUES(cart_seq.nextval, #{mem_num},#{p_no},#{cart_amount})" )
	public void insertCart(CartVO cart);
	
	//카트에 담긴 상품 목록
	@Select("SELECT * FROM CART c, Product p WHERE c.p_no=p.p_no AND mem_num = #{mem_num} ORDER BY cart_no DESC")
	public List<ProductCartVO> selectCart(int mem_num);
	
	//카트 상품 삭제
	@Delete("DELETE FROM CART WHERE p_no=#{p_no}")
	public void deleteCart(int p_no);
	
	//상품이 카트에 이미 담겨있는지 확인하는 sql문
	@Select("SELECT count(*) FROM CART WHERE p_no=#{p_no} AND mem_num=#{mem_num}")
	public int selectDuplicationCart(CartVO cart);
	
	//카트에 담겨있는 상품의 수량 확인하는 sql문
	@Select("SELECT cart_amount FROM CART WHERE p_no=#{p_no} AND mem_num=#{mem_num}")
	public int selectCartProduct(CartVO cart);
	
	//카트에 담겨있는 상품의 수량 변경
	@Update("UPDATE cart SET cart_amount = #{cart_amount} WHERE p_no=#{p_no} AND mem_num=#{mem_num}")
	public void updateCart(CartVO cart);
	
	//카트에 담긴 상품 모두 삭제
	@Delete("DELETE FROM CART WHERE mem_num = #{mem_num}")
	public void deleteAllCart(int mem_num);
	
	//카트에 담긴 상품 갯수
	@Select("SELECT count(*) FROM CART where mem_num=#{mem_num}")
	public int countCartAll(int mem_num);
	
}
