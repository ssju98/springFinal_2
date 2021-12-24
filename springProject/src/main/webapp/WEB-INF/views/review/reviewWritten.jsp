<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.barrating.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reviewList.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-stars.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<script type="text/javascript">
	$(document).ready(function(){
		// 삭제 버튼 클릭
		$("button[id^='deleteBtn']").on('click',function(e){	
			var choice = confirm('해당 후기를 삭제하시겠습니까?');
			if(choice){
				var review_no = $(this).attr('value');
				var data = {review_no : review_no};
				$.ajax({
					url:'delete.do',
		 			type:'POST',
		 			data:data,
		 			dataType:'json',
		 			catch:false,
		 			success:function(param){
		 				if(param.result == 'logout'){
		 					alert('로그인 후 사용하세요!');
		 				}else if(param.result == 'success'){
		 					location.reload(true);
		 				}
		 			},
		 			error:function(){
		 				alert('네트워크 오류');
		 			}
				});
			}else{
				return;
			}
		});
		
		//별점 js
		$(function() {
	      $("select[name=review_rating2]").barrating({
	        theme: 'fontawesome-stars',
	        readonly:true
	      });   
	   });
	});
</script>
<div class="top_menu_info">
	<div>
	홈 > 이미 작성한 후기
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
				<li class="order-a"><a href="${pageContext.request.contextPath}/review/reviewAvaliable.do">작성 가능한 후기</a></li>
				<li class="order-current"><a href="${pageContext.request.contextPath}/review/reviewWritten.do">이미 작성한 후기</a></li>
			</ul>
		</div>
	</div>
	<div class="top-small-menu">
		<div class="top-small-menu-title mt-3">
			이미 작성한 후기
		</div>
		<c:if test="${count == 0}">
			<div class="review-main">
				<div class="review-container">
					<div class="review-empty-text">
						작성한 후기가 없습니다.
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${count > 0}">
			<c:forEach var="list" items="${list}">
				<div class="review-list-div">
					<div class="review-list-product">
						<div class="review-list-product-img ml-3 mr-2">
							<img src="${pageContext.request.contextPath}/product/photoView.do?p_no=${list.p_no}" width="50" height="50">
						</div>
						<div class="review-list-product-name">
						${list.p_name}
						</div>
					</div>
				<div class="review-list-div2 mt-2 pl-3 mb-3">
					<div class="review-list-rating mr-1">
						<select id="review_rating2" name="review_rating2">
							<c:forEach var="i" begin="1" end="5">
								<option value="${i}"
									<c:if test="${i==list.review_rating}">
									selected="selected"</c:if>>
									${i}
				       			</option>
							</c:forEach>
						</select>
					</div>
					<div class="review-list-date">
						<fmt:formatDate value="${list.regdate}" pattern="yyyy.MM.dd."/>
					</div>
					<div class="review-list-content-div mt-1">
						<div class="review-list-content">
							${list.review_content}
						</div>
						<div class="reviw-list-btn-div mt-2">
							<jsp:include page="modifyModal.jsp"></jsp:include>
							<button class="btn btn-light btn-sm mb-2" id="modifyBtn" value="${list.review_no}"><i class="fas fa-edit"></i>수정</button>
							<button class="btn btn-light btn-sm" id="deleteBtn" value="${list.review_no}"><i class="fas fa-times mr-1"></i>삭제</button>
						</div>
						<c:if test="${!empty list.review_image_name}">
							<div class="review-list-img mr-5">
								<img src="${pageContext.request.contextPath}/review/imageView.do?review_no=${list.review_no}" width="90" height="90">
							</div>	
						</c:if>
					</div>				
				</div>
			</div>
			</c:forEach>
			<div align="center" style="margin-bottom: 100px;">${pagingHtml}</div>
		</c:if>
	</div>
</div>