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


public class CustomerSignup extends HttpServlet {

  
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        rs.setContentType("text/html;charset=UTF-8");
        String name=rq.getParameter("name");
        String email=rq.getParameter("email");
        String password=rq.getParameter("password");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             
            try {
                
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
                PreparedStatement ps = con.prepareStatement("insert into customersignup values(?,?,?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3,password);
               int r= ps.executeUpdate();
                if(r>0){
                    
                    
                    rs.sendRedirect("customer-up-in.html");
    
                }
                else{
                    rs.getWriter().println("<h1>enter valid</h1>");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

   

}
