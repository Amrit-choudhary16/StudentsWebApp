package mypkg;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WelcomeServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();

        // Write the response message, in an HTML page
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>Welcome Students</title></head>");
            out.println("<body>");
            out.println("<h1>Welcome To My App</h1>");
            out.println("<a href=\"view\">View Records</a></br>");
            out.println("<a href=\"add\">Add New Record</a></br>");
            out.println("<a href=\"modify\">Modify a Record</a></br>");
            out.println("<a href=\"delete\">Delete a Record</a></br>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();  // Always close the output writer
        }
    }
}