const API_KEY = "123";
const BASE_URL = `https://www.thesportsdb.com/api/v1/json/${API_KEY}`;

const selectPais = document.getElementById("selectPais");
const contenedorEquipos = document.getElementById("contenedorEquipos");
const contenedorJugadores = document.getElementById("contenedorJugadores");
const tituloEquipo = document.getElementById("tituloEquipo");

const EQUIPO_PLACEHOLDER = "https://via.placeholder.com/100?text=Equipo";
const JUGADOR_PLACEHOLDER = "https://via.placeholder.com/100?text=Jugador";

fetch(`${BASE_URL}/all_countries.php`)
  .then((res) => res.json())
  .then((data) => {
    selectPais.innerHTML = '<option value="">Selecciona un país</option>';
    data.countries.forEach((pais) => {
      const opcion = document.createElement("option");
      opcion.value = pais.name_en;
      opcion.textContent = pais.name_en;
      selectPais.appendChild(opcion);
    });
  })
  .catch((error) => {
    console.error("Error cargando países:", error);
    selectPais.innerHTML = '<option value="">Error al cargar países</option>';
  });

selectPais.addEventListener("change", () => {
  contenedorJugadores.innerHTML = "";
  tituloEquipo.textContent = "";

  const pais = selectPais.value;
  contenedorEquipos.innerHTML = `<div class="col-12">Cargando equipos…</div>`;

  if (!pais) {
    contenedorEquipos.innerHTML = "";
    return;
  }

  fetch(
    `${BASE_URL}/search_all_teams.php?c=${encodeURIComponent(pais)}&s=Soccer`
  )
    .then((res) => res.json())
    .then((data) => {
      const equipos = data.teams || [];
      contenedorEquipos.innerHTML = "";

      if (equipos.length === 0) {
        contenedorEquipos.innerHTML =
          "<div class='col-12'>No se encontraron equipos.</div>";
        return;
      }

      equipos.forEach((equipo) => {
        const col = document.createElement("div");
        col.className = "col-12 col-sm-6 col-md-4 col-lg-3";

        const card = document.createElement("div");
        card.className = "card h-100 text-center";

        const img = document.createElement("img");
        img.src = equipo.strTeamBadge || EQUIPO_PLACEHOLDER;
        img.className = "card-img-top p-3";
        img.alt = equipo.strTeam;
        card.appendChild(img);

        const body = document.createElement("div");
        body.className = "card-body";

        const nombre = document.createElement("h5");
        nombre.className = "card-title";
        nombre.textContent = equipo.strTeam;
        body.appendChild(nombre);

        const boton = document.createElement("button");
        boton.className = "btn btn-primary";
        boton.textContent = "Ver plantilla";
        boton.onclick = () => cargarJugadores(equipo.idTeam, equipo.strTeam);

        body.appendChild(boton);
        card.appendChild(body);
        col.appendChild(card);
        contenedorEquipos.appendChild(col);
      });
    })
    .catch((error) => {
      console.error("Error cargando equipos:", error);
      contenedorEquipos.innerHTML =
        "<div class='col-12'>Error al cargar equipos.</div>";
    });
});

function cargarJugadores(idEquipo, nombreEquipo) {
  contenedorJugadores.innerHTML = `<div class="col-12">Cargando jugadores…</div>`;
  tituloEquipo.textContent = `Plantilla de ${nombreEquipo}`;

  fetch(`${BASE_URL}/lookup_all_players.php?id=${idEquipo}`)
    .then((res) => res.json())
    .then((data) => {
      contenedorJugadores.innerHTML = "";
      const jugadores = data.player || [];

      if (jugadores.length === 0) {
        contenedorJugadores.innerHTML =
          "<div class='col-12'>No se encontraron jugadores.</div>";
        return;
      }

      jugadores.forEach((jugador) => {
        const col = document.createElement("div");
        col.className = "col-12 col-sm-6 col-md-4 col-lg-3";

        const card = document.createElement("div");
        card.className = "card h-100 text-center";

        const img = document.createElement("img");
        img.src = jugador.strCutout || jugador.strThumb || JUGADOR_PLACEHOLDER;
        img.className = "card-img-top p-3";
        img.alt = jugador.strPlayer;
        card.appendChild(img);

        const body = document.createElement("div");
        body.className = "card-body";

        const nombre = document.createElement("h6");
        nombre.className = "card-title";
        nombre.textContent = jugador.strPlayer;
        body.appendChild(nombre);

        card.appendChild(body);
        col.appendChild(card);
        contenedorJugadores.appendChild(col);
      });
    })
    .catch((error) => {
      console.error("Error cargando jugadores:", error);
      contenedorJugadores.innerHTML =
        "<div class='col-12'>Error al cargar jugadores.</div>";
    });
}
