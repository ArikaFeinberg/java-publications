<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Guest</title>
</head>
<body>
<div>
    <button onclick="window.location.href='Login'">Login</button>
    <button onclick="window.location.href='Registration'">Registration</button>
</div>
<p>
    <form action="Guest" method="GET">
        <div>
            <select name="sort">
                <option disabled>Choose sort</option>
                <option value="0">-</option>
                <option value="1">By name</option>
                <option value="2">By price</option>
            </select>

            <select name="theme">
                <option disabled>Choose theme</option>
                <option value="">-</option>
                <option value="POLITICS">POLITICS</option>
                <option value="MOVIES">MOVIES</option>
                <option value="ENTERTAINMENT">ENTERTAINMENT</option>
                <option value="NEWS">NEWS</option>
                <option value="TECHNOLOGY">TECHNOLOGY</option>
                <option value="SCIENCE">SCIENCE</option>
                <option value="SPORT">SPORT</option>
                <option value="HOME">HOME</option>
            </select>
<p><input type="submit" value="Do"></p>
</div>
</form>

<form action="Guest" method="GET">
    <div>
        <input name="search" type="text">
        <p><input type="submit" value="Search"></p>
    </div>
</form>
</p>

<c:forEach var="publication" items="${publications}">
    <div>
        <p>
            Name: "<c:out value="${publication.name}"/>"
            Theme: "<c:out value="${publication.theme}"/>"
            Score: <c:out value="${publication.price}"/>

        </p>
    </div>
</c:forEach>

<c:set var="href" value="Guest" scope="request"/>
<jsp:include page="pagination/pagination.jsp"/>


</body>
</html>