<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3>주문 상세 정보</h3>
	<!-- 주문 정보 -->
	<div>
		<table class="table table-bordered">
			<tr>
				<th>주문번호</th>
				<td>${adminOrderVO.order_no}</td>
			</tr>
			<tr>
				<th>주문일</th>
				<td>${adminOrderVO.order_date}</td>
			</tr>
			<tr>
				<th>주문자</th>
				<td>
					<c:if test="${empty adminOrderVO.mem_name}"><span class="blank-text">탈퇴회원</span></c:if>
					<c:if test="${!empty adminOrderVO.mem_name}">${adminOrderVO.mem_name}</c:if>
				</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${adminOrderVO.mem_phone}</td>
			</tr>
			<tr>
				<th>결제방법</th>
				<td>
					<c:if test="${adminOrderVO.order_method ==0}">카드</c:if>
					<c:if test="${adminOrderVO.order_method == 1}">현금</c:if>
				</td>
			</tr>
			<tr>
				<th>결제금액</th>
				<td>${adminOrderVO.order_pay}</td>
			</tr>
			<tr>
				<th>처리상태</th>
				<td>${adminOrderVO.d_status_name}</td>
			</tr>
			<tr>
				<th>배송지</th>
				<td>(${adminOrderVO.order_zipcode}) ${adminOrderVO.order_address1} ${adminOrderVO.order_address2}</td>
			</tr>
		</table>
		<div class="text-center">
			<input type="button" value="주문취소" class="btn btn-secondary" onclick="location.href=''">
		</div>
	</div>
	<!-- 주문 상품 -->
	<div>
		<h5>주문 상품</h5>
		<table class="table table-bordered">
			<thead class="table-secondary">
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