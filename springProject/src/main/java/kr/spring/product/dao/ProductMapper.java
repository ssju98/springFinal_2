package kr.spring.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.product.vo.ProductVO;

public interface ProductMapper {
	@Select("SELECT * FROM product")
	public List<ProductVO> ProductSelectAll();
	@Select("Select * FROM product WHERE p_no=#{p_no}")
	public ProductVO ProductSelect(int p_no);
	@Select("select * from product where c_top_no = #{param1} AND c_sub_no = #{param2}")
	public List<ProductVO> ProductCategorySelectAll(int param1, int param2);
	@Select("select count(*) from product where c_top_no = #{param1} AND c_sub_no = #{param2}")
	public int ProductCategorySelectCount(int param1, int param2);
	@Update("update product set p_amount=p_amount-#{param1} where p_no=#{param2}")
	public void productAmountUpdate(int param1, int param2);

}
