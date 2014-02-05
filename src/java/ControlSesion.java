/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;


/**
 *
 * @author Ivan
 */
public class ControlSesion extends HttpServlet {

        @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        Integer contadorAccesos;
        
        synchronized(session){
            contadorAccesos = (Integer)session.getAttribute("contadorAccesos");
            if(contadorAccesos == null){
                contadorAccesos = 0;
            }else{
                contadorAccesos = new Integer(contadorAccesos + 1);
            }
            session.setAttribute("contadorAccessos", contadorAccesos);
        }
        
        try{
            out.println("<html><head><title>Contador de sesiones</title></head><body>");
            out.print("<h2>Accesos: " + contadorAccesos + " en esta sesion.</h2>");
            out.print("<p>Sesion: " + session.getId() +"</h2>");
            out.print("<p>creacion de la sesion : " + new Date(session.getCreationTime()) +"</p>");
            out.print("<p>ultimo acceso de la sesion : " + new Date(session.getLastAccessedTime()) +"</p>");
            out.print("<p>tiempo inactivo de la sesion : " + session.getMaxInactiveInterval() +" seconds </p>");
            out.print("<p><a href='"+request.getRequestURI()+"'>Refrescar</a></p>");
            out.print("<p><a href='"+response.encodeURL(request.getRequestURI())+"'>Refrescar con reescritura de URLs</a></p>");
            out.print("<p><a href='index.html'>Volver</a></p>");
        }finally{
            out.close();
            
        }
    }
}
