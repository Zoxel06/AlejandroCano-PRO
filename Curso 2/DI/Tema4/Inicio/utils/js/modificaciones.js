let botonAgregar = document.querySelector("#btnAgregar");
let botonLimpiar = document.querySelector("#btnLimpiar");
let textoNombre = document.querySelector("#inputNombre");
let textoApellido = document.querySelector("#inputApellido");
let textoFecha = document.querySelector("#inputFecha");
let selectEdad = document.querySelector("#selectEdad");
let listaAgregados = document.querySelector("#divAgregados ul");

botonAgregar.addEventListener("click", (ev) => {
  if (
    textoNombre.value == "" ||
    textoApellido.value == "" ||
    textoFecha.value == ""
  ) {
    lanzarDialogo("Error", "Faltan datos", "warning");
  } else {
    agregarLi(textoNombre.value, textoApellido.value, textoFecha.value);
  }
});

botonLimpiar.addEventListener("click", (ev) => {
  limpiarCampos(textoNombre, textoApellido, textoFecha);
});

function limpiarCampos() {
  for (let index = 0; index < arguments.length; index++) {
    arguments[index].value = "";
  }
}

function lanzarDialogo(title, text, icon) {
  Swal.fire({
    title: title,
    text: text,
    icon: icon,
  });
}

function agregarLi(nombre, apellido, fecha) {
  /* listaAgregados.innerHTML += `<li
  class=" animate__animated animate__fadeInRight  list-group-item d-flex justify-content-between align-items-start" >
    ${(textoNombre.value, textoApellido.value, textoFecha.value)}
    </li>`; */

  let nodoLi = document.createElement("li");
  nodoLi.innerText = `${nombre} ${apellido} ${fecha}`;
  /* nodoLi.className =
    " animate__animated animate__fadeInRight  list-group-item d-flex justify-content-between align-items-start"; */
  nodoLi.classList.add(
    "animate__animated",
    "animate__fadeInRight",
    "list-group-item",
    "d-flex",
    "justify-content-between",
    "align-items-start"
  );

  listaAgregados.appendChild(nodoLi);

  limpiarCampos(textoNombre, textoApellido, textoFecha);

  /* lanzarDialogo(
    "Agregado correctamente",
    `Usuario con nombre ${textoNombre.value} agregado correctamente`,
    "sucess"
  ); */
}
