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
<form method="post" modelAttribute="usuario" action="/users/edit">
    <input type="hidden" value="<%=usuario.getUserId()%>"  name="userId">
    Username: <input type="text" name="username" value="<%=usuario.getUsername()%>">
    </br>
    Contraseña: <input type="password" name="password" value="<%=usuario.getPassword()%>">
    </br>
    Rol <input type="text" name="rol" value="<%=usuario.getRol()%>">
    </br>
    Email <input type="email" name="email" value="<%=usuario.getEmail()%>">
    </br>
    <input type="submit" value="Actualizar">
</form>
</body>
</html>
