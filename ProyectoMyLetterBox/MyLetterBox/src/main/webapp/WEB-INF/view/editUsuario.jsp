<%--
  Autor(es):
--%>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<%
    EntityUsuario usuario =(EntityUsuario) request.getAttribute("usuario");
%>
<body>
<h1>Editar usuario <%=usuario.getUserId()%>:</h1>
<form method="post" action="/users/edit">
    <input type="hidden" value="<%=usuario.getUserId()%>"  name="userId">

    <label for="username">Nombre de usuario:</label>
    <input id="username" type="text" name="username" value="<%=usuario.getUsername()%>">
    </br>

    <label for="email">Email:</label>
    <input id="email" type="email" name="email" value="<%=usuario.getEmail()%>">
    </br>

    <label for="password">Contraseña:</label>
    <input id="password" type="password" name="password" value="<%=usuario.getPassword()%>">
    </br>

    <label for="role">Rol:</label>
    <select id="role" name="rol" value="<%=usuario.getRol()%>">
        <option value="administrador">Administrador</option>
        <option value="editor">Editor</option>
        <option value="recomendador">Recomendador</option>
        <option value="usuario">Usuario</option>
        <option value="analista">Analista</option>
    </select>

    </br>
    <input type="submit" value="Actualizar">
</form>
</body>
</html>
