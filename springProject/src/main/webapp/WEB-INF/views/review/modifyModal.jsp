<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/review.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fontawesome-stars.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.barrating.min.js"></script>
<script type="text/javascript">
	 $(document).ready(function(){
		
		 var rating;
		 var reviewRating = 5;
		 $("button[id^='modifyBtn']").on('click',function(e){
				var review_no = $(this).attr('value');
				var data = {review_no : review_no};
				$.ajax({
		 			url:'modifyForm.do',
		 			type:'GET',
		 			data:data,
		 			dataType:'json',
		 			async:false,
		 			catch:false,
		 			success:function(data){
		 				$("#modal-title").text("후기 수정");
		 				$("#p_name").text(data.p_name);
						$("#p_image").attr("src","${pageContext.request.contextPath}/product/photoView.do?p_no="+data.p_no);
						$('#review_no').val(data.review_no);
						reviewRating=data.review_rating;
						$('#review_content').val(data.review_content);
		 				$("#myModal").modal({backdrop:'static',keyboard:false});
		 			},
		 			error:function(){
		 				alert("네트워크 오류");
		 			}		
		 		});
			});
		$("#review_rating").val(reviewRating);
		 $("#Submit2").off("click").on('click', function() {
			 if($("#review_rating option:selected").val()==0){	
				alert(reviewRating);
				 return false;
			 }
			 if($("#review_content").val()==''){
					alert(reviewRating);
					 return false;
				}
			 
			 var form = $('#modifyForm')[0];
			 var formData = new FormData(form);
			 $.ajax({
				 url:'modifyForm.do',
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
						alert('후기 작성자와 일치하지 않습니다.') 
					 }else if(param.result=='success'){
						 alert('후기가 수정되었습니다.');
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
			</div>
			<form id="modifyForm" enctype="multipart/form-data">
			<input type="hidden" id="review_no" name="review_no" value="">
			<div class="modal-body">
				<table class="table">
					<tr>
						<td>
							<img id="p_image" src="${pageContext.request.contextPath}/resources/images/testProduct.jpg" width="100" height="100">
						</td>
						<td id="p_name" class="font-style pt-5"></td>
					</tr>
					<tr>
						<td id="p_no" class="font-style rating">별점평가</td>
						<td style="text-align: center;">
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
						<td class="font-style">후기평 작성</td> 
						<td><textarea class="form-control" id="review_content"  name="review_content"rows="10"></textarea></td>
					</tr>
					<tr>
						<td class="font-style">파일 등록</td>
						<td><input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg"></td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button id="Submit2" type="button" class="btn btn-info" style="width:100px;">완료</button>
			</div>
			</form>
		</div>
		
	</div>
</div>