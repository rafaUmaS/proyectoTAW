<%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityProductionCompanies" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detalles de Película</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<%
    EntityMovie movie = (EntityMovie) request.getAttribute("movie");
%>

<h1><%= movie.getName() %></h1>

<p>
    <%
        List<EntityGenre> genres = movie.getGenreList();
        for (int i = 0; i < genres.size(); i++) {
            out.print(genres.get(i).getName());
            if (i < genres.size() - 1) {
                out.print(" - ");
            }
        }
    %>
</p>
<p> <%= movie.getRuntime() %> minutos</p>
<h3>Sinopsis</h3>
<p><%= movie.getOverview() %></p>

<button onclick="history.back()">Añadir a lista</button>
<button onclick="history.back()">Me gusta</button>
<button onclick="history.back()">Review</button>

<br><br><br>

<!-- Botones de navegación -->
<table border="">
    <tr>
        <td>
            <button onclick="showTab('cast')">Cast</button>
        </td>
        <td>
            <button onclick="showTab('crew')">Crew</button>
        </td>
        <td>
            <button onclick="showTab('details')">Details</button>
        </td>
        <td>
            <button onclick="showTab('reviews')">Reviews</button>
        </td>

    </tr>
</table>

<!-- Secciones -->
<div id="cast" style="display:block;">
    <p>Aquí va el CAST (aún no implementado)</p>
</div>

<div id="crew" style="display:none;">
    <p>Aquí va el CREW (aún no implementado)</p>
</div>

<div id="details" style="display:none;">
    <p><strong>Fecha de estreno:</strong> <%= movie.getReleaseDate() %></p>
    <p><strong>Popularidad:</strong> <%= movie.getPopularity() %></p>
    <p><strong>Idioma original:</strong> <%= movie.getOriginalLanguage() %></p>
    <p><strong>Presupuesto:</strong> <%= movie.getBudget() %> $</p>
    <p><strong>Ingresos:</strong> <%= movie.getRevenue() %> $</p>
    <p><strong>Estado:</strong> <%= movie.getStatus() %></p>
    <p><strong>Votos:</strong> <%= movie.getVoteCount() %> votos con media <%= movie.getVoteAverage() %></p>
    <p><strong>Productoras</strong></p>
    <ul>
        <% for (EntityProductionCompanies pc : movie.getProductionCompaniesList()) { %>
        <li><%= pc.getName() %></li>
        <% } %>
    </ul>
</div>

<div id="reviews" style="display:none;">
    <p>Opiniones y puntuaciones de usuarios</p>
</div>

<!-- Script para mostrar/ocultar secciones -->
<script>
    function showTab(tabId) {
        var tabs = ['cast', 'crew', 'details', 'reviews'];
        for (var i = 0; i < tabs.length; i++) {
            var tab = document.getElementById(tabs[i]);
            tab.style.display = (tabs[i] === tabId) ? 'block' : 'none';
        }
    }
</script>
<br>
<button onclick="window.location.href='http://localhost:8080/users/movies'">Volver</button>

</body>
</html>