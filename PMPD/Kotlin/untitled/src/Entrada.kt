fun main(){

    // comentario de linea
    /*
       comentario multiple
     */

    println("Este es mi primer programa Kotlin")

    var nombre = "Alejandro" // String
    var edad = 19 // Int
    var correo: String? = null
    lateinit var direccion: String
    val DNI = "123123A"

    // Hola soy Alejandro, tengo 19 años y mi DNI es 123123A

    println("Hola soy $nombre, tengo $edad años y mi DNI es $DNI")
    println("Mi nombre tiene ${nombre.length} letras")
    println("Mi correo es ${correo?.length?:"sin asignar"}")
    direccion = "Mostoles"
    println("Mi direccion es $direccion")


}
