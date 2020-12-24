<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Insert</title>
</head>
<body>

<jsp:include page="home.jsp"/>
<p>Create new publication</p>
<p>
    <form action="Insert" method="Post">
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
<p>Enter name:
    <input name="name" type="text">
</p>
<p>Price:
    <input name="score" type="text">
</p>
<p>
    <button type="submit">Create</button>
</p>
</div>
</form>

</p>
<p>${error}</p>
</body>
</html>


























