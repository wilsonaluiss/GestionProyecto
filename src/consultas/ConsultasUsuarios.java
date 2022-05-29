/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import clases.Organizacion;
import clases.Usuario;
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
public class ConsultasUsuarios {
    
    //Insertar en la tabla de usuario
    public boolean insertar (Usuario usuario){
        String query = "insert into usuarios (nombre_usuario,contrasena,nombre,telefono,correo_electronico,direccion,tipo_usuario,estado) "
                + "values ('"+usuario.getNombre_usuario()+"','"
                +usuario.getContrasena()+"','"
                +usuario.getNombre()+"','"
                +usuario.getTelefono()+"','"
                +usuario.getCorreoElectronico()+"','"
                +usuario.getDireccion()+"','"
                +usuario.getTipousuario()+"','"
                +usuario.getEstado()+"')";
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    //Modificar en la tabla de usuario
    public boolean modificar (Usuario usuario){
        String query = "update usuarios  set "+
                "tipo_usuario = '"+usuario.getTipousuario()+"',"+
                "nombre_usuario = '"+usuario.getNombre_usuario()+"',"+
                "contrasena = '"+usuario.getContrasena()+"',"+
                "nombre = '"+usuario.getNombre()+"',"+
                "telefono = '"+usuario.getTelefono()+"',"+
                "direccion = '"+usuario.getDireccion()+"',"+
                "tipo_usuario = '"+usuario.getTipousuario()+"',"+
                "correo_electronico = '"+usuario.getCorreoElectronico()+"',"+
                "estado = '"+usuario.getEstado()+"'"+
                " where codigo_usuario  = " +usuario.getCodigoUsuario();
        ConsultasDb jdbc = new ConsultasDb();
        System.out.println(query);
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean activo(Usuario usuario){
        String query = "update usuarios set  estado ='Activo' "+
                " where codigo_usuario  = "+usuario.getCodigoUsuario();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    public boolean inactivo(Usuario usuario){
        String query = "update usuarios set  estado ='Inactivo' "+
                " where codigo_usuario  = "+usuario.getCodigoUsuario();
        ConsultasDb jdbc = new ConsultasDb();
        boolean exito = jdbc.ejecutarQuery(query);
        return exito;
    }
    
    public boolean validaciones(Usuario usuario){
        StringBuilder sb = new StringBuilder();
        boolean isValid = true;
        if(usuario == null){
            isValid = false;
            sb.append("No existen organizacion\n");
        }
        if(usuario.getNombre_usuario().trim().equals("")){
            isValid = false;
            sb.append("Nombre del usuario requerido\n");
        }
        if(usuario.getNombre_usuario().trim().length() > 50){
            isValid = false;
            sb.append("Nombre del usuario debe ser menos a 50 caracteres\n");
        }
        if(!Validaciones.contrasena(usuario.getContrasena().trim())){
            isValid = false;
            sb.append("Formato de contraseña invalido\n");
        }
        if(usuario.getContrasena().trim().equals("")){
            isValid = false;
            sb.append("Contraseña del usuario requerido\n");
        }
        if(usuario.getContrasena().trim().length() > 16){
            isValid = false;
            sb.append("Contraseña del usuario debe ser mayor a 8 caracteres\n"
                    + "y menor a 17 caracters");
        }
        if(usuario.getNombre().trim().equals("")){
            isValid = false;
            sb.append("Nombre identificador del usuario requerido\n");
        }
        if(usuario.getNombre().trim().length() > 50){
            isValid = false;
            sb.append("Nombre del usuario debe ser menos a 50 caracteres\n");
        }
        if(usuario.getTelefono().trim().length() > 8){
            isValid = false;
            sb.append("Numero de telefono no puede ser mayor a 8 caracteres\n");
        }
        if(!Validaciones.numeroTelefono(usuario.getTelefono().trim())){
            isValid = false;
            sb.append("Numero de telefono deben ser numeros\n");
        }
        if(usuario.getCorreoElectronico().trim().length() > 50){
            isValid = false;
            sb.append("Correo electronico no debe ser mayor a 50 caracteres\n");
        }
        if(Validaciones.validarCorreo(usuario.getCorreoElectronico())){
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
    
    
    public boolean guardar(Usuario usuario){
        if(validaciones(usuario)== false){
            return false;
        }
        boolean exito;
        if(usuario.getCodigoUsuario() == 0)
            exito = insertar(usuario);
        else
            exito = modificar(usuario);
        return exito;
    }
    
    
    public boolean estado(Usuario usuario){
        boolean exito;
        if(usuario.getEstado()== "Activo")
            exito = activo(usuario);
        else
            exito = inactivo(usuario);
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
    public ObservableList <Usuario>buscarTodos(){
        String query = "select * from usuarios";
        ConsultasDb jdbc = new ConsultasDb();
        ResultSet rs = jdbc.realizarConsulta(query);
        ObservableList<Usuario> usuario = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                int codigoUsuario = rs.getInt("codigo_usuario");
                String tipousuario  = rs.getString("tipo_usuario");
                String nombreUsuario  = rs.getString("nombre_usuario");
                String contrasena  = rs.getString("contrasena");
                String nombre  = rs.getString("nombre");
                String correo  = rs.getString("correo_electronico");
                String telefono  = rs.getString("telefono");
                String estado  = rs.getString("estado");
                String direccion  = rs.getString("direccion");
                usuario.add(new Usuario (codigoUsuario, tipousuario, nombreUsuario, contrasena, nombre, correo, telefono, estado, direccion));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la organizacion : "+ex,
                    "Error",JOptionPane.WARNING_MESSAGE);
        }
        return usuario;
    }
    
    public ObservableList <Usuario> nombreOrganizacion(String nombreBusqueda){
        String query = "select * from usuario nombreorganizacion like '%" + nombreBusqueda+ "%'" ;
        ConsultasDb jdbc = new ConsultasDb();
        ResultSet rs = jdbc.realizarConsulta(query);
        ObservableList<Usuario> usuario = FXCollections.observableArrayList();
        
        try {
            while(rs.next()){
                long id = rs.getLong("codigo_usuario");
                String nombreUsuario  = rs.getString("nombre_usuario");
                String contrasena  = rs.getString("contrasena");
                String nombre  = rs.getString("nombre");
                String correo  = rs.getString("correo_electronico");
                String telefono  = rs.getString("telefono");
                String estado  = rs.getString("estado");
                String direccion  = rs.getString("direccion");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la organizacion por nombre de Organización : "+ex,
                    "Error",JOptionPane.WARNING_MESSAGE);
        }
        return usuario;
    }
    
}
