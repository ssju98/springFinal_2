<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin1_style.css">
<!-- 중앙 내용 시작 -->
<div id="category_sub-main-width">
	<h3>하위 카테고리 수정</h3>
	<!-- 하위 카테고리 정보 -->
	<div>
		<form:form action="category_subUpdate.do" modelAttribute="category_subVO">
			<form:hidden path="c_sub_no"/>
			<table>
				<tr>
					<th>카테고리 번호</th>
					<td>
						${category_subVO.c_sub_no}
					</td>
				</tr>
				<tr>
					<th>하위 카테고리 명</th>
					<td>
						<form:input path="c_sub_name" class="form-control form-control-sm"/>
						<form:errors path="c_sub_name" class="text-danger"/>
					</td>
				</tr>
				<tr>
					<th>속한 상위 카테고리 번호</th>
					<td>
						${category_subVO.c_top_no}
					</td>
				</tr>
			</table>
			<div class="align-center">
				<form:button>수정</form:button>
			</div>
			
		</form:form>
	</div>
</div>