/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproyecto;

import clases.Usuario;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import conexion.ConexionMySql;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import java.sql.*;
import conexion.ConexionMySql;
import consultas.ConsultasUsuarios;
import conexion.ConsultasDb;

/**
 * FXML Controller class
 *
 * @author lexlp
 */
public class LoginController implements Initializable {

    @FXML
     TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnIngresarAct(ActionEvent event) throws IOException {
        String user = txtUser.getText().trim();
        String pass = txtPass.getText().trim();
        if (!user.equals("") && !pass.equals("")) {

            try {
                Connection cn = ConexionMySql.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select tipo_usuario, estado from usuarios where nombre_usuario  = '" + user + "' and contrasena = '" + pass + "'");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String tipoUsuario = rs.getString("tipo_usuario");
                    String estado = rs.getString("estado");

                    if (tipoUsuario.equalsIgnoreCase("A") && estado.equalsIgnoreCase("Activo")) {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));

                        Scene scene = new Scene(root);

                        stage.setResizable(false);
                        stage.setTitle("Menu");

                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent e) {
                                Platform.exit();
                                System.exit(0);
                            }
                        });

                        stage.setScene(scene);
                        stage.show();

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    }else if(tipoUsuario.equalsIgnoreCase("U") && estado.equalsIgnoreCase("Activo")){
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("RservarRecurso.fxml"));

                        Scene scene = new Scene(root);

                        stage.setResizable(false);
                        stage.setTitle("ReservarRecurso");

                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent e) {
                                Platform.exit();
                                System.exit(0);
                            }
                        });

                        stage.setScene(scene);
                        stage.show();

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                        
                    }else if(tipoUsuario.equalsIgnoreCase("U") && estado.equalsIgnoreCase("Inactivo")){
                        JOptionPane.showMessageDialog(null, "La cuenta esta desactivada");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña incorrecta .");
                }
            } catch (SQLException e) {
                System.err.println("Error en el boton acceder" + e);
                JOptionPane.showMessageDialog(null, "Error al iniciar sesion");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Es necesario llenar los campos");
        }
    }

    @FXML
    private void btnSalirAct(ActionEvent event) {
        System.exit(0);
    }

}
