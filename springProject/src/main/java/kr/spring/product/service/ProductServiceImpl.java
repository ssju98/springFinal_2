package kr.spring.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.product.dao.ProductMapper;
import kr.spring.product.vo.ProductVO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductVO> ProductSelectAll() {
		return productMapper.ProductSelectAll();
	}

	@Override
	public ProductVO ProductSelect(int p_no) {
		return productMapper.ProductSelect(p_no);
	}

	@Override
	public List<ProductVO> ProductCategorySelectAll(int c_top_no, int c_sub_no) {
		return productMapper.ProductCategorySelectAll(c_top_no, c_sub_no);
	}

	@Override
	public int ProductCategorySelectCount(int c_top_no, int c_sub_no) {
		return productMapper.ProductCategorySelectCount(c_top_no, c_sub_no);
	}

}
