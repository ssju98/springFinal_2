package kr.spring.category_top.service;

import kr.spring.category_top.vo.Category_topVO;

public interface Category_topService {
	public void insertCategory_top(Category_topVO category_top);
	public Category_topVO selectCategory_top(int c_top_no);
	public void updateCategory_top(Category_topVO category_top);
	public void deleteCategory_top(Category_topVO category_top);
}
