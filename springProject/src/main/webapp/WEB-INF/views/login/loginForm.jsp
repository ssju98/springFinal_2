<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 메인 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/template/login_template.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css">
<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.css"></script>

<style type="text/css">
#menu ul li a{
	color:black;
}

.header-menu ul li a{
	color:black;
}

.form__container{
	display:flex;
	justify-content:center;
	width:100%;
	
}

.login-form{
width:300px;
margin-top:80px;
}
</style>

<form class="form__container" action="${pageContext.request.contextPath}/login/loginAction.do" method="post">
<div class="login-form">
  <div class="mb-3">
  	<h1>로그인</h1>
    <label for="exampleInputEmail1" class="form-label">아이디</label>
    <input type="text" class="form-control" name="mem_id" id="mem_id">
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">비밀번호</label>
    <input type="password" class="form-control" name="mem_passwd" id="mem_passwd">
  </div>
<!--   		<div class="caption"> -->
<!-- 			<a href="findIdForm.do">아이디 찾기</a> |  -->
<!-- 			<a href="findPasswdForm.do">비밀번호 찾기</a> -->
<!-- 		</div> -->
  
  <button type="submit" class="btn btn-primary">로그인</button>
</div>
</form>
<!-- 메인 끝 -->