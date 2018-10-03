<%--
  Created by IntelliJ IDEA.
  User: andresgismondi
  Date: 02/10/18
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/Login" method="post">
        <label>Nombre
            <input type="text" name="name">
        </label>
        <label>Password
            <input type="text" name="password">
        </label>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
