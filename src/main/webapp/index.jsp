<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        // If no user is logged in, redirect to login page
        response.sendRedirect("Login.jsp");
    }
%>
<html>
<body>
<h1>Congratulations. You are successfully here.</h1>
<h2>Welcome <%= username%>
</h2>
<br><br>

<form action="LoginServlet" method="POST">
    <button type="submit" name="delete" value="delete">Delete User</button>
</form>

<form action="Logout" method="POST">
    <button type="submit">Logout</button>
</form>

</body>
</html>
