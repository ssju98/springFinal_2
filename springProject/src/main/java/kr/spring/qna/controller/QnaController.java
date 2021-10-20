package kr.spring.qna.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.qna.service.QnaService;
import kr.spring.qna.vo.QnaVO;


@Controller
public class QnaController {
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	private QnaService qnaService;
	
	//자바빈(VO)초기화
	@ModelAttribute
	public QnaVO initCommand() {
		return new QnaVO();
	}
	
	//ckeditor를 이용한 이미지 업로드
	@RequestMapping("/product/imageUploader.do")
	@ResponseBody
	public Map<String,Object> uploadImage(MultipartFile upload,
			                              HttpSession session,
			                              HttpServletRequest request)
	                                         throws Exception{
		
		//업로드할 절대 경로 구하기
		String realFolder = 
			session.getServletContext().getRealPath("/resources/image_upload");
		
		//업로드한 파일 이름
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + org_filename;
		
		logger.debug("<<원본 파일명>> : " + org_filename);
		logger.debug("<<저장할 파일명>> : " + str_filename);
		
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		String filepath = realFolder + "\\" + mem_num + "\\" + str_filename;
		logger.debug("<<파일 경로>> : " + filepath);
		
		File f = new File(filepath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		upload.transferTo(f);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploaded", true);
		map.put("url", request.getContextPath()+"/resources/image_upload/"+mem_num+"/"+str_filename);
		
		return map;
	}
	
	//문의게시판목록
	@RequestMapping("/qna/qnaList.do")
	public ModelAndView qnaList(@RequestParam int board_kind) {
		List<QnaVO> list = qnaService.selectAllQna(board_kind);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",list);
		mav.addObject("board_kind",board_kind);
		mav.setViewName("qnaList");
		return mav;
	} 
	
	//문의게시판상세
	@RequestMapping("/qna/qnaDetail.do")
	public ModelAndView qnaDetail(@RequestParam int board_no) {
		QnaVO qna = qnaService.selectDetailQna(board_no);
		ModelAndView mav = new ModelAndView();
		mav.addObject("qna",qna);
		mav.setViewName("qnaDetail");
		return mav;
	} 
	
	//문의게시판 글 작성폼
	@RequestMapping("/qna/qnaWrite.do")
	public ModelAndView qnaWriteForm(@RequestParam int board_kind) {
		return new ModelAndView("qnaWrite","board_kind",board_kind);
	}
	
	//문의게시판 글 작성
	@PostMapping("/qna/qnaWrite.do")
	public String qnaWrite(QnaVO qnaVO, HttpSession session ) {
		logger.debug("<<글작성>> : "+qnaVO);
		
		qnaVO.setMem_num((Integer)session.getAttribute("mem_num"));
		
		qnaService.insertQna(qnaVO);
		
		return "redirect:/qna/qnaList.do?board_kind="+qnaVO.getBoard_kind();
	}	
		
	//문의게시판 글 답변폼
	@GetMapping("/qna/qnaReply.do")
	public ModelAndView qnaReplyForm(@RequestParam int board_no) {
		QnaVO qna = qnaService.selectDetailQna(board_no);
		
		return new ModelAndView("qnaReply","qna",qna);
	}
		
	//문의게시판 답변 작성
	@PostMapping("/qna/qnaReply.do")
	public String qnaReply(QnaVO qnaVO, HttpSession session) {
		logger.debug("<<답변작성>> : " + qnaVO);
		qnaVO.setMem_num((Integer)session.getAttribute("mem_num"));
		qnaService.insertQna(qnaVO);
		
		return "redirect:/qna/qnaList.do?board_kind="+qnaVO.getBoard_kind();
	}
	
	//문의게시판 글 삭제
	@RequestMapping("/qna/qnaDelete.do")
	public String qnaDelete(@RequestParam int board_no) {
		//board_kind알기위해
		QnaVO qna = qnaService.selectDetailQna(board_no);
		
		logger.debug("<<글 삭제 >> : "+board_no);
		qnaService.deleteQna(board_no);
		return "redirect:/qna/qnaList.do?board_kind="+qna.getBoard_kind();
	}
	
	//문의게시판 글 수정 폼
	@RequestMapping("/qna/qnaUpdate.do")
	public String qnaUpdateForm(@RequestParam int board_no, Model model) {
		QnaVO qnaVO = qnaService.selectDetailQna(board_no);
		model.addAttribute("qnaVO",qnaVO);
		
		return "qnaUpdate";
	}
	
	//문의게시판 글 수정
	@PostMapping("/qna/qnaUpdate.do")
	public String qnaUpdate(QnaVO qnaVO, Model model,HttpServletRequest request) {
		//board_kind알기위해
		qnaService.updateQna(qnaVO);
		
		model.addAttribute("message","글 수정이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/qna/qnaList.do?board_kind="+qnaVO.getBoard_kind());
		
		return "common/resultView";
	}
}
