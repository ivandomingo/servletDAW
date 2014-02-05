/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 *
 * @author Ivan
 */
public class AccesoDatos extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String username = "root";
            String password = "";
            String url="jdbc:mysql://localhost/tiendalibros";
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            
            String sql = "select * from libros where autor ='"+request.getParameter("autor")+"';";

            out.println("<html><head><title>Resultado de la cosulta</title></head><body>");
            out.println("<h3>Gracias por la consulta</h3>");
            out.println("<p>Tu consulta es: "+sql+"</p>");
            
            ResultSet resultSet = statement.executeQuery(sql);
            int count = 0;
            while(resultSet.next()){
                out.print("<p>"+resultSet.getString("autor")
                        +", "+ resultSet.getString("titulo")
                        +" ,"+ resultSet.getDouble("precio")+ "</p>");
                count++;
            }
            
            out.println("<p>==== "+count+" registros encontrados ====</p>");
            out.print("<p><a href='index.html'>Volver</a></p>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            out.close();  
        try{
            statement.close();
            connection.close();      
        }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
    }
}
