import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val input = BufferedReader(InputStreamReader(System.`in`))
    val array = input.readLine().split(",").toTypedArray()
    print(array.joinToString(" ")+"\n")

}