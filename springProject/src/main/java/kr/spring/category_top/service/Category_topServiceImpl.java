package kr.spring.category_top.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.category_top.dao.Category_topMapper;
import kr.spring.category_top.vo.Category_topVO;

@Service
@Transactional
public class Category_topServiceImpl implements Category_topService{

	@Autowired
	private Category_topMapper category_topMapper;
	
	@Override
	public void insertCategory_top(Category_topVO category_top) {
		//상위 카테고리번호 셋팅
		category_top.setC_top_no(category_topMapper.selectC_top_no());
		category_topMapper.insertCategory_top(category_top);
	}

	@Override
	public Category_topVO selectCategory_top(Integer c_top_no) {
		return category_topMapper.selectCategory_top(c_top_no);
	}
	
	@Override
	public void updateCategory_top(Category_topVO category_top) {
		
	}
	
	@Override
	public void deleteCategory_top(Category_topVO category_top) {
		
	}
	
}
