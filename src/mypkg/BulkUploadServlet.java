
package mypkg;


import com.opencsv.CSVReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class BulkUploadServlet  extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href=\"welcome\">Back to Welcome</a></br>");
        try {
        Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_form",
                    "root", "123456");

            readCsv(connection, out);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }



    }

    private static void readCsv(Connection connection, PrintWriter out)
    {

        try
        {
            CSVReader reader = new CSVReader(new FileReader("C://Users//choud//IdeaProjects//StudentsWebApp//upload.csv"), ',');
            String insertQuery = "Insert into students (first_name, last_name, email, street, city, state, zip, phone, birth_date, sex, lunch_cost) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            String[] rowData;
            int i = 1;
            while((rowData = reader.readNext()) != null)
            {
                for (String data : rowData)
                {
                    if(i == 11) {
                        pstmt.setFloat(i, Float.parseFloat(data));
                    }else{
                        pstmt.setString(i, data);
                    }

                    if(i == 11) {
                        pstmt.addBatch();// add batch
                    }
                    i++;
                    if (i == 12)// insert when the batch size is 10
                        pstmt.executeBatch();
                }
            }

            out.println("Data Successfully Uploaded");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
