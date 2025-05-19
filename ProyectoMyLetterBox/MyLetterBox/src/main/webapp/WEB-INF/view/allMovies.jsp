<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 07/05/2025
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All movies</title>
</head>
<%
    List<EntityMovie> listaPeliculas = (List<EntityMovie>)request.getAttribute("listaPeliculas");
    EntityUsuario user = (EntityUsuario) session.getAttribute("user");
%>
<body>

<jsp:include page="cabecera.jsp"/>

<table border="">
    <tr>
        <td>
            NOMBRE
        </td>
        <td>
            GENERO
        </td>
        <td>
            DURACIÓN
        </td>
        <td>
            POPULARIDAD
        </td>
        <%
            if(user.getRol().equals("editor")){
        %>
        <td>
            EDITAR
        </td>
        <%
            }
        %>
    </tr>
    <% for (EntityMovie pelicula : listaPeliculas){
    %>
    <tr>
        <td>
            <a href="/users/movie?id=<%= pelicula.getMovieId() %>">
                <%=pelicula.getName()%>
            </a>
        </td>
        <td>
            <%
                for(EntityGenre genre : pelicula.getGenreList()){
            %>
            - <%=genre.getName()%> -
            <%
                }
            %>
        </td>
        <td>
            <%=pelicula.getRuntime()%>
        </td>
        <td>
            <%=pelicula.getPopularity()%>
        </td>
        <%
            if (user.getRol().equals("editor")){
        %>
        <form method="post" action="">+
            <input type="hidden" value="<%=pelicula.getMovieId()%>">
            <input type="submit" value="Editar"/>
        </form>
        <%
            }
        %>
    </tr>
    <%}%>
</table></body>
</html>
