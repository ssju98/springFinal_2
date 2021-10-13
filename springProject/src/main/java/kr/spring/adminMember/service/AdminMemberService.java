package kr.spring.adminMember.service;

import java.util.List;
import java.util.Map;

import kr.spring.adminMember.vo.AdminMemberVO;

public interface AdminMemberService {
	
	//============== 회원관리 ==============
	public int getMemberCount(Map<String,Object> map);
	public List<AdminMemberVO> getMemberList(Map<String,Object> map);
	public AdminMemberVO selectCheckMember(String mem_id);
	public AdminMemberVO selectMember(int mem_num);
	public void updateMember(AdminMemberVO adminMemberVO);
	public void deleteMember(int mem_num);
}
