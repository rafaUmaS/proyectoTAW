<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 16/06/2025
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es): Adrian (100%)
--%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.UsuarioSaveMovieDTO" %>
<%@ page import="es.uma.demospring.myletterbox.dto.MovieDTO" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Películas Recomendadas</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<h1>Películas Recomendadas</h1>

<%
    Map<String, List<UsuarioSaveMovieDTO>> groupedByList = (Map<String, List<UsuarioSaveMovieDTO>>) request.getAttribute("groupedByList");
    Map<Integer,MovieDTO> movies = (Map<Integer,MovieDTO>) request.getAttribute("movies");
    EntityUsuario user = (EntityUsuario)session.getAttribute("user");
    for (String listName : groupedByList.keySet()) {
        List<UsuarioSaveMovieDTO> moviesInList = groupedByList.get(listName);
%>
<div class="lista-container">
    <h2><%= listName.replace(" Recomendado", "") %></h2>
    <div class="peliculas-fila">
        <table border="1" cellpadding="10" cellspacing="5">
            <tr>
                <%
                    for (UsuarioSaveMovieDTO saveMovie : moviesInList) {
                        MovieDTO movie = movies.get(saveMovie.getMovieId());
                %>
                <td>

                    <h4><%= movie.getName() %></h4>
                    <p>⭐ <%= movie.getVoteAverage() %></p>
                    <%if(user.getRol().equals("editor") || user.getRol().equals("administrador")){%>
                    <form method="post" action="/users/remove-recommendation">
                        <input type="hidden" name="saveMovieId" value="<%= saveMovie.getId() %>" />
                        <input type="submit" value="Eliminar" onclick="return confirm('¿Eliminar esta película de la lista?');" />
                    </form>
                    <%}%>
                </td>
                <% } %>
            </tr>
        </table>
    </div>
</div>
<% } %>
</body>
</html>

