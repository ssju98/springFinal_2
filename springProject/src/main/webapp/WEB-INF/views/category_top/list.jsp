<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="main-width">
<div id="menuinfo">대분류 카테고리 목록</div>
<div class="card mt-3">
		    <div class="card-body object-center text-center">
				<table class="table table-hover text-center line-bottom">
					<thead>
					<tr>
						<th>상위카테고리 번호</th>
						<th width="30%">상위카테고리명</th>
						<th>수정/삭제</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="category_top" items="${list}">
					<tr>
						<td>${category_top.c_top_no}</td>
						<td class="text-primary">${category_top.c_top_name}</td>
						<td>
							<input type="button" class="btn btn-info" value="수정" onclick="location.href='category_topUpdate.do?c_top_no=${category_top.c_top_no}'">
							<input type="button" class="btn btn-danger deleteBtn" value="삭제" id="delete_${category_top.c_top_no}">
						</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>