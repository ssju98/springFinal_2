package kr.spring.category_sub.service;

import kr.spring.category_sub.vo.Category_subVO;

public interface Category_subService {
	public void insertCategory_sub(Category_subVO category_sub);
	public Category_subVO selectCategory_sub(Integer c_sub_no);
	public void updateCategory_sub(Category_subVO category_sub);
	public void deleteCategory_sub(Category_subVO category_sub);
}
