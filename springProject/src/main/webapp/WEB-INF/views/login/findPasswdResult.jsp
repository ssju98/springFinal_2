<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<style type="text/css">
.top_menu_info{
	width:100%; 
	height:35px; 
	background: #f4f4f5;
	border-bottom: 1px solid #ebebeb;
	border-top:1px solid #ebebeb;
}

.top_menu_info > div {
	width:1200px; 
	line-height: 35px;  
	margin:0 auto; 
	font-size: 13px;
}

.order-main-div{
width:60%;
margin:0 auto;
}
.order-main{
	width:60%;
	border: 1px solid #f1f1f1;
	margin-left:17%;
}

.order-container{
	margin : 0 auto;
	text-align: center; 
	height:auto;
}

</style>
<div class="top_menu_info">
	<div>
	홈 > 비밀번호 찾기
	</div>
</div>
<div class="order-main-div">
<div class="order-main mt-5">
	<div class="order-container mt-5 mb-5">
		<div class="pb-4"style="font-size: 17px;">
			입력하신 이메일 계정으로 임시 비밀번호가 발송되었습니다.<br>
			이메일을 확인해주세요.
		</div>
		<div class="pb-5">
			<button class="btn btn-outline-primary shop-btn"  onclick="location.href='findIdForm.do'">아이디 찾기</button>
			<button class="btn btn-primary orderlist-btn" onclick="location.href='loginForm.do'">로그인 하기</button>
		</div>
	</div>
</div>
</div>
</div>
