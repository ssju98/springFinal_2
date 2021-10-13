<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3>송장 입력</h3>
	<div id="normal-width">
		<form>
			<table class="table table-bordered">
				<!-- *****임시 데이터***** -->
				<tr>
					<th>주문번호</th>
					<td>{order_no}</td>
				</tr>
				<tr>
					<th>주문일</th>
					<td>{order_date}</td>
				</tr>
				<tr>
					<th>주문자</th>
					<td>{mem_name}</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>{mem_phone}</td>
				</tr>
				<tr>
					<th>처리상태</th>
					<td>{d_status_num}</td>
				</tr>
				<tr>
					<th>배송지</th>
					<td>{mem_zipcode} {mem_address1} {mem_address2}</td>
				</tr>
				<tr>
					<th>송장번호</th>
					<td>
						<input type="text" name="tracking_num">
					</td>
				</tr>
				<tr>
					<th>배송시작일</th>
					<td>{delivery_date}</td>
				</tr>
			</table>
			<div class="text-center">
				<input type="submit" value="저장" class="btn btn-dark">
				<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='deliveryList.do'">
			</div>
		</form>
	</div>
</div>
<!-- 중앙 내용 끝 -->