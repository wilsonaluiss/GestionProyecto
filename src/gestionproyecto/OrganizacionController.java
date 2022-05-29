/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproyecto;

import clases.Organizacion;
import com.sun.prism.paint.Color;
import consultas.ConsultasOrganizacion;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import validaciones.Validaciones;

/**
 * FXML Controller class
 *
 * @author lexlp
 */
public class OrganizacionController implements Initializable {

    public ObservableList<Organizacion> dataOrtanizacion = FXCollections.observableArrayList();
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombreOrg;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    private ChoiceBox<String> choiceActivo;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnActivo;
    private ChoiceBox<String> choiceBuscar;
    @FXML
    private TableView<Organizacion> tablaOrganizacion;
    @FXML
    private TableColumn<Organizacion, Integer> colID;
    @FXML
    private TableColumn<Organizacion, String> colNomOrg;
    @FXML
    private TableColumn<Organizacion, String> colDirreccion;
    @FXML
    private TableColumn<Organizacion, String> colTelefono;
    @FXML
    private TableColumn<Organizacion, String> colCorreo;
    @FXML
    private Label labelResultados;
    @FXML
    private TextField txtDirreccion;
    @FXML
    private TabPane tabPaneOrganizacion;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private RadioButton rbActivo;
    @FXML
    private RadioButton rbDesactivo;
    @FXML
    private TableColumn<Organizacion, String> colEstado;
    @FXML
    private Button btnEmpty;
    @FXML
    private Button bntRegresar;
    @FXML
    private TableColumn<?, ?> colEstado1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        rellenarTablaOrganizacion();
        vaciarCampos();
        deshabilitarCampos();
        ToggleGroup group = new ToggleGroup();
        rbActivo.setToggleGroup(group);
        rbDesactivo.setToggleGroup(group);
    }

    public void configurarTabla() {
        colID.setCellValueFactory(new PropertyValueFactory<>("codigoOrganizacion"));
        colNomOrg.setCellValueFactory(new PropertyValueFactory<>("nombreOrganizacion"));
        colDirreccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tablaOrganizacion.setItems(dataOrtanizacion);
    }

    public void rellenarTablaOrganizacion() {

        dataOrtanizacion.clear();
        ConsultasOrganizacion consultas = new ConsultasOrganizacion();
        ObservableList<Organizacion> organizaciones = consultas.buscarTodos();
        dataOrtanizacion.setAll(organizaciones);
        int resultado = organizaciones.size();
        labelResultados.setText("Resultados: " + resultado);
    }

    public void vaciarCampos() {
        txtId.setText("");
        txtNombreOrg.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDirreccion.setText("");
        rbActivo.setSelected(true);
    }

    public void deshabilitarCampos() {
        txtId.setDisable(true);
        txtNombreOrg.setDisable(false);
        txtTelefono.setDisable(false);
        txtCorreo.setDisable(false);
        txtDirreccion.setDisable(false);
    }

    public void habilitarCampos() {
        txtId.setDisable(true);
        txtNombreOrg.setDisable(false);
        txtTelefono.setDisable(false);
        txtCorreo.setDisable(false);
        //choiceActivo.setDisable(false);
        txtDirreccion.setDisable(false);
    }

    @FXML
    private void btnCrearAct(ActionEvent event) {
        String nombreOrganizacion = txtNombreOrg.getText();
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

        Organizacion organizacion = new Organizacion(nombreOrganizacion, direccion, telefono, correo, estado);
        ConsultasOrganizacion consulta = new ConsultasOrganizacion();
        if (consulta.guardar(organizacion)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha guardado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaOrganizacion();
        }
        System.out.println(organizacion.toString());

    }

    @FXML
    private void btnModAct(ActionEvent event) {
        int id;
        String nombreOrganizacion = txtNombreOrg.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDirreccion.getText();
        String estado = "";
        if (rbActivo.isSelected()) {
            rbActivo.setText("Activo");
            estado = "Activo";
        } else {
            rbDesactivo.setText("Inactivo");
            estado = "Inactivo";
        }
        id = Integer.parseInt(txtId.getText());
        Organizacion organizacion = new Organizacion(id, nombreOrganizacion, direccion, telefono, correo, estado);
        ConsultasOrganizacion consulta = new ConsultasOrganizacion();
        if (consulta.guardar(organizacion)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha modificado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaOrganizacion();
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
        Organizacion organizacion = new Organizacion(id, estado);
        ConsultasOrganizacion consulta = new ConsultasOrganizacion();
        if (consulta.estado(organizacion)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha modificado el estado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaOrganizacion();
        }
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
        MenuItem itemModificar = new MenuItem("Modificar organización");
        itemModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Organizacion organizacion = tablaOrganizacion.getSelectionModel().getSelectedItem();
                if (organizacion == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona una organizacion para editar",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                txtId.setText(String.valueOf(organizacion.getCodigoOrganizacion()));
                txtNombreOrg.setText(organizacion.getNombreOrganizacion());
                txtDirreccion.setText(organizacion.getDireccion());
                txtTelefono.setText(organizacion.getTelefono());
                txtCorreo.setText(organizacion.getCorreoElectronico());

                habilitarCampos();

                //SingleSelectionModel<Tab> selectionModel = tabPaneOrganizacion.getSelectionModel();
                //selectionModel.select(0);
            }
        });
        menu.getItems().add(itemModificar);
        tablaOrganizacion.setContextMenu(menu);
    }

    /*private void btnLimpiarAct(ActionEvent event) {
        vaciarCampos();
    }*/
    @FXML
    private void contextMenuClick(MouseEvent event) {
    }

    @FXML
    private void btnEmptyAct(ActionEvent event) {
        vaciarCampos();
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

}
