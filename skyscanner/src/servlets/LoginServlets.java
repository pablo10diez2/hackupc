package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlets extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String dni = request.getParameter("dni");
        String contrasena = request.getParameter("contrasena");

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bdupc.db");

            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM usuarios WHERE dni = ? AND contrasena = ?"
            );
            stmt.setString(1, dni);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                response.getWriter().write("OK");
            } else {
                response.getWriter().write("ERROR");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("ERROR");
        }
    }
}
