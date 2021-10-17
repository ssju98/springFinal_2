<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<script type="text/javascript">
	window.onload = function(){
		var form = document.getElementById('tracking_form');
		form.onsubmit = function(){
			var tracking_num = document.getElementById('tracking_num');
			//송장번호 유효성 체크
			if(tracking_num.value.trim() == ''){
				tracking_num.focus();
				tracking_num.value='';
				alert('송장번호를 입력해주세요.');
				return false;
			}
			if(isNaN(tracking_num.value.trim())){
				tracking_num.focus();
				alert('송장번호는 숫자만 입력해주세요.');
				return false;
			}
			if(tracking_num.value.trim().length != 13){
				tracking_num.focus();
				alert('송장번호 13자리를 입력해주세요.');
				return false;
			}
			var check = confirm('[송장번호 : ' + tracking_num.value + ']\n배송상태가 [배송중]으로 변경됩니다.');
			return check;
		};
	};
</script>
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3 id="header-3">송장 등록</h3>
	<!-- 주문/배송 정보 -->
	<div id="normal-width">
		<form:form action="deliveryTrack.do" modelAttribute="deliveryVO" id="tracking_form" class="form">
			<form:hidden path="delivery_no" />
			<table class="table table-bordered cell-2">
				<tr>
					<th>주문번호</th>
					<td>${adminOrderVO.order_no}</td>
				</tr>
				<tr>
					<th>주문일</th>
					<td>${adminOrderVO.order_date}</td>
				</tr>
				<tr>
					<th>받는사람</th>
					<td>${adminOrderVO.order_name}</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>${adminOrderVO.order_phone}</td>
				</tr>
				<tr>
					<th>배송지</th>
					<td>(${adminOrderVO.order_zipcode}) ${adminOrderVO.order_address1} ${adminOrderVO.order_address2}</td>
				</tr>
				<tr>
					<th>배송상태</th>
					<td>${deliveryVO.d_status_name}</td>
				</tr>
				<tr>
					<th>배송업체</th>
					<td>${deliveryVO.dcompany_name}</td>
				</tr>
				<tr>
					<th>송장번호</th>
					<td>
						<form:input path="tracking_num" placeholder="-를 제외한 숫자 13자리 입력" class="form-control form-control-sm"/>
					</td>
				</tr>
			</table>
			<div class="element-center text-center">
				<button class="btn btn-dark">등록</button>
				<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='deliveryList.do'">
			</div>
		</form:form>
	</div>
</div>
<!-- 중앙 내용 끝 -->