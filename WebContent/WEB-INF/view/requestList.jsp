<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="index.jsp"%>
	<div align="center">
		<br />
		<h2>Friend Request</h2>
		<hr />
		<table algin="center" style="width: 30%;">
			<tr align="center">
				<th>ID</th>
				<th>신청일</th>
				<th></th>
			</tr>
			<c:forEach var="map" items="${list }">
				<tr align="center">
					<th>${map.ONE}</th>
					<td>${map.REQDATE }</td>
					<td><button style="color: green;'">수락</button>
						<button style="color: red;">거절</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>