<%@ page import="javax.swing.text.html.parser.Entity" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 27/04/2025
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es): Iván Pedraza Díez (90%), Gregorio Merchán Merchán (10%)
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

    <jsp:include page="cabecera.jsp"/>

    <table border="">
        <tr>
            <th>
                NOMBRE
            </th>
            <th>
                GENERO
            </th>
            <th>
                DURACIÓN
            </th>
            <th>
                POPULARIDAD
            </th>
            <th>
                QUITAR
            </th>

        </tr>
        <% for (EntityUsuarioSaveMovie savedMovie : savedMovies){
            if(savedMovie.getName().equals("Favorite Movie")){
        %>
        <tr>
            <td>
                <a href="/users/movie?id=<%=savedMovie.getMovieMovieId().getMovieId()%>"><%=savedMovie.getMovieMovieId().getName()%></a>
            </td>
            <td>
                <%
                    for(EntityGenre genre : savedMovie.getMovieMovieId().getGenreList()){
                %>
                   - <%=genre.getName()%> -
                <%
                    }
                %>
            </td>
            <td>
                <%=savedMovie.getMovieMovieId().getRuntime()%>
            </td>
            <td>
                <%=savedMovie.getMovieMovieId().getPopularity()%>
            </td>
            <td>
                <form method="post" action="/movies/dislike">
                    <input type="hidden" name="id" value="<%=savedMovie.getId()%>">
                    <input type="submit" value="Quitar">
                </form>
            </td>
        </tr>
        <%}}%>
    </table>
</body>
</html>
