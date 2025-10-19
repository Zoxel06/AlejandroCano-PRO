import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Stage
import kotlinx.coroutines.*

class MultiplicationTablesApp : Application() {

    override fun start(primaryStage: Stage) {
        val root = ScrollPane()
        val container = GridPane()
        container.hgap = 20.0
        container.vgap = 20.0
        container.padding = Insets(20.0)
        root.content = container

        // Lanzamos corutinas para no bloquear la UI
        runBlocking {
            val jobs = mutableListOf<Job>()
            for (tabla in 1..10) {
                val job = launch(Dispatchers.Default) {
                    val tableBox = createTableBox(tabla)
                    // Actualizamos la UI en el hilo de JavaFX
                    javafx.application.Platform.runLater {
                        val col = (tabla - 1) % 5
                        val row = (tabla - 1) / 5
                        container.add(tableBox, col, row)
                    }
                }
                jobs.add(job)
            }
            jobs.joinAll() // Esperamos a que terminen todas las corrutinas
        }

        val scene = Scene(root, 800.0, 600.0)
        primaryStage.title = "Tablas de Multiplicar"
        primaryStage.scene = scene
        primaryStage.show()
    }

    private fun createTableBox(tabla: Int): VBox {
        val box = VBox(5.0)
        box.padding = Insets(10.0)
        box.style = "-fx-background-color: linear-gradient(to bottom right, #ffd194, #70e1f5); -fx-border-color: #333; -fx-border-radius: 5; -fx-background-radius: 5;"

        val title = Label("Tabla del $tabla")
        title.font = Font.font("Arial", 18.0)
        title.textFill = Color.DARKBLUE

        box.children.add(title)

        for (i in 1..10) {
            val label = Label("$tabla x $i = ${tabla * i}")
            label.font = Font.font("Arial", 14.0)
            label.textFill = Color.DARKGREEN
            box.children.add(label)
        }

        return box
    }
}

fun main() {
    Application.launch(MultiplicationTablesApp::class.java)
}
