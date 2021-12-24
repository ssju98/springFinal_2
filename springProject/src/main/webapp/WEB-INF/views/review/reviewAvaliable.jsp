<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reviewList.css"> 
<script type="text/javascript">
	 $(document).ready(function(){
		// 리뷰작성 버튼 클릭(폼 호출)
			$("button[id^='createBtn']").on('click',function(e){
				var p_no = $(this).attr('value1');
				var order_no = $(this).attr('value2');
				var data = {p_no : p_no};
				$.ajax({
		 			url:'writeForm.do',
		 			type:'GET',
		 			data:data,
		 			dataType:'json',
		 			catch:false,
		 			success:function(data){
		 				$("#modal-title").text("후기 작성");
		 				$("#p_name").text(data.p_name);
						$("#p_image").attr("src","${pageContext.request.contextPath}/product/photoView.do?p_no="+data.p_no);
						$('#p_no').val(data.p_no);
						$('#order_no').val(order_no);
		 				$("#myModal").modal({backdrop:'static',keyboard:false});
		 			},
		 			error:function(){
		 				alert("네트워크 오류");
		 			}		
		 		});
			});
	 });
</script>
<div class="top_menu_info">
	<div>
	홈 > 작성 가능한 후기
	</div>
</div>
<div id="main-width">
	<div id="categorySide">
		<div id="showmenu">나의 쇼핑</div>
		<div class="menu mt-2">
			<ul>
				<li class="order-a"><a href="${pageContext.request.contextPath}/shop/orderList.do">주문 &#183; 배송</a></li>
				<li class="order-a"><a href="${pageContext.request.contextPath}/shop/orderCancel.do">주문취소</a></li>
				<li class="order-a"><a href="${pageContext.request.contextPath}/shop/orderExchange.do">교환하기</a></li>
				<li class="order-a"><a href="${pageContext.request.contextPath}/shop/orderRefund.do">반품하기</a></li>
				<li class="order-a"><a href="${pageContext.request.contextPath}/shop/orderConfirm.do">구매확정</a></li>
			</ul>
		</div>
		<div id="showmenu">구매후기</div>
		<div class="menu mt-2">
			<ul>
				<li class="order-current"><a href="${pageContext.request.contextPath}/review/reviewAvaliable.do">작성 가능한 후기</a></li>
				<li class="order-a"><a href="${pageContext.request.contextPath}/review/reviewWritten.do">이미 작성한 후기</a></li>
			</ul>
		</div>
	</div>
	<div class="top-small-menu">
		<div class="top-small-menu-title mt-3">
			작성 가능한 후기
		</div>
		<c:if test="${count == 0}">
			<div class="review-main">
				<div class="review-container">
					<div class="review-empty-text">
						작성할 수 있는 후기가 없습니다.
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${count > 0}">
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
						<th>후기 작성</th>
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
								<div class="table-product-name-img" onclick="location.href='${pageContext.request.contextPath}/shop/productDetail.do?p_no=${list.p_no}'">
									<img src="${pageContext.request.contextPath}/product/photoView.do?p_no=${list.p_no}" width="100" height="100">
								</div>
								<div class="table-product-name">
									${list.p_name}
								</div>
								<div  style="float: left;width:340px; padding-left: 15px; font-weight: bold;">
									<fmt:formatNumber value="${list.p_price}" pattern="#,###"/>원
								</div>
							</td>
							<td>
								<div class="order-state-text">
									<jsp:include page="writeModal.jsp"></jsp:include>
									<button class="btn btn-outline-info btn-sm" id="createBtn" value1="${list.p_no}" value2="${list.order_no}" 
									data-toggle="modal">작성하기</button>
								</div>
							</td>
						</tr>	
					</tbody>
				</c:forEach>
			</table>
			<div align="center" style="margin-bottom: 100px;">${pagingHtml}</div>
		</c:if>
	</div>
</div>