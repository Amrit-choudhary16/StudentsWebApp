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

public class ModifySearchRecordsServlet extends HttpServlet {
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
        try {

            String lastName = request.getParameter("lastname");

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_form",
                    "root", "123456");   // For MySQL
            Statement stmt = conn.createStatement();

            // Step 3: Execute a SQL SELECT query
            StringBuilder sqlStr = new StringBuilder();
            sqlStr.append("select * from students where last_name = '").append(lastName).append("'");

            System.out.println(sqlStr.toString());

            ResultSet rset = stmt.executeQuery(sqlStr.toString());  // Send the query to the server

            // Step 4: Process the query result set
            while (rset.next()) {
//            out.println("<tr>");
//            out.println("<td>" + rset.getString("student_id")
//                    + "</td> <td> " + rset.getString("first_name")
//                    + "</td> <td> " + rset.getString("last_name")
//                    + "</td> <td> " + rset.getDate("birth_date")+ "</td>");
                String maleChecked = "";
                String femaleChecked = "";
                if (rset.getString("sex").equals("M")) {
                    maleChecked = "checked";
                } else {
                    femaleChecked = "checked";
                }
                out.println("<form method='get' action=\"update\">");
                out.println("<input type=\"hidden\" name=\"student_id\" value =" + rset.getString("student_id") + " ><br>");
                out.println("        First name:<br>");
                out.println("<input type=\"text\" name=\"firstname\" value =" + rset.getString("first_name"));
                out.println("> <br> Last name:<br> ");
                out.println("  <input type=\"text\" name=\"lastname\" value =" + rset.getString("last_name"));
                out.println("><br>EMail:<br>");
                out.println("  <input type=\"text\" name=\"email\"  value =" + rset.getString("EMAIL"));
                out.println("><br> street:<br>");
                out.println("  <input type=\"text\" name=\"streetname\"  value =" + rset.getString("street"));
                out.println("> <br><br>city:<br>");
                out.println("  <input type=\"text\" name=\"city\" value =" + rset.getString("city"));
                out.println("><br><br>state:<br>");
                out.println("  <input type=\"text\"  name=\"state\" value =" + rset.getString("state"));
                out.println(" <br><br> Zip:<br>");
                out.println("  <input type=\"number\" name=\"zip\"  value =" + rset.getString("zip"));
                out.println("><br>  phone:<br>");
                out.println("  <input type=\"text\" name=\"number\" value =" + rset.getString("phone"));
                out.println("><br> birth date:<br>");
                out.println("  <input type=\"date\" name=\"DOB\"  value =" + rset.getString("birth_date"));
                out.println("><br> sex:<br>");
                out.println("  <input type=\"radio\" name=\"gender\"  value=\"M\" " + maleChecked + "> Male<br>");
                out.println("  <input type=\"radio\" name=\"gender\"  value=\"F\" " + femaleChecked + "> Female<br> ");
                out.println("<br> date entered<br>");
                out.println("<input type=\"date\" name=\"date_entered\" value =" + rset.getString("date_entered") + " disabled><br>");
                out.println("<br> lunch cost<br>");
                out.println("<input type=\"text\" name=\"lunch_cost\"  value =" + rset.getFloat("lunch_cost") + "><br>");
                out.println("  <input type=\"submit\" value=\"Update\">");
                out.println("</form>");
                out.println("<br>");
                out.println("<br>");
                out.println("---------------------------------------------------------------");
                out.println("<br>");
                out.println("<br>");
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