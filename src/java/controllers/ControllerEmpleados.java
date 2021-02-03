/*
1) Quitar elementos de Session
2) En lugar de mostrar los String de la sesión, quiero ver los empleados.
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
                html += "<li>" + dato + "</li>";
            }
            html += "</ul>";
            return html;
        }
    }

    public ControllerEmpleados() {
        this.repo = new RepositoryEmpleados();
    }

    public String getTablaEmpleados() throws SQLException {
        ArrayList<Empleado> empleados = this.repo.getEmpleados();
        String html = "";
        for (Empleado emp : empleados) {
            html += "<tr>";
            html += "<td>";
            html += "<a href='webalmacenarempleados.jsp?idempleado=";
            html += emp.getIdEmpleado() + "'>Guardar en Session</a>";
            html += "</td>";
            html += "<td>" + emp.getApellido() + "</td>";
            html += "<td>" + emp.getOficio() + "</td>";
            html += "<td>" + emp.getSalario() + "</td>";
            html += "<td>" + emp.getDepartamento() + "</td>";
            html += "</tr>";
        }
        return html;
    }
}
