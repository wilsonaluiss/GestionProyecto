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
public class Recurso {
    
    private int codigoRecurso;
    private String nombreRecurso;
    private String aprobacion;
    private String confirmacion;
    private String tiempo;
    private String costo;
    private String estado;

    public Recurso() {
    }
    

    public Recurso(int codigoOrganizacion, String nombreRecurso, String aprobacion, String confirmacion, String tiempo, String costo, String estado) {
        this.codigoRecurso = codigoOrganizacion;
        this.nombreRecurso = nombreRecurso;
        this.aprobacion = aprobacion;
        this.confirmacion = confirmacion;
        this.tiempo = tiempo;
        this.costo = costo;
        this.estado = estado;
    }

    public Recurso(String nombreRecurso, String aprobacion, String confirmacion, String tiempo, String costo, String estado) {
        this.nombreRecurso = nombreRecurso;
        this.aprobacion = aprobacion;
        this.confirmacion = confirmacion;
        this.tiempo = tiempo;
        this.costo = costo;
        this.estado = estado;
    }

    public Recurso(int codigoOrganizacion, String estado) {
        this.codigoRecurso = codigoOrganizacion;
        this.estado = estado;
    }

    public int getCodigoRecurso() {
        return codigoRecurso;
    }

    public void setCodigoRecurso(int codigoRecurso) {
        this.codigoRecurso = codigoRecurso;
    }


    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(String aprobacion) {
        this.aprobacion = aprobacion;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Recurso{" + "codigoOrganizacion=" + codigoRecurso + ", nombreRecurso=" + nombreRecurso + ", aprobacion=" + aprobacion + ", confirmacion=" + confirmacion + ", tiempo=" + tiempo + ", costo=" + costo + ", estado=" + estado + '}';
    }
     
}
