<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.*" %>
<%@ page import="es.uma.demospring.myletterbox.dao.UsuarioSaveMovieRepository" %>
<%@ page import="es.uma.demospring.myletterbox.dao.ReviewRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Autor(es): Gregorio Merchán Merchán (75%), Adrián Huete Peña (25%)
--%>
<html>
<head>
    <title>Detalles de Película</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<%
    EntityMovie movie = (EntityMovie) request.getAttribute("movie");
    EntityUsuario user = (EntityUsuario)session.getAttribute("user");

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



<% if(user.getRol().equals("recomendador") || user.getRol().equals("administrador")) { %>

<button onclick="toggleRecommender()" type="button">Recomendar</button>
<% String error = request.getParameter("error"); %>
<% if ("yaExiste".equals(error)) { %>
<p>Esta película ya está en esa lista.</p>
<% } %>

<% if ("nombreDuplicado".equals(error)) { %>
<p>Ya existe una lista con un nombre similar.</p>
<% } %>
<div id="recommenderPanel" style="display:none; margin-top:10px;">

    <!-- Formulario para crear nueva lista y añadir -->
    <form method="post" action="/users/recomendar" style="margin-bottom: 10px;">
        <input type="hidden" name="movieId" value="<%= movie.getMovieId() %>" />
        <input type="text" name="nombreListaBase" id="nuevaListaNombre" placeholder="Nombre de la nueva lista" />
        <input type="hidden" name="crearNueva" value="true" />
        <input type="submit" value="Crear nueva lista y añadir" />
    </form>

    <p>O selecciona una lista existente:</p>

    <%
        List<String> listas = (List<String>) request.getAttribute("listasRecomendadas");
        if (listas != null) {
            for(String lista : listas) {
                String nombreVisible = lista.replace(" Recomendado", "");
    %>
    <form method="post" action="/users/recomendar" style="display:inline-block; margin-right:5px;">
        <input type="hidden" name="movieId" value="<%= movie.getMovieId() %>" />
        <input type="hidden" name="nombreLista" value="<%= lista %>" />
        <input type="hidden" name="crearNueva" value="false" />

        <input type="submit" value="<%= nombreVisible %>" />
    </form>
    <%      }
    }
    %>
</div>

<script>
    function toggleRecommender() {
        var panel = document.getElementById("recommenderPanel");
        panel.style.display = (panel.style.display === "none") ? "block" : "none";
    }
</script>
<% } %>



<br><br><br>

<form action="/movies/like" method="post">
    <input type="hidden" name="movieId" value="<%= movie.getMovieId() %>"/>
    <input type="hidden" name="userId" value="<%= user.getUserId() %>"/>
    <button type="submit">Me gusta</button>
</form>
<button onclick="crearReview()">Review</button>


<div id="mostrarCrearReview" style="display: none;" >
    <br>
    <form action="/reviews/crear" method="post">
        <input type="hidden" name="movieId" value="<%= movie.getMovieId() %>"/>
        <input type="hidden" name="userId" value="<%= user.getUserId() %>"/>
        <p>Escribe tu review</p>
        <input type="text" id="comment" name="comment" required>
        <p>Dale una puntuación (0-100)</p>
        <input type="text" id="rate" name="rate" required>
        <!-- <input type="range" id="rate" name="rate" min="0" max="100"> -->
        <br> <br>
         <button type="submit">Enviar</button>
     </form>
 </div>

 <script>
     function crearReview(){
         var review = document.getElementById("mostrarCrearReview");
         if (review.style.display === "none" || review.style.display === "") {
             review.style.display = "block";
         } else {
             review.style.display = "none";
         }
     }
 </script>

 <br><br>

 <!-- Botones de navegación -->
<table border="">
    <tr>
        <td>
            <button onclick="Tabla('cast')">Cast</button>
        </td>
        <td>
            <button onclick="Tabla('crew')">Crew</button>
        </td>
        <td>
            <button onclick="Tabla('details')">Details</button>
        </td>
        <td>
            <button onclick="Tabla('reviews')">Reviews</button>
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
    <br>
    <p>
        <%
            List<EntityReview> reviews = movie.getReviewList();
            if (reviews != null) {
            for (EntityReview r : reviews) {
        %>      
               <p>Comentario: <%= r.getComment() %></p>
               <p>Puntuación: <%= r.getRate() %>/100</p>
               <p>Fecha: <%= r.getCreateTime() %></p>
                <br>
    <%
        }
    } else {
    %>
    <p>No hay reviews para esta película.</p>
    <%
        }
    %>
    </p>

</div>


<!-- Script para mostrar/ocultar secciones -->
<script>
    function Tabla(tabId) {
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