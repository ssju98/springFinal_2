package kr.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void insertMember(MemberVO member) {
		//회원번호 세팅
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_detail(member);
	}

	@Override
	public MemberVO selectCheckMember(String mem_id) {
		return memberMapper.selectCheckMember(mem_id);
	}

	@Override
	public MemberVO selectMember(Integer mem_num) {
		return memberMapper.selectMember(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
	}

	@Override
	public void deleteMember(Integer mem_num) {
		memberMapper.deleteMember(mem_num);
		memberMapper.deleteMember_detail(mem_num);
		
	}

	@Override
	public int memberCount(MemberVO member) {
		return memberMapper.memberCount(member);
	}

	@Override
	public MemberVO findIdMember(MemberVO member) {
		// TODO Auto-generated method stub
		return memberMapper.findIdMember(member);
	}

	@Override
	public int memberPasswdCount(String mem_email) {
		return memberMapper.memberPasswdCount(mem_email);
	}

	@Override
	public void updateMemberPasswd(MemberVO member) {
		memberMapper.updateMemberPasswd(member);
	}

	@Override
	public void insertMemberKakao(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_DetailKakao(member);
		
	}

	@Override
	public int selectMemberCount(String id) {
		return memberMapper.selectMemberCount(id);
		
	}

	@Override
	public void insertMemberNaver(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_DetailNaver(member);
		
	}

	@Override
	public String selectmemberPasswdToken(String mem_email) {
		return memberMapper.selectmemberPasswdToken(mem_email);
	}

	@Override
	public int selectCheckMemberEmail(String mem_email) {
		return memberMapper.selectCheckMemberEmail(mem_email);
	}
}
