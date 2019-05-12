package mypkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteSearchRecordsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<style>table, th, td {border: 1px solid black;}</style>");
        out.println("<title>Welcome Students</title></head>");
        out.println("<body>");
        out.println("<a href=\"welcome\">Back to Welcome</a></br>");
        out.println("<table>");
        out.println("  <tr>");
        out.println("    <th>ID</th>");
        out.println("    <th>First Name</th>");
        out.println("    <th>Last Name</th> ");
        out.println("    <th>EMAIL</th> ");
        out.println("    <th></th>");
        out.println("    <th></th>");
        out.println("  </tr>");
        try {

            String lastName = request.getParameter("lastname");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_form",
                    "root", "qwe1ASD2#");   // For MySQL
            Statement stmt = conn.createStatement();

            // Step 3: Execute a SQL SELECT query
            StringBuilder sqlStr = new StringBuilder();
            sqlStr.append("select * from students where last_name = '").append(lastName).append("'");


            ResultSet rset = stmt.executeQuery(sqlStr.toString());  // Send the query to the server

            // Step 4: Process the query result set
            while (rset.next()) {

                out.println(" <tr>");
                out.println("<form method='get' action=\"remove\">");
                out.println("<input type=\"hidden\" name=\"student_id\" value =" + rset.getString("student_id") + " ><br>");
                out.println("<td>" + rset.getString("student_id")
                        + "</td> " +
                        "<td> " + rset.getString("first_name")
                        + "</td> " +
                        "<td> " + rset.getString("last_name")
                        + "</td> " +
                        "<td>" + rset.getString("email")
                                + "</td> " +
                                "<td>" + rset.getString("street")
                                + "</td> " +
                                "<td>" + rset.getString("city")
                                + "</td> " +
                                "<td>" + rset.getString("state")
                                + "</td> " +
                                "<td>" + rset.getString("zip")
                                + "</td> " +
                                "<td>" + rset.getString("phone")
                                + "</td> " +
                                "<td>" + rset.getString("birth_date")
                                + "</td> " +
                        "<td>" + rset.getString("sex")
                                + "</td> " +
                                "<td>" + rset.getString("date_entered")
                                + "</td> " +
                                "<td>" + rset.getString("lunch_cost") + "</td>");


                out.println(" <td> <input type=\"submit\" value=\"Delete\"> </td>");
                out.println(" <td> <input type=\"button\" value=\"Cancel\"> </td>");
                out.println("</form>");
                out.println("  </tr>");
            }


            out.println("</body>");
            out.println("</html>");


        } catch (Exception ex) {
            out.println("<p>Error: " + ex.getMessage() + "</p>");
            out.println("<p>Check Tomcat console for details.</p>");
            ex.printStackTrace();
        } finally {
            out.close();  // Always close the output writer
        }
    }
}