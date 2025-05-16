<%@ page import="es.uma.demospring.myletterbox.ui.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityGenre" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15/05/2025
  Time: 18:25
  To change this template use File | Settings | File Templates.
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
    List<EntityGenre> generos = (List<EntityGenre>) request.getAttribute("generos");
    Movie.Estado[] estados = (Movie.Estado[]) request.getAttribute("estados");
%>


<body>
    <h1><%=(esCrear ? "Añadir nueva" : "Editar")%> película</h1>
    <form:form method="post" action="/movies/guardar" modelAttribute="pelicula">
        <form:input path="id" type="hidden"></form:input>
        <table>
            <tr>
                <td>Título:</td>
                <td><form:input path="titulo"/></td>
            </tr>
            <tr>
                <td>Descripción:</td>
                <td><form:input path="descripcion"/> </td>
            </tr>
            <tr>
                <td>Duración:</td>
                <td><form:input path="duracion"/></td>
            </tr>
            <tr>
                <td>Idioma original:</td>
                <td><form:input path="idioma"/></td>
            </tr>
            <tr>
                <td>Título en idioma original:</td>
                <td><form:input path="tituloOriginal"/></td>
            </tr>
            <tr>
                <td>popularidad:</td>
                <td><form:input path="popularidad"/></td>
            </tr>
            <tr>
                <td>Presupuesto:</td>
                <td><form:input path="presupuesto"/></td>
            </tr>
            <tr>
                <td>Beneficios:</td>
                <td><form:input path="beneficios"/></td>
            </tr>
            <tr>
                <td>Número de votos:</td>
                <td><form:input path="numVotos"/></td>
            </tr>
            <tr>
                <td>Puntuación media de votos:</td>
                <td><form:input path="mediaVotos"/></td>
            </tr>
            <tr>
                <td>Generos:</td>
                <td>
                    <form:checkboxes path="generos"
                                     items="${generos}"
                                     itemLabel="name"
                                     itemValue="id"/>
                </td>
            </tr>
            <tr>
                <td>Fecha:</td>
                <td><form:input type="date" path="fecha"/></td>
            </tr>
            <tr>
                <td>Estado:</td>
                <td>
                    <form:radiobuttons path="estado" items="${estados}" delimiter="</br>" />
                </td>
            </tr>
            <tr>
                <td><form:button>Guardar</form:button></td>
                <td>

                </td>
            </tr>
        </table>
    </form:form>
    <form method="get" action="/movies/">
        <button>Cancelar</button>
    </form>
</body>
</html>
