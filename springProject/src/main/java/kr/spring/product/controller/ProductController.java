package kr.spring.product.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.category_sub.service.Category_subService;
import kr.spring.category_sub.vo.Category_subVO;
import kr.spring.category_top.service.Category_topService;
import kr.spring.category_top.vo.Category_topVO;
import kr.spring.member.controller.MemberController;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private ProductService productService;
	
	@Autowired
	private Category_topService category_topService;
	
	@Autowired
	private Category_subService category_subService;

	// 상품 카테고리별 목록
	@RequestMapping("/shop/productList.do")
	public ModelAndView ShopProductList(@RequestParam(value="c_top_no") int c_top_no, 
										@RequestParam(value="c_sub_no") int c_sub_no) {

		List<ProductVO> list = null;
		int count = productService.ProductCategorySelectCount(c_top_no, c_sub_no);
		if(count > 0) {
			list = productService.ProductCategorySelectAll(c_top_no, c_sub_no);
		}
		
		List<Category_topVO> list_top = category_topService.category_topSelectAll();
		List<Category_subVO> list_sub = category_subService.category_subSelectAll();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopProductList");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("category_top",list_top);
		mav.addObject("category_sub",list_sub);

		return mav;
	}

	//상품 상세 목록
	@RequestMapping("/shop/productDetail.do")
	public ModelAndView productDetail(@RequestParam int p_no) {
		ProductVO product = productService.ProductSelect(p_no);
		System.out.println(product);
		return new ModelAndView("shopProductDetail","product",product);
	}
	
	//상품 이미지 출력
	@RequestMapping("/shop/photoView.do")
	public ModelAndView viewImage(@RequestParam int p_no) {
		
		logger.debug("<<상품 상세 이미지 출력>> : " + p_no);
		ProductVO product = productService.ProductSelect(p_no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",product.getP_image());
		mav.addObject("filename",product.getP_image_name());
		return mav;
	}
}
