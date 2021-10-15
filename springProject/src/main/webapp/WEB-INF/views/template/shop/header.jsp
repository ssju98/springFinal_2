<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 상단 시작 -->
<div id="header" class="mb-3">
	<a href="${pageContext.request.contextPath}/shop/main.do">
		<img src="${pageContext.request.contextPath}/resources/images/logo.png" >
	</a>
	<div class="searchbar">
		<input type="text">
		<button><img src="${pageContext.request.contextPath}/resources/images/search.png" height="24" style="padding-left: 14px;"></button>
	</div>
	<div class="header-menu">
		<ul>
		<c:if test="${empty mem_num}">
			<li class="noLogin"><a href="${pageContext.request.contextPath}/login/loginForm.do">로그인</a></li>
			<li class="noLogin"><a href="#">회원가입</a></li>
		</c:if>
		<c:if test="${!empty mem_num && mem_auth==2}">
			<li><a href="${pageContext.request.contextPath}/shop/cart.do">장바구니</a></li>
			<li><a href="#">주문조회</a></li>
			<li><a href="#">마이페이지</a></li>
		</c:if>
		<c:if test="${!empty mem_num && (mem_auth==3 || mem_auth==4) }">
			<li><a href="#">마이페이지</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/main.do">관리자페이지로</a></li>
		</c:if>
		<c:if test="${!empty mem_num}">
			<li><a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a></li>
		</c:if>
		</ul> 
	</div>
</div>
<div id="menu">
	<ul class="main1">
		<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/menu.png" height="16" width="auto">카테고리</a>
			<ul class="main2">
				<c:forEach var="top" items="${category_top}">
					<li><a href="#">${top.c_top_name}</a>
						<ul class="main3">
						<c:forEach var="sub" items="${category_sub}">
							<c:if test="${sub.c_top_no == top.c_top_no}">
							<li><a href="${pageContext.request.contextPath}/shop/productList.do?c_top_no=${sub.c_top_no}&c_sub_no=${sub.c_sub_no}">${sub.c_sub_name}</a></li>
							</c:if>
						</c:forEach>
						</ul>
					</li>
					</c:forEach>
			</ul>
		</li>
		<li><a href="#" style="padding-left: 30px;">글쓰기</a>
            <ul class="main2">
				<li><a href="#">상품후기작성</a>
				<li><a href="#">문의남기기</a>
			</li>
			</ul>

	</ul>
</div>
<!-- 상단 끝 -->