<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3 id="header-3">주문 상세정보</h3>
	<!-- 주문 정보 -->
	<div id="normal-width">
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
					<span class="blank-text">
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
					<fmt:formatNumber value="${adminOrder.order_pay + adminOrder.delivery_pay}" pattern="#,###"/>원 (배송비 포함)
				</td>
			</tr>
			<tr>
				<th>처리상태</th>
				<td colspan="3">${adminOrder.d_status_name}</td>
			</tr>
		</table>
		<!-- 배송지 정보 -->
		<h5 id="header-4">배송지 정보</h5>
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
			<div class="element-center text-center">
				<input type="button" value="주문취소" class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/admin/orderCancel.do?order_no=${adminOrder.order_no}'" 
					<c:if test="${mem_auth != 4}">disabled</c:if>
				>
			</div>
		</c:if>
	</div>
	<!-- 주문 상품 -->
	<div id="wide_width">
		<h5 id="header-4">주문 상품</h5>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>상품번호</th>
					<th>상품명</th>
					<th>옵션</th>
					<th>수량</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
				<!-- *****임시 데이터***** -->
				<tr>
					<td>{p_no}</td>
					<td>{p_name}</td>
					<td>{option_name}</td>
					<td>{order_d_amount}</td>
					<td>{order_d_price}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- 중앙 내용 끝 -->