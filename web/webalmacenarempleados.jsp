<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlleremp"
             class="controllers.ControllerEmpleados"
             scope="request"/>
<%
controlleremp.setSession(session);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String idempleado = request.getParameter("idempleado");
        if (idempleado != null){
            %>
            <h1 style="color:red">
                <%=controlleremp.almacenarEmpleadoSession(idempleado)%>
            </h1>
            <%
        }
        %>
        <h1>ALMACENAR</h1>
        <a href="webmostrarempleadossession.jsp">
            Ver session
        </a>
        <table border="1">
            <thead>
                <tr>
                    <th></th>
                    <th>Apellido</th>
                    <th>Oficio</th>
                    <th>Salario</th>
                    <th>Departamento</th>
                </tr>
            </thead>
            <tbody>
                <%=controlleremp.getTablaEmpleados()%>
            </tbody>
        </table>
    </body>
</html>
