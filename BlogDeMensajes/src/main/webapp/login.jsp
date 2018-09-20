<%--
  Created by IntelliJ IDEA.
  User: andresgismondi
  Date: 19/09/18
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/Login" method="post">
    <label>
        <input type="text" name="name">
    </label>
    <label>
        <input type="password" name="password">
    </label>
    <input type="submit" value="Submit">
</form>
</body>
</html>
