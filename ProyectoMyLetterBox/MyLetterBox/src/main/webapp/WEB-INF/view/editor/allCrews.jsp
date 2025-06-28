<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.PersonaDTO" %>
<%@ page import="es.uma.demospring.myletterbox.dto.MovieDTO" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CastDTO" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CrewDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Autor(es): Álvaro Sierra García (100%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Casts</title>
</head>
<%
    List<CrewDTO> crews = (List<CrewDTO>) request.getAttribute("crews");
    List<PersonaDTO> personas = (List<PersonaDTO>) request.getAttribute("personas");
    List<MovieDTO> movies = (List<MovieDTO>) request.getAttribute("movies");
    List<CastDTO> casts = (List<CastDTO>) request.getAttribute("casts");
    CrewDTO crewEditar = (CrewDTO) request.getAttribute("crewEditar");
%>
<body>
<jsp:include page="../cabecera.jsp"/>
<jsp:include page="cabeceraEditor.jsp"/>

<h2>Listado de Crews</h2>
<a href="/crews/nuevo">Añadir nuevo crew</a>

<table border="1">
    <tr>
        <td>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Persona</th>
                    <th>Película</th>
                    <th>Rol</th>
                    <th>Casts</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (CrewDTO crew : crews) {
                    PersonaDTO p = null;
                    MovieDTO m = null;
                    List<CastDTO> lcasts = new ArrayList<>();
                    for(PersonaDTO persona : personas) {
                        if(crew.getPERSONAid().equals(persona.getId())){
                            p = persona;
                        }
                    }

                    for(MovieDTO movie : movies) {
                        if(crew.getMovieId().equals(movie.getMovieId())){
                            m = movie;
                        }
                    }

                    for(CastDTO cast : casts) {
                        if(crew.getCastIds() != null && crew.getCastIds().contains(cast.getId())){
                            lcasts.add(cast);
                        }
                    }
                %>
                <tr>
                    <td><%= crew.getId() %></td>
                    <td><%= (p != null)? p.getName() : "" %></td>
                    <td><%= (m != null)? m.getName() : "" %></td>
                    <td><%= crew.getCrewRole() %></td>
                    <td>
                        <% for (CastDTO cast : lcasts) { %>
                            <%= (cast.getCharacter() != null) ? cast.getCharacter() : "" %><br/>
                        <% } %>
                    </td>
                    <td>
                        <a href="/crews/editar?id=<%= crew.getId() %>">Editar</a>
                    </td>
                    <td>
                        <a href="/crews/eliminar?id=<%= crew.getId() %>"
                           onclick="return confirm('¿Seguro que quieres eliminarlo?');">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </table>
        </td>
        <% if (crewEditar != null) { %>
        <td>
            <form:form method="post" action="/crews/guardar" modelAttribute="crewEditar">
                <% if (crewEditar.getId() != null) { %>
                <form:hidden path="id"/>
                <% } %>
                <table>
                    <tr>
                        <td colspan="2"><h3><%= crewEditar.getId() != null ? "Editar Crew" : "Nuevo Crew" %></h3></td>
                    </tr>
                    <tr>
                        <td>Persona:</td>
                        <td>
                            <form:select path="pERSONAid"
                                         items="${personas}"
                                         itemValue="id"
                                         itemLabel="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Película:</td>
                        <td>
                            <form:select path="movieId"
                                         items="${movies}"
                                         itemValue="movieId"
                                         itemLabel="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Rol: </td>
                        <td><form:input path="crewRole"/></td>
                    </tr>
                    <tr>
                        <td>Cast:</td>
                        <td>
                            <form:select multiple="true"
                                         path="castIds"
                                         items="${casts}"
                                         itemValue="id"
                                         itemLabel="character"
                                         size="10"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><form:button>Guardar</form:button></td>
                    </tr>
                </table>
            </form:form>
        </td>
        <% } %>
    </tr>
</table>
</body>
</html>
