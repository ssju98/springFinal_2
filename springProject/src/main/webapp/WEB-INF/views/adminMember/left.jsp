<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 왼쪽 메뉴 시작 -->    
<div id="sidebar">
	<ul class="menu">
		<li><a href="${pageContext.request.contextPath}/adminMember/adminMemberList.do">회원목록</a></li>
		<li><a href="${pageContext.request.contextPath}/adminMember/adminMemberDetail.do">회원상세</a></li>
		<li><a href="${pageContext.request.contextPath}/adminMember/adminMemberUpdate.do">회원수정</a></li>
		<li><a href="${pageContext.request.contextPath}/adminMember/adminMemberDelete.do">회원삭제</a></li>
	</ul>
</div>
<!-- 왼쪽 메뉴 끝 -->    