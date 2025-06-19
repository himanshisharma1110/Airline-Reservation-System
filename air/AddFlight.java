package air;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddFlight extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null) {
            String flightNumber = request.getParameter("t1");
            String flightName = request.getParameter("t2");
            String airline = request.getParameter("t3");
            String source = request.getParameter("t4");
            String destination = request.getParameter("t5");
            String seatCapacity = request.getParameter("t6");
            String firstClassPrice = request.getParameter("t7");
            String economyClassPrice = request.getParameter("t8");
            String businessClassPrice = request.getParameter("t9");
            String departureDateTime = request.getParameter("t10");
            String arrivalDateTime = request.getParameter("t11");

            Connection con = null;
            PreparedStatement ps = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
                ps = con.prepareStatement(
                        "INSERT INTO flight VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1, flightNumber);
                ps.setString(2, flightName);
                ps.setString(3, airline);
                ps.setString(4, source);
                ps.setString(5, destination);
                ps.setString(6, seatCapacity);
                ps.setString(7, firstClassPrice);
                ps.setString(8, economyClassPrice);
                ps.setString(9, businessClassPrice);
                ps.setString(10, departureDateTime);
                ps.setString(11, arrivalDateTime);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    
                      out.println("<html>");
                      out.println("<head>");
                      out.println("<title>Flight Added</title>");
                      out.println("<style>");
                      out.println("body {");
                      out.println("  background-color: #f0f0f0;");
                      out.println("  font-family: Arial, sans-serif;");
                      out.println("}");
                      out.println(".message {");
                      out.println("  background-color: orange;");
                      out.println("  color: Orange;");
                      out.println("  text-align: center;");
                      out.println("  padding: 20px;");
                      out.println("  margin-top: 20px;");
                      out.println("}");
                      out.println("</style>");
                      out.println("</head>");
                      out.println("<body>");
                      out.println("<div class='message'>");
                      out.println("<h1>Flight Added Successfully</h1><br>");
                      out.println("<h1>Add New Flight</h1>");
                      out.println("</div>");
                      out.println("</body>");
                      out.println("</html>");

                      RequestDispatcher rd = request.getRequestDispatcher("addflight.html");
                      rd.include(request, response);
                } else {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Failed to Add Flight</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Failed to save data</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (ClassNotFoundException | SQLException e) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error: " + e.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Error</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error closing database connection: " + e.getMessage() + "</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else {
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
            out.println("<h1>Kindly login first</h1>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
    }
}
