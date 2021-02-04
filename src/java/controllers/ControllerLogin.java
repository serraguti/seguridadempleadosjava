package controllers;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import models.Empleado;
import repositories.RepositoryEmpleados;

public class ControllerLogin {

    HttpSession session;
    RepositoryEmpleados repo;

    public ControllerLogin() {
        this.repo = new RepositoryEmpleados();
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean existeEmpleado(String apellido, int idempleado) throws SQLException {
        Empleado emp = this.repo.existeEmpleado(apellido, idempleado);
        if (emp == null) {
            //NO EXISTE
            return false;
        } else {
            //EXISTE EL EMPLEADO Y SE HA VALIDADO
            session.setAttribute("EMPLEADO", emp);
            return true;
        }
    }
}
