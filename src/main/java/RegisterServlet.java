

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printOut = response.getWriter();
        
        String name = request.getParameter("userName");
        String age = request.getParameter("userAge");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/college_app";
            String user = "root";
            String pass = "";
            Connection con = DriverManager.getConnection(url, user, pass);
            
            String query = "INSERT INTO test_table (name, age) VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(query);
            
            st.setString(1, name);
            st.setString(2, age);
            int result = st.executeUpdate();
            
            if(result > 0) {
                printOut.println("<h1> Your account is registered as </h1>");
                printOut.println("<h3>Name: "+ name + "</h3>");
                printOut.println("<h3>Age:" +age+ "</h3>");
            } else {
                printOut.println("<h3>Sorry! Your data is not registered.</h3>");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            printOut.println("<h1>Exception: " + ex.getMessage() + "</h1>");
        
        }
    }
}
