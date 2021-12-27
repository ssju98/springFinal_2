<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/review.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-stars.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.barrating.min.js"></script>
<script type="text/javascript">
	 $(document).ready(function(){
		 $("#Submit").off("click").on('click', function() {
			 if($("#review_rating option:selected").val()==0){
				alert('평점 필수');
				 return false;
			 }
			 if($("#review_content").val()==''){
					alert('후기평 작성 필수');
					 return false;
				}
			 
			 var form = $('#writeForm')[0];
			 var formData = new FormData(form);
			 $.ajax({
				 url:'writeForm.do',
				 type:'POST',
				 data:formData,
				 dataType:'json',
				 contentType:false,
				 enctype:'multipart/form-data',
				 processData:false,
				 catch:false,
				 success:function(param){
					if(param.result=='logout'){
						alert('로그인 후 사용하세요');
					}else if(param.result=='notMatch'){
						alert('주문한 계정과 일치하지 않습니다.') 
					}else if(param.result=='success'){
						alert('후기가 작성되었습니다.');
						location.reload();
					 }
				 },
				error:function(){
					alert("네트워크 오류");
				}
			 });
		 });
	//별점 js
	$(function() {
      $('#review_rating').barrating({
        theme: 'fontawesome-stars'
      });
   });
});
</script>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h5 id="modal-title" class="modal-title"></h5>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				
			</div >
			<form id="writeForm" enctype="multipart/form-data">
			<input type="hidden" id="p_no" name="p_no" value="">
			<input type="hidden" id="order_no" name="order_no" value="">
			<div class="modal-body">
				<table class="table">
					<tr>
						<td>
							<img id="p_image" src="" width="100" height="100">
						</td>
						<td id="p_name" class="font-style pt-5" ></td>
					</tr>
					<tr>
						<td id="p_no" class="font-style rating">별점평가</td>
						<td>
							<select id="review_rating" name="review_rating">
								<option value=""></option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="font-style">후기평 작성</td> 
						<td><textarea class="form-control" id="review_content"  name="review_content"rows="10"></textarea></td>
					</tr>
					<tr>
						<td style="font-style">사진 등록</td> 
						<td><input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg"></td>
					</tr>
					
				</table>
				<div class="file">
					
				</div>
			</div>
			<div class="modal-footer">
				<button id="Submit" type="button" class="btn btn-info" style="width:100px;">완료</button>
			</div>
			</form>
		</div>
		
	</div>
</div>