<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="category_top-main-width">
	<h3>카테고리 목록</h3>
	<div id="width">
		<form action="detail.do" method="get" id="category_main">
			<table class="table table-borderless table-sm">
				<tr>
					<th>대분류 카테고리</th>
					<td>
						
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 대분류 카테고리 수정 폼 -->
	<c:if test="${count > 0}">
	<table class="table table-hover table-bordered table-sm">
		<thead class="table-secondary">
			<tr>
				<th>상위 카테고리 번호</th>
				<th>상위 카테고리명</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="category_topVO" items="${list}">
			<tr>
				<td>${category_topVO.c_top_no}</td>
				<td>${category_topVO.c_top_name }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="text-center">${pagingHtml}</div>
	</c:if>
</div>










