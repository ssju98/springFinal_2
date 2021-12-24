<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css">
<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.css"></script>
<style>
#adminModify{
	width:50%;
	margin:0 auto;
	padding: 85px 0;
}

table tr:last-child{
	display:none;
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
<script type="text/javascript">
$(document).ready(function(){
	$('#adminModify').submit(function(){
		
		if($('#mem_name').val().trim()==''){
			alert('이름을 입력하세요');
			$('#mem_name').focus();
			$('#mem_name').val('');
			return false;
		}
        var n_RegExp = /^[가-힣]{2,15}$/; //이름 유효성검사 정규식
        
        if(!n_RegExp.test($('#mem_name').val())){
            alert("이름은 한글만 입력해주세요.(특수문자,영어,숫자 사용불가)");
            return false;
        }

		if($('#mem_phone').val().trim()==''){
			alert('휴대폰번호를 입력하세요');
			$('#mem_phone').focus();
			$('#mem_phone').val('');
			return false;
		}
		
        var patternPhone = RegExmp(/01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/);
		if(!patternPhone.test($('#mem_phone').val())){ 
			alert('전화번호형식에 맞게 입력해주세요'); 
			$('#mem_phone').val(''); 
			$('#mem_phone').focus(); 
			return false; 
		}
		

		if($('#mem_email').val().trim()==''){
			alert('이메일 입력하세요!');
			$('#mem_email').focus();
			return false;
		}
		
		var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/); //이메일 유효성
		if(!getMail.test($('#mem_email').val())){ 
			alert('이메일형식에 맞게 입력해주세요'); 
			$('#mem_email').val(''); 
			$('#mem_email').focus(); 
			return false; 
		}
		
	});
});
</script>
<div class="top_menu_info">
	<div>
	홈 > 정보 수정
	</div>
</div>
<form id="adminModify" action="${pageContext.request.contextPath}/admin/adminInfoUpdateAction.do" method="post">
<table class="table">
  <tbody>
    <tr>
    
      <th scope="row">구분</th>
      <c:if test="${vo.mem_auth==2}">
      	<td>일반회원</td>
      </c:if>
      <c:if test="${vo.mem_auth==3}">
      	<td>일반관리자</td>
      </c:if>
      <c:if test="${vo.mem_auth==4}">
      	<td>최고관리자</td>
      </c:if>
      
    </tr>
    <tr>
      <th scope="row">아이디</th>
      <td>${vo.mem_id}</td>
    </tr>
    
    <tr>
      <th scope="row">이름</th>
      <td><input type="text" name="mem_name" id="mem_name" value="${vo.mem_name}"/></td>
    </tr>

    <tr>
      <th scope="row">휴대폰</th>
      <td><input type="text" name="mem_phone" id="mem_phone" value="${vo.mem_phone}"/></td>
    </tr>

    <tr>
      <th scope="row">이메일</th>
      <td><input type="text" name="mem_email" id="mem_email" value="${vo.mem_email}"/></td>
    </tr>
    <tr>
      <th scope="row">가입일</th>
      <td>${vo.mem_date}</td>
    </tr>
    <tr>
      <th scope="row">
      </th>
 		<td>
 		</td>
		
    </tr>
    
  </tbody>
</table>
  <input type="submit" class="btn btn-primary" value="수정완료" />
  
  <input type="hidden" name="mem_num" value="${vo.mem_num}"/>
  <input type="hidden" name="mem_passwd" value="${vo.mem_passwd}" />
  <input type="hidden" name="mem_id" value="${vo.mem_id}" />
  <input type="hidden" name="mem_date" value="${vo.mem_date}" />
  
</form>
  
