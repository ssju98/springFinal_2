package kr.spring.category_top.service;

import java.util.List;

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
	public List selectCategory_top(Category_topVO category_top) {
		return category_topMapper.selectCategory_top(category_top);
	}
	
	@Override
	public void updateCategory_top(Category_topVO category_top) {
		
	}
	
	@Override
	public void deleteCategory_top(Category_topVO category_top) {
		
	}

	@Override
	public Category_topVO selectCategory_top(int c_top_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List listCategory_top() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
