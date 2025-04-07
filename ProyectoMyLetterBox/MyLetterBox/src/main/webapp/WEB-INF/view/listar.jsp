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
<%
    for (EntityUsuario usuario: usuarios) {
%>
<tr>
    <td><%= usuario.getId() %></td>
    <td><%= usuario.getEmail() %></td>
    <td><%= usuario.getRol() %></td>
    <td><%= usuario.getPassword() %></td>
    <td><%= usuario.getUsername() %></td>
</tr>
<br/>
<%
    }
%>
</body>
</html>
