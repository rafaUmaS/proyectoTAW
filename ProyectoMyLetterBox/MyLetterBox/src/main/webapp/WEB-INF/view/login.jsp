<%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 27/04/2025
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Iniciar sesión:</h1>
<form method="post" modelAttribute="usuario" action="/autentica">
    Usuario: <input type="text" name="username">
    </br>
    Contraseña: <input type="password" name="password">
    </br>
    <input type="submit" value="Enviar">
</form>
</body>
</html>
