package model

class Mutante(
    id: String,
    nombre: String,
    nivel: Int,
    var armaBiologica: ArmaBiologica
): Superheroe(
    id, nombre, nivel
)
{

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("armaBiologica = ${armaBiologica}")
    }

}