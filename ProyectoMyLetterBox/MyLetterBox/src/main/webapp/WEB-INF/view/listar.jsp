<%@ page import="java.util.List" %>
<%@ page import="javax.swing.text.html.parser.Entity" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 07/04/2025
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListarUsuarios</title>
</head>
<%
    List<EntityUsuario> usuarios = (List<EntityUsuario>)request.getAttribute("usuarios");
%>
<body>
<table border="">
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
            for (EntityUsuario usuario: usuarios) {
        %>
        <tr>
            <td><%= usuario.getUserId() %></td>
            <td><%= usuario.getEmail() %></td>
            <td><%= usuario.getRol() %></td>
            <td><%= usuario.getPassword() %></td>
            <td><%= usuario.getUsername() %></td>
            <td><a href="/users/edit?id=<%=usuario.getUserId()%>">editar</a></td>
            <td><a href="/users/delete?id=<%=usuario.getUserId()%>">eliminar</a></td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>
<br/>

</body>
</html>
