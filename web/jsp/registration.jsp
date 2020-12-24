<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Registration</title>
</head>
<body>
<div>
    <button onclick="window.location.href='Login'">Login</button>
    <button onclick="window.location.href='Guest'">Guest</button>
</div>
<form action="Registration" method="POST">
    <p>Enter name: <input name="username"/></p>
    <p>Enter email: <input name="email"/></p>
    <p>Enter password: <input name="password"/></p>
    <p><input type="submit" value="Registration"/></p>
    <p>${error}</p>
</form>
</body>
</html>