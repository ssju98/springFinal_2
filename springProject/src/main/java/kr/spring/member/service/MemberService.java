package kr.spring.member.service;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String mem_id);
	public int selectCheckMemberEmail(String mem_email);
	public MemberVO selectMember(Integer mem_num);
	public void updateMember(MemberVO member);
	//public void updatePassword(MemberVO member);
	public void deleteMember(Integer mem_num);
	
	public int memberCount(MemberVO member);
	public MemberVO findIdMember(MemberVO member); 
	public String selectmemberPasswdToken(String mem_email);
	public int memberPasswdCount(String mem_email);
	public void updateMemberPasswd(MemberVO member);
	
	public void insertMemberKakao(MemberVO member);
	public int selectMemberCount(String id);
	public void insertMemberNaver(MemberVO member);
}
