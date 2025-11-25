package org.example.examen1ertrimestre.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.example.examen1ertrimestre.model.Elemento;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxCategorias;
    @FXML
    private Button buttonFiltrar;
    @FXML
    private Button buttonDetalle;

    @FXML
    private ListView<Elemento> listViewProductos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listViewProductos.getItems().addAll(
                new Elemento("Laptop Lenovo", "tecnología", 799.99, "Portátil de alto rendimiento, 16GB RAM", 1),
                new Elemento("Smartphone Samsung", "tecnología", 599.50, "Teléfono móvil con pantalla Super AMOLED", 2),
                new Elemento("Smartwatch Xiaomi", "tecnología", 89.90, "Reloj inteligente con monitoreo de salud", 3),
                new Elemento("Televisor LG 4K", "tecnología", 1100.00, "Smart TV 55 pulgadas, 4K Ultra HD", 4),
                new Elemento("Auriculares Sony", "tecnología", 129.99, "Auriculares inalámbricos con cancelación de ruido", 5),
                new Elemento("Camisa azul", "ropa", 24.95, "Camisa de algodón, manga larga", 6),
                new Elemento("Pantalón vaquero", "ropa", 34.99, "Jeans unisex de corte recto", 7),
                new Elemento("Chaqueta de cuero", "ropa", 99.99, "Chaqueta negra, estilo moderno", 8),
                new Elemento("Vestido de fiesta", "ropa", 59.90, "Vestido largo, color rojo", 9),
                new Elemento("Sudadera deportiva", "ropa", 42.00, "Sudadera con capucha, algodón", 10),
                new Elemento("Sillón reclinable", "muebles", 249.99, "Sillón tapizado, mecanismo reclinable", 11),
                new Elemento("Mesa de comedor", "muebles", 150.00, "Mesa rectangular para 6 personas", 12),
                new Elemento("Estante para libros", "muebles", 75.50, "Estante de madera, 5 niveles", 13),
                new Elemento("Cama doble", "muebles", 320.00, "Cama de matrimonio, colchón incluido", 14),
                new Elemento("Silla ergonómica", "muebles", 115.00, "Silla de oficina, respaldo ajustable", 15)
        );


        instancias();

        buttonFiltrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String categoriaSeleccionada = comboBoxCategorias.getSelectionModel().getSelectedItem();
                if (categoriaSeleccionada != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Productos Filtrados");
                    alert.setHeaderText("Categoría seleccionada: " + categoriaSeleccionada);
                    for (Elemento elemento : listViewProductos.getItems()) {
                        if (elemento.getCategoria().equals(categoriaSeleccionada)) {
                            alert.setContentText(alert.getContentText() + "\n" +
                                    "Id: " + elemento.getId() + ", Nombre: " + elemento.getNombre() +
                                    ", Precio: " + elemento.getPrecio());
                        }
                    }
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Advertencia");
                    alert.setHeaderText("No se ha seleccionado ninguna categoría.");
                    alert.setContentText("Por favor, seleccione una categoría para filtrar los productos.");
                    alert.show();
                }
            }
        });

        buttonDetalle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Elemento elementoSeleccionado = listViewProductos.getSelectionModel().getSelectedItem();
                if (elementoSeleccionado != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Detalle del Producto");
                    alert.setHeaderText("Información del Producto");
                    alert.setContentText("Id: " + elementoSeleccionado.getId() + "\n" +
                            "Nombre: " + elementoSeleccionado.getNombre() + "\n" +
                            "Categoría: " + elementoSeleccionado.getCategoria() + "\n" +
                            "Precio: " + elementoSeleccionado.getPrecio() + "\n" +
                            "Descripción: " + elementoSeleccionado.getDescripcion());
                    alert.show();
                } else {
                    System.out.println("No se ha seleccionado ningún producto.");
                }
            }
        });

    }

    private void instancias() {
        comboBoxCategorias.getItems().addAll("tecnología", "ropa", "muebles");
    }


}
