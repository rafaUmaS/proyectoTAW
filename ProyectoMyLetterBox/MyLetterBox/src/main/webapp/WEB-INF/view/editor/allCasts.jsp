<%@ page import="es.uma.demospring.myletterbox.dto.CastDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CrewDTO" %>
<%@ page import="es.uma.demospring.myletterbox.dto.MovieDTO" %>
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
        List<CastDTO> casts = (List<CastDTO>) request.getAttribute("casts");
        List<CrewDTO> crews = (List<CrewDTO>) request.getAttribute("crews");
        List<MovieDTO> movies = (List<MovieDTO>) request.getAttribute("movies");
        CastDTO castEditar = (CastDTO) request.getAttribute("castEditar");
    %>
<body>
    <jsp:include page="../cabecera.jsp"/>
    <jsp:include page="cabeceraEditor.jsp"/>

    <h2>Listado de Cast</h2>
    <a href="/casts/nuevo">Añadir nuevo cast</a>

    <table border="1">
        <tr>
        <td>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Género</th>
                    <th>Personaje</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (CastDTO c : casts) { %>
                <tr>
                    <td><%= c.getId() %></td>
                    <td><%= c.getName() %></td>
                    <td>
                        <%
                            String genero;
                            Integer g = (c.getGender() == null) ? 0 : c.getGender();
                            if (g == 2) {
                                genero = "Male";
                            } else if (g == 1) {
                                genero = "Female";
                            } else {
                                genero = "Undefined";
                            }
                        %>
                        <%= genero %>
                    </td>
                    <td><%= c.getCharacter() != null ? c.getCharacter() : "" %></td>
                    <td>
                        <a href="/casts/editar?id=<%= c.getId() %>">Editar</a>
                    </td>
                    <td>
                        <a href="/casts/eliminar?id=<%= c.getId() %>"
                           onclick="return confirm('¿Seguro que quieres eliminarlo?');">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </table>
        </td>
        <% if (castEditar != null) { %>
        <td>
            <form method="post" action="/casts/guardar">
                <% if (castEditar.getId() != null) { %>
                <input type="hidden" name="id" value="<%= castEditar.getId() %>" />
                <% } %>
            <table>
                <tr>
                    <td colspan="2"><h3><%= castEditar.getId() != null ? "Editar Cast" : "Nuevo Cast" %></h3></td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="name" value="<%= castEditar.getName() != null ? castEditar.getName() : "" %>" /><br/></td>
                </tr>
                <tr>
                    <td>Género:</td>
                    <td>
                        <input type="radio" name="gender" value="2"
                                <%=castEditar.getGender() != null && castEditar.getGender() == 2 ? "checked" : ""%>/> Male
                        <input type="radio" name="gender" value="1"
                                <%=castEditar.getGender() != null && castEditar.getGender() == 1 ? "checked" : ""%>/>Female
                        <input type="radio" name="gender" value="0"
                                <%=castEditar.getGender() != null && castEditar.getGender() == 0 ? "checked" : ""%>/>Undefined
                    </td>
                </tr>
                <tr>
                    <td>Personaje: </td>
                    <td><input type="text" name="character" value="<%= castEditar.getCharacter() != null ? castEditar.getCharacter() : "" %>"/></td>
                </tr>
                <tr>
                    <td>Crew:</td>
                    <td>
                        <select name="crew">
                            <% for (CrewDTO crew: crews) {
                                String peli = "";
                                for (MovieDTO m : movies)
                                if(crew.getMovieId() == m.getMovieId()){
                                    peli = m.getName();
                                }
                            %>
                            <option value="<%=crew.getId()%>"
                                    <%= (castEditar.getCrew() != null && castEditar.getCrew() == crew.getId()) ? "selected" : "" %>>
                                <%=crew.getNombrePersona() + " - " + crew.getCrewRole() + " - " + peli%>
                            </option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Guardar"/></td>
                </tr>
            </table>
            </form>
        </td>
        <% } %>
        </tr>
    </table>
</body>
</html>
