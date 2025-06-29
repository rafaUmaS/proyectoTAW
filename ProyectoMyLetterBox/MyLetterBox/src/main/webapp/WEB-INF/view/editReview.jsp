<%@ page import="es.uma.demospring.myletterbox.entity.EntityReview" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<%--
  Autor(es): Gregorio Merchán Merchán (100%)
--%>

<head>
    <title>Editar Review</title>
</head>

<%
    EntityReview review = (EntityReview) request.getAttribute("review");

%>

<body>
    <h1>Editar Review</h1>

    <form method="post" action="/reviews/guardar">
        <input type="hidden" name="id" value="<%=review.getId()%>">
        Comentario: <input type="text" name="comentario" value="<%=review.getComment()%>">
        <br><br>
        Puntuación: <input type="number" name="puntuacion" min="0" max="100" value="<%=review.getRate()%>">
        <br><br>
        <input type="submit" value="Guardar">
    </form>
    <button onclick="window.location.href='/users/user-reviews'">Volver</button>
</body>
</html>