// nominales - flecha
// nomilales -> function (parametros) {}

function realizarCalculo(op1, op2) {
  console.log(`La suma de los dos parametros es ${op1 + op2}`);
}

function realizarCalculoRetorno(op1, op2) {
  return op1 + op2;
}

function realizarCalculoDefecto(op1, op2 = 7) {
  return op1 + op2;
}

function realizarCalculoFantasma(op1) {
  console.log("Calculo fantasma");
  console.log(op1 + " es parametro requerido");
  // arguments -> argumentos fantasma que son pasados adicionalmente..[]
  console.log(`El numero de argumentos fantasma es de ${arguments.length}`);
  // hacer la suma de todos los argumentos que me pasan por parametros
  // indicando cuantos son pasados y cuantos son fantasma
  // hay 4 argumentos, 3 fantasmas
  // la suma de todos es 10

  let suma = 0;

  for (let i = 0; i < arguments.length; i++) {
    suma += arguments[i];
  }

  console.log(`La suma de los argumentos es ${suma}`);
}

realizarCalculoFantasma(1, 2, 3, 4);

/* console.log(`La suma con retorno y default es ${realizarCalculoDefecto(1)}`); */
/* console.log(`El resultado de la operacion es ${realizarCalculoRetorno(4, 5)}`); */
