<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.GeneroDTO" %><%--
<%--
  Autor(es): Álvaro Sierra García (100%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Géneros</title>
</head>
<%
    List<GeneroDTO> generos = (List<GeneroDTO>) request.getAttribute("generos");
    GeneroDTO generoEditar = (GeneroDTO) request.getAttribute("generoEditar");
%>
<body>
    <jsp:include page="../cabecera.jsp"/>
    <jsp:include page="cabeceraEditor.jsp"/>
    <h2>Géneros</h2>
    <a href="/generos/nuevo">Añadir nuevo genero</a>
    <table border="1">
        <tr>
            <td>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <% for (GeneroDTO genero: generos){ %>
                    <tr>
                        <td><%=genero.getId()%></td>
                        <td><%=genero.getNombre()%></td>
                        <td><a href="/generos/editar?id=<%=genero.getId()%>">Editar</a></td>
                        <td><a href="/generos/eliminar?id=<%=genero.getId()%>"
                               onclick="return confirm('¿Seguro que quieres eliminarlo?');">Delete</a>
                    </tr>
                    <% } %>
                </table>
            </td>
            <td>
                <% if (generoEditar != null) { %>
                <h3><%= generoEditar.getId() != null ? "Editar Género" : "Nuevo Género" %></h3>
                <form method="post" action="/generos/guardar">
                    <% if (generoEditar.getId() != null) { %>
                    <input type="hidden" name="id" value="<%= generoEditar.getId() %>"/>
                    <% } %>
                    Nombre: <input type="text" name="nombre" value="<%= generoEditar.getNombre() != null ? generoEditar.getNombre() : "" %>"/>
                    <input type="submit" value="Guardar" />
                </form>
                <% } %>
            </td>
        </tr>
    </table>
</body>
</html>