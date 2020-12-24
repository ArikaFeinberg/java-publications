<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Replenish</title>
    </head>
<body>
<button onclick="window.location.href='Home'">Home</button>
<button onclick="window.location.href='Publication'">Publications</button>

<form action="Replenish" method="POST">
    <p>Enter score: <input name="score" /></p>
    <p><input type="submit" value="Submit" /></p>
	<p>${error}</p>
</form>
</body>
</html>