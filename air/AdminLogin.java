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


public class AdminLogin extends HttpServlet {

  
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        rs.setContentType("text/html;charset=UTF-8");
        String uid=rq.getParameter("t1");
        String uname=rq.getParameter("t2");
        String password=rq.getParameter("t3");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             
            try {
                
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
                PreparedStatement ps = con.prepareStatement("select*from adminlogin where uid=? and uname=? and password=?");
                ps.setInt(1, Integer.parseInt(uid));
                ps.setString(2, uname);
                ps.setString(3,password);
                ResultSet r= ps.executeQuery();
                if(r.next()){
                    
                    HttpSession h1 = rq.getSession(true);
                    h1.setAttribute("uid", uid);
                    rs.sendRedirect("afterloginindex.html");
                   
                  
                    
                }
                else{
                    PrintWriter out = rs.getWriter();
                      out.println("<html>");
                      out.println("<head>");
                      out.println("<title>Login Required</title>");
                      out.println("<style>");
                      out.println("body {");
                      out.println("  background-color: #f0f0f0;");
                      out.println("  font-family: Arial, sans-serif;");
                      out.println("}");
                      out.println(".message {");
                      out.println("  background-color: black;");
                      out.println("  color: #fff;");
                      out.println("  text-align: center;");
                      out.println("  padding: 20px;");
                      out.println("  margin-top: 20px;");
                      out.println("}");
                      out.println("</style>");
                      out.println("</head>");
                      out.println("<body>");
                      out.println("<div class='message'>");
                      out.println("<h1>Invalid Details</h1><br>");
                      out.println("<h1>Please Try Again</h1>");
                      out.println("</div>");
                      out.println("</body>");
                      out.println("</html>");

                      RequestDispatcher rd = rq.getRequestDispatcher("index.html");
                      rd.include(rq, rs);
                    
                }
                
                r.close();
                ps.close();
                con.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

   

}
