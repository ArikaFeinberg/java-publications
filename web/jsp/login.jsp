<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Login</title>
    </head>
<body>
<div>
<button onclick="window.location.href='Registration'">Registration</button>
<button onclick="window.location.href='Guest'">Guest</button>
</div>
<form action="Login" method="POST">
    <p>Name: <input name="username" /></p>
	<p>Password: <input name="password", type="password" /></p>
    <p><input type="submit" value="Submit" /></p>
	<p>${error}</p>
</form>
</body>
</html>