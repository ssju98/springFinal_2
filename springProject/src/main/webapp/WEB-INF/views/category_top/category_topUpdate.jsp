<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="category_top-main-width">
	<h3>상위 카테고리 수정</h3>
	<!-- 상위 카테고리 정보 -->
	<div>
		<form:form action="category_topUpdate.do" modelAttribute="category_topVO">
			<form:label path="c_top_no"/>
			<form:label path="c_top_name"/>
			<table class="table table-bordered cell-2">
				<tr>
					<th>카테고리 번호</th>
					<td>${category_topVO.c_top_no}</td>
				</tr>
				<tr>
					<th>상위 카테고리 명</th>
					<td>${category_topVO.c_top_name}</td>
				</tr>
			</table>
			
		</form:form>
	</div>
</div>