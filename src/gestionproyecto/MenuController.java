/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproyecto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import gestionproyecto.LoginController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lexlp
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnOrgaizacion;
    @FXML
    private Button btnUsuario;
    String user, nombre_usuario;
    public static int sesion_usuario;
    @FXML
    private Button btnRecurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colocarImagenBotones();
        // TODO
    }

    @FXML
    private void btnOrgaizacionAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionproyecto/Organizacion.fxml"));
            Parent root = loader.load();
            OrganizacionController controler = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controler.closeWindows());
            Stage myStage = (Stage) this.btnOrgaizacion.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnUsuarioAct(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionproyecto/Usuario.fxml"));
            Parent root = loader.load();
            UsuarioController controler = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controler.closeWindows());
            Stage myStage = (Stage) this.btnUsuario.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void btnRecursoAct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionproyecto/Recurso.fxml"));
            Parent root = loader.load();
            RecursoController controler = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controler.closeWindows());
            Stage myStage = (Stage) this.btnRecurso.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void colocarImagenBotones() {
        URL organizaciones = getClass().getResource("/imagenes/organizacion.png");
        URL usuarios = getClass().getResource("/imagenes/usuario.png");
        URL recursos = getClass().getResource("/imagenes/Recurso.png");

        Image nuevaOrganizacion = new Image(organizaciones.toString(), 64, 64, false, true);
        Image nuevaUsuarios = new Image(usuarios.toString(), 64, 64, false, true);
        Image nuevoRecurso = new Image(recursos.toString(), 64, 64, false, true);

        btnOrgaizacion.setGraphic(new ImageView(nuevaOrganizacion));
        btnUsuario.setGraphic(new ImageView(nuevaUsuarios));
        btnRecurso.setGraphic(new ImageView(nuevoRecurso));
    }
    
    
}
