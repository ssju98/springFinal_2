<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<script type="text/javascript">
	window.onload = function(){
		var form = document.getElementById('delete_form');
		form.onsubmit = function(){
			var mem_id = document.getElementById('mem_id');
			var mem_passwd = document.getElementById('mem_passwd');
			//아이디, 비밀번호 유효성 체크
			if(mem_id.value.trim() == ''){
				mem_id.focus();
				mem_id.value='';
				alert('아이디를 입력하세요!');
				return false;
			}
			if(mem_passwd.value.trim() == ''){
				mem_passwd.focus();
				mem_passwd.value='';
				alert('비밀번호를 입력하세요!');
				return false;
			}
		};
	};
</script>
<!-- 중앙 내용 시작 -->
<div id="admin-main-width">
	<h3 id="header-3">회원 삭제</h3>
	<div id="narrow-width" class="element-center text-center">
		<h4>최고관리자 인증</h4>
		<hr class="sub-line">
		<form action="memberDelete.do" method="post" id="delete_form" class="form">
			<input type="hidden" name="manage_num" value="${mem_num}"/>
			<table class="table table-borderless table-sm" style="width: 80%; margin: 0 auto;">
				<tr class="sub-line">
					<th>아이디</th>
					<td>
						<input type="text" name="mem_id" id="mem_id" class="form-control form-control-sm">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="mem_passwd" id="mem_passwd" class="form-control form-control-sm">
					</td>
				</tr>
			</table>
			<div class="element-center">
				<input type="submit" value="인증" class="btn btn-dark">
				<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='memberList.do'">
			</div>
		</form>
	</div>
</div>
<!-- 중앙 내용 끝 -->