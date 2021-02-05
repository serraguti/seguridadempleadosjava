<%@page import="models.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controllerlogin"
             class="controllers.ControllerLogin"
             scope="request"/>
<%
controllerlogin.setSession(session);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Empleado emp = (Empleado)
            session.getAttribute("EMPLEADO");
        %>
        <h1 style="color:blue">Bienvenido usuario!!!</h1>
        <h2 style="color:red">
           Perfil: <%=emp.getApellido()%>, <%=emp.getOficio()%>
        </h2>
        <a href="usuarioempleado.jsp?cerrar=1">Cerrar sesión</a>
        <ul>
            <li>
                <a href="../index.html">Home</a>
            </li>
            <li>
                <a href="../webalmacenarempleados.jsp">
                    Almacenar empleados
                </a>
            </li>
            <li>
                <a href="../webmostrarempleadossession.jsp">
                    Mostrar empleados Session
                </a>
            </li>
            <li>
                <a href="usuarioempleado.jsp">
                    Zona Empleado
                </a>
            </li>
        </ul>   
        <img src="../images/barney.jpg" alt="" style="width: 150px; height: 200px;"/>
        <%
        String cerrar = request.getParameter("cerrar");
        if (cerrar != null){
            controllerlogin.cerrarSesion();
            response.sendRedirect("../index.html");
        }
        %>
    </body>
</html>
