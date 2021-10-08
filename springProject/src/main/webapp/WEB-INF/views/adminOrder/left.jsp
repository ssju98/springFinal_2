<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 왼쪽 메뉴 시작 -->    
<div id="sidebar">
	<ul class="menu">
		<li><a href="${pageContext.request.contextPath}/adminOrder/adminOrderList.do">주문목록</a></li>
		<li><a href="${pageContext.request.contextPath}/adminOrder/adminOrderDetail.do">주문상세</a></li>
		<li><a href="${pageContext.request.contextPath}/adminOrder/adminOrderCancel.do">주문취소</a></li>
		<li><a href="${pageContext.request.contextPath}/adminOrder/adminDeliveryList.do">배송관리</a></li>
		<li><a href="${pageContext.request.contextPath}/adminOrder/adminDeliveryTrack.do">송장입력</a></li>
	</ul>
</div>
<!-- 왼쪽 메뉴 끝 -->    