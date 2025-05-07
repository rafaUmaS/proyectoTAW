<%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 07/05/2025
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<table border="">
    <% for (EntityUsuarioSaveMovie savedMovie : savedMovies){
    %>
    <tr>
        <td>
            <%=savedMovie.getMovieMovie().getName()%>
        </td>

    </tr>
    <%}%>
</table>
</body>
</html>
