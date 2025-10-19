import java.util.Calendar
import kotlin.concurrent.thread

fun main() {

    thread {
        while (true) {
            val horaactual = Calendar.getInstance().time
            println(horaactual)
            Thread.sleep(1000)
        }
    }

}