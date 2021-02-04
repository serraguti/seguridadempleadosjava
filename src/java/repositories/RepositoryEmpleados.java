package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Empleado;
import oracle.jdbc.OracleDriver;

public class RepositoryEmpleados {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        return cn;
    }

    public ArrayList<Empleado> getEmpleados() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from emp";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Empleado> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("EMP_NO");
            String ape = rs.getString("APELLIDO");
            String ofi = rs.getString("OFICIO");
            int sal = rs.getInt("SALARIO");
            int dept = rs.getInt("DEPT_NO");
            Empleado emp = new Empleado(id, ape, ofi, sal, dept);
            lista.add(emp);
        }
        rs.close();
        cn.close();
        return lista;
    }

    public ArrayList<Empleado> getEmpleadosSession(ArrayList<String> idsempleados) throws SQLException {
        //CONVERTIMOS LA COLECCION DE IDS A STRING SEPARADOS POR COMAS
        String datos = "";
        for (String id : idsempleados) {
            datos += id + ",";
        }
        int ultimacoma = datos.lastIndexOf(",");
        datos = datos.substring(0, ultimacoma);
        Connection cn = this.getConnection();
        String sql = "select * from emp where emp_no in (" + datos + ")";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Empleado> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("EMP_NO");
            String ape = rs.getString("APELLIDO");
            String ofi = rs.getString("OFICIO");
            int sal = rs.getInt("SALARIO");
            int deptno = rs.getInt("DEPT_NO");
            Empleado emp = new Empleado(id, ape, ofi, sal, deptno);
            lista.add(emp);
        }
        rs.close();
        cn.close();
        return lista;
    }
}
