<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<User> users = (List<User>) session.getAttribute("usuarios"); %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>Show Users</title>
    <link rel="stylesheet" type="text/css" media="screen" href="styles/style.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-6">
                <p>hola</p>
            </div>
            <div class="col-6 ">
                <% for (User u: users) {%>
                <h6 class="pb-1 content"><%= u.getName() %></h6>
                <%}%>
            </div>
        </div>
    </div>
    <%  System.out.println(session.getId());
        session.invalidate();
        System.out.println(session.getId());
       //response.sendRedirect("Login.jsp");%>
</body>
</html>
