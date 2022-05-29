/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproyecto;
import clases.Organizacion;
import clases.Usuario;
import consultas.ConsultasOrganizacion;
import consultas.ConsultasUsuarios;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import reportes.Reporte;

/**
 * FXML Controller class
 *
 * @author lexlp
 */
public class UsuarioController implements Initializable {
    
    public ObservableList<Usuario> dataUsuario = FXCollections.observableArrayList();
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombreUser;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtDirreccion;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnActivo;
    @FXML
    private Button btnEmpty;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TableColumn<Usuario,Integer> colID;
    @FXML
    private TableColumn<Usuario, String> colNomUser;
    @FXML
    private TableColumn<Usuario, String> colDirreccion;
    @FXML
    private TableColumn<Usuario, String> colTelefono;
    @FXML
    private TableColumn<Usuario, String> colCorreo;
    @FXML
    private TableColumn<Usuario, String> colEstado;
    @FXML
    private Label labelResultados;
    @FXML
    private RadioButton rbActivo;
    @FXML
    private RadioButton rbDesactivo;
    @FXML
    private Button bntRegresar;
    @FXML
    private TableView<Usuario> tablaUsuario;
    @FXML
    private TableColumn<Usuario, String> colTipoUsuario;
    @FXML
    private RadioButton rbAdmin;
    @FXML
    private RadioButton rbUser;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasena;
    @FXML
    private TableColumn<Usuario, String> colUsuario;
    @FXML
    private TableColumn<Usuario, String> colContrasena;
    @FXML
    private Button btnImprimir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        rellenarTablaUsuarios();
        vaciarCampos();
        deshabilitarCampos();
        ToggleGroup group = new ToggleGroup();
        rbActivo.setToggleGroup(group);
        rbDesactivo.setToggleGroup(group);
        
        ToggleGroup groupDos = new ToggleGroup();
        rbAdmin.setToggleGroup(groupDos);
        rbUser.setToggleGroup(groupDos);
    }  
    
    public void configurarTabla() {
        colID.setCellValueFactory(new PropertyValueFactory<>("codigoUsuario"));
        colTipoUsuario.setCellValueFactory(new PropertyValueFactory("tipousuario"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre_usuario"));
        colContrasena.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        colNomUser.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("CorreoElectronico"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDirreccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tablaUsuario.setItems(dataUsuario);
    }

    public void rellenarTablaUsuarios() {

        dataUsuario.clear();
        ConsultasUsuarios consultas = new ConsultasUsuarios();
        ObservableList<Usuario> usuarios = consultas.buscarTodos();
        dataUsuario.setAll(usuarios);
        int resultado = usuarios.size();
        labelResultados.setText("Resultados: " + resultado);
    }

    public void vaciarCampos() {
        txtId.setText("");
        txtNombreUser.setText("");
        txtContrasena.setText("");
        txtUsuario.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDirreccion.setText("");
        rbActivo.setSelected(true);
        rbAdmin.setSelected(false);
        rbUser.setSelected(true);
    }

    public void deshabilitarCampos() {
        txtId.setDisable(true);
        txtNombreUser.setDisable(false);
        txtTelefono.setDisable(false);
        txtCorreo.setDisable(false);
        txtDirreccion.setDisable(false);
    }

    public void habilitarCampos() {
        txtId.setDisable(true);
        txtNombreUser.setDisable(false);
        txtTelefono.setDisable(false);
        txtCorreo.setDisable(false);
        //choiceActivo.setDisable(false);
        txtDirreccion.setDisable(false);
    }

    

    @FXML
    private void btnCrearAct(ActionEvent event) {
        String nombreUsuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        String nombre = txtNombreUser.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDirreccion.getText();
        String estado = "";
        if (rbActivo.isSelected()) {
            rbActivo.setText("Activo");
            estado = "Activo";
        } else {
            rbDesactivo.setText("Desactivo");
            estado = "Desactivado";
        }
        String tipoUsuario ="";
        if (rbAdmin.isSelected()) {
            rbAdmin.setText("A");
            tipoUsuario = "A";
        } else if(rbUser.isSelected()) {
            rbUser.setText("U");
            tipoUsuario = "U";
        }
        
        Usuario usuario = new Usuario(tipoUsuario, nombreUsuario, contrasena, nombre, correo, telefono, estado, direccion);
        ConsultasUsuarios consulta = new ConsultasUsuarios();
        if (consulta.guardar(usuario)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha guardado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaUsuarios();
        }
        System.out.println(usuario.toString());
    }

    @FXML
    private void btnModAct(ActionEvent event) {
        int id;
        String nombreUsuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        String nombre = txtNombreUser.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String estado = "";
        if (rbActivo.isSelected()) {
            rbActivo.setText("Activo");
            estado = "Activo";
        } else {
            rbDesactivo.setText("Inactivo");
            estado = "Inactivo";
        }
        String tipoUsuario ="";
        if (rbAdmin.isSelected()) {
            rbAdmin.setText("A");
            tipoUsuario = "A";
        } else if(rbUser.isSelected()) {
            rbUser.setText("U");
            tipoUsuario = "U";
        }
        String direccion = txtDirreccion.getText();
        
        id = Integer.parseInt(txtId.getText());
        Usuario usuario = new Usuario(id, tipoUsuario, nombreUsuario, contrasena, nombre, correo, telefono, estado, direccion);
        ConsultasUsuarios consulta = new ConsultasUsuarios();
        if (consulta.guardar(usuario)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha modificado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaUsuarios();
        }
    }

    @FXML
    private void btnActivoAct(ActionEvent event) {
        
        int id;
        String estado = "";
        if (rbActivo.isSelected()) {
            rbActivo.setText("Activo");
            estado = "Activo";
        } else {
            rbDesactivo.setText("Inactivo");
            estado = "Inactivo";
        }
        id = Integer.parseInt(txtId.getText());
        Usuario usuario = new Usuario(id, estado);
        ConsultasUsuarios consulta = new ConsultasUsuarios();
        if (consulta.estado(usuario)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha modificado el estado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaUsuarios();
        }
    }

    @FXML
    private void btnEmptyAct(ActionEvent event) {
    }

    @FXML
    private void btnBuscarAct(ActionEvent event) {
    }

    @FXML
    private void btnRefreshAct(ActionEvent event) {
    }

    @FXML
    private void contextMenuAct(ContextMenuEvent event) {
        ContextMenu menu = new ContextMenu();
        MenuItem itemModificar = new MenuItem("Modificar usuario");
        itemModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Usuario usuario = tablaUsuario.getSelectionModel().getSelectedItem();
                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona una usuario para editar",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                txtId.setText(String.valueOf(usuario.getCodigoUsuario()));
                txtNombreUser.setText(usuario.getNombre_usuario());
                txtContrasena.setText(usuario.getContrasena());
                txtUsuario.setText(usuario.getNombre_usuario());
                txtTelefono.setText(usuario.getTelefono());
                txtCorreo.setText(usuario.getCorreoElectronico());
                txtDirreccion.setText(usuario.getDireccion());
                habilitarCampos();

                //SingleSelectionModel<Tab> selectionModel = tabPaneOrganizacion.getSelectionModel();
                //selectionModel.select(0);
            }
        });
        menu.getItems().add(itemModificar);
        tablaUsuario.setContextMenu(menu);
    }

    @FXML
    private void contextMenuClick(MouseEvent event) {
    }

    
    
    public void closeWindows(){
        
        try {
            FXMLLoader loader = new FXMLLoader (getClass().getResource("/gestionproyecto/Menu.fxml"));
            
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();


            Stage myStage = (Stage) this.bntRegresar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void bntRegresarAct(ActionEvent event) {
        closeWindows();
    }

    @FXML
    private void btnImprimirAct(ActionEvent event) {
        Reporte reporte = new Reporte("Usuarios");
        reporte.generarReporte();
    }
    
}
