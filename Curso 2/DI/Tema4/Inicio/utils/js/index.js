let nombre = prompt("Introduce tu nombre:");
let respuesta = true;

let n1 = 0;
let n2 = 0;

let suma = n1 + n2;
let resta = n1 - n2;
let multiplicacion = n1 * n2;
let division = n1 / n2;

while (respuesta) {
  n1 = Number(prompt("Introduce el primer numero:"));
  n2 = Number(prompt("Introduce el segundo numero:"));
  if (!isNaN(n1) && !isNaN(n2)) {
    let operacion = prompt(
      "Que operacion quieres realizar? (sum, res, mul, div)"
    );

    switch (operacion) {
      case "sum":
        suma = n1 + n2;
        alert(`Hola ${nombre}, la suma de ${n1} y ${n2} es: ${suma}`);
        break;
      case "res":
        resta = n1 - n2;
        alert(`Hola ${nombre}, la resta de ${n1} y ${n2} es: ${resta}`);
        break;
      case "mul":
        multiplicacion = n1 * n2;
        alert(
          `Hola ${nombre}, la multiplicacion de ${n1} y ${n2} es: ${multiplicacion}`
        );
        break;
      case "div":
        division = n1 / n2;
        alert(`Hola ${nombre}, la division de ${n1} y ${n2} es: ${division}`);
        break;
      default:
        alert("Operacion no valida");
        continue;
    }

    respuesta = confirm("¿Quieres realizar otra operacion?");
  } else {
    alert("Por favor introduce solo numeros validos.");
  }
}
