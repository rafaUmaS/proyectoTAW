<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityProductionCompanies" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CompanieDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Autor(es): Álvaro Sierra García (100%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Companies</title>
</head>
<%
    List<CompanieDTO> companies = (List<CompanieDTO>) request.getAttribute("companies");
    CompanieDTO companieEditar = (CompanieDTO) request.getAttribute("companieEditar");
%>
<body>
    <jsp:include page="../cabecera.jsp"/>
    <jsp:include page="cabeceraEditor.jsp"/>
<h2>Empresas productoras:</h2>
<a href="/companies/nuevo">Añadir nueva empresa</a>
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
                    <% for (CompanieDTO companie : companies) {%>
                    <tr>
                        <td><%=companie.getId()%></td>
                        <td><%=companie.getName()%></td>
                        <td>
                            <a href="/companies/editar?id=<%=companie.getId()%>">Editar</a>
                        </td>
                        <td>
                            <a href="/companies/eliminar?id=<%=companie.getId()%>"
                               onclick="return confirm('¿Seguro que quieres eliminarlo?');">Eliminar</a>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </td>
            <td>
                <% if (companieEditar != null) { %>
                <h3><%= companieEditar.getId() != null ? "Editar Empresa" : "Nueva Empresa" %></h3>
                <form action="/companies/guardar" method="post">
                    <% if (companieEditar.getId() != null) { %>
                    <input type="hidden" name="id" value="<%=companieEditar.getId()%>"/>
                    <% } %>
                    Nombre: <input type="text" name="name" value="<%=companieEditar.getName() != null ? companieEditar.getName() : "" %>"/>
                    <br/>
                    <input type="submit" value="Guardar"/>
                </form>
                <% } %>
            </td>
        </tr>
    </table>
</body>
</html>
