<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3>회원 수정</h3>
	<!-- 회원 정보 -->
	<div id="normal-width">
		<form:form action="memberUpdate.do" modelAttribute="adminMemberVO">
			<form:hidden path="mem_num"/>
			<form:hidden path="mem_id"/>
			<form:hidden path="mem_date"/>
			<table class="table table-bordered" id="form-height">
				<tr>
					<th style="width:150px;">회원번호</th>
					<td>${adminMemberVO.mem_num}</td>
				</tr>
				<tr>
					<th>회원유형</th>
					<td>
						<form:select path="mem_auth">
							<form:option value="2" label="일반"/>
							<form:option value="1" label="정지"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${adminMemberVO.mem_id}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<form:input path="mem_name"/>
						<form:errors path="mem_name"/>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<form:input path="mem_phone"/>
						<form:errors path="mem_phone"/>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<form:input path="mem_email"/>
						<form:errors path="mem_email"/>
					</td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td>
						<form:input path="mem_zipcode"/>
						<form:errors path="mem_zipcode"/>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<form:input path="mem_address1"/>
						<form:errors path="mem_address1"/>
					</td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td>
						<form:input path="mem_address2"/>
						<form:errors path="mem_address2"/>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${adminMemberVO.mem_date}</td>
				</tr>
			</table>
			<div class="text-center">
				<form:button class="btn btn-secondary">수정</form:button>
				<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='memberDetail.do?mem_num=${adminMemberVO.mem_num}'">
			</div>
		</form:form>
	</div>
</div>
<!-- 중앙 내용 끝 -->