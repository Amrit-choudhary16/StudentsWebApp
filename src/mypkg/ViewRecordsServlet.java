package mypkg;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewRecordsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Write the response message, in an HTML page
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<style>table, th, td {border: 1px solid black;}</style>");
            out.println("<title>Welcome Students</title></head>");
            out.println("<body>");
            out.println("<h1>View Records</h1>");
            out.println("<a href=\"welcome\">Back to Welcome</a></br>");
            out.println("<table>");
            out.println("  <tr>");
            out.println("    <th>ID</th>");
            out.println("    <th>First Name</th>");
            out.println("    <th>Last Name</th> ");
            out.println("    <th>Date Of Birth</th>");
            out.println("    <th>Email</th>");
            out.println("    <th>street</th>");
            out.println("    <th>city</th>");
            out.println("    <th>state</th>");
            out.println("    <th>zip</th>");
            out.println("    <th>phone no</th>");
            out.println("    <th>sex</th>");
            out.println("    <th>lunch cost</th>");
            out.println("  </tr>");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_form",
                    "root", "qwe1ASD2#");   // For MySQL
            Statement stmt = conn.createStatement();

            // Step 3: Execute a SQL SELECT query
            String sqlStr = "select * from students";

            ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

            // Step 4: Process the query result set
            while (rset.next()) {
                out.println("<tr>");
                out.println("<td>" + rset.getString("student_id")
                        + "</td> <td> " + rset.getString("first_name")
                        + "</td> <td> " + rset.getString("last_name")
                        + "</td> <td> " + rset.getDate("birth_date")
                        + "</td> <td> " + rset.getString("email")
                        + "</td> <td> " + rset.getString("street")
                        + "</td> <td> " + rset.getString("city")
                        + "</td> <td> " + rset.getString("state")
                        + "</td> <td> " + rset.getString("zip")
                        + "</td> <td> " + rset.getString("phone")
                        + "</td> <td> " + rset.getString("sex")
                        + "</td> <td> " + rset.getString("lunch_cost") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
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