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

public class UpdateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href=\"welcome\">Back to Welcome</a></br>");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String streetname = request.getParameter("streetname");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String number = request.getParameter("number");
        String dob = request.getParameter("DOB");
        String gender = request.getParameter("gender");
        String cost = request.getParameter("lunch_cost");
        String id = request.getParameter("student_id");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_form",
                    "root", "qwe1ASD2#");
            Statement ps = conn
                    .createStatement();
            String sql = "update students set first_name = '" + firstname + "', " +
                    "last_name = '" + lastname + "', " +
                    "email = '" + email + "', " +
                    "street = '" + streetname + "', " +
                    "city = '" + city + "', " +
                    "state = '" + state + "', " +
                    "zip = " + zip + ", " +
                    "phone = '" + number + "', " +
                    "birth_date = '" + dob + "', " +
                    "sex = '" + gender + "', " +
                    "lunch_cost = " + cost + " " +
                    " where student_id = " + id;

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