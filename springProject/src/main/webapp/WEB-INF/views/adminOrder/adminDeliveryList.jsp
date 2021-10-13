<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3>배송 목록</h3>
	<!-- 배송 조회 조건 -->
	<div id="normal-width">
		<form>
			<table class="table table-borderless table-sm">
				<tr>
					<th>처리상태</th>
					<td>
						<input type="radio" name="d_status_num" value="" checked="checked">전체 
						<input type="radio" name="d_status_num" value="1">배송준비 
						<input type="radio" name="d_status_num" value="2">배송중 
						<input type="radio" name="d_status_num" value="3">배송완료
					</td>
				</tr>
				<tr>
					<th>조회조건</th>
					<td>
						<select name="search_field">
							<option value="order_no">주문번호</option>
							<option value="mem_num">회원번호</option>
							<option value="tracking_num">송장번호</option>
						</select>
						<input type="text" name="search_keyword">
						<input type="submit" value="조회" class="btn btn-dark btn-sm">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 배송 조회 결과 -->
	<div>
		<table class="table table-hover table-bordered table-sm">
			<thead class="table-secondary">
				<tr>
					<th>주문일</th>
					<th>주문번호</th>
					<th>주문자</th>
					<th>상품명</th>
					<th>배송상태</th>
					<th>배송시작일</th>
					<th>송장번호</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<!-- *****임시 데이터***** -->
				<tr>
					<td>{order_date}</td>
					<td>{order_no}</td>
					<td>{mem_name}</td>
					<td>{p_name} 외 {} 건</td>
					<td>{d_status_name}</td>
					<td>{delivery_date}</td>
					<td>{tracking_num}</td>
					<td>
						<input type="button" value="송장" class="btn btn-secondary btn-sm" onclick="location.href=''">
						<input type="button" value="완료" class="btn btn-secondary btn-sm" onclick="location.href=''">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<!-- 중앙 내용 끝 -->