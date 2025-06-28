<%@ page import="es.uma.demospring.myletterbox.entity.EntityMovie" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityCrew" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityCast" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %>
<%@ page import="es.uma.demospring.myletterbox.dto.*" %>
<%@ page import="java.util.Map" %><%--
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
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");
    List<CrewDTO> crewList = (List<CrewDTO>) request.getAttribute("crewList");
    Map<Integer, PersonaDTO> personaMap = (Map<Integer, PersonaDTO>) request.getAttribute("personaMap");
%>
<body>
<h1>Datos de la pelicula: <%=movie.getName()%></h1>
<table border="0">
    <tr>
        <td>Descripción general:</td>
        <td><%=movie.getDescription()%></td>
    </tr>
    <tr>
        <td>Popularidad:</td>
        <td><%=movie.getPopularity()%></td>
    </tr>
    <tr>
        <td>Fecha de salida:</td>
        <td><%=movie.getDate()%></td>
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
        <td><%=movie.getDuration()%> min.</td>
    </tr>
    <tr>
        <td>Estado:</td>
        <td><%=movie.getEstado()%></td>
    </tr>
    <tr>
        <td>Valoración media:</td>
        <td><%=movie.getVoteAverage()%></td>
    </tr>
    <tr>
        <td>Número de votos:</td>
        <td><%=movie.getVoteNumber()%></td>
    </tr>
    <tr>
        <td>Idioma original:</td>
        <td><%=movie.getLanguage()%></td>
    </tr>
    <tr>
        <td>Título original:</td>
        <td><%=movie.getOriginalTittle()%></td>
    </tr>
    <tr>
        <td>Géneros:</td>
        <td>
        <%
            List<GeneroDTO> generos = movie.getGenerosDTO();
            if(generos!=null && !generos.isEmpty()){
        %>
            <table border="1">
                <tr>
            <%
                for (GeneroDTO genero : generos){
            %>
                    <td><%=genero.getNombre()%></td>
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
                    for (CrewDTO crewPersona : crewList){
                %>
                <tr>
                    <td><%=personaMap.get(crewPersona.getPERSONAid()).getName()%></td>
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
                    <th>ACTOR/ACTRIZ</th>
                    <th>PERSONAJE</th>
                    <th>GÉNERO</th>
                </tr>
                <%
                    for (CrewDTO crewPersona : crewList){
                        List<CastDTO> castList = crewPersona.getCastList();
                        if (castList!=null && !castList.isEmpty()){
                            for (CastDTO castCharacter : castList){
                                if (castCharacter.getCharacter()!=null){
                %>
                <tr>
                    <td><%=castCharacter.getName()%></td>
                    <td><%=castCharacter.getCharacter()%></td>
                    <td><%=(castCharacter.getGender() == null)? "Undefined" : (castCharacter.getGender() == 2? "Male" : "Female")%></td>
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
