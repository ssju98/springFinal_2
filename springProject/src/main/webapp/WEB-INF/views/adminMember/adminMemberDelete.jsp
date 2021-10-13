<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				alert('최고관리자 아이디를 입력하세요!');
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
	<h3>회원 삭제(최고관리자 인증)</h3>
	<div id="narrow-width">
		<form action="memberDelete.do" method="post" id="delete_form">
			<input type="hidden" name="manage_num" value="${adminMemberVO.manage_num}"/>
			<table class="table table-borderless table-sm">
				<tr>
					<td style="width:80px;">아이디</td>
					<td>
						<input type="text" name="mem_id" id="mem_id">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="mem_passwd" id="mem_passwd">
					</td>
				</tr>
			</table>
			<div class="text-center">
				<input type="submit" value="인증" class="btn btn-dark">
				<input type="button" value="취소" class="btn btn-secondary" onclick="location.href='memberList.do'">
			</div>
		</form>
	</div>
</div>
<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<!-- 중앙 내용 끝 -->