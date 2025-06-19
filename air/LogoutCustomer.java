
package air;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutCustomer extends HttpServlet {

   
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)
            throws ServletException, IOException {
        rs.setContentType("text/html;charset=UTF-8");
        
          HttpSession h2 = rq.getSession(true);
          
        if (h2 != null) {
            h2.invalidate(); // Invalidate the session
            rs.sendRedirect("customer-up-in.html");
        }

        // Redirect to login page or any other page after logout
        
    }
      
    


}
