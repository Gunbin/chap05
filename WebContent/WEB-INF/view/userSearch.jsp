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
		<h3>User Search</h3>
		<hr style="width: 50%;" />
		<table>
			<tr align="center">
				<th>ID</th>
				<th>EMAIL</th>
				<th>FLLOW</th>
			</tr>
			<tr>
				<td><hr /></td>
			</tr>
			<c:forEach var="map" items="${list }">
				<c:if test="${map.ID != login }">
					<tr align="center">
						<form action="/fllow" method="post">
							<input type="hidden" name="id" value="${map.ID }" />
							<th>${map.ID}</th>
							<td style="width: 50%;">[ ${map.EMAIL } ]</td>
							<td><c:choose>
									<c:when test="${map.ONE == null or map.STATUS==0}">
										<button type="submit">친구신청</button>
									</c:when>
									<c:otherwise>
										<b><font color="green">Friend</font></b>
									</c:otherwise>
								</c:choose></td>
						</form>
					</tr>
					<tr>
						<td><hr /></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>

	<c:if test="${fllow=='true' }">
		<script>
			window.alert("친구신청하였습니다.");
		</script>
	</c:if>

	<c:if test="${fllow=='false' }">
		<script>
			window.alert("이미 친구신청이 완료되었습니다.");
		</script>
	</c:if>
</body>
</html>