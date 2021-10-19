<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/adminPage.css">
<!-- BootStrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css">
<script type="text/javascript">
	window.onload = function(){
		var form = document.getElementById('search_form');
		form.onsubmit = function(){
			var keyfield = document.getElementById('keyfield');
			var keyword = document.getElementById('keyword');
			//회원번호 검색시 숫자 체크
			if(keyfield.value == 'mem_num' && isNaN(keyword.value)){
				keyword.focus();
				keyword.value='';
				alert('회원번호는 숫자만 입력하세요.');
				return false;
			}
		};
	};
</script>
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<div id="wide-width" class="wide-table">
		<h4 id="header-main">회원 목록</h4>
		<!-- 회원 검색 조건 -->
		<form action="memberList.do" method="get" id="search_form">
			<table class="table table-bordered table-sm">
				<tr>
					<th>회원유형</th>
					<td>
						<div class="form-check-inline">
							<input type="radio" name="auth_num" value="" checked="checked" class="form-check-input">전체
							<input type="radio" name="auth_num" value="2" class="form-check-input ml-3">일반
							<input type="radio" name="auth_num" value="1" class="form-check-input ml-3">정지
							<input type="radio" name="auth_num" value="0" class="form-check-input ml-3">탈퇴
						</div>
					</td>
				</tr>
				<tr>
					<th>검색조건</th>
					<td>
						<div class="form-inline">
							<select name="keyfield" id="keyfield" class="form-control form-control-sm">
								<option value="mem_num">회원번호</option>
								<option value="mem_id">아이디</option>
								<option value="mem_phone">연락처</option>
							</select>
							<input type="search" name="keyword" id="keyword" placeholder="검색어 입력" class="form-control form-control-sm ml-1">
							<input type="submit" value="검색" class="btn btn-dark btn-sm">
						</div>
					</td>
				</tr>
			</table>
		</form>
		<!-- 회원 검색 결과 -->
		<c:if test="${count == 0}">
			회원 정보가 없습니다.
		</c:if>
		<c:if test="${count > 0}">
		<table class="table table-hover table-bordered table-sm">
			<thead>
				<tr>
					<th class="c-num">번호</th>
					<th class="c-auth">회원유형</th>
					<th class="c-id">아이디</th>
					<th class="c-name">이름</th>
					<th class="c-phone">연락처</th>
					<th class="c-email">이메일</th>
					<th class="c-manage">관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="adminMemberVO" items="${list}">
				<tr>
					<td>${adminMemberVO.mem_num}</td>
					<td>
						<c:if test="${adminMemberVO.mem_auth == 0}"><span class="text-danger">탈퇴회원</span></c:if>
						<c:if test="${adminMemberVO.mem_auth == 1}">정지회원</c:if>
						<c:if test="${adminMemberVO.mem_auth == 2}">일반회원</c:if>
					</td>
					<td>${adminMemberVO.mem_id}</td>
					<td>${adminMemberVO.mem_name}</td>
					<td>${adminMemberVO.mem_phone}</td>
					<td>${adminMemberVO.mem_email}</td>
					<td>
						<button class="btn btn-light btn-sm" onclick="location.href='memberDetail.do?mem_num=${adminMemberVO.mem_num}'">
							<i class="bi bi-info-circle mr-1"></i>상세</button>
						<c:if test="${adminMemberVO.mem_auth != 0}">
							<button class="btn btn-light btn-sm" onclick="location.href='memberUpdate.do?mem_num=${adminMemberVO.mem_num}'">
								<i class="bi bi-pencil-square mr-1"></i>수정
							</button>
							<button class="btn btn-light btn-sm" onclick="location.href='memberDelete.do?mem_num=${adminMemberVO.mem_num}'"  
								<c:if test="${mem_auth != 4}">disabled</c:if>
							><i class="bi bi-trash-fill mr-1"></i>삭제</button>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="text-center mt-4">${pagingHtml}</div>
		</c:if>
	</div>
</div>
<!-- 중앙 내용 끝 -->