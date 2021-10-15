<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css">
<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.css"></script>

<script>
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('mem_zipcode').value = data.zonecode;
                document.getElementById("mem_address1").value = data.jibunAddress;
                
                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';
                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
<style>

#adminModify{
	width:50%;
	margin:0 auto;
	padding: 85px 0;
}

table tr:last-child{
	display:none;
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
		
		
		if($('#mem_email').val().trim()==''){
			alert('이메일 입력하세요!');
			$('#mem_email').focus();
			return false;
		}
		
		
		if($('#mem_zipcode').val().trim()==''){
			alert('우편번호를 체크하세요!');
			$('#mem_zipcode').focus();
			return false;
		}
		
		if($('#mem_address1').val().trim()==''){
			alert('주소를 입력하세요!');
			$('#mem_address1').focus();
			return false;
		}
		
		if($('#mem_address2').val().trim()==''){
			alert('상세주소를 입력하세요!');
			$('#mem_address2').focus();
			return false;
		}
		
		
		
	});
});
</script>

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
      <th scope="row">우편번호</th>
      <td><input type="text" name="mem_zipcode" id="mem_zipcode" value="${vo.mem_zipcode}" readonly/>&nbsp;
		<button type="button" class="btn btn-outline-dark" onclick="sample4_execDaumPostcode()">우편번호 찾기</button>
      </td>
    </tr>
    <tr>
      <th scope="row">주소</th>
      <td><input type="text" name="mem_address1" id="mem_address1" value="${vo.mem_address1}"/></td>
    </tr>
    <tr>
      <th scope="row">상세주소</th>
      <td><input type="text" name="mem_address2" id="mem_address2" value="${vo.mem_address2}"/></td>
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
  
