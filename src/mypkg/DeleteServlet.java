package mypkg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href=\"welcome\">Back to Welcome</a></br>");

        String id = request.getParameter("student_id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_form",
                    "root", "qwe1ASD2#");
            Statement ps = conn
                    .createStatement();
            String sql = "delete from students  where student_id = " + id;

            System.out.println(sql);


            int i = ps.executeUpdate(sql);
            if (i > 0)
                out.print("You have successfully updated...");

        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }

}