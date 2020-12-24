<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Update</title>
</head>
<body>
<jsp:include page="home.jsp"/>
<p>
    At this moment publication have:</p>
<p>
    Name: "<c:out value="${publication.name}"/>"
</p>
<p>
    Theme: "<c:out value="${publication.theme}"/>"
</p>
<p>
    Price: <c:out value="${publication.price}"/>
</p>
<p>
    <form action="Change" method="Post">
        <div>

            <select name="theme">POLITICS,MOVIES,ENTERTAINMENT,NEWS,TECHNOLOGY,SCIENCE,SPORT,HOME
                <option disabled>Choose theme</option>
                <option value="POLITICS">POLITICS</option>
                <option value="MOVIES">MOVIES</option>
                <option value="ENTERTAINMENT">ENTERTAINMENT</option>
                <option value="NEWS">NEWS</option>
                <option value="TECHNOLOGY">TECHNOLOGY</option>
                <option value="SCIENCE">SCIENCE</option>
                <option value="SPORT">SPORT</option>
                <option value="HOME">HOME</option>
            </select>
            <input name="name" type="text">

            <input name="score" type="text">

<p>
    <button type="submit" name="publication_id" value="${publication.publication_id}">Update</button>
</p>
</div>
</form>

</p>


<p>${error}</p>

</body>
</html>


























