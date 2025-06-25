<%@ page import="es.uma.demospring.myletterbox.dto.CastDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CrewDTO" %>
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
        CastDTO cast = (CastDTO) request.getAttribute("cast");
    %>
<body>
    <jsp:include page="../cabecera.jsp"/>
    <jsp:include page="cabeceraEditor.jsp"/>
    <h2>Listado de Cast</h2>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Género</th>
            <th>Personaje</th>
            <th>Acciones</th>
        </tr>
        <% for (CastDTO c : casts) { %>
        <tr>
            <td><%= c.getName() %></td>
            <td>
                <%
                    Integer g = c.getGender();
                    String genero;
                    if (g == 1) {
                        genero = "Male";
                    } else if (g == 2) {
                        genero = "Female";
                    } else {
                        genero = "Undefined";
                    }
                %>
                <%= genero %>
            </td>
            <td><%= c.getCharacterName() %></td>
            <td>
                <a href="/casts/editar?id=<%= c.getId() %>">Editar</a>
            </td>
        </tr>
        <% } %>
    </table>

    <br/>
    <a href="/casts/nuevo">Añadir nuevo</a>

    <% if (cast != null) { %>
    <h3><%= cast.getId() != null ? "Editar Cast" : "Nuevo Cast" %></h3>
    <form method="post" action="/casts/guardar">
        <% if (cast.getId() != null) { %>
        <input type="hidden" name="id" value="<%= cast.getId() %>" />
        <% } %>

        Nombre: <input type="text" name="name" value="<%= cast.getName() != null ? cast.getName() : "" %>" /><br/>

        Género:
        <input type="radio" name="gender" value="1"
            <%=cast.getGender() != null && cast.getGender() == 1 ? "checked" : ""%>/> Male
        <input type="radio" name="gender" value="2"
            <%=cast.getGender() != null && cast.getGender() == 2 ? "checked" : ""%>/>Female
        <input type="radio" name="gender" value="2"
            <%=cast.getGender() != null && cast.getGender() == 0 ? "checked" : ""%>/>Undefined
        <br/>
        Personaje: <input type="text" name="characterName" value="<%= cast.getCharacterName() != null ? cast.getCharacterName() : "" %>" /><br/>
        <input type="submit" value="Guardar" />
        <br/>
        <select name="crew">
            <% for (CrewDTO crew: crews) { %>
            <option value="<%=crew.getId()%>"
                    <%= (cast.getCrew() != null && cast.getCrew() == crew.getId()) ? "selected" : "" %>>
                <%=crew.getPERSONAid().getName()%>
            </option>
            <% } %>
        </select>
    </form>
    <% } %>
</body>
</html>
