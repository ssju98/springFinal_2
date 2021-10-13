<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 상단 시작 -->
<div id="header" class="mb-3">
	<a href="#">
	<img src="${pageContext.request.contextPath}/resources/images/logo.png" ></a>
	<div class="searchbar">
		<input type="text">
		<button><img src="${pageContext.request.contextPath}/resources/images/search.png" height="24" style="padding-left: 14px;"></button>
	</div>
	<div class="header-menu">
		<ul>
		<c:if test="${empty mem_num}">
			<li class="noLogin"><a href="#">로그인</a></li>
			<li class="noLogin"><a href="#">회원가입</a></li>
		</c:if>
		<c:if test="${!empty mem_num && mem_auth==2}">
			<li><a href="#">장바구니</a></li>
			<li><a href="#">주문조회</a></li>
			<li><a href="#">마이페이지</a></li>
		</c:if>
		<c:if test="${!empty mem_num && (mem_auth==3 || mem_auth==4) }">
			<li><a href="#">마이페이지</a></li>
			<li><a href="#">관리자페이지로</a></li>
		</c:if>
		</ul> 
	</div>
</div>
<div id="menu">
	<ul class="main1">
		<li><a href="#"><img src="${pageContext.request.contextPath}/resources/images/menu.png" height="16" width="auto">카테고리</a>
			<ul class="main2">
				<li><a href="#">가구</a>
					<ul class="main3">
						<li><a href="#">소파/거실가구</a></li>
						<li><a href="#">침실가구</a></li>
						<li><a href="#">드레스룸</a></li>
						<li><a href="#">주방가구</a></li>
						<li><a href="#">학생/서재가구</a></li>
						<li><a href="#">수납가구</a></li>
					</ul>
				</li>
				<li><a href="#">패브릭</a>
					<ul class="main3">
						<li><a href="#">침구</a></li>
						<li><a href="#">커튼/블라인드</a></li>
						<li><a href="#">카페트/러그</a></li>
						<li><a href="#">쿠션/방석</a></li>
						<li><a href="#">홈패브릭</a></li>
					</ul>
				</li>
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