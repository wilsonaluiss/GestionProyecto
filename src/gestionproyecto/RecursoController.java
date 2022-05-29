/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproyecto;

import clases.Organizacion;
import clases.Recurso;
import clases.Usuario;
import consultas.ConsultaRecurso;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import reportes.Reporte;

/**
 * FXML Controller class
 *
 * @author lexlp
 */
public class RecursoController implements Initializable {

    public ObservableList<Recurso> dataRecurso = FXCollections.observableArrayList();
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombreRecurso;
    @FXML
    private TextField txtCosto;
    @FXML
    private RadioButton rbAprobOk;
    @FXML
    private RadioButton rbAproNot;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TableView<Recurso> tablaRecurso;
    @FXML
    private TableColumn<Recurso, Integer> colID;
    @FXML
    private TableColumn<Recurso, String> colNombRec;
    @FXML
    private TableColumn<Recurso, String> colAprobacion;
    @FXML
    private TableColumn<Recurso, String> colConfirmacion;
    @FXML
    private TableColumn<Recurso, Integer> colTiempo;
    @FXML
    private TableColumn<Recurso, Double> colCosto;
    @FXML
    private TableColumn<Recurso, String> colEstado;
    @FXML
    private Label labelResultados;
    @FXML
    private RadioButton rbActivo;
    @FXML
    private RadioButton rbDesactivo;
    @FXML
    private Button bntRegresar;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnActivo;
    @FXML
    private Button btnEmpty;
    @FXML
    private RadioButton rbConOk;
    @FXML
    private RadioButton rbConNot;
    @FXML
    private TextField txtTiempo;
    @FXML
    private Button btnImprimir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        rellenarTablaRecurso();
        vaciarCampos();
        deshabilitarCampos();
        ToggleGroup group = new ToggleGroup();
        rbActivo.setToggleGroup(group);
        rbDesactivo.setToggleGroup(group);

        ToggleGroup aprobacion = new ToggleGroup();
        rbAproNot.setToggleGroup(aprobacion);
        rbAprobOk.setToggleGroup(aprobacion);
        
        ToggleGroup confirmacion = new ToggleGroup();
        rbConOk.setToggleGroup(confirmacion);
        rbConNot.setToggleGroup(confirmacion);
        
    }

    public void configurarTabla() {
        colID.setCellValueFactory(new PropertyValueFactory<>("codigoRecurso"));
        colNombRec.setCellValueFactory(new PropertyValueFactory("nombreRecurso"));
        colAprobacion.setCellValueFactory(new PropertyValueFactory("aprobacion"));
        colConfirmacion.setCellValueFactory(new PropertyValueFactory("confirmacion"));
        colTiempo.setCellValueFactory(new PropertyValueFactory("tiempo"));
        colCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        colEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        tablaRecurso.setItems(dataRecurso);
    }

    public void rellenarTablaRecurso() {

        dataRecurso.clear();
        ConsultaRecurso consultas = new ConsultaRecurso();
        ObservableList<Recurso> recurso = consultas.buscarTodos();
        dataRecurso.setAll(recurso);
        int resultado = recurso.size();
        labelResultados.setText("Resultados: " + resultado);
    }

    public void vaciarCampos() {
        txtId.setText("");
        txtNombreRecurso.setText("");
        txtCosto.setText("");
        txtTiempo.setText("");
        rbActivo.setSelected(true);
        rbAproNot.setSelected(true);
        rbConNot.setSelected(true);
    }

    public void deshabilitarCampos() {
        txtId.setDisable(true);
        txtNombreRecurso.setDisable(false);
        txtCosto.setDisable(false);
        txtTiempo.setDisable(false);
    }

    public void habilitarCampos() {
        txtId.setDisable(true);
        txtNombreRecurso.setDisable(false);
        txtCosto.setDisable(false);
        txtTiempo.setDisable(false);
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
        MenuItem itemModificar = new MenuItem("Modificar recurso");
        itemModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Recurso recurso = tablaRecurso.getSelectionModel().getSelectedItem();
                if (recurso == null) {
                    JOptionPane.showMessageDialog(null, "Selecciona una usuario para editar",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                txtId.setText(String.valueOf(recurso.getCodigoRecurso()));
                txtId.setText(String.valueOf(recurso.getCodigoRecurso()));
                txtNombreRecurso.setText(recurso.getNombreRecurso());
                txtCosto.setText(recurso.getCosto());
                txtTiempo.setText(recurso.getTiempo());

                habilitarCampos();

                //SingleSelectionModel<Tab> selectionModel = tabPaneOrganizacion.getSelectionModel();
                //selectionModel.select(0);
            }
        });
        menu.getItems().add(itemModificar);
        tablaRecurso.setContextMenu(menu);
    }

    @FXML
    private void contextMenuClick(MouseEvent event) {
    }

    @FXML
    private void bntRegresarAct(ActionEvent event) {
        closeWindows();
    }

    @FXML
    private void btnCrearAct(ActionEvent event) {
        String nombreRecurso = txtNombreRecurso.getText();
        String aprobacion ="";
        if (rbAprobOk.isSelected()) {
            rbAprobOk.setText("Si");
            aprobacion = "Si";
        } else {
            rbAprobOk.setText("No");
            aprobacion = "No";
        }
        String confirmacion="";
        if (rbConOk.isSelected()) {
            rbConOk.setText("Si");
            confirmacion = "Si";
        } else {
            rbConNot.setText("No");
            confirmacion = "No";
        }
        String tiempo = txtTiempo.getText();
        String costo = txtCosto.getText();
        
        
        String estado = "";
        if (rbActivo.isSelected()) {
            rbActivo.setText("Activo");
            estado = "Activo";
        } else {
            rbDesactivo.setText("Desactivo");
            estado = "Desactivado";
        }
        Recurso recurso = new Recurso(nombreRecurso, aprobacion, confirmacion, tiempo, costo, estado);
        ConsultaRecurso consulta = new ConsultaRecurso();
        if (consulta.guardar(recurso)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha guardado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaRecurso();
        }
        System.out.println(recurso.toString());
    }

    @FXML
    private void btnModAct(ActionEvent event) {
        
        int id;
        String nombreRecurso = txtNombreRecurso.getText();
        String aprobacion ="";
        if (rbAprobOk.isSelected()) {
            rbAprobOk.setText("Si");
            aprobacion = "Si";
        } else {
            rbAprobOk.setText("No");
            aprobacion = "No";
        }
        String confirmacion="";
        if (rbConOk.isSelected()) {
            rbConOk.setText("Si");
            confirmacion = "Si";
        } else {
            rbConNot.setText("No");
            confirmacion = "No";
        }
        String tiempo = txtTiempo.getText();
        String costo = txtCosto.getText();
        
        
        String estado = "";
        if (rbActivo.isSelected()) {
            rbActivo.setText("Activo");
            estado = "Activo";
        } else {
            rbDesactivo.setText("Desactivo");
            estado = "Desactivado";
        }
        id = Integer.parseInt(txtId.getText());
        Recurso recurso = new Recurso(id, nombreRecurso, aprobacion, confirmacion, tiempo, costo, estado);
        ConsultaRecurso consulta = new ConsultaRecurso();
        if (consulta.guardar(recurso)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha modificado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaRecurso();
        }
        System.out.println(recurso.toString());
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
        Recurso recurso = new Recurso(id, estado);
        ConsultaRecurso consulta = new ConsultaRecurso();
        if (consulta.estado(recurso)) {
            vaciarCampos();
            JOptionPane.showMessageDialog(null, "Se ha modificado el estado con exito!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            rellenarTablaRecurso();
        }
    }

    @FXML
    private void btnEmptyAct(ActionEvent event) {
        vaciarCampos();
    }

    public void closeWindows() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestionproyecto/Menu.fxml"));

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
    private void btnImprimirAct(ActionEvent event) {
        Reporte reporte = new Reporte("Recurso");
        reporte.generarReporte();
    }
}
