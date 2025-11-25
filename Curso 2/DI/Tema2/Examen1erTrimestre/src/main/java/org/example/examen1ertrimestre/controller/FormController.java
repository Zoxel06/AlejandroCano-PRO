package org.example.examen1ertrimestre.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.examen1ertrimestre.HelloApplication;
import org.example.examen1ertrimestre.dao.UsuarioDAO;
import org.example.examen1ertrimestre.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldContrasenia;
    @FXML
    private Button buttonInicio;
    @FXML
    private TextField textFieldCorreo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UsuarioDAO usuarioDAOImp = new UsuarioDAO();
        usuarioDAOImp.realizarInsercion(new Usuario("Usuario1","correo1","contrasenia1"));
        usuarioDAOImp.realizarInsercion(new Usuario("Usuario2","correo2","contrasenia2"));

        acciones();
    }

    private void acciones() {
        buttonInicio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String nombre = textFieldNombre.getText();
                String contrasenia = textFieldContrasenia.getText();
                String correo = textFieldCorreo.getText();
                System.out.println("Nombre: " + nombre);
                System.out.println("Contraseña: " + contrasenia);
                System.out.println("Correo: " + correo);

                if (nombre.isEmpty() || contrasenia.isEmpty() || correo.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de validación");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor, complete todos los campos.");
                    alert.show();
                    return;
                }

                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productos-view.fxml"));
                Parent root = null;

                try {
                    root = loader.load();
                    ProductosController productosController = loader.getController();
                    Stage stage = new Stage();
                    stage.setTitle("Productos");
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }



                ((Stage)buttonInicio.getScene().getWindow()).close();

            }
        });
    }


}
