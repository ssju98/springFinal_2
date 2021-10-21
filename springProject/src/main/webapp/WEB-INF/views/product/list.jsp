<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 중앙 내용 시작 -->
<div class="page-main">
	<h2>상품 목록</h2>
	<div class="align-right">
		<input type="button" value="상품 등록" onclick="location.href='write.do'">
	</div>
	<c:if test="${count == 0}">
	<div class="result-display">
		등록된 상품이 없습니다.
	</div>	
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>상품 번호</th>
			<th width="400">상품명</th>
			<th>상품 이미지</th>
			<th>상품 가격</th>
			<th>할인율</th>
		</tr>
		<c:forEach var="product" items="${list}">
		<tr>
			<td>${product.p_no}</td>
			<td><a href="detail.do?board_num=${product.p_no}">${product.p_name}</a></td>
			<td><img src="photoView.do?p_no=${product.p_no}" style="max-width:100px;"></td>
			<td>${product.p_price}</td>
			<td>${product.p_discount}</td>
		</tr>	
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>