package org.example.pizzeria.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.pizzeria.HelloApplication;
import org.example.pizzeria.model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PizzeriaController implements Initializable {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldTelefono;
    @FXML
    private ComboBox<String> comboBoxTipoPizza;
    @FXML
    private RadioButton radioButtonPequenia;
    @FXML
    private RadioButton radioButtonMediana;
    @FXML
    private RadioButton radioButtonFamiliar;
    @FXML
    private Button buttonPedir;
    @FXML
    private Button buttonServir;
    @FXML
    private Button buttonPendientes;
    @FXML
    private Button buttonDetalle;
    @FXML
    private ListView<String> listViewPedidos;

    private ToggleGroup grupoTamanio;

    private ArrayList<Usuario> listaUsuarios;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        initGUI();
        acciones();
    }

    private void acciones() {
        buttonPedir.setOnAction(new ManejoActions());
        buttonServir.setOnAction(new ManejoActions());
        buttonDetalle.setOnAction(new ManejoActions());
        buttonPendientes.setOnAction(new ManejoActions());
    }

    private void initGUI() {
        listViewPedidos.setVisible(false);
    }

    private void instancias() {
        grupoTamanio = new ToggleGroup();
        grupoTamanio.getToggles().addAll(radioButtonPequenia, radioButtonMediana, radioButtonFamiliar);

        comboBoxTipoPizza.getItems().addAll("Barbacoa", "Hawaiana", "Jamon y queso", "Cuatro Quesos");

        listaUsuarios = new ArrayList<>();
    }


    class ManejoActions implements EventHandler<ActionEvent> {
        int id = 0;
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == buttonPedir) {
                if (textFieldNombre.getText().isBlank() || textFieldTelefono.getText().isBlank() || comboBoxTipoPizza.getSelectionModel().isEmpty() ||
                        grupoTamanio.getSelectedToggle() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error en los datos");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor, complete todos los campos del pedido.");
                    alert.show();
                } else {

                    String nombre = textFieldNombre.getText();
                    String telefono = textFieldTelefono.getText();
                    String pizza = comboBoxTipoPizza.getSelectionModel().getSelectedItem();
                    String tamanio = "";

                    if (grupoTamanio.getSelectedToggle() == radioButtonPequenia) {
                        tamanio = "Pequeña";
                    } else if (grupoTamanio.getSelectedToggle() == radioButtonMediana) {
                        tamanio = "Mediana";
                    } else if (grupoTamanio.getSelectedToggle() == radioButtonFamiliar) {
                        tamanio = "Familiar";
                    }


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pedido añadido correctamente");
                    alert.setHeaderText(null);
                    alert.setContentText(String.format("Pedido realizado:\nNombre: %s\nTeléfono: %s\nPizza: %s\nTamaño: %s",
                            nombre, telefono, pizza, tamanio));
                    alert.show();


                    textFieldNombre.clear();
                    textFieldTelefono.clear();
                    comboBoxTipoPizza.getSelectionModel().clearSelection();
                    grupoTamanio.getSelectedToggle().setSelected(false);

                    id += 1;

                    listViewPedidos.getItems().add("Pedido: " + id + " - Pizza: " + pizza);

                }
            } else if (actionEvent.getSource() == buttonServir) {
                String pedidoSeleccionado = listViewPedidos.getSelectionModel().getSelectedItem();
                if (pedidoSeleccionado == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al servir el pedido");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor, seleccione un pedido para servir.");
                    alert.show();
                } else {
                    listViewPedidos.getItems().remove(pedidoSeleccionado);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pedido servido correctamente");
                    alert.setHeaderText(null);
                    alert.setContentText("El pedido ha sido servido correctamente.");
                    alert.show();
                }
            } else if (actionEvent.getSource() == buttonPendientes) {
                if (listViewPedidos.isVisible()) {
                    listViewPedidos.setVisible(false);
                } else {
                    listViewPedidos.setVisible(true);
                }
            } else if (actionEvent.getSource() == buttonDetalle) {
                String pedidoSeleccionado = listViewPedidos.getSelectionModel().getSelectedItem();
                if (pedidoSeleccionado == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al mostrar el detalle");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor, seleccione un pedido para ver el detalle.");
                    alert.show();
                } else {
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("detalle-view.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                        DetalleController detalleController = loader.getController();
                        detalleController.setDetalle(pedidoSeleccionado);

                        Stage stage = new Stage();
                        stage.setTitle("Detalle del Pedido");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            }

        }
    }
}



