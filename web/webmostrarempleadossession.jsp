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
        <ul>
            <li>
                <a href="index.html">Home</a>
            </li>
            <li>
                <a href="webalmacenarempleados.jsp">
                    Almacenar empleados
                </a>
            </li>
            <li>
                <a href="webmostrarempleadossession.jsp">
                    Mostrar empleados Session
                </a>
            </li>
            <li>
                <a href="proteccion/usuarioempleado.jsp">
                    Zona Empleado
                </a>
            </li>
        </ul>        
        <a href="webalmacenarempleados.jsp">
            Almacenar empleados
        </a>
        <%=controlleremp.getEmpleadosSession()%>
    </body>
</html>
