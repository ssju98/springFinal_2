<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<script type="text/javascript">
	window.onload = function(){
		var form = document.getElementById('search_form');
		form.onsubmit = function(){
			var keyfield = document.getElementById('keyfield');
			var keyword = document.getElementById('keyword');
			//주문번호 검색시 숫자 체크
			if(keyfield.value == 'order_no' && isNaN(keyword.value)){
				keyword.focus();
				keyword.value='';
				alert('주문번호는 숫자만 입력하세요!');
				return false;
			}
		};
	};
</script>
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3 id="header-3">주문 목록</h3>
	<!-- 주문 검색 조건 -->
	<div id="normal-width">
		<form action="orderList.do" method="get" id="search_form">
			<table class="table table-borderless table-sm">
				<tr>
					<th width="13%">처리상태</th>
					<td>
						<div class="form-check-inline">
						<input type="radio" name="d_status_num" value="" checked="checked" class="form-check-input">전체
						<input type="radio" name="d_status_num" value="0" class="form-check-input ml-2">결제완료
						<input type="radio" name="d_status_num" value="1" class="form-check-input ml-2">배송준비중
						<input type="radio" name="d_status_num" value="2" class="form-check-input ml-2">배송중
						<input type="radio" name="d_status_num" value="3" class="form-check-input ml-2">배송완료
						<input type="radio" name="d_status_num" value="4" class="form-check-input ml-2">구매확정
						<input type="radio" name="d_status_num" value="5" class="form-check-input ml-2">반품
						<input type="radio" name="d_status_num" value="6" class="form-check-input ml-2">교환
						</div>
					</td>
				</tr>
				<tr>
					<th>주문기간</th>
					<td>
						<input type="button" value="1일" onclick="location.href=''">
						<input type="button" value="7일" onclick="location.href=''">
						<input type="button" value="30일" onclick="location.href=''">
						<input type="date" name="start_date"> ~ 
						<input type="date" name="end_date">
					</td>
				</tr>
				<tr>
					<th>검색조건</th>
					<td>
						<div class="form-inline">
							<select name="keyfield" id="keyfield" class="form-control form-control-sm">
								<option value="order_no">주문번호</option>
								<option value="mem_name">주문자</option>
							</select>
							<input type="search" name="keyword" id="keyword" placeholder="검색어 입력" class="form-control form-control-sm ml-1">
							<input type="submit" value="검색" class="btn btn-dark btn-sm ml-1">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 주문 검색 결과 -->
	<div id="wide_width">
		<c:if test="${count == 0}">
			주문 정보가 없습니다.
		</c:if>
		<c:if test="${count > 0}">
		<table class="table table-hover table-bordered">
			<thead>
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
						<c:if test="${empty adminOrderVO.mem_name}">
							<span class="blank-text">탈퇴회원(${adminOrderVO.mem_id})</span>
						</c:if>
						<c:if test="${!empty adminOrderVO.mem_name}">
							${adminOrderVO.mem_name}(${adminOrderVO.mem_id})
						</c:if>
					</td>
					<td>
						<c:if test="${adminOrderVO.order_method == 0}">카드</c:if>
						<c:if test="${adminOrderVO.order_method == 1}">현금</c:if>
					</td>
					<td>${adminOrderVO.order_pay}</td>
					<td>${adminOrderVO.d_status_name}</td>
					<td>
						<input type="button" value="상세" class="btn btn-secondary btn-sm" onclick="location.href='orderDetail.do?order_no=${adminOrderVO.order_no}'">
						<input type="button" value="취소" class="btn btn-secondary btn-sm" onclick="location.href='orderCancel.do?order_no=${adminOrderVO.order_no}'"
							<c:if test="${mem_auth != 4}">disabled</c:if>
						>
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