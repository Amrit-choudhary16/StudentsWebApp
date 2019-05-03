package mypkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteRecordsServlet extends HttpServlet {
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
            out.println("<title>Delete Students</title></head>");
            out.println("<body>");
            out.println("<h1>Delete Records</h1>");
            out.println("<a href=\"welcome\">Back to Welcome</a></br>");
            out.println("<form name='form' action='deleteSearch'>");
            out.println(" <br> Last name:<br> ");
            out.println(" <input type=\"text\" name=\"lastname\" >");
            out.println(" <input type=\"submit\" value=\"Submit\">");
            out.println("</form>");


        } catch (Exception e) {
        }
    }
}


