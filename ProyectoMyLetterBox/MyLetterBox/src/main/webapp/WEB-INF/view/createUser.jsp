<%--
  Created by IntelliJ IDEA.
  User: rafas
  Date: 25/05/2025
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form method="post" action="/users/">

    <label for="username">Nombre de usuario:</label>
    <input id="username" type="text"  name="username">
    <br>
    <label for="email">Email:</label>
    <input id="email" type="email"  name="email">
    <br>
    <label for="password">Contraseña:</label>
    <input id="password" type="password"  name="password">
    <br>
    <label for="role">Rol:</label>
    <select id="role" name="rol">
        <option value="administrador">Administrador</option>
        <option value="editor">Editor</option>
        <option value="recomendador">Recomendador</option>
        <option value="usuario">Usuario</option>
        <option value="analista">Analista</option>
    </select>
     <br>
    <input type="submit" value="Crear">
  </form>
</body>
</html>
