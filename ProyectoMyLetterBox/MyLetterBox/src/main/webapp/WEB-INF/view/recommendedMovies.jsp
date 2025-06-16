<%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 16/06/2025
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuarioSaveMovie" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Películas Recomendadas</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<h1>Películas Recomendadas</h1>

<%
    Map<String, List<EntityUsuarioSaveMovie>> groupedByList = (Map<String, List<EntityUsuarioSaveMovie>>) request.getAttribute("groupedByList");

    for (String listName : groupedByList.keySet()) {
        List<EntityUsuarioSaveMovie> moviesInList = groupedByList.get(listName);
%>
<div class="lista-container">
    <h2><%= listName.replace(" Recomendado", "") %></h2>
    <div class="peliculas-fila">
        <table border="1" cellpadding="10" cellspacing="5">
            <tr>
                <%
                    for (EntityUsuarioSaveMovie saveMovie : moviesInList) {
                        EntityMovie movie = saveMovie.getMovieMovieId();
                %>
                <td>

                    <h4><%= movie.getName() %></h4>
                    <p>⭐ <%= movie.getVoteAverage() %></p>
                    <form method="post" action="/users/remove-recommendation" style="display:inline;">
                        <input type="hidden" name="saveMovieId" value="<%= saveMovie.getId() %>" />
                        <input type="submit" value="Eliminar" onclick="return confirm('¿Eliminar esta película de la lista?');" />
                    </form>

                </td>
                <% } %>
            </tr>
        </table>
    </div>
</div>
<% } %>
</body>
</html>

