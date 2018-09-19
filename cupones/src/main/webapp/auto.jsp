<%--
  Created by IntelliJ IDEA.
  User: andresgismondi
  Date: 14/09/18
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/DatosAuto" method="post">
    Marca: <input type="text" name="marca" value="Mickey"><br>
    Modelo: <input type="text" name="modelo" value="Mouse"><br>
    Año del Vehiculo:
    <select name="anioVehiculo">

        <%
        for (int i=1990; i<2100; i++){
             out.println("<option value='"+i+"'>"+i+"</option>");
        }
        %>

    </select>
    <br>
    Uso en kilometros: <input type="text" name="usoKilometro" value="Mouse"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
