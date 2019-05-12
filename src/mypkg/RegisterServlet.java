package mypkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
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
        String cost = request.getParameter("cost");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_form",
                    "root", "123456");
            Statement ps = conn
                    .createStatement();
            String sql = "insert into students (first_name, last_name, email, street, city, state, zip, phone, birth_date, sex, lunch_cost)" +
                    "values('" + firstname + "','" + lastname + "','" + email + "','" + streetname + "','" + city + "','" + state + "','" + zip + "','" + number + "','" + dob + "','" + gender + "'," + cost + ")";


            int i = ps.executeUpdate(sql);
            if (i > 0)
                out.print("Thank you! You are successfully registered...");

        } catch (Exception e2) {
            out.print("There is some exception. Please try again");
        }

        out.close();
    }

}