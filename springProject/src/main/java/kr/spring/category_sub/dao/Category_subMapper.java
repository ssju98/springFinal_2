package kr.spring.category_sub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.category_sub.vo.Category_subVO;

public interface Category_subMapper {
	@Select("SELECT category_sub_seq.nextval FROM dual")
	public int selectC_sub_no();
	@Insert("INSERT INTO category_sub (c_sub_no, c_sub_name, c_top_no) VALUES (#{c_sub_no}, #{c_top_name}, #{c_top_no})")
	public void insertCategory_sub(Category_subVO category_sub);
	
	@Select("SELECT * FROM category_sub WHERE c_sub_no=#{c_sub_no}")
	public Category_subVO selectCategory_sub(Integer c_sub_no);
	
	@Update("UPDATE category_sub SET c_sub_name=#{c_sub_name} WHERE c_sub_no=#{c_sub_no}")
	public void updateCategory_sub(Category_subVO category_sub);
	@Delete("DELETE FROM category_sub WHERE c_sub_no=#{c_sub_no}")
	public void deleteCategory_sub(Integer c_sub_no);
	
	@Select("SELECT * FROM category_sub ORDER BY c_sub_no")
	public List<Category_subVO> category_subSelectAll();
}
