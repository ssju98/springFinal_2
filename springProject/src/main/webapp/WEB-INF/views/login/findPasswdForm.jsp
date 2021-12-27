<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	color:#a1a1a5;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//이메일 정규식 : 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식
		var regExpEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	
		$('#findPasswdForm').submit(function(){
			if($('#mem_email').val().trim()==''){
				alert('이메일을 입력해주세요!');
				$('#mem_email').focus();
				$('#mem_email').val('');
				return false;
			}else if(!regExpEmail.test($('#mem_email').val().trim())){	//정규식을 이용한 이메일 유효성 검사
				alert('이메일 형식이 일치하지 않습니다.');
				return false;
			}
		});
	});
</script>
<title>비밀번호 찾기</title>
</head>
<body>
<div class="top_menu_info">
	<div>
	홈 > 비밀번호 찾기
	</div>
</div>
<div id="main-width" style="margin: 0 auto; padding-top: 6%;">
<h4 style="text-align: center;">비밀번호 찾기</h4>
<div class="mt-4" style="width: 1200px;" >
<form:form id="findPasswdForm" action="findPasswd.do" modelAttribute="memberVO" style="padding-left:35%;">
  <div class="form-group">
    <label class="control-label col-sm-6" for="mem_email">가입한 이메일 주소를 입력해주세요</label>
    <div class="col-sm-6 input-group input-group-lg">
    	<form:input path="mem_email" class="form-control" style="font-size:18px;"/>
    </div>
  </div>
  <div class="form-group ml-3 pt-1">
      <button type="submit" id="findIdBtn" name="findIdBtn" class="btn btn-info" style="width:47%; height:47px;">확인</button>
  </div>
</form:form>
</div>
</div>
</body>
</html>