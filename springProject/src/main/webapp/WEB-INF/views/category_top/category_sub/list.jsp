<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="main-width">
<div id="menuinfo">소분류 카테고리 목록</div>
	<div class="card mt-3">
		 <div class="card-body object-center text-center">
			<table class="table table-hover text-center line-bottom">
				<thead>
					<tr>
						<th>하위카테고리 번호</th>
						<th width="30%">하위카테고리명</th>
						<th>상위 카테고리 번호</th>
						<th>수정/삭제</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="category_sub" items="${list}">
					<tr>
						<td>${category_sub.c_sub_no}</td>
						<td class="text-primary">${category_sub.c_sub_name}</td>
						<td>${category_sub.c_top_no}</td>
						<td>
							<input type="button" class="btn btn-info" value="수정" onclick="location.href='category_subUpdate.do?c_sub_no=${category_sub.c_sub_no}'">
							<input type="button" class="btn btn-danger deleteBtn" value="삭제" onclick="location.href='category_subDelete.do?c_sub_no=${category_sub.c_sub_no}'" id="delete_${category_sub.c_sub_no}">
						</td>
					</tr>
					
					</c:forEach>
						
					</tbody>
				</table>
				<input type="button" class="btn btn-secondary" value="카테고리 등록" onclick="location.href='category_subRegister.do'">
			</div>
			<br><br>
		</div>
	<input type="button" class="btn btn-secondary" value="상위 카테고리 관리" onclick="history.back(-1)">
</div>