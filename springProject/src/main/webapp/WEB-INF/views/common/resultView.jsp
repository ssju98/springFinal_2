<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	if('${empty message}'){
		location.href='${url}';
	}
	if('${!empty message}'){
		alert('${message}');
		location.href='${url}';
	}
	
	
</script>t>