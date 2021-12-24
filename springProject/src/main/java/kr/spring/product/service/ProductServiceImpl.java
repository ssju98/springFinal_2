package kr.spring.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.category_sub.vo.Category_subVO;
import kr.spring.orderDetail.vo.OrderDetailVO;
import kr.spring.product.dao.ProductMapper;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.dao.ReviewMapper;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public ProductVO productSelect(int p_no) {
		return productMapper.productSelect(p_no);
		
	}

	@Override
	public List<ProductVO> productCategorySelectAll(Category_subVO category_subVO) {
		return productMapper.productCategorySelectAll(category_subVO);
	}

	@Override
	public int productCategorySelectCount(Category_subVO category_subVO) {
		return productMapper.productCategorySelectCount(category_subVO);
	}

	@Override
	public void productAmountPlusUpdate(int p_amount, int p_no) {
		productMapper.productAmountPlusUpdate(p_amount, p_no);
		
	}

	@Override
	public int selectRowCount() {
		return productMapper.selectRowCount();
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

	@Override
	public List<ProductVO> selectSearchProduct(Map<String, Object> map) {
		return productMapper.selectSearchProduct(map);
	}

	@Override
	public int selectCountSearchProduct(Map<String, Object> map) {
		return productMapper.selectCountSearchProduct(map);
	}

	@Override
	public List<ProductVO> selectPriceHigh(Category_subVO category_subVO) {
		return productMapper.selectPriceHigh(category_subVO);
	}

	@Override
	public List<ProductVO> selectPriceRow(Category_subVO category_subVO) {
		return productMapper.selectPriceRow(category_subVO);
	}

	@Override
	public List<ProductVO> selectPriceBest(Category_subVO category_subVO) {
		return productMapper.selectPriceBest(category_subVO);
	}

	@Override
	public List<ProductVO> selectProductAll(Map<String, Object> map) {
		return productMapper.selectProductAll(map);
	}

	@Override
	public List<ProductVO> selectSearchPriceHigh(Map<String, Object> map) {
		return productMapper.selectSearchPriceHigh(map);
	}

	@Override
	public List<ProductVO> selectSearchPriceRow(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productMapper.selectSearchPriceRow(map);
	}

	@Override
	public List<ProductVO> selectSearchPriceBest(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productMapper.selectSearchPriceBest(map);
	}

	@Override
	public ProductVO selectProduct(int p_no) {
		return productMapper.selectProduct(p_no);
	}

	@Override
	public List<ProductVO> selectAllProduct() {
		return productMapper.selectAllProduct();
	}
}
