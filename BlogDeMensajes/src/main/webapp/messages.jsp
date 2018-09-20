<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: andresgismondi
  Date: 19/09/18
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>
    <h3>Messages Information</h3>
    <% List<User> users = (ArrayList<User>) request.getServletContext().getAttribute("Usuarios"); %>
    <%
        for (User user: users) {
            out.print("<h6>"+user.getName()+"</h6>");
        }
    %>
    <%
        if(session.getAttribute("nombreUsuario").equals(null)){
            out.print("<a href='/Login'>Login");
        }else{
            out.print("<a href='/Login'>Login");
        }
    %>
</body>
</html>
