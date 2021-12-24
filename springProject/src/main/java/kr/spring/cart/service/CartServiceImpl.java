package kr.spring.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.cart.dao.CartMapper;
import kr.spring.cart.vo.CartVO;
import kr.spring.cart.vo.ProductCartVO;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartMapper cartMapper;

	@Override
	public void insertCart(CartVO cart) {
		cartMapper.insertCart(cart);
	}

	@Override
	public List<ProductCartVO> selectCart(int mem_num) {
		return cartMapper.selectCart(mem_num);
	}

	@Override
	public void deleteCart(int p_no) {
		cartMapper.deleteCart(p_no);
		
	}

	@Override
	public int selectDuplicationCart(CartVO cart) {
		return cartMapper.selectDuplicationCart(cart);
	}

	@Override
	public int selectCartProduct(CartVO cart) {
		return cartMapper.selectCartProduct(cart);
	}

	@Override
	public void updateCart(CartVO cart) {
		cartMapper.updateCart(cart);
		
	}

	@Override
	public int countCartAll(int mem_num) {
		return cartMapper.countCartAll(mem_num);
	}
}
