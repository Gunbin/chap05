<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<div style="width: 80%;">
			<h2>SPRING PROJECT</h2>
			<hr />
			<div align="right">
				<div>
					<b>${ment }</b>
				</div>
				<c:choose>
					<c:when test="${login==null }">
						<a href="/login" style="text-decoration: none;"><b>Sign In</b>
						</a>
				| <a href="/join" style="text-decoration: none;"><b>Sign Up</b></a>
					</c:when>
					<c:otherwise>
						<a href="/out" style="text-decoration: none;"><b>Sign Out</b></a>
					</c:otherwise>
				</c:choose>
			</div>
			<hr />
		</div>
	</div>
</body>
</html>