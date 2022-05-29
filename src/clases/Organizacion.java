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
public class Organizacion {
    
    private int codigoOrganizacion;
    private String nombreOrganizacion;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String estado;

    public Organizacion() {
    }

    
    public Organizacion(int codigoOrganizacion, String nombreOrganizacion, String direccion, String telefono, String correoElectronico, String estado) {
        this.codigoOrganizacion = codigoOrganizacion;
        this.nombreOrganizacion = nombreOrganizacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.estado = estado;
    }

    public Organizacion(String nombreOrganizacion, String direccion, String telefono, String correoElectronico, String estado) {
        this.nombreOrganizacion = nombreOrganizacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.estado = estado;
    }

    public Organizacion(int codigoOrganizacion, String estado) {
        this.codigoOrganizacion = codigoOrganizacion;
        this.estado = estado;
    }

    public int getCodigoOrganizacion() {
        return codigoOrganizacion;
    }

    public void setCodigoOrganizacion(int codigoOrganizacion) {
        this.codigoOrganizacion = codigoOrganizacion;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Organizacion{" + "codigoOrganizacion=" + codigoOrganizacion + ", nombreOrganizacion=" + nombreOrganizacion + ", direccion=" + direccion + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + ", estado=" + estado + '}';
    }

   
}
