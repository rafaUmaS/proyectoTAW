<%@ page import="es.uma.demospring.myletterbox.entity.EntityUsuario" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityPersona" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demospring.myletterbox.dto.PersonaDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 19/06/2025
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Autor(es): Iván Pedraza Díez (100%)
--%>
<html>
<head>
    <title>All person data</title>
</head>
<%
    List<PersonaDTO> personas = (List<PersonaDTO>) request.getAttribute("personas");
    EntityUsuario user = (EntityUsuario) session.getAttribute("user");
    Integer asc = (Integer) request.getAttribute("asc");
%>
<body>
<jsp:include page="cabeceraAnalist.jsp"/>

<form:form method="post" action="/analist/personas/filtrar" modelAttribute="filtro">
  Filtrar por nombre: <form:input path="nombre"></form:input>
  <form:button>Filtrar</form:button>
</form:form>

<table border="">
    <tr>
        <th><a href="/analist/personas/ordenar?filtro=name&asc=<%=(asc!=null? asc : 0)%>">NOMBRE</a></th>
        <th><a href="/analist/personas/ordenar?filtro=num_peliculas&asc=<%=(asc!=null? asc : 0)%>">NÚM. PELICULAS</a></th>
    </tr>
    <%
        for (PersonaDTO persona : personas){
    %>
    <tr>
        <td><a href="/analist/persona?id=<%=persona.getId()%>"><%=persona.getName()%></a></td>
        <td><%=persona.getNumeroPeliculas()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
