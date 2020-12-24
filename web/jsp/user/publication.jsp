<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Publication</title>
</head>
<body>
<div>
    <button onclick="window.location.href='Replenish'">Replenish</button>
    <button onclick="window.location.href='Home'">Home</button>
    <button onclick="window.location.href='../Exit'">Exit</button>
</div>
<p>
    <form action="Publication" method="GET">
        <div>
            <select name="sort">
                <option disabled>Choose sort</option>
                <option value="0">-</option>
                <option value="1">By name</option>
                <option value="2">By price</option>
            </select>

            <select name="theme">POLITICS,MOVIES,ENTERTAINMENT,NEWS,TECHNOLOGY,SCIENCE,SPORT,HOME
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

<form action="Publication" method="GET">
    <div>
        <input name="search" type="text">
        <p><input type="submit" value="Search"></p>
    </div>
</form>
</p>
<div>
    <p></p>
</div>
<div>
    <p>On your balance: <c:out value="${account.score}"/></p>
</div>
<form action="Publication" method="POST">
    <c:forEach var="publication" items="${publications}">
        <div>
            <p>
                Name: "<c:out value="${publication.name}"/>"
                Theme: "<c:out value="${publication.theme}"/>"
                Price: <c:out value="${publication.price}"/>
                <button type="submit" name="publication_id" value="${publication.publication_id}">Subscribe</button>
            </p>
        </div>
    </c:forEach>
</form>

<c:set var="href" value="Publication" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>


<p>${error}</p>

</body>
</html>


























