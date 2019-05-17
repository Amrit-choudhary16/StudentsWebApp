package mypkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddRecordsServlet extends HttpServlet {
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
            out.println("<h1>Add Records</h1>");

            out.println("<a href=\"welcome\">Back to Welcome</a></br>");

            out.println("<form method='get' action=\"register\">");
            out.println("        First name:<br>");
            out.println("  <input type=\"text\" name=\"firstname\" > <br> Last name:<br> ");
            out.println("  <input type=\"text\" name=\"lastname\" ><br><br>EMail:<br>");
            out.println("  <input type=\"text\" name=\"email\"  >  <br><br> street:<br>");
            out.println("  <input type=\"text\" name=\"streetname\"  > <br><br>city:<br>");
            out.println("  <input type=\"text\" name=\"city\" ><br><br>state:<br>");
            out.println("  <input type=\"text\"  name=\"state\" <br><br> Zip:<br>");
            out.println("  <input type=\"text\" name=\"zip\">  <br><br>  phone:<br>");
            out.println("  <input type=\"text\" name=\"number\" ><br><br> birth date:<br>");
            out.println("  <input type=\"date\" name=\"DOB\" > <br><br> sex:<br>");
            out.println("  <input type=\"radio\" name=\"gender\" value=\"M\" checked> Male<br>");
            out.println("  <input type=\"radio\" name=\"gender\" value=\"F\"> Female<br>  <br><br> lunch cost<br>");
            out.println("  <input type=\"text\" name=\"cost\"  > <br><br>");
            out.println("  <input type=\"submit\" value=\"Submit\">");
            out.println("  <input type=\"reset\" value=\"Reset\">");
            out.println(" <br>");
            out.println("</form>");


            out.println("<form method='get' action=\"bulkupload\">");
            out.println("  <input type=\"submit\" value=\"Bulk Upload\">");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();  // Always close the output writer
        }
    }
}