<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 상단 시작 -->
	<div class="admin-top-title">
		<div class="title1">
			내일의 집 관리자 페이지
		</div>
		<div class="title2">
			<ul>
			<c:if test="${!empty mem_num && (mem_auth==3 || mem_auth==4) }">
				<li>${member_id}님</li>
				<li><a href="#">쇼핑몰로 이동</a></li>
				<li><a href="#">로그아웃</a></li>
			</c:if>
			</ul>
		</div>
	</div>
	
	<div id="top-menu">
		<ul>
			<li><a href="#">메인</a></li>
			<li><a href="#">카테고리관리</a></li>
			<li><a href="#">상품관리</a></li>
			<li><a href="${pageContext.request.contextPath}/adminOrder/adminOrderList.do">주문관리</a></li>
			<li><a href="${pageContext.request.contextPath}/adminMember/adminMemberList.do">회원관리</a></li>
			<li><a href="#">매출관리</a></li>
			<c:if test="${!empty mem_num && mem_auth==4}">
				<li><a href="#">관리자관리</a></li>
			</c:if>
		</ul>
	</div>
<!-- 상단 끝 -->