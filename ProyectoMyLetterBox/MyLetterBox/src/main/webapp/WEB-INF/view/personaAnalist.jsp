<%@ page import="es.uma.demospring.myletterbox.dto.PersonaDTO" %>
<%@ page import="es.uma.demospring.myletterbox.entity.EntityCrew" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CrewDTO" %>
<%@ page import="es.uma.demospring.myletterbox.dto.CastDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="es.uma.demospring.myletterbox.dto.MovieDTO" %><%--
  Created by IntelliJ IDEA.
  User: Ivan Pedraza
  Date: 25/06/2025
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Datos persona</title>
</head>
<%
    PersonaDTO persona = (PersonaDTO) request.getAttribute("persona");
    CrewDTO crewSelected = (CrewDTO) request.getAttribute("crewSelected");
    List<CrewDTO> crewList = (List<CrewDTO>) request.getAttribute("crewList");
    Map<Integer, MovieDTO> movieMap = (Map<Integer, MovieDTO>) request.getAttribute("movieMap");
%>
<body>
<h1>Datos de la persona: <%=persona.getName()%></h1>
<table border="0">
    <tr>
        <td>Nombre completo:</td>
        <td><%=persona.getName()%></td>
    </tr>
    <tr>
        <td>Número de películas en las que ha participado:</td>
        <td><%=persona.getNumeroPeliculas()%></td>
    </tr>
    <tr>
        <td>
            <br>
        </td>
    </tr>
    <tr>
        <td>Películas en las que ha participado:</td>
        <td>
            <table border="1">
                <tr>
                    <th>NOMBRE</th>
                    <th>ROL/ES</th>
                </tr>
                <%
                    for (CrewDTO crew : crewList){
                %>
                <tr>
                    <td><%=movieMap.get(crew.getMovieId()).getName()%></td>
                    <td>
                        <form method="get" action="/analist/persona/selectedCrew">
                            <input type="hidden" name="crewId" value="<%=crew.getId()%>">
                            <input type="hidden" name="id" value="<%=persona.getId()%>">
                            <input type="submit" value="Ver rol">
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </table>
        </td>
        <td><%=(crewSelected!=null? "===>" : "")%></td>
        <td>
            <table border="0">
                <%
                    if(crewSelected!=null){
                %>
                <tr>
                    <td>PELICULA SELECIONADA:</td>
                    <td><%=movieMap.get(crewSelected.getMovieId()).getName()%></td>
                </tr>
                <tr>
                    <td>ROL EN LA PELICULA:</td>
                    <td>
                        <table border="1">
                            <tr>
                                <td><%=crewSelected.getCrewRole()%></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
                <tr>
                    <%
                        if(!crewSelected.getCastList().isEmpty() && !crewSelected.getCastList().isEmpty() && crewSelected.getCastList().get(0).getCharacter()!=null){
                    %>
                    <td>CAST:</td>
                    <td>
                        <table border="1">
                            <tr>
                                <th>PERSONAJE</th>
                                <th>GÉNERO</th>
                            </tr>
                            <%
                                List<CastDTO> castList = crewSelected.getCastList();
                                for (CastDTO cast : castList){
                                    if(cast.getCharacter()!=null){
                            %>
                            <tr>
                                <td><%=cast.getCharacter()%></td>
                                <td><%=(cast.getGender()==2? "Male" : "Female")%></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </table>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <%
                    }else{
                %>
                <tr>
                    <td>(Seleccione una pelicula para ver el rol)</td>
                </tr>
                <%
                    }
                %>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <form method="get" action="/analist/personas">
                <input type="submit" value="Volver">
            </form>
        </td>
        <td>
            <form method="get" action="/analist/persona">
                <input type="hidden" name="id" value="<%=persona.getId()%>">
                <input type="submit" value="Limpiar rol">
            </form>
        </td>
    </tr>
</table>

</body>
</html>
