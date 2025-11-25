package org.example.pizzeria.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class DetalleController implements Initializable {

    @FXML
    private TextArea textAreaDetalle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void setDetalle(String detalle) {
        textAreaDetalle.setText(detalle);
    }
}
