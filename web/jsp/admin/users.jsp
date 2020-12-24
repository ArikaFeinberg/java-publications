<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Users</title>
    </head>
<body>
<jsp:include page="home.jsp"/>
			<form action="Users" method="POST">
<table style="width:40%">
<tr>
		<th>UserID</th>
		<th>Username</th>
		<th>Email</th>
		<th>Blocked</th>
		<th>Action</th>
	</tr>
    <c:forEach var="user" items="${users}">
	<tr>
		<th><c:out value="${user.user_id}" /></th>
		<th><c:out value="${user.userName}" /></th>
		<th><c:out value="${user.email}"/></th>
		<th><c:out value="${user.blocked}"/></th>
		<th>
		<c:if test="${user.blocked}">
			<form action="Users" method="POST">
				 <button type="submit" name="unblock" value="${user.user_id}">Unblock</button>
			</form>
		</c:if>
		<c:if test="${!user.blocked}">

				 <button type="submit" name="block" value="${user.user_id}">Block</button>

		</c:if>
		</th>
	</tr>

    </c:forEach>
</table>
			</form>
			
<c:set var="href" value="Users" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>
</body>
</html>


























