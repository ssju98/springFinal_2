<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/qnaList.css"> 
<div class="top_menu_info">
	<div>
	홈 > 문의게시판
	</div>
</div>
<div id="main-width">
	<div id="categorySide">
		<div id="showmenu">문의게시판</div>
		<div class="menu mt-2">
			<ul>
				<c:if test="${board_kind == 0}">
					<li class="order-current"><a href="qnaList.do?board_kind=0">상품 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=1">배송 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=2">교환/환불 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=3">기타 문의</a></li>
				</c:if>
				<c:if test="${board_kind == 1}">
					<li class="order-a"><a href="qnaList.do?board_kind=0">상품 문의</a></li>
					<li class="order-current"><a href="qnaList.do?board_kind=1">배송 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=2">교환/환불 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=3">기타 문의</a></li>
				</c:if>
				<c:if test="${board_kind == 2}">
					<li class="order-a"><a href="qnaList.do?board_kind=0">상품 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=1">배송 문의</a></li>
					<li class="order-current"><a href="qnaList.do?board_kind=2">교환/환불 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=3">기타 문의</a></li>
				</c:if>
				<c:if test="${board_kind == 3}">
					<li class="order-a"><a href="qnaList.do?board_kind=0">상품 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=1">배송 문의</a></li>
					<li class="order-a"><a href="qnaList.do?board_kind=2">교환/환불 문의</a></li>
					<li class="order-current"><a href="qnaList.do?board_kind=3">기타 문의</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<div class="top-small-menu">
		<div class="top-small-menu-title mt-3">
			<c:if test="${board_kind == 0}">
				상품 문의게시판
			</c:if>
			<c:if test="${board_kind == 1}">
				배송 문의게시판
			</c:if>
			<c:if test="${board_kind == 2}">
				교환/환불 문의게시판
			</c:if>
			<c:if test="${board_kind == 3}">
				기타 문의게시판
			</c:if>
		</div>
		<div class="mb-2 mt-3">
			<form action="qnaList.do" method="get" id="search_form" style="width: auto;">
				<div class="form-inline">
					<select name="keyfield" id="keyfield" class="form-control form-control-sm">
						<option value="mem_id">아이디</option>
						<option value="board_content">내용</option>
					</select>
					<input type="search" name="keyword" id="keyword" placeholder="검색어 입력" class="form-control form-control-sm ml-1">
					<input type="submit" value="검색" class="btn btn-dark btn-sm">
				</div>
			</form>
			<button class="btn btn-info write-btn btn-sm" style="float:right;" onclick="location.href='qnaWrite.do?board_kind=${board_kind}'">글작성</button>
		</div>
		<table class="order-table" border="1">
			<colgroup>
				<col width="100px">
				<col width="600px">
				<col width="130px">
				<col width="170px">
			</colgroup>
			<thead>
				<tr>
					<th>게시물 번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<c:forEach var="list" items="${list}">
				<tbody>
					<tr>	
						<td class="table-text">${list.board_no}</td>
						<td class="table-product">
							<div class="table-product-name">
								<c:if test="${list.level > 1}">
									<c:forEach begin="1" end="${list.level}">
										&nbsp;
									</c:forEach>
									<a href="qnaDetail.do?board_no=${list.board_no}">RE : </a> 
								</c:if>
								<a href="qnaDetail.do?board_no=${list.board_no}">${list.board_title}</a>
							</div>
						</td>
						<td class="table-text">
							${list.mem_id}
						</td>
						<td class="table-text">
							<fmt:formatDate value="${list.board_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					</tr>	
				</tbody>
			</c:forEach>
		</table>
	</div>
	
</div>