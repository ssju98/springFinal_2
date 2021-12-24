package kr.spring.member.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.login.vo.LoginVO;
import kr.spring.member.vo.MemberVO;

public interface MemberMapper {
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	@Insert("INSERT INTO member (mem_num,mem_id) VALUES (#{mem_num},#{mem_id})")
	public void insertMember(MemberVO member);
	@Insert("INSERT INTO member_detail (mem_num,mem_name,mem_passwd,mem_phone,mem_email) VALUES (#{mem_num},#{mem_name},#{mem_passwd},#{mem_phone},#{mem_email})")
	public void insertMember_detail(MemberVO member);
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,d.mem_passwd,d.mem_email FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckMember(String mem_id);
	@Select("SELECT count(*) FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE d.mem_email=#{mem_email}")
	public int selectCheckMemberEmail(String mem_email);
	@Select("SELECT * FROM member m JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	public void updateMember(MemberVO member);
	//public void updatePassword(MemberVO member);
	@Update("UPDATE member SET mem_auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);
	
	//아이디 찾기 : 이름과 이메일로 회원 존재 여부 확인
	public int memberCount(MemberVO member);
	
	//아이디 찾기 : 회원 아이디 조회
	public MemberVO findIdMember(MemberVO member); 
	
	//비밀번호 찾기 : 이메일로 회원 존재 여부 확인
	@Select("SELECT count(*) FROM member_detail where mem_email=#{mem_email}")
	public int memberPasswdCount(String mem_email);
	
	//비밀번호 찾기 : token값 확인
	@Select("SELECT NVL(mem_token,0) FROM member_detail where mem_email=#{mem_email}")
	public String selectmemberPasswdToken(String mem_email);
	
	//비밀번호 찾기 : 임시비밀번호로 비밀번호 변경
	@Update("Update member_detail SET mem_passwd=#{mem_passwd} WHERE mem_email=#{mem_email}")
	public void updateMemberPasswd(MemberVO member);
	
	//카카오 로그인 : 회원가입
	@Insert("INSERT INTO member_detail (mem_num,mem_name,mem_email,mem_token) VALUES (#{mem_num},#{mem_name},#{mem_email},#{mem_token})")
	public void insertMember_DetailKakao(MemberVO member);

	//회원 존재 여부 확인
	@Select("SELECT COUNT(*) FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public int selectMemberCount(String id);
	
	//네이버 로그인 : 회원가입
	@Insert("INSERT INTO member_detail (mem_num,mem_name,mem_email,mem_phone,mem_token) VALUES (#{mem_num},#{mem_name},#{mem_email},#{mem_phone},#{mem_token})")
	public void insertMember_DetailNaver(MemberVO member);

	
}
