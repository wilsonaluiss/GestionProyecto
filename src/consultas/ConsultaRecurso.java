/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import clases.Organizacion;
import clases.Recurso;
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
public class ConsultaRecurso {
    
     
    //Insertar en la tabla de organizacion
    public boolean insertar (Recurso recurso){
        String query = "insert into recursos (nombre_recurso,requiere_aprobacion,requiere_confirmacion,tiempo_max_uso,costo,estado) "
                + "values ('"+recurso.getNombreRecurso()+"','"
                +recurso.getAprobacion()+"','"
                +recurso.getConfirmacion()+"','"
                +recurso.getTiempo()+"','"
                +recurso.getCosto()+"','"
                +recurso.getEstado()+"')";
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    //Modificar en la tabla de organizacion
    public boolean modificar (Recurso recurso){
        String query = "update recursos  set "+
                "nombre_recurso = '"+recurso.getNombreRecurso()+"',"+
                "requiere_aprobacion = '"+recurso.getAprobacion()+"',"+
                "requiere_confirmacion = '"+recurso.getConfirmacion()+"',"+
                "tiempo_max_uso = '"+recurso.getTiempo()+"',"+
                "costo = '"+recurso.getCosto()+"',"+
                "estado = '"+recurso.getEstado()+"'"+
                " where codigo_recurso  = "+recurso.getCodigoRecurso();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean activo(Recurso recurso){
        String query = "update recursos set  estado ='Activo' "+
                " where codigo_recurso  = "+recurso.getCodigoRecurso();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    public boolean inactivo(Recurso recurso){
        String query = "update recursos set  estado ='Inactivo' "+
                " where codigo_recurso  = "+recurso.getCodigoRecurso();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean validaciones(Recurso recurso){
        StringBuilder sb = new StringBuilder();
        boolean isValid = true;
        if(recurso == null){
            isValid = false;
            sb.append("No existen organizacion\n");
        }
        if(recurso.getNombreRecurso().trim().equals("")){
            isValid = false;
            sb.append("Nombre del recurso requerido\n");
        }
        if(recurso.getNombreRecurso().trim().length() > 50){
            isValid = false;
            sb.append("Nombre del recurso debe ser menos a 50 caracteres\n");
        }
        if(!Validaciones.numeroTelefono(recurso.getTiempo().trim())){
            isValid = false;
            sb.append("Tiempo no pueden ser letras\n");
        }
        if(recurso.getTiempo().trim().length() > 10){
            isValid = false;
            sb.append("Tiempo no puede ser mayo a 10 caracteres\n");
        }
        if(!Validaciones.decimal(recurso.getCosto().trim())){
            isValid = false;
            sb.append("Formato de costo incorrecto\n");
        }
        if(recurso.getCosto().trim().length() > 10){
            isValid = false;
            sb.append("El costo no pueden ser mayor a 10 caracteres\n");
        }
        if(!isValid){
            JOptionPane.showMessageDialog(null, "Se encontraron los siguientes errores : \n"
            +sb.toString(),"Error de validación",JOptionPane.WARNING_MESSAGE);
        }
        return isValid;
        
    }
    
    
    public boolean guardar(Recurso recurso){
        if(validaciones(recurso)== false){
            return false;
        }
        boolean exito;
        if(recurso.getCodigoRecurso() == 0)
            exito = insertar(recurso);
        else
            exito = modificar(recurso);
        return exito;
    }
    
    
    public boolean estado(Recurso recurso){
        boolean exito;
        if(recurso.getEstado()== "Activo")
            exito = activo(recurso);
        else
            exito = inactivo(recurso);
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
    public ObservableList <Recurso>buscarTodos(){
        String query = "select * from recursos";
        ConsultasDb jdbc = new ConsultasDb();
        ResultSet rs = jdbc.realizarConsulta(query);
        ObservableList<Recurso> recurso = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int codigoRecurso = rs.getInt("codigo_recurso");
                String nombreRecurso  = rs.getString("nombre_recurso");
                String aprobacion  = rs.getString("requiere_aprobacion");
                String confirmacion = rs.getString("requiere_confirmacion");
                //int tiempo = rs.getInt("tiempo_max_uso");
                String tiempo = rs.getString("tiempo_max_uso");
                String costo = rs.getString("costo");
                String estado = rs.getString("estado");
                recurso.add(new Recurso (codigoRecurso, nombreRecurso, aprobacion, confirmacion, tiempo, costo, estado));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la organizacion : "+ex,
                    "Error",JOptionPane.WARNING_MESSAGE);
        }
        return recurso;
    }
    
    public ObservableList <Recurso> nombreOrganizacion(String nombreBusqueda){
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
        return null;
    }
}
