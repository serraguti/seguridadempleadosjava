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
        <h1>Log In Empleados</h1>
        <form method="post">
            <label>Apellido: </label>
            <input type="text" name="apellido" required/><br/>
            <label>Id empleado: </label>
            <input type="number" name="cajaid" required/><br/>
            <button type="submit">Log In</button>
        </form>
        <%
        String apellido = request.getParameter("apellido");
        String dato = request.getParameter("cajaid");
        if (apellido != null){
            int idempleado = Integer.parseInt(dato);
            boolean acceso = controllerlogin.existeEmpleado(apellido, idempleado);
            if (acceso == true){
                response.sendRedirect("protegida/usuarioempleado.jsp");
            }else{
                %>
                <h1 style="color:red">Usuario/Password incorrectos</h1>
                <%
            }
        }
        %>
    </body>
</html>
