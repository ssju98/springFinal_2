package kr.spring.login.service;

import kr.spring.login.vo.LoginVO;

public interface LoginService {

	public LoginVO loginAction(String id);
	
	public int loginMem_num(String id);
	
	public void sendMail(LoginVO loginVO);
 
}