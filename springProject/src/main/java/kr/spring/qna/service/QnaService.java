package kr.spring.qna.service;

import java.util.List;

import kr.spring.qna.vo.QnaVO;

public interface QnaService {
	public void insertQna(QnaVO qna);
	public void insertQnaReply(QnaVO qna);
	public QnaVO selectDetailQna(int board_no);
	public List<QnaVO> selectAllQna(int board_kind);
	public void deleteQna(int board_no);
	public void updateQna(QnaVO qna);
}
