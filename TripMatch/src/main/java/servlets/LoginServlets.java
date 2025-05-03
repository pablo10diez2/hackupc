package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlets extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String dni = request.getParameter("Dni_Usuario");
        String contrasena = request.getParameter("contrasena_Usuario");
        
        System.out.println("DNI recibido: " + dni);
        System.out.println("Contraseña recibida: " + contrasena);


        String dbPath = getServletContext().getRealPath("/WEB-INF/bdupc.db");
        String sql = "SELECT * FROM Usuario WHERE Dni_Usuario = ? AND Contrasena_Usuario = ?";

        try {
            Class.forName("org.sqlite.JDBC"); // ← fuerza a cargar el driver manualmente

            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, dni);
            pstmt.setString(2, contrasena);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                response.getWriter().write("OK");
            } else {
                response.getWriter().write("ERROR");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().write("ERROR");
        }

    }
}
