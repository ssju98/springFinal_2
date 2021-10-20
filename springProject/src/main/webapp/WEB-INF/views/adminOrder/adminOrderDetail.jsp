<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminPage.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<div id="wide-width" class="wide-table">
		<h4 id="header-main">주문 상세정보</h4>
		<!-- 주문 정보 -->
		<table class="table table-bordered">
			<tr class="cell-4">
				<th>주문번호</th>
				<td>${adminOrder.order_no}</td>
				<th>주문일</th>
				<td>${adminOrder.order_date}</td>
			</tr>
			<tr class="cell-4">
				<th>주문자 아이디</th>
				<td>${adminMember.mem_id}
					<span class="text-danger">
					<c:if test="${adminMember.mem_auth == 0}">
						[탈퇴회원]
					</c:if>
					<c:if test="${adminMember.mem_auth == 1}">
						[정지회원]
					</c:if>
					</span>
				</td>
				<th>주문자 연락처</th>
				<td>${adminMember.mem_phone}</td>
			</tr>
			<tr class="cell-4">
				<th>결제방법</th>
				<td>
					<c:if test="${adminOrder.order_method == 0}">카드</c:if>
					<c:if test="${adminOrder.order_method == 1}">현금</c:if>
				</td>
				<th>결제금액</th>
				<td>
					<fmt:formatNumber value="${adminOrder.order_pay + adminOrder.delivery_pay}" pattern="#,###"/>원
				</td>
			</tr>
			<tr>
				<th>처리상태</th>
				<td colspan="3">${adminOrder.d_status_name}</td>
			</tr>
		</table>
		<!-- 배송지 정보 -->
		<h5 id="header-sub">배송지 정보</h5>
		<table class="table table-bordered">
			<tr class="cell-4">
				<th>받는사람</th>
				<td>${adminOrder.order_name}</td>
				<th>연락처</th>
				<td>${adminOrder.order_phone}</td>
			</tr>
			<tr class="cell-2">
				<th>배송지</th>
				<td colspan="3">(${adminOrder.order_zipcode}) ${adminOrder.order_address1} ${adminOrder.order_address2}</td>
			</tr>
		</table>
		<c:if test="${adminOrder.d_status_name == '결제완료' || adminOrder.d_status_name == '배송준비중'}">
			<div class="element-center text-right div-button">
				<c:if test="${mem_auth == 4}">
				<input type="button" value="주문취소" class="btn btn-danger btn-sm" onclick="location.href='${pageContext.request.contextPath}/admin/orderCancel.do?order_no=${adminOrder.order_no}'">
				</c:if>
			</div>
		</c:if>
		<!-- 주문 상품 -->
		<h5 id="header-sub">주문 상품</h5>
		<table class="table table-bordered text-center">
			<thead>
				<tr>
					<th class="c-pnum">상품번호</th>
					<th class="c-pname">상품명</th>
					<th class="c-mount">수량</th>
					<th class="c-pay">총상품금액</th>
					<th class="c-pay">배송비</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${listProduct}" varStatus="status">
				<tr>
					<td>${product.p_no}</td>
					<td>${product.p_name}</td>
					<td>${product.order_d_amount}</td>
					<c:if test="${status.first}">
					<td rowspan="${fn:length(listProduct)}"  class="text-right">
						<fmt:formatNumber value="${product.order_pay}" pattern="#,###"/>원
					</td>
					<td rowspan="${fn:length(listProduct)}"  class="text-right">
						<fmt:formatNumber value="${product.delivery_pay}" pattern="#,###"/>원
					</td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!-- 중앙 내용 끝 -->