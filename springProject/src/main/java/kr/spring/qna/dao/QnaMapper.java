package kr.spring.qna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.qna.vo.QnaVO;

public interface QnaMapper {
	//시퀀스 가져오기
	@Select("SELECT qna_seq.nextval FROM dual")
	public int selectQna_seq();
	
	//글작성
	@Insert("insert into qna(board_no,grpno,board_title,board_content,mem_num,board_kind,board_parent) values(#{board_no},#{grpno}, #{board_title},#{board_content},#{mem_num},#{board_kind},#{board_parent})")
	public void insertQna(QnaVO qna);
	
	//문의글목록
	@Select("SELECT LEVEL, BOARD_NO,GRPNO,BOARD_TITLE,BOARD_CONTENT,member.MEM_NUM,member.mem_id,BOARD_KIND,BOARD_PARENT,BOARD_DATE "
			+ "FROM QNA, member WHERE BOARD_KIND = #{board_kind} and qna.mem_num=member.mem_num START WITH BOARD_PARENT = 0 "
			+ "CONNECT BY PRIOR BOARD_NO = BOARD_PARENT ORDER SIBLINGS BY GRPNO desc")
	public List<QnaVO> selectAllQna(int board_kind);
	
	//글 상세
	@Select("SELECT * FROM qna q, member m where board_no = #{board_no} and q.mem_num = m.mem_num")
	public QnaVO selectDetailQna(int board_no);
	
	//글삭제
	@Delete("DELETE FROM qna WHERE board_no = #{board_no}")
	public void deleteQna(int board_no);
	
	//글삭제시, 답변글도 같이 삭제
	@Delete("DELETE FROM qna WHERE board_parent = #{board_parent}")
	public void deleteQnaReply(int board_parent);
	
	//글수정
	@Update("UPDATE qna set board_content=#{board_content} where board_no=#{board_no}")
	public void updateQna(QnaVO qnavo);
	

}
