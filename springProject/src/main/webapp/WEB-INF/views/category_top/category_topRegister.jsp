<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="category_top-main-width">
	<h3>상위 카테고리 등록</h3>
	<!-- 상위 카테고리 정보 -->
	<div>
		<form:form action="category_topRegister.do" modelAttribute="category_topVO">
			<table>
				<tr>
					<th>상위 카테고리 명</th>
					<td>
						<form:input path="c_top_name" class="form-control form-control-sm"/>
					</td>
				</tr>
			</table>
			<div class="align-center">
				<form:button>등록</form:button>
			</div>
		</form:form>
	</div>
</div>