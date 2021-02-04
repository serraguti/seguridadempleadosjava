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
        String eliminar = request.getParameter("eliminar");
        if (eliminar != null){
            //QUITAR DE LA SESION
            controlleremp.eliminarEmpleadoSession(eliminar);
        }
        %>
        
        <h1>MOSTRAR SESSION</h1>
        <a href="webalmacenarempleados.jsp">
            Almacenar empleados
        </a>
        <%=controlleremp.getEmpleadosSession()%>
    </body>
</html>
