<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/template/shop_template.css">
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="body"/>
	<%-- <tiles:insertAttribute name="footer"/> --%>

</body>

</html>




