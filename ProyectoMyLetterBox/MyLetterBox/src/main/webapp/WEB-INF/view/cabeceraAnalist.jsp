<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 04/06/2025
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es): Iván Pedraza Díez (100%)
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
    <td><a href="/analist/movies">Películas</a></td>
    <td><a href="">Personas</a></td>
    <td>Bienvenido, analista <b><%= user.getUsername()%></b>, al sistema <br/>
      sessionid: <%= session.getId() %> <br/>
      fecha de entrada al sistema: <%= new Date(session.getCreationTime()) %> <br/>
      (<a href="/analist/volver">Volver</a>)</td>
  </tr>
</table>
<br/><br/>
</body>
</html>
