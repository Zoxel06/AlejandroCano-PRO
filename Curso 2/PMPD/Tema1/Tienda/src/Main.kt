import model.Categoria
import model.Cliente
import model.Producto
import model.Tienda

fun main() {


    /*
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
    var cliente: model.Cliente = model.Cliente(1,"model.Cliente 1")

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
     */

    var tienda = Tienda("Tienda")
    var cliente = Cliente(1, "model.Cliente")

    var opcion = 0

    do {
        println("1. Agregar producto a la tienda")
        println("2. Vender producto de la tienda")
        println("3. Buscar producto de la tienda")
        println("4. Buscar productos de la tienda por categoria")
        println("5. Agregar producto al carrito")
        println("6. Eliminar producto del carrito")
        println("7. Buscar producto del carrito")
        println("8. Mostrar carrito")
        println("9. Calcular factura")
        println("10. Pedir factura (+ vaciar carrito)")
        println("11. Salir")
        println("Elije una opción: ")

        opcion = readln().toInt()

        when (opcion) {
            1 -> {
                println("Elije un producto para agregar")
                println("1. Zapatillas - (Precio: 60.0)")
                println("2. Pantalones - (Precio: 45.0)")
                println("3. Camiseta - (Precio: 33.0)")
                println("4. Silla - (Precio: 15.0)")
                println("5. Armario - (Precio: 20.0)")
                println("6. Telefono - (Precio: 150.0)")
                println("7. Todos los productos anteriores")

                var opcion2 = readln().toInt()

                when (opcion2) {
                    1 -> {
                        val zapatillas = Producto(1, 60.0, "Zapatillas", "Zapatillas edicion especial", Categoria.Ropa)
                        if (tienda.estaProducto(zapatillas.id)) {
                            println("El producto ya está en tu tienda")
                        } else {
                            tienda.agregarProducto(zapatillas)
                            println("Produco agregado correctamente a la tienda")
                        }
                    }

                    2 -> {
                        val pantalones = Producto(2, 45.0, "Pantalones", "Pantalones vaqueros", Categoria.Ropa)
                        if (tienda.estaProducto(pantalones.id)) {
                            println("El producto ya está en tu tienda")
                        } else {
                            tienda.agregarProducto(pantalones)
                            println("Produco agregado correctamente a la tienda")
                        }
                    }

                    3 -> {
                        val camiseta = Producto(3, 33.0, "Camiseta", "Camiseta de poliester", Categoria.Ropa)
                        if (tienda.estaProducto(camiseta.id)) {
                            println("El producto ya está en tu tienda")
                        } else {
                            tienda.agregarProducto(camiseta)
                            println("Produco agregado correctamente a la tienda")
                        }
                    }

                    4 -> {
                        val silla =
                            Producto(4, 15.0, "Silla", "Silla resistente de madera de acacia", Categoria.Muebles)
                        if (tienda.estaProducto(silla.id)) {
                            println("El producto ya está en tu tienda")
                        } else {
                            tienda.agregarProducto(silla)
                            println("Produco agregado correctamente a la tienda")
                        }
                    }

                    5 -> {
                        val armario = Producto(5, 20.0, "Armario", "Armario amplio y practico", Categoria.Muebles)
                        if (tienda.estaProducto(armario.id)) {
                            println("El producto ya está en tu tienda")
                        } else {
                            tienda.agregarProducto(armario)
                            println("Produco agregado correctamente a la tienda")
                        }
                    }

                    6 -> {
                        val telefono = Producto(
                            6,
                            150.0,
                            "Telefono",
                            "Telefono con la mas nueva tecnologia incorporada",
                            Categoria.Tecnologia
                        )
                        if (tienda.estaProducto(telefono.id)) {
                            println("El producto ya está en tu tienda")
                        } else {
                            tienda.agregarProducto(telefono)
                            println("Produco agregado correctamente a la tienda")
                        }
                    }

                    7 -> {
                        val zapatillas = Producto(1, 60.0, "Zapatillas", "Zapatillas edicion especial", Categoria.Ropa)
                        val pantalones = Producto(2, 45.0, "Pantalones", "Pantalones vaqueros", Categoria.Ropa)
                        val camiseta = Producto(3, 33.0, "Camiseta", "Camiseta de poliester", Categoria.Ropa)
                        val silla =
                            Producto(4, 15.0, "Silla", "Silla resistente de madera de acacia", Categoria.Muebles)
                        val armario = Producto(5, 20.0, "Armario", "Armario amplio y practico", Categoria.Muebles)
                        val telefono = Producto(
                            6,
                            150.0,
                            "Telefono",
                            "Telefono con la mas nueva tecnologia incorporada",
                            Categoria.Tecnologia
                        )

                        if (tienda.almacen.isNotEmpty()) {
                            tienda.agregarProducto(zapatillas)
                            tienda.agregarProducto(pantalones)
                            tienda.agregarProducto(camiseta)
                            tienda.agregarProducto(silla)
                            tienda.agregarProducto(armario)
                            tienda.agregarProducto(telefono)
                            println("Productos agregados correctamente")
                        } else {
                            println("La tienda ya tiene uno de estos productos agregado, no puedo volver a agregarlo/s")
                        }

                    }
                }

            }

            2 -> {
                println("Introduce el id del producto que quieres vender")
                var id = readln().toInt()
                if (tienda.estaProducto(id)) {
                    tienda.venderProducto(id)
                    println("Producto vendido correctamente")
                } else {
                    println("No se ha encontrado el producto en el almacen")
                }
            }

            3 -> {
                println("Introduce el id del producto que quieres buscar")
                var id = readln().toInt()
                if (tienda.estaProducto(id)) {
                    tienda.buscarProductoId(id)
                } else {
                    println("No se ha encontrado el producto en el almacen")
                }
            }

            4 -> {
                println("Introduce la categoria de productos que quieres ver (1-Generica/2-Ropa/3-Muebles/4-Tecnologia)")
                var opcion = readln().toInt()
                var categoria: Categoria? = null

                when (opcion) {
                    1 -> {
                        categoria = Categoria.Generica
                    }

                    2 -> {
                        categoria = Categoria.Generica
                    }

                    3 -> {
                        categoria = Categoria.Generica
                    }

                    4 -> {
                        categoria = Categoria.Generica
                    }

                    else -> println("Opcion no permitida")
                }

                tienda.almacen.forEach {
                    if (it?.categoria == categoria) {
                        it?.mostrarDatos()
                    }
                }

            }

            5 -> {
                println("Introduce el id del producto que quieres agregar a tu carrito")
                var id = readln().toInt()
                var encontrado = false
                tienda.almacen.forEach {
                    if (it?.id == id) {
                        cliente.agregarProductoCarrito(it)
                        encontrado = true
                    }
                }
                if (!encontrado) {
                    println("El producto no se encuentra disponible en esta tienda")
                }
            }

            6 -> {
                if (cliente.carrito.isNotEmpty()) {
                    println("Introduce el id del producto que quieres borrar de tu carrito")
                    val id = readln().toInt()
                    var encontrado = false

                    cliente.carrito.forEach {
                        if (it.id == id) {
                            cliente.eliminarProductoCarrito(id)
                            encontrado = true
                        }
                    }
                    if (!encontrado) {
                        println("No se ha encontrado un producto con ese id en tu carrito")
                    }
                } else {
                    println("Tu carrito está vacío")
                }
            }

            7 -> {
                println("Introduce la posicion del carrito que quieres ver")
                var posicion = readln().toInt()
                cliente.accesoPorPosicion(posicion - 1)
            }

            8 -> cliente.mostrarCarrito()

            9 -> cliente.calcularFactura()

            10 -> cliente.pedirFactura()

            11 -> println("Saliendo...")

            else -> println("Opcion no permitida")
        }

    } while (opcion != 11)


}

//vamos a crear una tienda. Para ello crear la clase necesaria donde
//se pueda asignar:
//1- Nombre a la tienda (obligatorio)
//2- Almacen: sitio donde se guardan los productos. Tiene un tamaño fijo de 6
//3- Caja: se guardara la pasta cuando se venda un producto