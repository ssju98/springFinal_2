<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3>주문 취소(관리자 인증)</h3>
	<div id="narrow-width">
		<form>
			<!-- *****임시 데이터***** -->
			<%-- <form:hidden path="mem_num"/> --%>
			<table class="table table-borderless table-sm">
				<tr>
					<td style="width:150px;">아이디</td>
					<td>
						<%-- <form:password path="mem_id"/>
						<form:errors path="mem_id" /> --%>
						<input type="text">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<%-- <form:password path="mem_passwd"/>
						<form:errors path="mem_passwd" /> --%>
						<input type="text">
					</td>
				</tr>
			</table>
			<div class="text-center">
				<button class="btn btn-dark">인증</button>
				<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='orderList.do'">
			</div>
		</form>
	</div>
</div>
<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<!-- 중앙 내용 끝 -->