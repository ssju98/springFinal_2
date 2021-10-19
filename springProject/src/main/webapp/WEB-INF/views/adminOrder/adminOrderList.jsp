<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminPage.css">
<!-- BootStrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css">
<script type="text/javascript">
	window.onload = function(){
		//날짜 포맷 변환
		function dateConvert(period){
			var today = new Date();
			today.setDate(today.getDate() - period);
			var year = today.getFullYear();
			var month = ('0' + (today.getMonth() + 1)).slice(-2);
			var day = ('0' + today.getDate()).slice(-2);
			var dateStr = year + '-' + month  + '-' + day;
			return dateStr;
		}
		
		//날짜 input
		var start = document.getElementById('start_date');
		var end = document.getElementById('end_date');
		
		//오늘 버튼
		var day = document.getElementById('btn_day');
		day.onclick = function(){
			start.value = dateConvert(0);
			end.value = dateConvert(0);
		};
		//7일 버튼
		var week = document.getElementById('btn_week');
		week.onclick = function(){
			start.value = dateConvert(6);
			end.value = dateConvert(0);	
		};
		//30일 버튼
		var month = document.getElementById('btn_month');
		month.onclick = function(){
			start.value = dateConvert(29);
			end.value = dateConvert(0);
		};
		
		var form = document.getElementById('search_form');
		form.onsubmit = function(){
			if(start.value.trim() != null && end.value.trim() == null){
				end.focus();
				end.value='';
				alert('주문 기간의 종료일을 입력하세요.');
				return false;
			}
		};
	}
</script>
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<div id="wide-width" class="wide-table">
		<h4 id="header-main">주문 목록</h4>
		<!-- 주문 검색 조건 -->
		<form action="orderList.do" method="get" id="search_form">
			<table class="table table-bordered table-sm">
				<tr>
					<th>주문기간</th>
					<td>
						<span id="search-period">
							<input type="button" value="오늘" id="btn_day" class="btn btn-sm">
							<input type="button" value=" 7일" id="btn_week"class="btn btn-sm">
							<input type="button" value="30일" id="btn_month" class="btn btn-sm">
						</span>
						<input type="date" name="start_date" id="start_date" class="form-control-sm ml-2"> ~
						<input type="date" name="end_date" id="end_date" class="form-control-sm">
					</td>
				</tr>
				<tr>
					<th>처리상태</th>
					<td>
						<div class="form-check-inline">
						<input type="radio" name="d_status_num" value="" checked="checked" class="form-check-input">전체
						<input type="radio" name="d_status_num" value="0" class="form-check-input ml-3">결제완료
						<input type="radio" name="d_status_num" value="1" class="form-check-input ml-3">배송준비중
						<input type="radio" name="d_status_num" value="2" class="form-check-input ml-3">배송중
						<input type="radio" name="d_status_num" value="3" class="form-check-input ml-3">배송완료
						<input type="radio" name="d_status_num" value="4" class="form-check-input ml-3">구매확정
						<input type="radio" name="d_status_num" value="5" class="form-check-input ml-3">반품
						<input type="radio" name="d_status_num" value="6" class="form-check-input ml-3">교환
						<input type="radio" name="d_status_num" value="7" class="form-check-input ml-3">주문취소
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
							<input type="submit" value="검색" class="btn btn-dark btn-sm">
						</div>
					</td>
				</tr>
			</table>
		</form>
		<!-- 주문 검색 결과 -->
		<c:if test="${count == 0}">
			주문 정보가 없습니다.
		</c:if>
		<c:if test="${count > 0}">
		<table class="table table-hover table-bordered table-sm">
			<thead>
				<tr>
					<th class="c-date">주문일</th>
					<th class="c-onum">주문번호</th>
					<th class="c-id">주문아이디</th>
					<th class="c-method">결제수단</th>
					<th class="c-pay">결제금액</th>
					<th class="c-satus">처리상태</th>
					<th class="c-manage">관리</th>
				</tr>
			<thead>
			<tbody>
				<c:forEach var="adminOrder" items="${list}">
				<tr>
					<td>${adminOrder.order_date}</td>
					<td>${adminOrder.order_no}</td>
					<td>
							${adminOrder.mem_id}
					</td>
					<td>
						<c:if test="${adminOrder.order_method == 0}">카드</c:if>
						<c:if test="${adminOrder.order_method == 1}">현금</c:if>
					</td>
					<td><fmt:formatNumber value="${adminOrder.order_pay}" pattern="#,###"/>원</td>
					<td>${adminOrder.d_status_name}</td>
					<td>
						<button class="btn btn-light btn-sm" class="btn btn-light btn-sm" onclick="location.href='orderDetail.do?order_no=${adminOrder.order_no}'">
							<i class="bi bi-info-circle mr-1"></i>상세</button>
						<c:if test="${adminOrder.d_status_name == '결제완료' || adminOrder.d_status_name == '배송준비중'}">
						<button class="btn btn-light btn-sm" onclick="location.href='orderCancel.do?order_no=${adminOrder.order_no}'"
							<c:if test="${mem_auth != 4}">disabled</c:if>
						><i class="bi bi-x-circle-fill mr-1"></i>취소</button>			
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