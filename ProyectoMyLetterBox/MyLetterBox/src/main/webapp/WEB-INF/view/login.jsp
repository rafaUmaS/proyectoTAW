<%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 27/04/2025
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es): Iván Pedraza Díez (70%), Álvaro Sierra garcía (30%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<%
    String error = (String) request.getAttribute("error");
%>
<body>
<h1>Iniciar sesión:</h1>
<form method="post" modelAttribute="usuario" action="/autentica">
    <table>
        <tr>
            <td>Usuario:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Contraseña:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <% if (error != null) { %>
        <tr>
            <td colspan="2"><%=error%></td>
        </tr>
        <% } %>
        <tr>
            <td colspan="2"><input type="submit" value="Enviar"></td>
        </tr>
    </table>
</form>
</body>
</html>
