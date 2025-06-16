<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 07/05/2025
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es): Iván Pedraza Díez (85%), Adrián Huete Peña (15%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cabecera</title>
</head>
<%
    EntityUsuario user = (EntityUsuario)session.getAttribute("user");
%>
<body>
<table width="100%">
    <tr>
        <td><a href="/movies/">Películas</a></td>
        <td><a href="">Géneros</a></td>
        <td><a href="/users/saved-movies">Películas guardadas</a></td>
        <td><a href="/users/recommended-movies">Películas Recomendadas</a></td>
        <td><a href="">Tus comentarios</a></td>
        <%
            if(user.getRol().equals("analista") || user.getRol().equals("administrador")){
        %>
        <td><a href="/analist/movies">Datos analista</a> </td>
        <%
            }
        %>
        <td>Bienvenido, <b><%= user.getUsername()%></b>, al sistema <br/>
            sessionid: <%= session.getId() %> <br/>
            fecha de entrada al sistema: <%= new Date(session.getCreationTime()) %> <br/>
            (<a href="/salir">Salir</a>)</td>
    </tr>
</table>
<br/><br/>
</body>
</html>
