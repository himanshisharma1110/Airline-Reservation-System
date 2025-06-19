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
import javax.servlet.http.HttpSession;

public class SearchFlight extends HttpServlet {

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        rs.setContentType("text/html");
        PrintWriter out = rs.getWriter();
        
        String source = rq.getParameter("s");
        String destination = rq.getParameter("d");
        
        HttpSession h2 = rq.getSession(false);
      
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet r = null;
        
        try {
              if(h2!=null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
            ps = con.prepareStatement("SELECT * FROM flight WHERE source=? AND destination=?");
            ps.setString(1, source);
            ps.setString(2, destination);
            r = ps.executeQuery();
            
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
                        "                   <th>Flight Number</th>\n" +
                        "                   <th>Flight Name</th>\n" +
                        "                   <th>Airline</th>\n" +
                        "                   <th>Source</th>\n" +
                        "                   <th>Destination</th>\n" +
                        "                   <th>Seat Capacity</th>\n" +
                        "                   <th>First Class Ticket Price</th>\n" +
                        "                   <th>Economy Class Ticket Price</th>\n" +
                        "                   <th>Business Class Ticket Price</th>\n" +
                        "                   <th>Departure Date & Time</th>\n" +
                        "                   <th>Arrival Date & Time</th>\n" +
                        "                   <th>Book</th>\n" +
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
                                "  <td><a href='customerbooking.html?flightNumber=" +r.getString(1)+ "&flightName=" + r.getString(2) + "&airline=" +r.getString(3) + "&source=" + r.getString(4) + "&destination=" + r.getString(5) + "&seatCapacity=" + r.getString(6) + "&fcPrice=" + r.getString(7) + "&ecPrice=" + r.getString(8) + "&bcPrice=" + r.getString(9) + "&departureDate=" + r.getString(10) + "&arrivalDate=" + r.getString(11) + "'>Book</a></td>\n" +
                                "</tr>\n");
                }
            } else {
                out.println("No flights found");
            }

            out.println("</table>\n" +
                        "             </div>\n" +
                        "        </div>\n" +
                        "    </body>\n" +
                        "</html>\n");
              }else{
                  out.println("<h1>plzzz login first...</h1>");
              }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchFlight.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (r != null) r.close(); } catch (SQLException e) { /* Ignored */ }
            try { if (ps != null) ps.close(); } catch (SQLException e) { /* Ignored */ }
            try { if (con != null) con.close(); } catch (SQLException e) { /* Ignored */ }
        }
    }
}
