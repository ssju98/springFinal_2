<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!-- 내용 시작 -->
<div class="page-main">
	<h2>회원가입</h2>
	<form:form id="register_form" action="registerUser.do" modelAttribute="memberVO">
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<form:input path="mem_id" placeholder="4~12자 영문,숫자만 허용"/>
				<input type="button" id="confirmId" value="ID 중복 체크">
				<span id="message_id"></span>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_name">이름</label>
				<form:input path="mem_name" />
				<form:errors path="mem_name" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_passwd">비밀번호</label>
				<form:input path="mem_passwd" placeholder="4~12자 영문,숫자만 허용"/>
				<form:errors path="mem_passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_phone">연락처</label>
				<form:input path="mem_phone"/>
				<form:errors path="mem_phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_email">이메일</label>
				<form:input path="mem_email"/>
				<form:errors path="mem_email" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_zipcode">우편번호</label>
				<form:input path="mem_zipcode"/>
				<form:errors path="mem_zipcode" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_address1">주소</label>
				<form:input path="mem_address1"/>
				<form:errors path="mem_address1" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_address2">나머지 주소</label>
				<form:input path="mem_address2"/>
				<form:errors path="mem_address2" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>회원가입</form:button>
		</div>
	</form:form>
</div>
<!-- 내용 끝 -->