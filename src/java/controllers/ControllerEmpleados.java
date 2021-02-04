/*
1) Quitar elementos de Session CHECK
2) En lugar de mostrar los String de la sesión, quiero ver los empleados. CHECK
3) Cuando almacenemos un empleado, quitamos el LINK de almacenar.
 */
package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import models.Empleado;
import repositories.RepositoryEmpleados;

public class ControllerEmpleados {

    RepositoryEmpleados repo;
    HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String almacenarEmpleadoSession(String idempleado) {
        ArrayList<String> sessionempleados;
        //PARA ALMACENAR, PRIMERO TENEMOS QUE SABER SI
        //EXISTE LA SESION O NO
        if (session.getAttribute("EMPLEADOS") == null) {
            //CREAMOS LA LISTA POR PRIMERA VEZ
            sessionempleados = new ArrayList<>();
        } else {
            //YA TENEMOS UNA LISTA EN LA SESION Y LA RECUPERAMOS
            sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        }
        //YA TENEMOS UNA LISTA (NUEVA O RECUPERADA)
        //DEBEMOS AÑADIR EL EMPLEADO
        //SI NO QUIERO REPETIDOS, COMPROBAMOS SI YA EXISTE O NO
        if (sessionempleados.contains(idempleado) == false) {
            //NO EXISTE, LO AÑADIMOS
            sessionempleados.add(idempleado);
            //ACTUALIZAMOS LA SESSION
            session.setAttribute("EMPLEADOS", sessionempleados);
            return "Almacenados: " + sessionempleados.size();
        } else {
            return "Ya existe en la sesión: " + idempleado;
        }
    }

    public String getDatosSession() {
        //NECESITAMOS RECUPERAR LA LISTA DE LA SESION
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        //ES POSIBLE QUE NO EXISTA LA SESION
        if (sessionempleados == null) {
            return "<h1 style='color:red'>No hay nada en Session</h1>";
        } else {
            String html = "<ul>";
            for (String dato : sessionempleados) {
                html += "<li>" + dato + " | ";
                html += "<a href='webmostrarempleadossession.jsp?eliminar=";
                html += dato + "'>Quitar de sesión</a>";
                html += "</li>";
            }
            html += "</ul>";
            return html;
        }
    }

    public void eliminarEmpleadoSession(String idempleado) {
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        sessionempleados.remove(idempleado);
        //SI NO EXISTEN MAS EMPLEADOS, DEBEMOS ELIMINAR EMPLEADOS
        //DE SESSION
        if (sessionempleados.size() == 0) {
            //ELIMINAMOS SESSION
            session.setAttribute("EMPLEADOS", null);
        } else {
            //ACTUALIZAMOS SESSION
            session.setAttribute("EMPLEADOS", sessionempleados);
        }
    }

    public ControllerEmpleados() {
        this.repo = new RepositoryEmpleados();
    }

    public String getTablaEmpleados() throws SQLException {
        ArrayList<Empleado> empleados = this.repo.getEmpleados();
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        String html = "";
        for (Empleado emp : empleados) {
            html += "<tr>";
            html += "<td>";
            String empno = String.valueOf(emp.getIdEmpleado());
            if (sessionempleados == null) {
                //PINTAMOS EL ENLACE ALMACENAR
                html += "<a href='webalmacenarempleados.jsp?idempleado=";
                html += emp.getIdEmpleado() + "'>Guardar en Session</a>";
            } else if (sessionempleados.contains(empno) == false) {
                //PINTAMOS EL ENLACE ALMACENAR
                html += "<a href='webalmacenarempleados.jsp?idempleado=";
                html += emp.getIdEmpleado() + "'>Guardar en Session</a>";
            } else {
                //PINTAMOS CUALQUIER DIBUJO
                html += "<img src='images/check.jpg' style='width:25px;height:25px;'/>";
            }
            html += "</td>";
            html += "<td>" + emp.getApellido() + "</td>";
            html += "<td>" + emp.getOficio() + "</td>";
            html += "<td>" + emp.getSalario() + "</td>";
            html += "<td>" + emp.getDepartamento() + "</td>";
            html += "</tr>";
        }
        return html;
    }

    public String getEmpleadosSession() throws SQLException {
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        if (sessionempleados == null) {
            //NO HAY NADA EN LA SESION
            return "<h1 style='color:red'>No hay empleados en Session</h1>";
        } else {
            ArrayList<Empleado> empleados
                    = this.repo.getEmpleadosSession(sessionempleados);
            String html = "<ul>";
            for (Empleado emp : empleados) {
                html += "<li>" + emp.getApellido() + " | ";
                html += "<a href='webmostrarempleadossession.jsp?eliminar=";
                html += emp.getIdEmpleado() + "'>Quitar sesión</a>";
                html += "</li>";
            }
            html += "</ul>";
            return html;
        }
    }
}
