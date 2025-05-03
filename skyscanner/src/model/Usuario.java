package model;

public class Usuario {
    private String dni;
    private String contrasena;

    public Usuario(String dni, String contrasena) {
        this.dni = dni;
        this.contrasena = contrasena;
    }

    public String getDni() { return dni; }
    public String getContrasena() { return contrasena; }
}
