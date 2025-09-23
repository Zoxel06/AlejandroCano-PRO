package model

import java.lang.classfile.constantpool.DoubleEntry

class Tienda(var nombre: String) {

    var caja: Double = 0.0
    lateinit var almacen: Array<Producto?>

    // init -> se ejecuta SI O SI despues del constructor (ya sea primario o secundario)

    init {
        almacen = arrayOfNulls(6)
    }

    // un metodo para poder mostrar todos los productos del almacen
    // en caso de tener todos los huecos disponibles (vacios) sacar un aviso


    override fun toString(): String {
        return super.toString()
    }

    fun mostrarAlmacen() {
        var nulos = 0
        almacen.forEach {
            it?.mostrarDatos() ?: nulos++
        }
        if (nulos == almacen.size)
            println("No hay productos en el almacen")
    }

    // agregar un elemento al almacen. En caso de no tener hueco disponible se mostrara un aviso

    fun agregarProducto(producto: Producto) {
        for (item in 0..almacen.size - 1) {
            if (almacen[item] == null) {
                almacen[item] = producto
                return
            }
        }
        print("El almacen esta completo")
    }

    fun venderProducto(id: Int) {
        for (item in 0..almacen.size - 1) {
            if (almacen[item]?.id == id) {
                caja += almacen[item]!!.precio
                almacen[item] = null
                return
            }
        }
        print("El id indicado no esta en la lista")
    }

    fun buscarProductosCategoria(categoria: Categoria) {
        // filtrando -> obteniendo varios


    }

    fun buscarProductoId(id: Int): Unit {
        // buscando -> obtengo solo 1 elemento

        var productoBusqueda: Producto? = almacen.find {
            return@find it?.id == id
        }
    }

    // vamos a vender un producto. Para ello se solicita el id del producto a vender
    // En caso de estar en el almacen_
    // se elimina de este
    // su precio se suma a la caja
    // si se vende se da aviso de producto vendido y se da el valor de la caja
    // si no se encuentra se da aviso de que no esta en el almacen

}