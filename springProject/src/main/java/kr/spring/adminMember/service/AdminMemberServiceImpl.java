package kr.spring.adminMember.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.adminMember.dao.AdminMemberMapper;
import kr.spring.adminMember.vo.AdminMemberVO;

@Service
@Transactional
public class AdminMemberServiceImpl implements AdminMemberService{
	
	@Autowired
	private AdminMemberMapper adminMemberMapper;
	
	//============== 회원관리 ==============
	@Override
	public int getMemberCount(Map<String,Object> map) {
		return adminMemberMapper.getMemberCount(map);
	}
	@Override
	public List<AdminMemberVO> getMemberList(Map<String, Object> map){	
		return adminMemberMapper.getMemberList(map);
	}
	@Override
	public AdminMemberVO selectCheckMember(String mem_id) {
		return adminMemberMapper.selectCheckMember(mem_id);
	}
	@Override
	public AdminMemberVO selectMember(int mem_num) {
		return adminMemberMapper.selectMember(mem_num);
	}
	@Override
	public void updateMember(AdminMemberVO adminMemberVO) {
		adminMemberMapper.updateMember(adminMemberVO);
		adminMemberMapper.updateMemberAuth(adminMemberVO);
	}
	@Override
	public void deleteMember(int mem_num) {
		adminMemberMapper.deleteMember(mem_num);
		adminMemberMapper.deleteMemberAuth(mem_num);
	}
}
