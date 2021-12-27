package kr.spring.cart.service;

import java.util.List;

import kr.spring.cart.vo.CartVO;
import kr.spring.cart.vo.ProductCartVO;

public interface CartService {

	public void insertCart(CartVO cart);
	public List<ProductCartVO> selectCart(int mem_num);
	public void deleteCart(int p_no);
	public int selectDuplicationCart(CartVO cart);
	public int selectCartProduct(CartVO cart);
	public void updateCart(CartVO cart);
	public int countCartAll(int mem_num);
}