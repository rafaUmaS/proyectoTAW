<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 04/06/2025
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>All movies data</title>
</head>
<%
    List<EntityMovie> listaPeliculas = (List<EntityMovie>)request.getAttribute("listaPeliculas");
    EntityUsuario user = (EntityUsuario) session.getAttribute("user");
%>
<body>
<jsp:include page="cabeceraAnalist.jsp"/>

<table border="">
    <tr>
        <th><a href="/analist/movies/ordenar?filtro=name">NOMBRE</a></th>
        <th>GENEROS</th>
        <th><a href="/analist/movies/ordenar?filtro=runtime">DURACIÓN</a></th>
        <th><a href="/analist/movies/ordenar?filtro=popularity">POPULARIDAD</a></th>
        <th><a href="/analist/movies/ordenar?filtro=vote_average">VALORACIÓN MEDIA</a></th>
        <th><a href="/analist/movies/ordenar?filtro=vote_count">NÚMERO DE VALORACIONES</a></th>
        <th><a href="/analist/movies/ordenar?filtro=budget">PRESUPUESTO</a></th>
        <th><a href="/analist/movies/ordenar?filtro=revenue">BENEFICIO</a></th>
        <th><a href="/analist/movies/ordenar?filtro=original_language">IDIOMA ORIGINAL</a></th>
    </tr>
    <%
        for (EntityMovie pelicula : listaPeliculas){
    %>
    <tr>
        <td><a href="/analist/movie?id=<%=pelicula.getMovieId()%>"><%=pelicula.getName()%></a></td>
        <td>
            <%
                for(EntityGenre genre : pelicula.getGenreList()){
            %>
            - <%=genre.getName()%> -
            <%
                }
            %>
        </td>
        <td><%=pelicula.getRuntime()%></td>
        <td><%=pelicula.getPopularity()%></td>
        <td><%=pelicula.getVoteAverage()%></td>
        <td><%=pelicula.getVoteCount()%></td>
        <td><%=pelicula.getBudget()%></td>
        <td><%=pelicula.getRevenue()%></td>
        <td><%=pelicula.getOriginalLanguage()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

