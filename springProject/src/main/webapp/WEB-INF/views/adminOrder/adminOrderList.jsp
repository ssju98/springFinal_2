<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3>주문 목록</h3>
	<!-- 주문 조회 조건 -->
	<div id="normal-width">
		<form>
			<table class="table table-borderless table-sm">
				<tr>
					<th>처리상태</th>
					<td>
						<input type="radio" name="d_status_num" value="9" checked="checked">전체
						<input type="radio" name="d_status_num" value="0">결제완료
						<input type="radio" name="d_status_num" value="1">배송준비중
						<input type="radio" name="d_status_num" value="2">배송중
						<input type="radio" name="d_status_num" value="3">배송완료
						<input type="radio" name="d_status_num" value="4">구매확정
						<input type="radio" name="d_status_num" value="5">반품
						<input type="radio" name="d_status_num" value="6">교환
					</td>
				</tr>
				<tr>
					<th>조회기간</th>
					<td>
						<input type="button" value="1일" onclick="location.href=''">
						<input type="button" value="7일" onclick="location.href=''">
						<input type="button" value="30일" onclick="location.href=''">
						<input type="date" name="start_date"> ~ 
						<input type="date" name="end_date">
					</td>
				</tr>
				<tr>
					<th>조회조건</th>
					<td>
						<select name="keyfield" id="keyfield">
							<option value="order_no">주문번호</option>
							<option value="mem_name">주문자</option>
						</select>
						<input type="search" name="keyword" id="keyword">
						<input type="submit" value="조회" class="btn btn-dark btn-sm">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 주문 검색 결과 -->
	<c:if test="${count == 0}">
	주문 정보가 없습니다.
	</c:if>
	<c:if test="${count > 0}">
	<div id="table-list">
		<table class="table table-hover table-bordered table-sm">
			<thead class="table-secondary">
				<tr>
					<th>주문번호</th>
					<th>주문일</th>
					<th>주문자</th>
					<th>결제수단</th>
					<th>결제금액</th>
					<th>처리상태</th>
					<th>관리</th>
				</tr>
			<thead>
			<tbody>
				<c:forEach var="adminOrderVO" items="${list}">
				<tr>
					<td>${adminOrderVO.order_no}</td>
					<td>${adminOrderVO.order_date}</td>
					<td>
						<c:if test="${empty adminOrderVO.mem_name}"><span class="blank-text">탈퇴회원(${adminOrderVO.mem_id})</span></c:if>
						<c:if test="${!empty adminOrderVO.mem_name}">${adminOrderVO.mem_name}(${adminOrderVO.mem_id})</c:if>
					</td>
					<td>
						<c:if test="${adminOrderVO.order_method == 0}">카드</c:if>
						<c:if test="${adminOrderVO.order_method == 1}">현금</c:if>
					</td>
					<td>${adminOrderVO.order_pay}</td>
					<td>${adminOrderVO.d_status_name}</td>
					<td>
						<input type="button" value="상세" class="btn btn-secondary btn-sm" onclick="location.href='orderDetail.do?order_no=${adminOrderVO.order_no}'">
						<input type="button" value="취소" class="btn btn-secondary btn-sm" onclick="location.href='orderCancel.do?order_no=${adminOrderVO.order_no}''">
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
</div>
<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<!-- 중앙 내용 끝 -->