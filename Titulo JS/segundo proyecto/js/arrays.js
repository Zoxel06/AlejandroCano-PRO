// En Java
// array -> no dinamico
// arraylist -> dinamico

let numeros = [2521, 53426, 743, 454, 5125, 654, 1237, true, false, "hola"] // los tipos de este array son ANY

// obtener un dato
numeros[1]
numeros[numeros.length-1]
// cuantos tengo
numeros.length
// modifico un elemento
numeros[0] = 1;
// agregar un array
numeros[16] = 234123

// como obtener todos los numeros de un array
/* for (let index = 0; index < numeros.length; index++) {
    console.log(numeros[index]);
} */

/* numeros.forEach((item, index)=> {
    if (index%2 == 0) {
        console.log(`Posicion ${index} -> ${item}`);
    }
}) */

// item/element -> el elemento del array que estoy iterando
// index/position -> la posicion del array
// a -> el array que se recorre


let tareas = ["Programar", "Documentar", "Probar", "Presentar"]
// lenght -> 4

// agregar cosas al array -> AL FINAL
tareas.push("Testea", "Modificar", "Gestionar")
// agregar cosas al array -> AL PRINCIPIO
tareas.unshift("Planificar", "Entrevistar")
// borra el ULTIMO elemento del array
console.log(`El elemento borrado es ${tareas.pop()}`)
// borra el PRIMER elemento del array
console.log(`El elemento borrado es ${tareas.shift()}`)



// tareas.forEach((item, index) => console.log(`${index} - ${item}`));

/* 0 - Entrevistar
1 - Programar
2 - Documentar
3 - Probar
4 - Presentar
5 - Testea
6 - Modificar */

// busquedas simples -> undefined o any
let elementoBuscado = tareas.find((item) => { 
    return item.length == 6
    /* if (item.length == 6) {
        return true;
    }
    return false; */
    })


let elementoBuscado2 = tareas.find((item) => item.length == 6)

/* console.log(elementoBuscado2) */

let elementosBuscados = tareas.filter((item) => { return item.length == 6 })

/* console.log(elementosBuscados); */

// quiero que elimineis el elemento Presentar del array

tareas = tareas.filter((item) => item != "Presentar")

console.log(tareas)