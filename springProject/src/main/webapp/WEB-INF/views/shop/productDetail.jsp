<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/productDetail.css">
 <script>
 	$(document).ready(function(){
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
 		
 	});
 </script>
<div class="top_menu_info">
	<div>
	</div>
</div>
<div id="main-width" class="mt-3">
	<div class="main-width-container">
	<div class="product-detail-sumnail">
		<img src="photoView.do?p_no=${product.p_no}" width="520" height="520">
	</div>
	<div class="product-detail-info ml-5 mt-4">
		<div class="product-detail-name" >
			${product.p_name}
		</div>
		<div class="product-detail-rating mt-2">
			<img src="${pageContext.request.contextPath}/resources/images/star_detail.png">
			<div class="product-detail-review-count ml-1 mb-1">
				30개 리뷰
			</div>
		</div>
		<c:if test="${product.p_discount != 0}">
			<div class="product-detail-sale">
				${product.p_discount}%
			</div>
			<div class="product-detail-netprice ml-2">
				${product.p_price}원
			</div>
		</c:if>
		<div class="product-detail-saleprice mt-3 mb-3">
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
				<select id="select_amount" class="form-select product-option-select mt-3" >
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
	
</div>