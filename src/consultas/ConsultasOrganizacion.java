/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import clases.Organizacion;
import clases.ReservaRecurso;
import conexion.ConsultasDb;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import validaciones.Validaciones;

/**
 *
 * @author lexlp
 */
public class ConsultasOrganizacion {
    
    
    //Insertar en la tabla de organizacion
    public boolean insertar (Organizacion organizacion){
        String query = "insert into organizacion (nombre_organizacion,direccion,telefono,correo_electronico,estado) "
                + "values ('"+organizacion.getNombreOrganizacion()+"','"
                +organizacion.getDireccion()+"','"
                +organizacion.getTelefono()+"','"
                +organizacion.getCorreoElectronico()+"','"
                +organizacion.getEstado()+"')";
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    //Modificar en la tabla de organizacion
    public boolean modificar (Organizacion organizacion){
        String query = "update organizacion  set "+
                "direccion = '"+organizacion.getDireccion()+"',"+
                "telefono = '"+organizacion.getTelefono()+"',"+
                "correo_electronico = '"+organizacion.getCorreoElectronico()+"'"+
                " where codigo_organizacion  = "+organizacion.getCodigoOrganizacion();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean activo(Organizacion organizacion){
        String query = "update organizacion set  estado ='Activo' "+
                " where codigo_organizacion  = "+organizacion.getCodigoOrganizacion();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    public boolean inactivo(Organizacion organizacion){
        String query = "update organizacion set  estado ='Inactivo' "+
                "where codigo_organizacion  = "+organizacion.getCodigoOrganizacion();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean validaciones(Organizacion organizacion){
        StringBuilder sb = new StringBuilder();
        boolean isValid = true;
        if(organizacion == null){
            isValid = false;
            sb.append("No existen organizacion\n");
        }
        if(organizacion.getNombreOrganizacion().trim().equals("")){
            isValid = false;
            sb.append("Nombre de la organizacion requerido\n");
        }
        if(organizacion.getNombreOrganizacion().trim().length() > 100){
            isValid = false;
            sb.append("Nombre de la organizacion debe ser menos a 100 caracteres\n");
        }
        if(organizacion.getTelefono().trim().length() > 8){
            isValid = false;
            sb.append("Numero de telefono no puede ser mayor a 8 caracteres\n");
        }
        if(!Validaciones.numeroTelefono(organizacion.getTelefono().trim())){
            isValid = false;
            sb.append("Numero de telefono deben ser numeros\n");
        }
        if(organizacion.getCorreoElectronico().trim().length() > 50){
            isValid = false;
            sb.append("Correo electronico mayor a 50 caracteres\n");
        }
        if(Validaciones.validarCorreo(organizacion.getCorreoElectronico())){
        } else {
            isValid = false;
            sb.append("Formato de correo electronico incorrecto\n");
        }
        if(!isValid){
            JOptionPane.showMessageDialog(null, "Se encontraron los siguientes errores : \n"
            +sb.toString(),"Error de validación",JOptionPane.WARNING_MESSAGE);
        }
        return isValid;
        
    }
    
    
    public boolean guardar(Organizacion organizacion){
        if(validaciones(organizacion)== false){
            return false;
        }
        boolean exito;
        if(organizacion.getCodigoOrganizacion() == 0)
            exito = insertar(organizacion);
        else
            exito = modificar(organizacion);
        return exito;
    }
    
    
    public boolean estado(Organizacion organizacion){
        boolean exito;
        if(organizacion.getEstado()== "Activo")
            exito = activo(organizacion);
        else
            exito = inactivo(organizacion);
        return exito;
    }
    //Busqueda por id
    public Organizacion buscarOrganizacion(long idBusqueda){
        String query = "select * from organizacion where id = "+idBusqueda;
        ConsultasDb jdbc = new ConsultasDb();
        ResultSet rs = jdbc.realizarConsulta(query);
        Organizacion organizacion = null;
        
        try {
            if(rs.next()){
                long id = idBusqueda;
                String nombreOrganizacion  = rs.getString("nombre_organizacion");
                String direccion  = rs.getString("direccion");
                String telefono  = rs.getString("telefono");
                String correoElectronico  = rs.getString("correo_electronico");
                organizacion = new Organizacion(0, nombreOrganizacion, direccion, telefono, correoElectronico, telefono);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la organizacion : "+ex,
                    "Error",JOptionPane.WARNING_MESSAGE);
        }
        return organizacion;
    }
    
    //Buscar todo lo de la base de datos
    public ObservableList <Organizacion>buscarTodos(){
        String query = "select * from organizacion";
        ConsultasDb jdbc = new ConsultasDb();
        ResultSet rs = jdbc.realizarConsulta(query);
        ObservableList<Organizacion> organizaciones = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int id = rs.getInt("codigo_organizacion");
                String nombreOrganizacion  = rs.getString("nombre_organizacion");
                String direccion  = rs.getString("direccion");
                String telefono  = rs.getString("telefono");
                String estado = rs.getString("estado");
                String correoElectronico  = rs.getString("correo_electronico");
                organizaciones.add(new Organizacion(id, nombreOrganizacion, direccion, telefono, correoElectronico, estado));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la organizacion : "+ex,
                    "Error",JOptionPane.WARNING_MESSAGE);
        }
        return organizaciones;
    }
    
    public ObservableList <Organizacion> nombreOrganizacion(String nombreBusqueda){
        String query = "select * from organizacion nombreorganizacion like '%" + nombreBusqueda+ "%'" ;
        ConsultasDb jdbc = new ConsultasDb();
        ResultSet rs = jdbc.realizarConsulta(query);
        ObservableList<Organizacion> organizaciones = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                long id = rs.getLong("codigo_organizacion");
                String nombreOrganizacion  = rs.getString("nombre_organizacion");
                String direccion  = rs.getString("direccion");
                String telefono  = rs.getString("telefono");
                String correoElectronico  = rs.getString("correo_electronico");
                organizaciones.add(new Organizacion(nombreOrganizacion, direccion, telefono, correoElectronico, telefono));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la organizacion por nombre de Organización : "+ex,
                    "Error",JOptionPane.WARNING_MESSAGE);
        }
        return organizaciones;
    }
    
}
