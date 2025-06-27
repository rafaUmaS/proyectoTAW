<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: rafas
  Date: 25/05/2025
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%--
  Autor(es):
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Crea un nuevo usuario</h1>
<form:form method="post" action="/users/" modelAttribute="usuario">
    <form:hidden path="userId" />
    Nombre de usuario: <form:input path="username" /><br/><br/>
    Email: <form:input path="email" /><br/><br/>
    Contraseña: <form:input path="password" /><br/><br/>
    Rol: <form:select items="${roles}" path="rol"></form:select><br/><br/>
    <form:button>Crear</form:button>
</form:form>
</body>
</html>
