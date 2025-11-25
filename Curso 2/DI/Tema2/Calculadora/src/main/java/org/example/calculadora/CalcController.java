package org.example.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalcController {

    @FXML
    private TextField textFieldOperaciones;

    private double num1 = 0;
    private String operador = "";
    private boolean numeroNuevo = true;

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        String texto = btn.getText();

        switch (texto) {
            case "C":
                textFieldOperaciones.clear();
                operador = "";
                num1 = 0;
                numeroNuevo = true;
                break;
            case "←":
                if (!textFieldOperaciones.getText().isEmpty()) {
                    textFieldOperaciones.setText(textFieldOperaciones.getText(0, textFieldOperaciones.getText().length() - 1));
                }
                break;
            case "±":
                if (!textFieldOperaciones.getText().isEmpty()) {
                    if (textFieldOperaciones.getText().startsWith("-")) {
                        textFieldOperaciones.setText(textFieldOperaciones.getText().substring(1));
                    } else {
                        textFieldOperaciones.setText("-" + textFieldOperaciones.getText());
                    }
                }
                break;
            case "+": case "-": case "*": case "/":
                if (!textFieldOperaciones.getText().isEmpty()) {
                    num1 = Double.parseDouble(textFieldOperaciones.getText());
                    operador = texto;
                    numeroNuevo = true;
                }
                break;
            case "=":
                if (!textFieldOperaciones.getText().isEmpty() && !operador.isEmpty()) {
                    double num2 = Double.parseDouble(textFieldOperaciones.getText());
                    double resultado = 0;
                    switch (operador) {
                        case "+": resultado = num1 + num2; break;
                        case "-": resultado = num1 - num2; break;
                        case "*": resultado = num1 * num2; break;
                        case "/":
                            if(num2 != 0) resultado = num1 / num2;
                            else {
                                textFieldOperaciones.setText("Error");
                                operador = "";
                                numeroNuevo = true;
                                return;
                            }
                            break;
                    }
                    textFieldOperaciones.setText(String.valueOf(resultado));
                    operador = "";
                    numeroNuevo = true;
                }
                break;
            case ".":
                if (numeroNuevo) {
                    textFieldOperaciones.setText("0.");
                    numeroNuevo = false;
                } else if (!textFieldOperaciones.getText().contains(".")) {
                    textFieldOperaciones.appendText(".");
                }
                break;
            default: // números
                if (numeroNuevo) {
                    textFieldOperaciones.setText(texto);
                    numeroNuevo = false;
                } else {
                    textFieldOperaciones.appendText(texto);
                }
                break;
        }
    }
}
