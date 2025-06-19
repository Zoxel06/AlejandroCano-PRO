//alert -> feedback informativo
// confirm -> feedback de confirmación
// prompt -> feedback de entrada de datos

let nombre = "Alejandro Cano"
let edad = 19

// alert(`Primera página dinámica de ${nombre}`)

/* let respuesta = confirm("Estás seguro que quieres continuar?")

if (respuesta) {
    alert(`Bienvenido ${nombre} a la segunda parte del ejercicio`)
} else {
    alert("Has seleccionado que no quieres continuar")
} */

/* let nombrePrompt = prompt("Introduce tu nombre", "nombre random")

console.log(nombrePrompt); */

/* Pedir por prompt un nombre
   Una vez introducido, preguntar al usuario si quiere hacer operaciones
      - En caso de decir que si, preguntar por operando 1 y operando 2
        y mostrar por consola todos los resultados de las operaciones (+, -, *, /)
      - En caso de decir que no, mostrar un mensaje de despedida */

let nombrePrompt = prompt("Introduce tu nombre")

if (nombrePrompt) {
    let respuesta = prompt("Quieres hacer operaciones?")

    if (respuesta.toLowerCase("si")) {
        let operando1 = Number(prompt("Dime el operando 1"))
        let operando2 = Number(prompt("Dime el operando 2"))

        if (isNaN(operando1) || isNaN(operando2)) {
            alert("Tienes que introducir numeros validos")
        } else {
            let suma = operando1 + operando2 // para que me lo de sin decimales se usa -> parseInt(operando1)
                                                         // para que me lo de con decimales se usa -> parseFloat(operando1)
        let resta = operando1 - operando2
        let multiplicacion = operando1 * operando2
        let division = operando1 / operando2

        console.log(`Suma: ${suma}, Resta: ${resta}, Multiplicacion: ${multiplicacion}, Division: ${division}`);
        }
        
    } else {
        console.log("Pues hasta luego");
    }
} else {
    console.log("No has introducido tu nombre, no puedes seguir");
}