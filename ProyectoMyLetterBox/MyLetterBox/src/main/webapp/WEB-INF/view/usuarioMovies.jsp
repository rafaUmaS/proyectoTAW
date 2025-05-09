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
            <td>
                NOMBRE
            </td>
            <td>
                GENERO
            </td>
            <td>
                DURACIÓN
            </td>
            <td>
                POPULARIDAD
            </td>
            <%
                if(user.getRol().equals("editor")){
            %>
                <td>
                    EDITAR
                </td>
            <%
                }
            %>
        </tr>
        <% for (EntityUsuarioSaveMovie savedMovie : savedMovies){
        %>
        <tr>
            <td>
                <%=savedMovie.getMovieMovie().getName()%>
            </td>
            <td>
                <%
                    for(EntityGenre genre : savedMovie.getMovieMovie().getGenres()){
                %>
                   - <%=genre.getName()%> -
                <%
                    }
                %>
            </td>
            <td>
                <%=savedMovie.getMovieMovie().getRuntime()%>
            </td>
            <td>
                <%=savedMovie.getMovieMovie().getPopularity()%>
            </td>
            <%
                if (user.getRol().equals("editor")){
            %>
                <form method="post" action="">+
                    <input type="hidden" value="<%=savedMovie.getMovieMovie().getId()%>">
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
