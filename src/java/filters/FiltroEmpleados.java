package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Empleado;

public class FiltroEmpleados implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //NECESITAMOS LAS PETICIONES
        HttpServletRequest peticion = (HttpServletRequest) request;
        //NECESITAMOS LA URI (STRING PETICION)
        String uri = peticion.getRequestURI();
        //NECESITAMOS RECUPERAR EL USUARIO DE LA SESION
        //EN NUESTRO CASO, ES UN Empleado
        Empleado empleado = (Empleado) peticion.getSession().getAttribute("EMPLEADO");
        if (uri.contains("proteccion")) {
            //NECESITAMOS VALIDAR, ZONA PROTEGIDA
            //SI EL EMPLEADO EXISTE, LE DEJAMOS
            //NAVEGAR FELIZ
            if (empleado != null) {
                //NO VA A NAVEGAR FELIZ, DEBEMOS PREGUNTAR SI TIENE
                //PERMISOS SUFICIENTES
                if (empleado.getOficio().equalsIgnoreCase("presidente")
                        || empleado.getOficio().equalsIgnoreCase("director")) {
                    //NAVEGA FELIZ
                    chain.doFilter(request, response);
                } else {
                    //EL EMPLEADO EXISTE, PERO NO TIENE PERMISOS
                    HttpServletResponse respuesta = (HttpServletResponse) response;
                    respuesta.sendRedirect("../accesodenegado.jsp");
                }
            } else {
                //NO SE HA VALIDADO TODAVIA
                HttpServletResponse respuesta = (HttpServletResponse) response;
                respuesta.sendRedirect("../login.jsp");
            }
        } else {
            //ACCESO LIBRE, QUE NAVEGUE FELIZ
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
