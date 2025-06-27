
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Autor(es): Rafael Sáaez Arana
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Editar usuario</h1>
<form:form method="post" action="/users/edit" modelAttribute="usuario">
    <form:hidden path="userId" />
    Nombre de usuario: <form:input path="username" /><br/> <br/>
    Email: <form:input path="email" /><br/><br/>
    Contraseña: <form:input path="password" /><br/><br/>
    Rol: <form:select items="${roles}" path="rol"></form:select><br/><br/>
    <form:button>Actualizar</form:button>
</form:form>
</body>
</html>
