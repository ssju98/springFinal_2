package kr.spring.product.controller;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.category_sub.service.Category_subService;
import kr.spring.category_sub.vo.Category_subVO;
import kr.spring.category_top.service.Category_topService;
import kr.spring.category_top.vo.Category_topVO;
import kr.spring.member.controller.MemberController;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.qna.service.QnaService;
import kr.spring.qna.vo.QnaVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.util.PagingUtil;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private int rowCount = 10;
	private int pageCount = 10;

	@Autowired
	private QnaService qnaService;
	@Autowired
	private ProductService productService;
	@Autowired
	private Category_topService category_topService;
	@Autowired
	private Category_subService category_subService;
	@Autowired
	private ReviewService reviewService;

	// ?????????(VO) ?????????
	@ModelAttribute
	public ProductVO initCommand() {
		return new ProductVO();
	}

	// ?????? ??????????????? ??????
	@RequestMapping("/shop/productList.do")
	public ModelAndView ShopProductList(@RequestParam(value = "c_top_no") int c_top_no,
			@RequestParam(value = "c_sub_no") int c_sub_no,
			@RequestParam(value = "orderby", defaultValue = "") String orderby) {

		Category_subVO category_subVO = new Category_subVO();
		category_subVO.setC_top_no(c_top_no);
		category_subVO.setC_sub_no(c_sub_no);

		List<ProductVO> list = null;

		int count = productService.productCategorySelectCount(category_subVO);
		if (count > 0) {
			if (orderby.equals("default") || orderby.equals("")) {
				list = productService.productCategorySelectAll(category_subVO);
			} else if (orderby.equals("best")) {
				list = productService.selectPriceBest(category_subVO);
			} else if (orderby.equals("high")) {
				list = productService.selectPriceHigh(category_subVO);
			} else if (orderby.equals("row")) {
				list = productService.selectPriceRow(category_subVO);
			}

		}

		Category_topVO category_top = category_topService.selectCategoryOne(c_top_no);
		Category_subVO category_sub = category_subService.selectCategoryOne(c_sub_no);
		List<Category_topVO> list_top = category_topService.selectCategory_top();
		List<Category_subVO> list_sub = category_subService.category_subSelectAll();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopProductList");
		mav.addObject("list", list);
		mav.addObject("count", count);
		mav.addObject("category_top", list_top);
		mav.addObject("category_sub", list_sub);
		mav.addObject("category_top_name", category_top);
		mav.addObject("category_sub_name", category_sub);

		mav.addObject("c_sub_no", c_sub_no);
		mav.addObject("c_top_no", c_top_no);
		mav.addObject("orderby", orderby);

		return mav;
	}

	// ?????? ?????? ??????
	@RequestMapping("/shop/productSearch.do")
	public ModelAndView productSearch(@RequestParam(value = "keyfield", defaultValue = "p_name") String keyfield,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "orderby", defaultValue = "") String orderby) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		List<ProductVO> list = null;
		int count = productService.selectCountSearchProduct(map);

		// ?????? ?????? ?????? ??????
		if (count > 0) {
			if (orderby.equals("default") || orderby.equals("")) {
				list = productService.selectSearchProduct(map);
			} else if (orderby.equals("best")) {
				list = productService.selectSearchPriceBest(map);
			} else if (orderby.equals("high")) {
				list = productService.selectSearchPriceHigh(map);
			} else if (orderby.equals("row")) {
				list = productService.selectSearchPriceRow(map);
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopProductSearchList");
		mav.addObject("list", list);
		mav.addObject("keyword", keyword);
		mav.addObject("count", count);
		mav.addObject("orderby", orderby);
		return mav;
	}

	// ?????? ?????? ??????
	@RequestMapping("/shop/productDetail.do")
	public ModelAndView productDetail(@RequestParam int p_no,
			@RequestParam(value = "pageNum", defaultValue = "1") int currentPage) {

		int Productcount = productService.countProduct(p_no);

		if (Productcount == 0) {
			return new ModelAndView("/common/notice");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_no", p_no);

		// ?????? ?????? ??? ??????
		int count = qnaService.selectAllPrpductQnaCount(map);

		// ???????????????
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "productDetail.do", "&p_no=" + p_no);

		// ?????????
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		// ?????? ?????? ???????????????
		List<QnaVO> list = qnaService.selectAllProductQna(map);

		
		//?????? ?????? ?????? ?????? 
		 int reviewCount = reviewService.selectProductReviewCount(p_no);
		 List<ReviewVO> review = null; 
		 if(reviewCount !=0) { 
			 review = reviewService.selectProductReview(p_no); 
		}

		 

		ProductVO product = productService.selectProduct(p_no);

		Category_topVO category_top = category_topService.selectCategoryOne(product.getC_top_no());
		Category_subVO category_sub = category_subService.selectCategoryOne(product.getC_sub_no());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopProductDetail");
		mav.addObject("product", product);

		mav.addObject("category_top_name", category_top);
		mav.addObject("category_sub_name", category_sub);

		mav.addObject("reviewCount", reviewCount); 
		mav.addObject("review", review);

		// ?????????
		mav.addObject("list", list);
		// ???????????????
		mav.addObject("count", count);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	// ?????? ????????? ??????
	@RequestMapping("/product/photoView.do")
	public ModelAndView viewImage(@RequestParam int p_no) {

		logger.debug("<<?????? ?????? ????????? ??????>> : " + p_no);
		ProductVO product = productService.productSelect(p_no);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", product.getP_image());
		mav.addObject("filename", product.getP_image_name());
		return mav;
	}

	// ckeditor??? ????????? ????????? ?????????
	@RequestMapping("/product/imageUploader.do")
	@ResponseBody
	public Map<String, Object> uploadImage(MultipartFile upload, HttpSession session, HttpServletRequest request)
			throws Exception {

		// ???????????? ?????? ?????? ?????????
		String realFolder = session.getServletContext().getRealPath("/resources/image_upload");

		// ???????????? ?????? ??????
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + org_filename;

		logger.debug("<<?????? ?????????>> : " + org_filename);
		logger.debug("<<????????? ?????????>> : " + str_filename);

		Integer mem_num = (Integer) session.getAttribute("mem_num");

		String filepath = realFolder + "\\" + mem_num + "\\" + str_filename;
		logger.debug("<<?????? ??????>> : " + filepath);

		File f = new File(filepath);
		if (!f.exists()) {
			f.mkdirs();
		}

		upload.transferTo(f);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uploaded", true);
		map.put("url", request.getContextPath() + "/resources/image_upload/" + mem_num + "/" + str_filename);

		return map;
	}

	// --------------------?????? ??????----------------------
	// ?????? ?????????
	@RequestMapping("/product/list.do")
	public ModelAndView process(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage) {

		Map<String, Object> map = new HashMap<String, Object>();

		// ?????? ??????
		int count = productService.selectRowCount();

		logger.debug("<<count>> : " + count);

		// ????????? ??????
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "list.do");

		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<ProductVO> list = null;
		if (count > 0) {
			list = productService.selectProductAll(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("productList"); // ????????? ?????????
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	// ?????? ?????? ???
	@GetMapping("/product/productDelete.do")
	public String category_topDeleteForm(@RequestParam int p_no, HttpServletRequest request, Model model) {
		logger.debug("product deleteForm ?????? - p_no : " + p_no);

		// ????????? ???????????? ??????
		model.addAttribute("p_no", p_no);

		return "productDelete";
	}

	// ?????? ?????? ??????
	@PostMapping("/product/productDelete.do")
	public String category_topDelete(int p_no) {
		logger.debug("product delete ?????? - p_no : " + p_no);

		// ???????????? ??????
		productService.deleteProduct(p_no);

		return "redirect:/product/list.do";
	}

	// ?????? ??????
	@GetMapping("/product/productRegister")
	public String productRegisterForm(Model model) {
		logger.info("<<product_registerForm>>");

		List<Category_topVO> list = category_topService.selectCategory_top();

		model.addAttribute("topList", list);

		logger.info("<<?????????>> : " + list);

		return "productRegister"; // ????????? ?????????
	}

	// ?????? ??????
	@PostMapping("/product/productRegister")
	public String productRegister(ProductVO productVO) {
		logger.info("<<product_register:>>" + productVO.getP_name());

		productService.insertProduct(productVO);

		return "redirect:/product/list.do";

	}

	// ?????? ??????(ajax)
	@RequestMapping("/product/getSubCate.do")
	@ResponseBody
	public Map<String, Object> getList(@RequestParam int c_top_no) {

		List<Category_subVO> list = category_subService.category_subWanted(c_top_no);

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);

		logger.info("<<??????????????????>>" + list);

		return mapJson;
	}

	// ?????? ?????? ?????? - ?????? ???
	@GetMapping("/product/productUpdate.do")
	public String formUpdate(@RequestParam int p_no, Model model) {
		logger.info("p_no : " + p_no);

		ProductVO productVO = productService.productSelect(p_no);
		List<Category_topVO> list = category_topService.selectCategory_top();

		logger.debug("ProductVO : " + productVO);

		model.addAttribute("topList", list);
		model.addAttribute("productVO", productVO);

		return "productUpdate"; // ????????? ??????
	}

	// ?????? ?????? ?????? - ?????? ????????? ??????
	@PostMapping("/product/productUpdate.do")
	public String submitUpdate(@Valid ProductVO productVO, BindingResult result, HttpSession session) {

		logger.info("?????? ???????????? : " + productVO);

		// ????????? ?????? ?????? ????????? ????????? ??? ??????
		if (result.hasErrors()) {
			return "productUpdate";
		}

		// ????????? ???????????? ????????????
		productService.updateProduct(productVO);

		return "redirect:/product/list.do";
	}

}
