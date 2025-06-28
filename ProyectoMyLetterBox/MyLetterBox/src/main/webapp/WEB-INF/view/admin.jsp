<%@ page import="java.util.List" %>
<%@ page import="javax.swing.text.html.parser.Entity" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="es.uma.demospring.myletterbox.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 07/04/2025
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es): Rafael Sáez Arana, Iván Pedraza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListarUsuarios</title>
</head>
<%
    List<UsuarioDTO> usuarios = (List<UsuarioDTO>)request.getAttribute("usuarios");
    EntityUsuario usuarioLogueado = (EntityUsuario) session.getAttribute("user");
%>
<body>
<table border="">
    <form method="get" action="/movies/">
        <button>Acceder a la app</button>
    </form>
    <br>
    <form method="get" action="/createUser">
        <button>Nuevo usuario</button>
    </form>
    <thead>
        <tr>
            <td>ID</td>
            <td>EMAIL</td>
            <td>ROL</td>
            <td>PASSWORD</td>
            <td>USERNAME</td>
        </tr>
    </thead>
    <tbody>
        <%
            for (UsuarioDTO usuario: usuarios) {
        %>
        <tr>
            <%if(usuario.getUserId() != usuarioLogueado.getUserId()){%>
            <td><%= usuario.getUserId() %></td>
            <td><%= usuario.getEmail() %></td>
            <td><%= usuario.getRol() %></td>
            <td><%= usuario.getPassword() %></td>
            <td><%= usuario.getUsername() %></td>
            <td><a href="/users/edit?id=<%=usuario.getUserId()%>">editar</a></td>
            <td><a href="/users/delete?id=<%=usuario.getUserId()%>">eliminar</a></td>
            <%}%>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
<br/>

</body>
</html>
