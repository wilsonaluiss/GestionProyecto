/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lexlp
 */
public class ConexionMySql {
    
   
    
    public static Connection conectar(){
        
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/gestion";
            String user = "root";
            String pass = "root";
            Class.forName(driver);
            Connection cn = DriverManager.getConnection(url, user, pass);
            return cn;
        } catch (Exception e) {
            System.out.println("Error en conexión local " + e);
        }
        return (null);
         
    

    }
}
