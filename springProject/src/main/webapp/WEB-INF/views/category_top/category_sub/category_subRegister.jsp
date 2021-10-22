<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="category_top-main-width">
	<h3>하위 카테고리 등록</h3>
	<!-- 하위 카테고리 정보 -->
	<div>
		<form:form action="category_subRegister.do" modelAttribute="category_subVO">
			<table>
				<tr>
					<th>넣을 상위 카테고리 번호</th>
					<td>
						<form:select path="c_top_no" class="form-control form-control-sm">
						</form:select>
					</td>
				</tr>
				<tr>
					<th>하위 카테고리 명</th>
					<td>
						<form:input path="c_sub_name" class="form-control form-control-sm"/>
						<form:option value="${c_top_no}" label="${c_top_name}"></form:option>
					</td>
				</tr>
				<tr>
					
				</tr>
			</table>
			<div class="align-center">
				<form:button>등록</form:button>
			</div>
		</form:form>
	</div>
</div>