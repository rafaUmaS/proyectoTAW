<%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 04/06/2025
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Datos pelicula</title>
</head>
<%
    EntityMovie movie = (EntityMovie) request.getAttribute("movie");
%>
<body>
<h1>Datos de la pelicula: <%=movie.getName()%></h1>
<table border="0">
    <tr>
        <td>Descripción general:</td>
        <td><%=movie.getOverview()%></td>
    </tr>
    <tr>
        <td>Popularidad:</td>
        <td><%=movie.getPopularity()%></td>
    </tr>
    <tr>
        <td>Fecha de salida:</td>
        <td><%=movie.getReleaseDate()%></td>
    </tr>
    <tr>
        <td>Presupuesto:</td>
        <td><%=movie.getBudget()%></td>
    </tr>
    <tr>
        <td>Beneficio:</td>
        <td><%=movie.getRevenue()%></td>
    </tr>
    <tr>
        <td>Duración:</td>
        <td><%=movie.getRuntime()%> min.</td>
    </tr>
    <tr>
        <td>Estado:</td>
        <td><%=movie.getStatus()%></td>
    </tr>
    <tr>
        <td>Valoración media:</td>
        <td><%=movie.getVoteAverage()%></td>
    </tr>
    <tr>
        <td>Número de votos:</td>
        <td><%=movie.getVoteCount()%></td>
    </tr>
    <tr>
        <td>Idioma original:</td>
        <td><%=movie.getOriginalLanguage()%></td>
    </tr>
    <tr>
        <td>Título original:</td>
        <td><%=movie.getOriginalTitle()%></td>
    </tr>
    <tr>
        <td>
            <form method="get" action="/analist/movies">
                <input type="submit" value="Volver">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
