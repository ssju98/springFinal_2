<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminPage.css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<div id="wide-width" class="wide-table">
		<h4 id="header-main">회원정보 수정</h4>
		<!-- 회원 정보 -->
		<form:form action="memberUpdate.do" modelAttribute="adminMemberVO" class="form-inline">
			<form:hidden path="mem_num"/>
			<form:hidden path="mem_id"/>
			<form:hidden path="mem_date"/>
			<table class="table table-bordered">
				<tr>
					<th>회원번호</th>
					<td>${adminMemberVO.mem_num}</td>
				</tr>
				<tr>
					<th>회원유형</th>
					<td>
						<form:select path="mem_auth" class="form-control form-control-sm">
							<form:option value="2" label="일반회원"/>
							<form:option value="1" label="정지회원"/>
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
						<form:input path="mem_name" class="form-control form-control-sm"/>
						<form:errors path="mem_name" class="text-danger"/>
					</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<form:input path="mem_phone" class="form-control form-control-sm"/>
						<form:errors path="mem_phone" class="text-danger"/>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<form:input path="mem_email" class="form-control form-control-sm"/>
						<form:errors path="mem_email" class="text-danger"/>
					</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${adminMemberVO.mem_date}</td>
				</tr>
			</table>
			<div class="element-center text-center div-button">
				<form:button class="btn btn-primary">수정</form:button>
				<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='memberDetail.do?mem_num=${adminMemberVO.mem_num}'">
			</div>
		</form:form>
	</div>
</div>
<!-- 중앙 내용 끝 -->