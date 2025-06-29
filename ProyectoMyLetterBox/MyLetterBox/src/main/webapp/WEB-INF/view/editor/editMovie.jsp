<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Autor(es): Álvaro Sierra García (100%)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    boolean esCrear = (boolean) request.getAttribute("esCrear");
%>

<head>
    <title><%=(esCrear ? "Añadir nueva" : "Editar")%> película</title>
</head>

<%
    List<GeneroDTO> generos = (List<GeneroDTO>) request.getAttribute("generos");
    List<CrewDTO> crews = (List<CrewDTO>) request.getAttribute("crews");
    List<CompanieDTO> empresas = (List<CompanieDTO>) request.getAttribute("empresas");
    List<CountryDTO> paises = (List<CountryDTO>) request.getAttribute("paises");
    MovieDTO.Estado[] estados = (MovieDTO.Estado[]) request.getAttribute("estados");
%>


<body>
    <h1><%=(esCrear ? "Añadir nueva" : "Editar")%> película</h1>
    <form:form method="post" action="/movies/guardar" modelAttribute="pelicula">
        <form:input path="movieId" type="hidden"></form:input>
        <table>
            <tr>
                <td>Título:</td>
                <td>
                    <form:input path="name" size="50"/>
                    <form:errors path="name" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Descripción:</td>
                <td><form:textarea path="description" cols="50" rows="10"/></td>
            </tr>
            <tr>
                <td>Duración:</td>
                <td>
                    <form:input path="duration" type="number" size="5"/>
                    <form:errors path="duration" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Idioma original:</td>
                <td>
                    <form:input path="language" size="20"/>
                    <form:errors path="language" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Título en idioma original:</td>
                <td><form:input path="originalTittle" size="50"/></td>
            </tr>
            <tr>
                <td>popularidad:</td>
                <td>
                    <form:input path="popularity" type="number" step="0.1" size="5"/>
                    <form:errors path="popularity" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Presupuesto:</td>
                <td>
                    <form:input path="budget" type="number" size="20"/>
                    <form:errors path="budget" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Beneficios:</td>
                <td>
                    <form:input path="revenue" type="number" size="20"/>
                    <form:errors path="revenue" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Número de votos:</td>
                <td>
                    <form:input path="voteNumber" type="number"/>
                    <form:errors path="voteNumber" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Puntuación media de votos:</td>
                <td>
                    <form:input path="voteAverage" type="number" step="0.1" size="4"/>
                    <form:errors path="voteAverage" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Generos:</td>
                <td>
                    <form:checkboxes path="generos"
                                     items="${generos}"
                                     itemLabel="nombre"
                                     itemValue="id"
                                     delimiter="<br/>"/>
                    <br/>
                    <form:errors path="generos" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Fecha:</td>
                <td>
                    <form:input type="date" path="date"/>
                    <form:errors path="date" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Estado:</td>
                <td>
                    <form:radiobuttons path="estado"
                                       items="${estados}"
                                       delimiter="</br>"/>
                </td>
            </tr>
            <tr>
                <td>Empresas productoras:</td>
                <td>
                    <form:checkboxes path="empresas"
                                     items="${empresas}"
                                     itemLabel="name"
                                     itemValue="id"
                                     delimiter="<br/>"/>
                    <form:errors path="empresas" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td>Paises de producción:</td>
                <td>
                    <form:checkboxes path="paises"
                                     items="${paises}"
                                     itemLabel="name"
                                     itemValue="iso31661"
                                     delimiter="<br/>"/>
                    <form:errors path="paises" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><form:button>Guardar</form:button></td>
            </tr>
        </table>
    </form:form>
    <form method="get" action="/movies/">
        <button>Cancelar</button>
    </form>
</body>
</html>
