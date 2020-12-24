<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Home</title>
</head>
<body>

<div>
    <button onclick="window.location.href='Publication'">Publications</button>
    <button onclick="window.location.href='Replenish'">Replenish</button>
    <button onclick="window.location.href='../Exit'">Exit</button>
</div>

<div>
    <p>On your balance: <c:out value="${account.score}"/></p>
</div>
<c:if test="${subscriptions==null}">
    <p>You are not subscribed to anything.</p>
</c:if>
<c:forEach var="publication" items="${subscriptions}">
    <div>
        <p>
            Name: "<c:out value="${publication.name}"/>"
            Theme: "<c:out value="${publication.theme}"/>"
            Price: <c:out value="${publication.price}"/>
        </p>
    </div>
</c:forEach>
</body>
</html>