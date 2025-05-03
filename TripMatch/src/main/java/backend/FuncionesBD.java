package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FuncionesBD {
	public static boolean registrarUsuario(String dni, String nombre, String contrasena) {
        String sql = "INSERT INTO Usuario (Dni_Usuario, Nombre_Usuario, Contrasena_Usuario) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:bdupc.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            pstmt.setString(2, nombre);
            pstmt.setString(3, contrasena);
            pstmt.executeUpdate();
            return true; // Registro exitoso

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false; // Registro fallido (posible duplicado)
        }
    }

    // Función de inicio de sesión: comprueba credenciales
    public static boolean iniciarSesion(String dni, String contrasena) {
        String sql = "SELECT * FROM Usuario WHERE Dni_Usuario = ? AND Contrasena_Usuario = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:bdupc.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // true si existe usuario con esas credenciales

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return false;
        }
    }
}
