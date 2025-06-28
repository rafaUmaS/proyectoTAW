<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CountryDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Autor(es): Álvaro Sierra García (100%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Countries</title>
</head>
<%
    List<CountryDTO> countries = (List<CountryDTO>) request.getAttribute("countries");
    CountryDTO countryEditar = (CountryDTO) request.getAttribute("countryEditar");
%>
<body>
<jsp:include page="../cabecera.jsp"/>
<jsp:include page="cabeceraEditor.jsp"/>
<h2>Países productores:</h2>
<a href="/countries/nuevo">Añadir nuevo pais</a>
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
                <% for (CountryDTO country : countries) {%>
                <tr>
                    <td><%=country.getIso31661()%></td>
                    <td><%=country.getName()%></td>
                    <td>
                        <a href="/countries/editar?id=<%=country.getIso31661()%>">Editar</a>
                    </td>
                    <td>
                        <a href="/countries/eliminar?id=<%=country.getIso31661()%>"
                           onclick="return confirm('¿Seguro que quieres eliminarlo?');">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </table>
        </td>
        <td>
            <% if (countryEditar != null) { %>
            <h2><%= request.getAttribute("modoCreacion") != null && (Boolean) request.getAttribute("modoCreacion") ? "Nuevo País" : "Editar País" %></h2>
            <form:form action="/countries/guardar" method="post" modelAttribute="countryEditar">
                <table>
                    <tr>
                        <td>Código ISO:</td>
                        <td>
                            <form:input path="iso31661"/>
                            <form:errors path="iso31661" cssStyle="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Nombre:</td>
                        <td>
                            <form:input path="name"/>
                            <form:errors path="name" cssStyle="color:red"/>
                        </td>
                    </tr>
                </table>
                <form:button>Guardar</form:button>
            </form:form>
            <% } %>
        </td>
    </tr>
</table>
</body>
</html>
