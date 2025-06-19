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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author laksh
 */
public class CancelTicket extends HttpServlet {

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        
            rs.setContentType("text/html;charset=UTF-8");
            
            String cid = rq.getParameter("cid");
            
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CancelTicket.class.getName()).log(Level.SEVERE, null, ex);
            }
            
              Connection con;
            
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
                PreparedStatement ps1 = con.prepareStatement("select*from flightbooking where cid=?");
                ps1.setString(1,cid );
                ResultSet re = ps1.executeQuery();
            
                if(re.next()){
                   
               
                PreparedStatement ps3;
                try {
                    ps3 = con.prepareStatement("update flight set tseat=? where fnumber=?");
                    ps3.setString(1,re.getString(1) );
                ps3.setString(2,re.getString(2) );
                int r1 = ps3.executeUpdate();
                
                
                if(r1>0){
                    rs.getWriter().println("<h1>updated...</h1>");
                }else{
                    rs.getWriter().println("<h1>not updated...</h1>");
                }
                    
                    
                    rs.getWriter().println("<h1>BOOKED...</h1>");
                    } catch (SQLException ex) {
                    Logger.getLogger(CancelTicket.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else{
                    rs.getWriter().println("<h1>NOT BOOKED...</h1>");
                }
                
        
 } catch (SQLException ex) {
                Logger.getLogger(CancelTicket.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

   

}
