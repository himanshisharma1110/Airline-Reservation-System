/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package air;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MyTickets extends HttpServlet {

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        rs.setContentType("text/html;charset=UTF-8");
        String cid = rq.getParameter("cid");
        
         HttpSession h2 = rq.getSession(false);
         PrintWriter out = rs.getWriter();
        try {
            if(h2!=null){
            Class.forName("com.mysql.cj.jdbc.Driver");
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
                    PreparedStatement ps=con.prepareStatement("select*from flightbooking where cid=?");
                    ps.setString(1, cid);
                    ResultSet r =ps.executeQuery();
                    out.println("<h1 style='text-align:center;'>LIST OF ALL FLIGHTS</h1>");
            out.println("<html>\n" +
                        "    <head>\n" +
                        "        <title>TODO supply a title</title>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "        <style>\n" +
                        "        table {\n" +
                        "          border-collapse: collapse;\n" +
                        "          border-spacing: 0;\n" +
                        "          width: 100%;\n" +
                        "          border: 1px solid #ddd;\n" +
                        "           border: black ;\n" +
                        "            border-style: ridge;\n" +
                        "            background-color: #ffffff;\n" +
                        "        }\n" +
                        "\n" +
                        "        th, td {\n" +
                        "          text-align: left;\n" +
                        "          padding: 8px;\n" +
                        "        }\n" +
                        "\n" +
                        "        tr:nth-child(even){background-color: #f2f2f2}\n" +
                        "        body{\n" +
                        "            background-color: #cc8552;\n" +
                        "        }\n" +
                        "       \n" +
                        "        </style>\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <div style=\"text-align: center;\">\n" +
                        "            <br><br><h4 >The Available Flights</h4>\n" +
                        "            <img style=\"border-radius: 100%; height: 25%; width: 25%;\" src=\"img/s1.png\" alt=\"\"/><br>\n" +
                        "        </div>\n" +
                        "        <br><div>\n" +
                        "             <div style=\"overflow-x:auto;\">\n" +
                        "               <br><table>\n" +
                        "                <thead >\n" +
                        "                 <tr>\n" +
                        "                   <th>Source</th>\n" +
                        "                   <th>Destination</th>\n" +
                        "                   <th>Departure Date & Time</th>\n" +
                        "                   <th>Arrival DAte & Time </th>\n" +
                        "                   <th>Airlines</th>\n" +
                        "                   <th>Class</th>\n" +
                        "                   <th>Adult</th>\n" +
                        "                   <th>Children</th>\n" +
                        "                   <th>infant</th>\n" +
                        "                   <th>Fight no</th>\n" +
                        "                   <th>Flight Name</th>\n" +
                        "                   <th>Customer Name</th>\n" +
                        "                   <th>Customer Phone No</th>\n" +
                        "                   <th>Customer Email</th>\n" +
                        "                   <th>Customer id</th>\n" +
                        "                   <th>Customer Booked Tickets</th>\n" +
                        "                   <th>Total Bill</th>\n" +
                        "                   <th>Customer Reference no</th>\n" +
                        "                 </tr>\n" +
                        "                </thead>\n");

            if (r != null) {
                while (r.next()) {
                    out.println("<tr>\n" +
                                "  <td>" + r.getString(1) + "</td>\n" +
                                "  <td>" + r.getString(2) + "</td>\n" +
                                "  <td>" + r.getString(3) + "</td>\n" +
                                "  <td>" + r.getString(4) + "</td>\n" +
                                "  <td>" + r.getString(5) + "</td>\n" +
                                "  <td>" + r.getString(6) + "</td>\n" +
                                "  <td>" + r.getString(7) + "</td>\n" +
                                "  <td>" + r.getString(8) + "</td>\n" +
                                "  <td>" + r.getString(9) + "</td>\n" +
                                "  <td>" + r.getString(10) + "</td>\n" +
                                "  <td>" + r.getString(11) + "</td>\n" +
                                "  <td>" + r.getString(12) + "</td>\n" +
                                "  <td>" + r.getString(13) + "</td>\n" +
                                "  <td>" + r.getString(14) + "</td>\n" +
                                "  <td>" + r.getString(15) + "</td>\n" +
                                "  <td>" + r.getString(16) + "</td>\n" +
                                "  <td>" + r.getString(17) + "</td>\n" +
                                "  <td>" + r.getString(18) + "</td>\n" +
                                "  <td><form   action=\"MyTickets\" method=\"post\">\n" +
"                                        <input type='submit' value='Cencel'/>\n" +
"                                        </form><br></td>\n" +
                                        
 "                           \n");
                    
                    
                }
            } else {
                out.println("No flights found");
            }
                } catch (SQLException ex) {
                    Logger.getLogger(MyTickets.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
