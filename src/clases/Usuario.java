/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author lexlp
 */
public class Usuario {
    
    private int codigoUsuario;
    private String tipousuario;
    private String nombre_usuario;
    private String contrasena;
    private String nombre;
    private String CorreoElectronico;
    private String telefono;
    private String estado;
    private String direccion;

    public Usuario() {
    }

    public Usuario(int codigoUsuario, String tipousuario, String nombre_usuario, String contrasena, String nombre, String CorreoElectronico, String telefono, String estado, String direccion) {
        this.codigoUsuario = codigoUsuario;
        this.tipousuario = tipousuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.CorreoElectronico = CorreoElectronico;
        this.telefono = telefono;
        this.estado = estado;
        this.direccion = direccion;
    }

    public Usuario(String tipousuario, String nombre_usuario, String contrasena, String nombre, String CorreoElectronico, String telefono, String estado, String direccion) {
        this.tipousuario = tipousuario;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.CorreoElectronico = CorreoElectronico;
        this.telefono = telefono;
        this.estado = estado;
        this.direccion = direccion;
    }

    public Usuario(int codigoUsuario, String estado) {
        this.codigoUsuario = codigoUsuario;
        this.estado = estado;
    }
    
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoUsuario=" + codigoUsuario + ", tipousuario=" + tipousuario + ", nombre_usuario=" + nombre_usuario + ", contrasena=" + contrasena + ", nombre=" + nombre + ", CorreoElectronico=" + CorreoElectronico + ", telefono=" + telefono + ", estado=" + estado + ", direccion=" + direccion + '}';
    }
    
    
    
}
