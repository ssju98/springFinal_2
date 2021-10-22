package kr.spring.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.product.vo.ProductVO;

public interface ProductMapper {
	@Select("SELECT * FROM product")
	public List<ProductVO> ProductSelectAll();
	@Select("SELECT COUNT(*) FROM product")
	public int selectRowCount(Map<String, Object> map);
	@Select("Select * FROM product WHERE p_no=#{p_no}")
	public ProductVO ProductSelect(int p_no);
	@Select("select * from product where c_top_no = #{param1} AND c_sub_no = #{param2} order by decode(p_amount,0,1)DESC")
	public List<ProductVO> ProductCategorySelectAll(int param1, int param2);
	@Select("select count(*) from product where c_top_no = #{param1} AND c_sub_no = #{param2}")
	public int ProductCategorySelectCount(int param1, int param2);
	@Update("update product set p_amount=p_amount-#{param1} where p_no=#{param2}")
	public void productAmountUpdate(int param1, int param2);
	@Update("update product set p_amount=p_amount+#{param1} where p_no=#{param2}")
	public void productAmountPlusUpdate(int param1, int param2);
	@Delete("DELETE FROM product WHERE p_no=#{p_no}")
	public void deleteProduct(int p_no);
	@Insert("INSERT INTO product (p_no, p_name, p_price, p_amount, p_image_name, p_image, p_discount, p_sub_text, c_top_no, c_sub_no) "
			+ "VALUES (p_no_seq.nextval, #{p_name}, #{p_price}, #{p_amount}, #{p_image_name}, #{p_image}, #{p_discount}, #{p_sub_text}, #{c_top_no}, #{c_sub_no})")
	public void insertProduct(ProductVO product);
	public void updateProduct(ProductVO productVO);
	@Select("select count(*) from product where p_no = #{p_no}")
	public int countProduct(int p_no);
}
