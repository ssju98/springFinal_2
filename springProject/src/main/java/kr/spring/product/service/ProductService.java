package kr.spring.product.service;

import java.util.List;
import java.util.Map;

import kr.spring.product.vo.ProductVO;

public interface ProductService {
	public List<ProductVO> ProductSelectAll();
	public ProductVO ProductSelect(int p_no);
	public List<ProductVO> ProductCategorySelectAll(int c_top_no, int c_sub_no);
	public int ProductCategorySelectCount(int c_top_no, int c_sub_no);
	public void productAmountUpdate(int p_amount, int p_no);
	public void productAmountPlusUpdate(int p_amount, int p_no);
	
	public int selectRowCount(Map<String, Object> map);
	public List<ProductVO> selectList(Map<String, Object> map);
	public void deleteProduct(int p_no);
	public void insertProduct(ProductVO productVO);
	public void updateProduct(ProductVO productVO);
	public int countProduct(int p_no);
}
