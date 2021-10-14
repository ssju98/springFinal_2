<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="main-width">
<div id="menuinfo">대분류 카테고리 목록</div>
<%-- <div class="card mt-3">
		    <div class="card-body object-center text-center">
				 <c:if test="${count>0}">
				<table class="table table-hover text-center line-bottom">
					<thead>
					<tr>
						<th>상위카테고리 번호</th>
						<th width="30%">상위카테고리명</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="category_top" items="${list}">
					<tr>
						<td>${category_top.c_top_no}</td>
						<td class="text-primary">${category_top.c_top_name}</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
				${pagingHtml}
				</c:if> 
			</div>
		</div> --%>
	</div>