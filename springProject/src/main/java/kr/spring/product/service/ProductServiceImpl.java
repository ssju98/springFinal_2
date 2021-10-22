package kr.spring.product.service;

import java.util.List;
import java.util.Map;

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

	@Override
	public void productAmountUpdate(int p_amount, int p_no) {
		productMapper.productAmountUpdate(p_amount, p_no);
		
	}

	@Override
	public void productAmountPlusUpdate(int p_amount, int p_no) {
		productMapper.productAmountPlusUpdate(p_amount, p_no);
		
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return productMapper.selectRowCount(map);
	}

	@Override
	public List<ProductVO> selectList(Map<String, Object> map) {
		return productMapper.ProductSelectAll();
	}

	@Override
	public void deleteProduct(int p_no) {
		productMapper.deleteProduct(p_no);
		
	}

	@Override
	public void insertProduct(ProductVO productVO) {
		productMapper.insertProduct(productVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) {
		productMapper.updateProduct(productVO);
		
	}
	@Override
	public int countProduct(int p_no) {
		return productMapper.countProduct(p_no);
	}
}
