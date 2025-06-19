/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package air;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CustomerLogin extends HttpServlet {

  
    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        rs.setContentType("text/html;charset=UTF-8");
       
        String email=rq.getParameter("t1");
        String password=rq.getParameter("t2");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             
            try {
                
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
                PreparedStatement ps = con.prepareStatement("select*from customersignup where emailphone=? and password=?");
                
                ps.setString(1, email);
                ps.setString(2,password);
                
                
                ResultSet r = ps.executeQuery();
                
                if(r.next()){

                    HttpSession h2 = rq.getSession(true);
                    h2.setAttribute("email", email);
                    rs.sendRedirect("customerhomepage.html");
                 
                    
                }
                else{
                    
                    rs.getWriter().println("<h1>enter valid</h1>");
                   
                }
                ps.close();
                con.close();
                r.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }  

}
