import model.Categoria
import model.Producto
import model.Tienda

fun main() {



    var camiseta: Producto = Producto(id = 1, precio = 14.99, categoria = Categoria.Ropa)
    var zapatillas = Producto(2) // EL PRECIO ES 10.0 POR DEFECTO (parametro de la clase producto)
    // id, precio, nombre
    var pantalones = Producto(3,30.0,"Pantalones", categoria = Categoria.Ropa)
    var gorra = Producto(4,20.0, descripcion = "Gorra molona", categoria = Categoria.Ropa)
    var cartera = Producto(5,5.0,"Cartera","Cartera para guardar la pasta", categoria = Categoria.Generica)

    var telefono = Producto(6,500.0,"Iphone","Telefono movil", categoria = Categoria.Tecnologia)

    //println(gorra.mostrarDatos())

    val listaProductos: Array<Producto?> = arrayOf(camiseta, zapatillas, pantalones, gorra, cartera,telefono)

    val listaProductoVacio : Array<Producto?> = arrayOfNulls(5)

    //listaProductos[listaProductos.size-1].mostrarDatos()
    //listaProductos.first().mostrarDatos()
    //listaProductos.last().mostrarDatos()

    listaProductos.last()?.precio = 20.0
    listaProductos[2] = null

    /*for (i in listaProductos) {
        i?.mostrarDatos()
    }*/

    /*listaProductos.forEach {
        it?.mostrarDatos()
    }*/

    /*listaProductos.forEachIndexed { index, producto ->
        println("Mostrando producto en posicion $index")
        producto?.mostrarDatos()
    }*/

    var tienda: Tienda = Tienda("Tienda 1")
    var cliente: Cliente = Cliente(1,"Cliente 1")

    tienda.almacen = listaProductos
    //tienda.mostrarAlmacen()
    //tienda.buscarProductosCategoria(Categoria.Tecnologia)

    cliente.agregarProductoCarrito(gorra)
    cliente.agregarProductoCarrito(pantalones)
    cliente.agregarProductoCarrito(pantalones)
    //cliente.mostrarCarrito()
    //cliente.accesoPorPosicion(0)
    //cliente.eliminarProductoCarrito(3)
    //cliente.mostrarCarrito()

    cliente.calcularFactura()
    cliente.pedirFactura()



}

//vamos a crear una tienda. Para ello crear la clase necesaria donde
//se pueda asignar:
//1- Nombre a la tienda (obligatorio)
//2- Almacen: sitio donde se guardan los productos. Tiene un tamaño fijo de 6
//3- Caja: se guardara la pasta cuando se venda un producto