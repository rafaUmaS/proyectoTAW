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
<%--
  Autor(es): Iván Pedraza Díez (80%), resto (20%)
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

<%
    if(user.getRol().equals("editor") || user.getRol().equals("administrador")) {%>
        <form method="get" action="/movies/crear">
            <button>+</button> Añadir nueva Película
        </form>
    <%}
%>
<%
    if(user.getRol().equals("administrador")) {%>

        <form method="get" action="/admin">
            <button>Modo administrador</button>
        </form>
    <%}
%>

<table border="">
    <tr>
        <th>NOMBRE</th>
        <th>GENERO</th>
        <th>DURACIÓN</th>
        <th>POPULARIDAD</th>

        <%
            if(user.getRol().equals("editor") || user.getRol().equals("administrador")){
        %>
        <th>EDITAR</th>
        <th>BORRAR</th>
        <%
            }
        %>
    </tr>
        <%
            for (EntityMovie pelicula : listaPeliculas){
        %>
    <tr>
        <td><a href="/users/movie?id=<%=pelicula.getMovieId()%>"><%=pelicula.getName()%></a></td>
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
        <%
            if(user.getRol().equals("editor") || user.getRol().equals("administrador")){
        %>
        <td>
            <form method="get" action="/movies/editar">
                <input type="hidden" name="id" value="<%=pelicula.getMovieId()%>">
                <input type="submit" value="Editar"/>
            </form>
        </td>
        <td>
            <form method="post" action="/movies/borrar">
                <input type="hidden" name="id" value="<%=pelicula.getMovieId()%>">
                <input type="submit" value="Borrar"/>
            </form>
        </td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
