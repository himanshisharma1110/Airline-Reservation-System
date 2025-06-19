
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
public class FlightBooking extends HttpServlet {

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        try {
            rs.setContentType("text/html;charset=UTF-8");
            
            String source = rq.getParameter("source");
            String destination = rq.getParameter("destination");
            String departure = rq.getParameter("departure");
            String arrival = rq.getParameter("arrival");
            String airlines = rq.getParameter("airlines");
            String clas = rq.getParameter("class");
            String adult = rq.getParameter("adult");
            String children = rq.getParameter("children");
            String infant = rq.getParameter("infant");
            String flightno = rq.getParameter("flightno");
            String flightname = rq.getParameter("flightname");
            String cname = rq.getParameter("cname");
            String cphone = rq.getParameter("cphone");
            String cemail = rq.getParameter("cemail");
            String cid = rq.getParameter("cid");
            int totalticket = Integer.parseInt(rq.getParameter("totalticket"));
            String customerreferanceno= cid+cname;
            float totalbill=0.0f;
            
            
            
            
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
              Connection con;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Airline","root","Mukul4628H");
                PreparedStatement ps1 = con.prepareStatement("select*from flight where fnumber=?");
                ps1.setString(1,flightno );
                ResultSet re = ps1.executeQuery();
                
                if(re.next()){
                if(clas.equals("Economy Class")){
                   totalbill=totalticket*Float.parseFloat(re.getString(8));
                    }
                    if(clas.equals("Business Class")){
                      totalbill=totalticket*Float.parseFloat(re.getString(9));
                    }
                    if(clas.equals("First Class")){
                       totalbill=totalticket*Float.parseFloat(re.getString(7));
                    }
                    rs.getWriter().println("<h1>Bill cal...</h1>");
                }else{
                    rs.getWriter().println("<h1>Bill not cal...</h1>");
                }
                
                
                    
                if(Integer.parseInt(re.getString(6))>0){    
                    
                PreparedStatement ps = con.prepareStatement("insert into flightbooking values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, source);
                ps.setString(2, destination);
                ps.setString(3, departure);
                ps.setString(4, arrival);
                ps.setString(5, airlines);
                ps.setString(6, clas);
                ps.setString(7, adult);
                ps.setString(8, children);
                ps.setString(9, infant);
                ps.setString(10, flightno);
                ps.setString(11, flightname);
                ps.setString(12, cname);
                ps.setString(13, cphone);
                ps.setString(14, cemail);
                ps.setString(15, cid);
                ps.setInt(16, totalticket);
                ps.setFloat(17, totalbill);
                ps.setString(18, customerreferanceno);

                
                int r = ps.executeUpdate();
                if(r>0){
                   
                 int tseats=Integer.parseInt(re.getString(6))-totalticket;
                PreparedStatement ps3 = con.prepareStatement("update flight set tseat=? where fnumber=?");
                ps3.setString(1,String.valueOf(tseats) );
                ps3.setString(2,flightno );
                int r1 = ps3.executeUpdate();
                if(r1>0){
                    rs.getWriter().println("<h1>updated...</h1>");
                }else{
                    rs.getWriter().println("<h1>not updated...</h1>");
                }
                    
                    
                    rs.getWriter().println("<h1>BOOKED...</h1>");
                }else{
                    rs.getWriter().println("<h1>NOT BOOKED...</h1>");
                }
                ps.close();
                con.close();
                }else{
                     rs.getWriter().println("<h1>Tickets not available....</h1>");
                }
            } catch (SQLException ex) {
                rs.getWriter().println("<h1>NOT BOOKED PLzz enter Uniq costomer id...</h1>");
            }
               
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FlightBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }

   

}
