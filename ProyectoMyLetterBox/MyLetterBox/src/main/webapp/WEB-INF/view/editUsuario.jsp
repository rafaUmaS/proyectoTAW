
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Autor(es): Rafael Sáaez Arana
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<%
    boolean esCrear = (boolean) request.getAttribute("esCrear");
    boolean exist = (Boolean) request.getAttribute("exist");
%>
<body>
<h1><%=(esCrear ? "Añadir nuevo" : "Editar")%> usuario </h1>

<form:form method="post"  action="${esCrear ? '/users/' : '/users/edit'}" modelAttribute="usuario">
    <form:hidden path="userId" />
    Nombre de usuario:
    <%if(esCrear){%>
         <form:input path="username" disabled="${!esCrear}"/><br/>
         <form:errors path="username" cssStyle="color:red"/>
        <%if(exist){%>
        <p style="color:red">El nombre de usuario ya existe</p>
        <%}%>
    <%}else{%>
        <form:input path="username" readonly="true"/><br/>
    <%}%>


    <br><br/>
    Email: <form:input path="email" /><br/>
    <form:errors path="email" cssStyle="color:red"/>
    <br/><br/>
    Contraseña: <form:input path="password" /><br/>
    <form:errors path="password" cssStyle="color:red"/>
    <br/><br/>
    Rol: <form:select items="${roles}" path="rol"></form:select><br/>
    <form:errors path="rol" cssStyle="color:red"/>
    <br/><br/>
    <form:button>Guardar</form:button>
</form:form>

</body>
</html>
