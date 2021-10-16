<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>     
<!-- 중앙 내용 시작 -->
<div class="page-main">
	<h2>회원탈퇴</h2>
	<form:form id="delete_form" action="delete.do" modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<form:input path="mem_id" placeholder="4~12자 영문,숫자만 허용"/>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_passwd">비밀번호</label>
				<form:password path="mem_passwd" placeholder="4~12자 영문,숫자만 허용"/>
				<form:errors path="mem_passwd" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>탈퇴하기</form:button>
		</div>
	</form:form>
</div>
<!-- 중앙 내용 끝 -->



