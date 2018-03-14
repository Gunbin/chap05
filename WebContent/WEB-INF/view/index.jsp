<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="alert alert-warning" style="display: none;" id="alert">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>Warning!</strong> Please refresh F5
	</div>

	<div align="center">
		<div style="width: 80%;">
			<h2>SPRING PROJECT</h2>
			<div>
				<b>${ment }</b>
			</div>
			<div>
				<div class="alert alert-info">
					<b>현재접속자수 : </b><span id="cnt"></span> / <strong>서버알림 - </strong><span
						id="info"></span>
				</div>
			</div>
			<hr />
			<div align="right">
				<c:choose>
					<c:when test="${login == null}">
						<!-- 로그아웃 상태 -->
						<a href="/login" style="text-decoration: none;"><b>Sign In</b>
						</a>
				| <a href="/join" style="text-decoration: none;"><b>Sign Up</b></a>
					</c:when>
					<c:otherwise>
						<!-- 로그인상태 -->
						<table style="width: 100%;">
							<tr>
								<td align="left"><a>바보</a></td>
								<td align="right"><a href="/out"
									style="text-decoration: none;"><b>Sign Out</b></a></td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
			<hr />
			<c:if test="${login!=null }">
				<nav class="navbar navbar-expand-sm bg-dark navbar-dark"> <!-- Brand/logo -->
				<a class="navbar-brand" href="">Home</a> <!-- Links -->
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/create">▶
							ChattingRoom</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link 2</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link 3</a></li>
				</ul>
				</nav>
			</c:if>
		</div>
	</div>
	<script>
		var ws = new WebSocket("ws://${pageContext.request.serverName}/handle");

		// 메시지가 들어올 때
		ws.onmessage = function(res) {
			console.log(res);
			var obj = JSON.parse(res.data);
			$("#cnt").html(obj.cnt); // 사람수
			$("#info").html(obj.info); // 누가오픈또는 클로즈햇는지
		}

		var ws2 = new WebSocket("ws://${pageContext.request.serverName}/alert");
		ws2.onmessage = function(rst) {
			console.log(rst.data);
			if (rst.data == "login")
				$("#alert").show();
		}
	</script>
</body>
</html>