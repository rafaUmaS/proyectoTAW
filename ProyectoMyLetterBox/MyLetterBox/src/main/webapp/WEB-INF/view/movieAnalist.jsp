<%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityCrew" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityCast" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 04/06/2025
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es): Iván Pedraza Díez (100%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Datos pelicula</title>
</head>
<%
    EntityMovie movie = (EntityMovie) request.getAttribute("movie");
    List<EntityCrew> crewList = movie.getCrewList();
%>
<body>
<h1>Datos de la pelicula: <%=movie.getName()%></h1>
<table border="0">
    <tr>
        <td>Descripción general:</td>
        <td><%=movie.getOverview()%></td>
    </tr>
    <tr>
        <td>Popularidad:</td>
        <td><%=movie.getPopularity()%></td>
    </tr>
    <tr>
        <td>Fecha de salida:</td>
        <td><%=movie.getReleaseDate()%></td>
    </tr>
    <tr>
        <td>Presupuesto:</td>
        <td><%=movie.getBudget()%></td>
    </tr>
    <tr>
        <td>Beneficio:</td>
        <td><%=movie.getRevenue()%></td>
    </tr>
    <tr>
        <td>Duración:</td>
        <td><%=movie.getRuntime()%> min.</td>
    </tr>
    <tr>
        <td>Estado:</td>
        <td><%=movie.getStatus()%></td>
    </tr>
    <tr>
        <td>Valoración media:</td>
        <td><%=movie.getVoteAverage()%></td>
    </tr>
    <tr>
        <td>Número de votos:</td>
        <td><%=movie.getVoteCount()%></td>
    </tr>
    <tr>
        <td>Idioma original:</td>
        <td><%=movie.getOriginalLanguage()%></td>
    </tr>
    <tr>
        <td>Título original:</td>
        <td><%=movie.getOriginalTitle()%></td>
    </tr>
    <tr>
        <td>Géneros:</td>
        <td>
        <%
            List<EntityGenre> generos = movie.getGenreList();
            if(generos!=null && !generos.isEmpty()){
        %>
            <table border="1">
                <tr>
            <%
                for (EntityGenre genero : generos){
            %>
                    <td><%=genero.getName()%></td>
            <%
                }
            %>
                </tr>
            </table>
        <%
            }else {
        %>
        <p>No se ha encontrado ningún género asociado.</p>
        <%
            }
        %>
        </td>
    </tr>
    <tr>
        <td>
            <br>
        </td>
    </tr>
    <tr>
        <td>
            CREW:
        </td>
        <td>
            <%
                if(crewList != null && !crewList.isEmpty()){
            %>
            <table border="1">
                <tr>
                    <th>NOMBRE</th>
                    <th>ROL</th>
                </tr>
                <%
                    for (EntityCrew crewPersona : crewList){
                %>
                <tr>
                    <td><%=crewPersona.getPERSONAid().getName()%></td>
                    <td><%=crewPersona.getCrewRole()%></td>
                </tr>
                <%
                    }
                %>
            </table>
            <%
            }else {
            %>
            <p>(Crew no encontrada)</p>
            <%
                }
            %>
        </td>
    </tr>
    <tr>
        <td>
            <br>
        </td>
    </tr>
    <tr>
        <td>
            CAST:
        </td>
        <td>
            <%
                if(crewList != null && !crewList.isEmpty()){
            %>
            <table border="1">
                <tr>
                    <th>ACTOR</th>
                    <th>PERSONAJE</th>
                    <th>GÉNERO</th>
                </tr>
                <%
                    for (EntityCrew crewPersona : crewList){
                        List<EntityCast> castList = crewPersona.getCastList();
                        if (castList!=null && !castList.isEmpty()){
                            for (EntityCast castCharacter : castList){
                                if (castCharacter.getCharacter()!=null){
                %>
                <tr>
                    <td><%=castCharacter.getName()%></td>
                    <td><%=castCharacter.getCharacter()%></td>
                    <td><%=(castCharacter.getGender() == 2? "Male" : "Female")%></td>
                </tr>
                <%
                                }
                            }
                        }
                    }
                %>
            </table>
            <%
                }else {
            %>
            <p>(Cast no encontrado)</p>
            <%
                }
            %>
        </td>
    </tr>
    <tr>
        <td>
            <form method="get" action="/analist/movies">
                <input type="submit" value="Volver">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
