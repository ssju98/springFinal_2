package kr.spring.adminMember.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.adminMember.vo.AdminMemberVO;

public interface AdminMemberMapper {
	
	//============== 회원관리 ==============
	public int getMemberCount(Map<String,Object> map);
	
	public List<AdminMemberVO> getMemberList(Map<String,Object> map);
	
	@Select("SELECT m.*, d.mem_passwd FROM member M JOIN member_detail d ON m.mem_num=d.mem_num WHERE mem_id=#{mem_id}")
	public AdminMemberVO selectCheckMember(String mem_id);
	
	@Select("SELECT * FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public AdminMemberVO selectMember(int mem_num);
	
	@Update("UPDATE member_detail SET mem_name=#{mem_name},mem_phone=#{mem_phone},mem_email=#{mem_email},mem_zipcode=#{mem_zipcode},mem_address1=#{mem_address1},mem_address2=#{mem_address2} WHERE mem_num=#{mem_num}")
	public void updateMember(AdminMemberVO adminMemberVO);
	@Update("UPDATE member SET mem_auth=#{mem_auth} WHERE mem_num=#{mem_num}")
	public void updateMemberAuth(AdminMemberVO adminMemberVO);
	
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember(int mem_num);
	@Update("UPDATE member SET mem_auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMemberAuth(int mem_num);
	
	@Select("SELECT COUNT(*) FROM sorder WHERE mem_num=#{mem_num}")
	public int getOrderCount(int mem_num);
	@Select("SELECT COUNT(*) FROM sorder o JOIN delivery d ON o.order_no=d.order_no WHERE d_status_num=7 AND mem_num=#{mem_num}")
	public int getCancelCount(int mem_num);
	@Select("SELECT COUNT(*) FROM review WHERE mem_num=#{mem_num}")
	public int getReviewCount(int mem_num);
	@Select("SELECT COUNT(*) FROM qna WHERE mem_num=#{mem_num}")
	public int getQnaCount(int mem_num);
}
