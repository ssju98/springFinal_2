package kr.spring.review.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.adminOrder.vo.AdminOrderVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewListVO;
import kr.spring.review.vo.ReviewVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderAllVO;
import kr.spring.order.vo.OrderListVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.orderDetail.vo.OrderDetailVO;
import kr.spring.product.service.ProductService;
import kr.spring.product.vo.ProductVO;
import kr.spring.review.service.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	private int rowCount = 7;
	private int rowCount2 = 5;
	private int pageCount = 10;
	
	//작성 가능한 후기 페이지
	@GetMapping("/review/reviewAvaliable.do")
	public ModelAndView reviewAvaliavleForm(HttpSession session,
											@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", mem_num);

		int count = reviewService.selectReviewAvaliableCount(mem_num);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, "reviewAvaliable.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		   
		List<OrderListVO> list = reviewService.selectReviewAvaliable(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewAvaliable");
		mav.addObject("list",list);
		mav.addObject("count", count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
		
	//이미 작성한 후기 페이지
	@GetMapping("/review/reviewWritten.do")
	public ModelAndView reviewWrittenForm(HttpSession session,
											@RequestParam(value="pageNum",defaultValue="1") int currentPage){
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", mem_num);

		int count = reviewService.selectReviewWrittenCount(mem_num);
		
		PagingUtil page = new PagingUtil(currentPage, count, rowCount2, pageCount, "reviewWritten.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<ReviewListVO> list = reviewService.selectReviewWritten(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewWritten");
		mav.addObject("list",list);
		mav.addObject("count",count);
		mav.addObject("pagingHtml",page.getPagingHtml());
		return mav;
	}
	
	//이미지 출력
	@RequestMapping("/review/imageView.do")
	public ModelAndView viewImage(@RequestParam int review_no) {
		ReviewVO review = reviewService.selectReview(review_no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		                //속성명         속성값(byte[]의 데이터)     
		mav.addObject("imageFile", review.getReview_image());
		mav.addObject("filename", review.getReview_image_name());
			
		return mav;
	}
	
	//리뷰 작성 Modal 호출
	@ResponseBody
	@GetMapping("/review/writeForm.do")
	public ProductVO reviewWriteForm(int p_no) {
		ProductVO product=productService.productSelect(p_no);
		return product;
	}
	
	//리뷰 작성
	@ResponseBody
	@PostMapping("/review/writeForm.do")
	public Map<String,String> reviewWrite(HttpSession session, ReviewVO reviewVO) {
		logger.info("<리뷰작성>:"+reviewVO);
		Map<String,String> map = new HashMap<String, String>();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		OrderAllVO order = orderService.selectOrderDetailInfo(reviewVO.getOrder_no());
		
		if(mem_num == null) {
			map.put("result", "logout");
		}else if(mem_num != order.getMem_num()) {
			map.put("result", "notMatch");
		}else {
			reviewVO.setMem_num(mem_num);
			reviewService.insertReview(reviewVO);
			map.put("result", "success");
		}
		return map;
	}
	
	//리뷰 수정 Modal 호출
	@ResponseBody
	@GetMapping("/review/modifyForm.do")
	public ReviewVO reviewModifyForm(int review_no) {
		ReviewVO review = reviewService.selectReview(review_no);
		return review;
	}
	
	//리뷰 수정
	@ResponseBody
	@PostMapping("/review/modifyForm.do")
	public Map<String,String> reviewModify(HttpSession session, ReviewVO reviewVO){
		logger.info("<리뷰수정>:"+reviewVO);
		Map<String,String> map = new HashMap<String, String>();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		ReviewVO review = reviewService.selectReview(reviewVO.getReview_no());
		
		//HTML 태그 불허, 줄바꿈 허용
		review.setReview_content(StringUtil.useBrNoHtml(review.getReview_content()));
		
		if(mem_num == null) {
			map.put("result", "logout");
		} else if(mem_num != review.getMem_num()) {
			map.put("result", "notMatch");
		}else {
			reviewVO.setMem_num(mem_num);
			reviewService.updateReview(reviewVO);
			map.put("result", "success");
		}
		return map;
	}
	
	//리뷰 삭제
	@ResponseBody
	@RequestMapping("/review/delete.do")
	public Map<String,String> deleteReview(HttpSession session, int review_no){
		logger.info("<리뷰삭제>:"+review_no);
		Map<String,String> map = new HashMap<String,String>();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {
			map.put("result","logout");
		}else {
			reviewService.deleteReview(review_no);
			map.put("result", "success");
		}
		return map;
	}
}
