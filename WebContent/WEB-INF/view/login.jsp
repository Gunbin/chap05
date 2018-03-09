<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="index.jsp"%>
	<div align="center">
		<div style="width: 80%;">
			<h3>SIGN IN</h3>

			<form action="/login" method="post">
				<table>
					<tr align="left">
						<th>ID</th>
					</tr>
					<tr align="left">
						<td><input type="text" name="id" value="${id }" /></td>
					</tr>
					<tr align="left">
						<th>PASS</th>
					<tr align="left">
						<td><input type="password" name="pass" /></td>
					</tr>
					<tr align="center">
						<td><br />
							<button type="submit" style="font-size: 20px;">Login</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>