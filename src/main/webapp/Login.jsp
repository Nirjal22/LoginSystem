<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="LoginServlet" method="POST">
    <label>Enter the username: </label>
    <input type="text" name="username"><br>
    <label>Enter the password: </label>
    <input type="password" name="passKey"><br>
    <input type="submit" name="action" value="Login">
    <input type="submit" name="action" value="Register">
</form>
</body>
</html>
