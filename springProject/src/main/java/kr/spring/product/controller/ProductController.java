package kr.spring.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import kr.spring.util.PagingUtil;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private int rowCount = 5;
	private int pageCount = 10;

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
		
		Category_topVO category_top = category_topService.selectCategoryOne(c_top_no);
		Category_subVO category_sub = category_subService.selectCategoryOne(c_sub_no);
		List<Category_topVO> list_top = category_topService.selectCategory_top();
		List<Category_subVO> list_sub = category_subService.category_subSelectAll();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopProductList");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("category_top",list_top);
		mav.addObject("category_sub",list_sub);
		mav.addObject("category_top_name",category_top);
		mav.addObject("category_sub_name",category_sub);

		return mav;
	}

	//상품 상세 목록
	@RequestMapping("/shop/productDetail.do")
	public ModelAndView productDetail(@RequestParam int p_no) {
		
		int count = productService.countProduct(p_no);
		
		if(count == 0) {
			 return new ModelAndView("/common/notice");
		}
		
		ProductVO product = productService.ProductSelect(p_no);
		Category_topVO category_top = category_topService.selectCategoryOne(product.getC_top_no());
		Category_subVO category_sub = category_subService.selectCategoryOne(product.getC_sub_no());
		System.out.println(category_top);
		System.out.println(category_sub);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shopProductDetail");
		mav.addObject("product",product);
		 mav.addObject("category_top_name",category_top);
		 mav.addObject("category_sub_name",category_sub);
		 
		return mav;
	}
	
	//상품 이미지 출력
	@RequestMapping("/product/photoView.do")
	public ModelAndView viewImage(@RequestParam int p_no) {
		
		logger.debug("<<상품 상세 이미지 출력>> : " + p_no);
		ProductVO product = productService.ProductSelect(p_no);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile",product.getP_image());
		mav.addObject("filename",product.getP_image_name());
		return mav;
	}
	
	//상품관리 - 상품 리스트 , 상품 이미지 출력
	//ckeditor를 이용한 이미지 업로드
//	@RequestMapping("/product/imageUploader.do")
//	@ResponseBody
//	public Map<String,Object> uploadImage(MultipartFile upload,
//			                              HttpSession session,
//			                              HttpServletRequest request)
//	                                         throws Exception{
//		
//		//업로드할 절대 경로 구하기
//		String realFolder = 
//			session.getServletContext().getRealPath("/resources/image_upload");
//		
//		//업로드한 파일 이름
//		String org_filename = upload.getOriginalFilename();
//		String str_filename = System.currentTimeMillis() + org_filename;
//		
//		logger.debug("<<원본 파일명>> : " + org_filename);
//		logger.debug("<<저장할 파일명>> : " + str_filename);
//		
//		Integer user_num = (Integer)session.getAttribute("user_num");
//		
//		String filepath = realFolder + "\\" + user_num + "\\" + str_filename;
//		logger.debug("<<파일 경로>> : " + filepath);
//		
//		File f = new File(filepath);
//		if(!f.exists()) {
//			f.mkdirs();
//		}
//		
//		upload.transferTo(f);
//		
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("uploaded", true);
//		map.put("url", request.getContextPath()+"/resources/image_upload/"+user_num+"/"+str_filename);
//		
//		return map;
//	}
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public ProductVO initCommand() {
		return new ProductVO();
	}
	
	//상품 리스트
	@RequestMapping("/product/list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			                    @RequestParam(value="keyfield",defaultValue="") String keyfield,
			                    @RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		
		
		//글의 총갯수 또는 검색된 글의 갯수
		int count = productService.selectRowCount(map);
		
		logger.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = 
			new PagingUtil(keyfield,keyword,currentPage,count,rowCount,pageCount,"list.do");
		
		map.put("start",page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<ProductVO> list = null;
		if(count > 0) {
			list = productService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("productList"); //타일스 식별자
		mav.addObject("count", count);
		mav.addObject("list",list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//상품 등록 페이지
	
}