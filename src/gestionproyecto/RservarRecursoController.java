/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproyecto;

import clases.ReservaRecurso;
import clases.Usuario;
import consultas.ConsultaReserva;
import consultas.ConsultasUsuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author lexlp
 */
public class RservarRecursoController implements Initializable {

    public ObservableList<ReservaRecurso> dataReserva = FXCollections.observableArrayList();
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEmpty;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TableView<ReservaRecurso> tablaReserva;
    @FXML
    private TableColumn<ReservaRecurso, String> colNombreAct;
    @FXML
    private TableColumn<ReservaRecurso, String> colDescripcion;
    @FXML
    private TableColumn<ReservaRecurso, String> colFecha;
    @FXML
    private TableColumn<ReservaRecurso, String> colHoraIni;
    @FXML
    private TableColumn<ReservaRecurso, String> ColHoraFin;
    @FXML
    private Label labelResultados;
    @FXML
    private Button bntRegresar;
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtHoraInicio;
    @FXML
    private TextField txtHoraFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        rellenarTablaUsuarios();
        vaciarCampos();
        deshabilitarCampos();
    }

    public void configurarTabla() {
        colNombreAct.setCellValueFactory(new PropertyValueFactory<>("nombreActividad"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaReserva.setItems(dataReserva);
    }

    public void rellenarTablaUsuarios() {

        dataReserva.clear();
        ConsultaReserva consultas = new ConsultaReserva();
        ObservableList<ReservaRecurso> reservaRecurso = consultas.buscarTodos();
        dataReserva.setAll(reservaRecurso);
        int resultado = reservaRecurso.size();
        labelResultados.setText("Resultados: " + resultado);
    }

    public void vaciarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
    }

    public void deshabilitarCampos() {
        txtNombre.setDisable(true);
        txtDescripcion.setDisable(false);
    }

    public void habilitarCampos() {
        txtNombre.setDisable(true);
        txtDescripcion.setDisable(false);
    }

    @FXML
    private void btnCrearAct(ActionEvent event) {
    }

    @FXML
    private void btnModAct(ActionEvent event) {
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
    }

    @FXML
    private void contextMenuClick(MouseEvent event) {
    }

    @FXML
    private void bntRegresarAct(ActionEvent event) {
    }

}
