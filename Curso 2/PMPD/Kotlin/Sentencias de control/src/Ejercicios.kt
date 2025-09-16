fun main() {
    val ejercicios = Ejercicios()
    ejercicios.ejercicio5()
}

class Ejercicios {

    fun ejercicio1() {
        print("Introduce un numero: ")

        val n = readLine()!!.toInt()

        if (n % 2 == 0) {
            print("Es par")
        } else {
            print("Es impar")
        }
    }

    fun ejercicio2() {
        print("Introduce un numero: ")

        val n = readLine()!!.toInt()

        if (n < 0) {
            print("El numero $n es negativo")
        } else if (n > 0) {
            print("El numero $n es positivo")
        } else {
            print("El numero es 0l")
        }
    }

    fun ejercicio3() {
        print("Introduce un numero: ")

        val n = readLine()!!.toInt()

        if (esPrimo(n)) {
            print("El numero $n es primo")
        } else {
            print("El numero $n no es primo")
        }

    }

    fun ejercicio4() {
        print("Introduce un numero: ")

        val n = readLine()!!.toInt()

        for (i in 2..n) {
            if (esPrimo(i)) {
                print("$i ")
            }
        }
    }

    fun ejercicio5() {
        print("Introduce un numero: ")

        var n = readLine()!!.toInt()
        val numero = n

        for (i in 1..n-1) {
            n = n * i
        }

        print("El factorial de $numero es $n")
    }


    fun esPrimo(n: Int): Boolean {
        for (i in 2..n-1) {
            if (n%i==0) {
                return false
            }
        }
        return true
    }

}