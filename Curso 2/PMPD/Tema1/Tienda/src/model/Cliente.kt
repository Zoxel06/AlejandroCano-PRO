package model

class Cliente(
    var id: Int,
    var nombre: String,
) {

    lateinit var carrito: ArrayList<Producto> //--> no hay que poner el ? porque un arraylist empieza siendo nulo SI o SI
    var factura: Double = 0.0

    init {
        carrito = arrayListOf()
    }

    fun agregarProductoCarrito(producto: Producto) {
        carrito.add(producto)
        println("Producto agregado al carrito correctamente")
    }

    fun mostrarCarrito() {
        if (carrito.isEmpty()) {
            println("No hay nada en el carrito")
        } else {
            carrito.forEach {
                it.mostrarDatos()
            }
        }
    }

    fun accesoPorPosicion(posicion: Int) {
        if (posicion > carrito.size - 1) {
            println("Id fuera de rango")
        } else {
            carrito[posicion].mostrarDatos()
            // tambien es lo mismo carrito.get(pocicion)
        }
    }

    fun eliminarProductoCarrito(id: Int) {

        var listaFiltrada = carrito.filter {
            return@filter it.id == id
        }

        if (listaFiltrada.isEmpty()) {
            println("No hay productos en el carrito con ese id")
        } else if (listaFiltrada.size == 1) {
            carrito.remove(listaFiltrada.first())
            println("Producto borrado correctamente")
        } else {
            println("Se han encontrado varias coincidencias, cual quieres borrar")
            println("1. El primero")
            println("N. Todos")

            val opcion: String = readln()
            if (opcion.equals("1", true)) {
                carrito.remove(listaFiltrada.first())
                println("Pimer producto con este id borrado correctamente")
            } else if (opcion.equals("n", true)) {
                carrito.removeAll(listaFiltrada.toSet())  //el toSet solo es para que lo haga mas rapido
                println("Productos con este id borrados correctamente")
            }
        }
    }

    fun calcularFactura() {
        if (carrito.isNotEmpty()) {
            carrito.forEach {
                factura += it.precio
            }
            println("$factura$")
        } else {
            println("El carrito esta vacio")
        }
    }

    fun pedirFactura() {
        if (carrito.isNotEmpty()){
            println("La factura es de $factura$")

            carrito.clear()
            factura = 0.0
            println("El carrito se ha vaciado")
        } else {
            println("El carrito esta vacio")
        }
    }

}