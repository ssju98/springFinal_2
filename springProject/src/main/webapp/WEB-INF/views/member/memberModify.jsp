<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
h2{
text-align:center;
margin-top:120px;
}
.button{
display:flex;
justify-content:center;
width:100%;
}
.mid_form{
display:flex;
	justify-content:center;
	width:100%;
}
ul{
   list-style:none;
}
.top_menu_info{
	width:100%; 
	height:35px; 
	background: #f4f4f5;
	border-bottom: 1px solid #ebebeb;
	border-top:1px solid #ebebeb;
	color:#a1a1a5;
}

.top_menu_info > div {
	width:1200px; 
	line-height: 35px;  
	margin:0 auto; 
	font-size: 13px;
	color:#a1a1a5;
}

</style> 
<!-- 중앙 내용 시작 -->
<div class="top_menu_info">
	<div>
	홈 > 정보 수정 
	</div>
</div>
<div class="page-main">
	<h2>회원정보수정</h2><br><br>
	<form:form id="modify_form" action="update.do" modelAttribute="memberVO">
		<div class="mid_form">
		<ul>
			<li>
				<label for="mem_name">이름 :</label>
				<form:input path="mem_name" class="form-control"/>
				<form:errors path="mem_name" cssClass="error-color"/><br>
			</li>
			<li>
				<br><label for="mem_phone">전화번호 :</label>
				<form:input path="mem_phone" class="form-control"/>
				<form:errors path="mem_phone" cssClass="error-color"/><br>
			</li>
			<li>
				<br><label for="mem_email">이메일 :</label>
				<form:input path="mem_email" class="form-control"/>
				<form:errors path="mem_email" cssClass="error-color"/><br>
			</li>
		</ul>
		</div><br><br>
		<div class="button">
			<form:button class="btn btn-primary">수정하기</form:button>
		</div><br><br>
	</form:form>
</div>
<!-- 중앙 내용 끝 -->



