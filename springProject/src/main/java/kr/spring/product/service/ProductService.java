package kr.spring.product.service;

import java.util.List;
import java.util.Map;

import kr.spring.category_sub.vo.Category_subVO;
import kr.spring.orderDetail.vo.OrderDetailVO;
import kr.spring.product.vo.ProductVO;

public interface ProductService {
	public List<ProductVO> selectProductAll(Map<String,Object>map);
	public ProductVO productSelect(int p_no);
	public List<ProductVO> productCategorySelectAll(Category_subVO category_subVO);
	public int productCategorySelectCount(Category_subVO category_subVO);
	public void productAmountPlusUpdate(int p_amount, int p_no);
	
	public int selectRowCount();
	public void deleteProduct(int p_no);
	public void insertProduct(ProductVO productVO);
	public void updateProduct(ProductVO productVO);
	public int countProduct(int p_no);
	public ProductVO selectProduct(int p_no);
	public List<ProductVO> selectAllProduct();
	
	public List<ProductVO> selectSearchProduct(Map<String, Object> map);
	public int selectCountSearchProduct(Map<String, Object> map);
	
	public List<ProductVO> selectPriceHigh(Category_subVO category_subVO);
	public  List<ProductVO> selectPriceRow(Category_subVO category_subVO);
	public  List<ProductVO> selectPriceBest(Category_subVO category_subVO);
	
	public List<ProductVO> selectSearchPriceHigh(Map<String, Object> map);
	public List<ProductVO> selectSearchPriceRow(Map<String, Object> map);
	public List<ProductVO> selectSearchPriceBest(Map<String, Object> map);
}
