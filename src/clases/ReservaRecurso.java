/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Date;

/**
 *
 * @author lexlp
 */
public class ReservaRecurso {
    private int codigoReserva;
    private String nombreActividad;
    private String descripcion;
    private Date fecha;
    private String horaInicio;
    private String horaFin;

    public ReservaRecurso(int codigoReserva, String nombreActividad, String descripcion, Date fecha, String horaInicio, String horaFin) {
        this.codigoReserva = codigoReserva;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public ReservaRecurso(String nombreActividad, String descripcion, Date fecha, String horaInicio, String horaFin) {
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(int codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "ReservaRecurso{" + "codigoReserva=" + codigoReserva + ", nombreActividad=" + nombreActividad + ", descripcion=" + descripcion + ", fecha=" + fecha + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + '}';
    }

}
