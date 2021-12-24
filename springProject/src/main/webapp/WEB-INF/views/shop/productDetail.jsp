<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/productDetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-stars.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.barrating.min.js"></script>
 <script>
 	$(document).ready(function(){
		//별점 js
		$(function() {
	      $("select[name=review_rating2]").barrating({
	        theme: 'fontawesome-stars',
	        readonly:true
	      });   
	   });
 		//select박스 이벤트
 		$('#select_amount').on('change',function(){
 			var amount = $('#select_amount option:selected').val();
 			var discount = ${product.p_discount};
 			if(discount==0){
 				pay = ${product.p_price};
 			}else{
 				pay=${product.p_price-(product.p_price*product.p_discount/100)};
 			}
 			var price = amount * pay;
 			var money = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원';
 	 		$('#proudct-option-price').text(money);
 	 		$('#product-detail-pay-text2').text(money);
 		});
 		
 		//바로구매 버튼 클릭
 		var mem_num = '<%=session.getAttribute("mem_num")%>';
 		$('.order-btn').click(function(){
 			if(mem_num == null){
 				alert('로그인후 이용해주세요!');
 				return;
 			}else{
 				var cart_amount = $('#select_amount option:selected').val();
 	 			var p_no = ${product.p_no};
 	 			location.href="${pageContext.request.contextPath}/shop/orderNow.do?p_no="+p_no+"&cart_amount="+cart_amount;
 			}
 			
 			
 		});
 		
 		//장바구니 버튼 클릭
 		$('.cart-btn').click(function(){
 			 var p_no = ${product.p_no};
 			var cart_amount = $('#select_amount option:selected').val();
 			var data= {
 					p_no : p_no,
 					cart_amount : cart_amount
 					};
 			
 			$.ajax({
 				url: '${pageContext.request.contextPath}/shop/insertCart.do',
 				type:'post',
 				data : data,
 				dataType:'json',
 				catch:false,
 				success:function(param){
 					if(param.result == 'logout'){
 						alert('로그인 후 사용하세요');
 						$('#select_amount').val('1');
 					}else if(param.result=='numExcess'){	
 						alert('1인당 10개까지 구매 가능합니다.');
 						$('#select_amount').val('1');
 					}else if(param.result=='successDuplication'){ 
 						alert('상품이 장바구니에 담겼습니다.');
 						$('#select_amount').val('1');
 					}
 					else if(param.result == 'success'){
 						alert('상품이 장바구니에 담겼습니다.');
 						$('#select_amount').val('1');
 					}
 				},
 				error: function(){
 					alert("네트워크 오류");
 				}	
 			}); 	
 		}); 	
 		//리뷰 평점 출력
 		 if('${product.p_review_rating!=0}'){
 		var ratings = {RatingScore: '${product.p_review_rating}'}
		 	totalRating = 5;
		 	table = document.querySelector('.RatingStar');
		 	function rateIt() {
		 		for (rating in ratings) {
		 			ratingPercentage = ratings[rating] / totalRating * 100;
		 			ratingRounded = Math.round(ratingPercentage / 10) * 10 + '%';
		 			star = table.querySelector('${ratings} .inner-star'); 
		 			numberRating = table.querySelector('${ratings} .numberRating');
		 			star.style.width = ratingRounded;
		 			}
		 		}
		 		rateIt();
 		}  
 		
 	});
 </script>
<div class="top_menu_info">
	<div>
	${category_top_name.c_top_name} > ${category_sub_name.c_sub_name} > ${product.p_name}
	</div>
</div>
<div id="main-width" class="mt-3">
	<div class="main-width-container">
	<div class="product-detail-sumnail">
		<img src="${pageContext.request.contextPath}/product/photoView.do?p_no=${product.p_no}" width="520" height="520">
	</div>
	<div class="product-detail-info ml-5 mt-5">
		<div class="product-detail-name" >
			${product.p_name}
		</div>
		<c:if test="${product.p_review_count != 0}">
			<div class="product-detail-rating mt-1">
				<div class='RatingStar'>
  					<div class='RatingScore'>
   						 <div id='inner-star' class='outer-star'><div id='inner-star' class='inner-star'></div></div>
  					</div>
				</div>
				<div class="product-detail-review-count ml-2 mb-1">
					${product.p_review_count}개의 리뷰
				</div>
			</div>
		</c:if>
		
		<c:if test="${product.p_discount != 0}">
			<div class="product-detail-sale">
				${product.p_discount}%
			</div>
			<div class="product-detail-netprice ml-2">
			<fmt:formatNumber value="${product.p_price}" pattern="#,###"/>원	
			</div>
		</c:if>
		<div class="product-detail-saleprice mt-2 mb-2">
			<c:if test="${product.p_discount != 0}">
				<fmt:formatNumber value="${product.p_price-(product.p_price*product.p_discount/100)}" pattern="#,###"/>원		
			</c:if>
			<c:if test="${product.p_discount == 0}">
				<fmt:formatNumber value="${product.p_price }" pattern="#,###"/>원
			</c:if>
		</div>
		<div class="product-detail-delivery">
			배송
		</div>
		<div class="product-detail-delivery-nomal ml-4">
			일반배송
		</div>
		<div class="product-detail-delivery-sub mb-4">
			5만원 이상 구매시 무료배송
		</div>
		<c:if test="${product.p_amount != 0}">	
			<div class="product-option-container p-2">
				<div class="product-option-amount">
					수량
				</div>
				<select id="select_amount" name="select_amount" class="form-select product-option-select mt-3" >
					<c:if test="${product.p_amount != 0 && product.p_amount >= 10}">	
						<c:forEach var="i" begin="1" end="10">
							<option value="${i}"
								<c:if test="${i==1}">
									selected="selected"</c:if>>
									${i}
			       			</option>
						</c:forEach>
					</c:if>
					<c:if test="${product.p_amount != 0 && product.p_amount < 10}">	
						<c:forEach var="i" begin="1" end="${product.p_amount}">
							<option value="${i}"
								<c:if test="${i==1}">
									selected="selected"</c:if>>
									${i}
			       			</option>
						</c:forEach>
					</c:if>
				</select>
				<div id="proudct-option-price" class="proudct-option-price mt-3" >
					<c:if test="${product.p_discount != 0}">
						<fmt:formatNumber value="${product.p_price-(product.p_price*product.p_discount/100)}" pattern="#,###"/>원		
					</c:if>
					<c:if test="${product.p_discount == 0}">
						<fmt:formatNumber value="${product.p_price }" pattern="#,###"/>원
					</c:if>
				</div>
			</div>
		</c:if>
			<c:if test="${product.p_amount == 0}">	
			<div class="product-option-container p-2"  style="opacity: 0.4;">
				<div class="product-option-amount">
					수량
				</div>
				<select id="select_amount" class="form-select product-option-select mt-3" >
						<option value="0" selected="selected" disabled="disabled">0</option>
				</select>
				<div id="proudct-option-price" class="proudct-option-price mt-3" >
					<c:if test="${product.p_discount != 0}">
						<fmt:formatNumber value="${product.p_price-(product.p_price*product.p_discount/100)}" pattern="#,###"/>원		
					</c:if>
					<c:if test="${product.p_discount == 0}">
						<fmt:formatNumber value="${product.p_price }" pattern="#,###"/>원
					</c:if>
				</div>
			</div>
		</c:if>
		<div class="product-detail-pay-container">
			<div class="product-detail-pay-text mt-4">
				주문금액
			</div>
			<div id="product-detail-pay-text2" class="product-detail-pay-text2 mt-3">
				<c:if test="${product.p_amount != 0}">
					<c:if test="${product.p_discount != 0}">
						<fmt:formatNumber value="${product.p_price-(product.p_price*product.p_discount/100)}" pattern="#,###"/>원		
					</c:if>
					<c:if test="${product.p_discount == 0}">
						<fmt:formatNumber value="${product.p_price }" pattern="#,###"/>원
					</c:if>
				</c:if>
				<c:if test="${product.p_amount == 0}">
					0원
				</c:if>
			</div>
		</div>
		<c:if test="${product.p_amount != 0}">	
			<button class="btn btn-outline-primary cart-btn">
				장바구니
			</button>
			<button class="btn btn-primary order-btn">
				바로구매
			</button>
		</c:if>
		<c:if test="${product.p_amount == 0}">	
			<button class="btn btn-secondary soldout-btn" disabled="disabled">
				품절
			</button>
		</c:if>
	</div> 
	</div>
	<br>
	<div style="clear:both;" >
	<br>
		<div id="middle-menu">
			<ul>
				<li><a href="#info">상품상세정보</a><a id="info"></a></li>
				<li><a href="#review">사용후기</a></li>
				<li><a href="#qna">상품문의</a></li>
				
			</ul>
		</div>
		<div class="product_sub_info">
		${product.p_sub_text}
		</div>
		<div id="middle-menu">
			<ul>
				<li><a href="#info">상품상세정보</a></li>
				<li><a href="#review">사용후기</a><a id="review"></a></li>
				<li><a href="#qna">상품문의</a></li>
			</ul>
		</div>
		<div class="review-list-detail-div">상품사용후기</div>
		<c:if test="${product.p_review_count != 0}">
			<c:forEach var="list" items="${review}">
				<div class="review-list-div">
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
					${list.mem_name}님 |
					<fmt:formatDate value="${list.regdate}" pattern="yyyy.MM.dd."/>
				</div>
				<div class="review-list-content-div mt-1">
					<div class="review-list-content">
						${list.review_content}
					</div>
					<c:if test="${!empty list.review_image_name}">
						<div class="review-list-img mr-5">
							<img src="${pageContext.request.contextPath}/review/imageView.do?review_no=${list.review_no}" width="110" height="110">
						</div>	
					</c:if>
				</div>				
			</div>
		</div>
			</c:forEach>
		</c:if>
		
		
		
		<c:if test="${product.p_review_count == 0}">
			<div class="qna-empty-container">
				<div class="qna-empty">
					작성된 사용후기가 없습니다.
				</div>
			</div>
		</c:if>
		<c:if test="${product.p_review_count != 0}">
		
		</c:if>
		
		<div id="middle-menu">
			<ul>
				<li><a href="#info">상품상세정보</a></li>
				<li><a href="#review">사용후기</a></li>
				<li><a href="#qna">상품문의</a><a id="qna"></a></li>
			</ul>
		</div>
		<div>
		<div class="qna-list-div">상품문의</div>
		<div class="qna-list-container">
			<button class="btn btn-dark btn-xs" onclick="location.href='${pageContext.request.contextPath}/qna/qnaProductWrite.do?p_no=${product.p_no}'">작성하기</button>
		</div>
		<c:if test="${count == 0}">
			<div class="qna-empty-container">
				<div class="qna-empty">
					작성된 문의가 없습니다.
				</div>
			</div>
		</c:if>
		<c:if test="${count != 0}">
				<table class="order-table" border="1">
			<colgroup>
				<col width="150px">
				<col width="700px">
				<col width="150px">
				<col width="187px">
			</colgroup>
			<thead>
				<tr>
					<th>게시물 번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<c:forEach var="list" items="${list}">
				<tbody>
					<tr>	
						<td class="table-text">${list.board_no}</td>
						<td class="table-product">
							<div class="table-product-name">
								<c:if test="${list.level > 1}">
									<c:forEach begin="1" end="${list.level}">
										&nbsp;
									</c:forEach>
									<a href="${pageContext.request.contextPath}/qna/qnaDetail.do?board_no=${list.board_no}">RE : </a> 
								</c:if>
								<a href="${pageContext.request.contextPath}/qna/qnaDetail.do?board_no=${list.board_no}">${list.board_title}</a>
							</div>
						</td>
						<td class="table-text">
							${list.mem_id}
						</td>
						<td class="table-text">
							<fmt:formatDate value="${list.board_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>	
				</tbody>
			</c:forEach>
		</table>
		<div align="center">${pagingHtml}</div>
		</c:if>
	
		</div>
	</div>

	
</div>