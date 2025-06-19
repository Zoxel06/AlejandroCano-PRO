// hacer una clase coche con las siguientes propiedades:
// marca, modelo, color, año, precio
// constructor que reciba todas las propiedades
// getters y setters para todas las propiedades
// mostrar datos

// en el fichero promesas
// crear un array de coches, con al menos 5 coches con datos aleatorios

class Coche {

    marca
    modelo
    color
    año
    precio

    constructor(marca, modelo, color, año, precio) {
        this.marca = marca
        this.modelo = modelo
        this.color = color
        this.año = año
        this.precio = precio
    }

    mostrarDatos() {
        console.log(`Marca: ${this.marca}`)
        console.log(`Modelo: ${this.modelo}`)
        console.log(`Color: ${this.color}`)
        console.log(`Año: ${this.año}`)
        console.log(`Precio: ${this.precio}`)
    }

    get getMarca() {
        return this.marca
    }

    get getModelo() {
        return this.modelo
    }

    get getColor() {
        return this.color
    }

    get getAño() {
        return this.año
    }

    get getPrecio() {
        return this.precio
    }

    set setMarca(marca){
        this.marca = marca
    }

    set setModelo(modelo){
        this.modelo = modelo
    }

    set setColor(color){
        this.color = color
    }

    set setAño(año){
        this.año = año
    }

    set setPrecio(precio){
        this.precio = precio
    }

}