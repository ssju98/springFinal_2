<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<div id="header">
	<a href="#">
	<img src="${pageContext.request.contextPath}/resources/images/logo.png" ></a>
	<div class="searchbar">
		<input type="text">
		<button><img src="${pageContext.request.contextPath}/resources/images/search.png" height="25" style="padding-left: 14px;"></button>
	</div>
	<div class="header-menu" class="mb-3">
		<ul>
		<c:if test="${!empty mem_num && (mem_auth==3 || mem_auth==4) }">
			<li><a href="${pageContext.request.contextPath}/admin/adminInfoForm.do">마이페이지</a></li>
			<li><a href="${pageContext.request.contextPath}/main/admin.do">관리자페이지로</a></li>
		</c:if>
		
		<c:if test="${!empty mem_num }">
			<li><a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a></li>
		
		</c:if> 
	
		</ul> 
	</div>
</div>