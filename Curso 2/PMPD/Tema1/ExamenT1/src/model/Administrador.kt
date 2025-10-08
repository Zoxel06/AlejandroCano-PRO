package model

class Administrador(
    var id: Int,
    var nombre: String,
    var clave: Int
) {

    fun iniciarJuego(clave: Int): Boolean {
        if (clave == this.clave){
            println("El juego fantasy ha iniciado correctamente")
            return true
        } else {
            println("La clave no coindice con la necesaria, el juego no se puede iniciar")
        }
        return false
    }

}