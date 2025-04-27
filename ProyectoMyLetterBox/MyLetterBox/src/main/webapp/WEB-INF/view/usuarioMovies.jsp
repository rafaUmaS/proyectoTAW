<%@ page import="javax.swing.text.html.parser.Entity" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 27/04/2025
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mis peliculas guardadas</title>
</head>
<%
    List<EntityUsuarioSaveMovie> savedMovies = (List<EntityUsuarioSaveMovie>)request.getAttribute("savedMovies");
    EntityUsuario user = (EntityUsuario)session.getAttribute("user");
%>
<body>
    <% for (EntityUsuarioSaveMovie savedMovie : savedMovies){
    %>
    <p><%=savedMovie.getMovieMovie().getName()%></p>
    <%}%>
</body>
</html>
