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
  Autor(es): Iván Pedraza Díez (100%)
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
            <%
                if(user.getRol().equals("editor") || user.getRol().equals("administrador")){
            %>
                <th>
                    EDITAR
                </th>
            <%
                }
            %>
        </tr>
        <% for (EntityUsuarioSaveMovie savedMovie : savedMovies){
        %>
        <tr>
            <td>
                <%=savedMovie.getMovieMovieId().getName()%>
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
            <%
                if (user.getRol().equals("editor") || user.getRol().equals("administrador")){
            %>
                <form method="post" action="">
                    <input type="hidden" value="<%=savedMovie.getMovieMovieId().getMovieId()%>">
                    <input type="submit" value="Editar"/>
                </form>
            <%
                }
            %>
        </tr>
        <%}%>
    </table>
</body>
</html>
