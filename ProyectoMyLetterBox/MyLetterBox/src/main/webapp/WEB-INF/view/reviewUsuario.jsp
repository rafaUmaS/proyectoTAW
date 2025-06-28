    <%@ page import="java.util.List" %>
    <%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %>
    <%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
    <%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %>
    <%@ page import="es.uma.demospring.myletterbox.entity.EntityReview" %>

    <%--
      Autor(es): Gregorio Merchán Merchán (100%)
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Tus comentaross</title>
    </head>
    <%
        List<EntityReview> listaReviews = (List<EntityReview>)request.getAttribute("userReviews");
        EntityUsuario user = (EntityUsuario) session.getAttribute("user");
    %>
    <body>
    <jsp:include page="cabecera.jsp"/>

    <table border="">
        <tr>
            <th>PELICULA</th>
            <th>COMENTARIO</th>
            <th>PUNTUACIÓN</th>
            <th>FECHA DE CREACIÓN</th>
            <th>EDITAR</th>
            <th>BORRAR</th>
        </tr>
        <%
            for (EntityReview review : listaReviews){
                EntityMovie movies = review.getMovieMovieId();

        %>
        <tr>
            <td><a href="/users/movie?id=<%=movies.getMovieId()%>"><%= movies.getName()%></a></td>
            <td><%= review.getComment()%></td>
            <td><%= review.getRate()%></td>
            <td><%= review.getCreateTime()%></td>
            <td><form method="post" action="/reviews/editar">
                <input type="hidden" name="id" value="<%=review.getId()%>">
                <input type="submit" value="Editar">
            </form></td>
            <td><form method="post" action="/reviews/borrar">
                <input type="hidden" name="id" value="<%=review.getId()%>">
                <input type="submit" value="Borrar">
            </form></td>
        </tr>
        <% }%>
    </table>
    </body>
    </html>