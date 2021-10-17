<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3 id="header-3">배송 목록</h3>
	<!-- 배송 검색 조건 -->
	<div id="normal-width">
		<form action="deliveryList.do" method="get" id="search_form">
			<table class="table table-borderless table-sm">
				<tr>
					<th width="13%">배송상태</th>
					<td>
						<div class="form-check-inline">
						<input type="radio" name="d_status_num" value="" checked="checked" class="form-check-input">전체
						<input type="radio" name="d_status_num" value="0" class="form-check-input ml-2">결제완료
						<input type="radio" name="d_status_num" value="1" class="form-check-input ml-2">배송준비중
						<input type="radio" name="d_status_num" value="2" class="form-check-input ml-2">배송중
						<input type="radio" name="d_status_num" value="3" class="form-check-input ml-2">배송완료
						</div>
					</td>
				</tr>
				<tr>
					<th>검색조건</th>
					<td>
						<div class="form-inline">
							<select name="keyfield" id="keyfield" class="form-control form-control-sm">
								<option value="order_no">주문번호</option>
								<option value="mem_id">아이디</option>
							</select>
							<input type="search" name="keyword" id="keyword" placeholder="검색어 입력" class="form-control form-control-sm ml-1">
							<input type="submit" value="검색" class="btn btn-dark btn-sm ml-1">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 배송 조회 결과 -->
	<div id="wide_width">
		<c:if test="${count == 0}">
			주문 정보가 없습니다.
		</c:if>
		<c:if test="${count > 0}">
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>주문일</th>
					<th>주문번호</th>
					<th>주문자아이디</th>
					<th>배송상태</th>
					<th>송장번호</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="delivery" items="${list}">
				<tr>
					<td>
						<c:set var="year" value="${fn:substring(delivery.order_no,0,4)}" />
						<c:set var="month" value="${fn:substring(delivery.order_no,4,6)}" />
						<c:set var="day" value="${fn:substring(delivery.order_no,6,8)}" />
						<c:out value="${year}-${month}-${day}"/>
					</td>
					<td>${delivery.order_no}</td>
					<td>${delivery.mem_id}</td>
					<td>${delivery.d_status_name}</td>
					<td>${delivery.tracking_num}</td>
					<td>
						<c:if test="${delivery.d_status_num == 0}">
							<input type="button" value="배송준비" class="btn btn-secondary btn-sm btn_ready" onclick="location.href='dStatusUpdate.do?delivery_no=${delivery.delivery_no}&d_status_num=1'">
						</c:if>
						<c:if test="${delivery.d_status_num == 1}">
							<input type="button" value="송장등록" class="btn btn-info btn-sm" onclick="location.href='deliveryTrack.do?delivery_no=${delivery.delivery_no}'">
						</c:if>
						<c:if test="${delivery.d_status_num == 2}">
							<input type="button" value="배송완료" class="btn btn-danger btn-sm btn_completed" onclick="location.href='dStatusUpdate.do?delivery_no=${delivery.delivery_no}&d_status_num=3'">
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-center mt-4">${pagingHtml}</div>
		</c:if>
	</div>
</div>
<!-- 중앙 내용 끝 -->