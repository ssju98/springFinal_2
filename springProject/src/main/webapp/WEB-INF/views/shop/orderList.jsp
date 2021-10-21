<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/orderList.css"> 
<div class="top_menu_info">
	<div>
	홈 > 주문조회
	</div>
</div>
<div id="main-width">
	<div id="categorySide">
		<div id="showmenu">나의 쇼핑</div>
		<div class="menu mt-2">
			<ul>
				<li class="order-current"><a href="orderList.do">주문 &#183; 배송</a></li>
				<li class="order-a"><a href="orderCancel.do">주문취소</a></li>
				<li class="order-a"><a href="orderExchange.do">교환하기</a></li>
				<li class="order-a"><a href="orderRefund.do">반품하기</a></li>
				<li class="order-a"><a href="orderConfirm.do">구매확정</a></li>
			</ul>
		</div>
		<div id="showmenu">구매후기</div>
		<div class="menu mt-2">
			<ul>
				<li class="order-a"><a href="${pageContext.request.contextPath}/review/reviewAvaliable.do">작성 가능한 후기</a></li>
				<li class="order-a"><a href="${pageContext.request.contextPath}/review/reviewWritten.do">이미 작성한 후기</a></li>
			</ul>
		</div>
	</div>
	<div class="top-small-menu">
		<div class="top-small-menu-title mt-3">
			주문 &#183; 배송
		</div>
		<table class="order-table" border="1">
			<colgroup>
				<col width="170px">
				<col width="170px">
				<col width="470px">
				<col width="200px">
			</colgroup>
			<thead>
				<tr>
					<th>주문 번호</th>
					<th>주문 일자</th>
					<th>상품 정보</th>
					<th>진행 상황</th>
				</tr>
			</thead>
			<c:forEach var="list" items="${list}">
				<tbody>
					<tr>	
						<td class="table-text">${list.order_no}</td>
						<td class="table-text">
							<fmt:formatDate value="${list.order_date}" pattern="yyyy.MM.dd."/>
						</td>
						<td class="table-product">
							<div class="table-product-name-img">
								<img src="${pageContext.request.contextPath}/resources/images/testProduct.jpg" width="100" height="100">
							</div>
							<div class="table-product-name">
								상품명 외 3건
							</div>
							<div class="table-product-pay">
								<fmt:formatNumber value="${list.order_pay+list.delivery_pay}" pattern="#,###"/>원
							</div>
						</td>
						<td>
							<div class="order-state-text">
								<h5><span class="badge badge-light">${list.d_status_name}</span></h5>
							</div>
							<div class="order-state-detail mt-1">
								<a href="orderDetail.do?order_no=${list.order_no}">주문 상세 보기 > </a>
							</div>
						</td>
					</tr>	
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>