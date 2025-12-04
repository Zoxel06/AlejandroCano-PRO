let lista = ["Palabra1", "Palabra2", "Palabra3"];
lista.push("Palabra4", "Palabra5");
lista.unshift("Palabra6", "Palabra7");
//lista[2] = "Palabra mia";

lista.pop();
lista.shift();

lista = lista.filter((element) => {
  return element != "Palabra3";
});

lista = lista.find((element) => {
  return element != "Palabra3";
});

console.log(lista);
