package model

class Producto(var id: Int, var precio: Double = 10.0, var nombre: String? = null, var descripcion: String? = null) {

    // id
    // precio

    /*var nombre: String? = null
        var descripcion: String? = null

        constructor(id: Int, precio: Double, nombre: String): this(id,precio) {
            this.nombre = nombre
        }

        constructor(id: Int, precio: Double, descripcion: String?): this(id,precio) {
            this.descripcion = descripcion
        }

        constructor(id: Int, precio: Double, nombre: String, descripcion: String): this(id,precio) {
            this.nombre = nombre
            this.descripcion = descripcion
        }
         */

    // metodo para poder poder mostrar todos los datos del producto concreto
    // si no hay nombre, aparece SIN NOMBRE
    // si no hay descripcion, aparece SIN DESCRIPCION

    override fun toString(): String {
        return "id=$id, precio=$precio, nombre=$nombre, descripcion=$descripcion"
    }

    fun mostrarDatos() {
        println("ID: $id")
        println("Precio: $precio")
        println("Nombre: ${nombre?: "Sin definir"}")
        println("Descripcion: ${descripcion?: "Sin definir"}")
    }
}