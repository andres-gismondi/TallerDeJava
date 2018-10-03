<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> users = (List<User>) session.getAttribute("usuarios"); %>
<% User camila = (User)session.getAttribute("camila"); %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>Show Users</title>
</head>
<body>
    <% for (User u: users) {%>
        <h2><%= u.getName() %></h2>
    <%}%>
</body>
</html>
