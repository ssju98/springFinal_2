<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3 id="header-3">회원정보 상세</h3>
	<!-- 회원 정보 -->
	<div id="normal-width">
		<table class="table table-bordered cell-2">
			<tr>
				<th>회원번호</th>
				<td>${adminMemberVO.mem_num}</td>				
			</tr>
			<tr>
				<th>회원유형</th>
				<td>
					<c:if test="${adminMemberVO.mem_auth == 0}">탈퇴</c:if>
					<c:if test="${adminMemberVO.mem_auth == 1}">정지</c:if>
					<c:if test="${adminMemberVO.mem_auth == 2}">일반</c:if>
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>${adminMemberVO.mem_id}</td>
			</tr>
			<c:if test="${adminMemberVO.mem_auth != 0}">
			<tr>
				<th>이름</th>
				<td>${adminMemberVO.mem_name}</td>
				
			</tr>
			<tr>
				<th>연락처</th>
				<td>${adminMemberVO.mem_phone}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${adminMemberVO.mem_email}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>(${adminMemberVO.mem_zipcode}) ${adminMemberVO.mem_address1} ${adminMemberVO.mem_address2}</td>
			</tr>
			<tr>
				<th>가입일</th>
				<td>${adminMemberVO.mem_date}</td>
			</tr>
			</c:if>
		</table>
		<div class="element-center text-center">
			<c:if test="${adminMemberVO.mem_auth != 0}">
			<input type="button" value="수정" class="btn btn-info" onclick="location.href='memberUpdate.do?mem_num=${adminMemberVO.mem_num}'">
			<input type="button" value="삭제" class="btn btn-danger" onclick="location.href='memberDelete.do?mem_num=${adminMemberVO.mem_num}'"
				<c:if test="${mem_auth != 4}">disabled</c:if>
			>
			</c:if>
		</div>
	</div>
	<!-- 활동 정보 -->
	<div id="wide_width">
		<h4 id="header-4">활동정보</h4>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>주문건수</th>
					<th>취소건수</th>
					<th>후기건수</th>
					<th>문의건수</th>
				</tr>
			</thead>
			<tbody>
				<!-- *****임시 데이터***** -->
				<tr>
					<td>{orderCnt}</td>
					<td>{cancelCnt}</td>
					<td>{reviewCnt}</td>
					<td>{qnaCnt}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- 중앙 내용 끝 -->