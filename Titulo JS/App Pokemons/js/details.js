const url = localStorage.getItem("valor");

fetch(url)
  .then((res) => res.json())
  .then((data) => {
    const detailDiv = document.getElementById("pokemonDetail");

    const nombre = data.name.toUpperCase();
    const imagen = data.sprites.front_default;
    const imagenBack = data.sprites.back_default;
    const imagenShiny = data.sprites.front_shiny;
    const altura = data.height / 10;
    const peso = data.weight / 10;
    const tipos = data.types.map((t) => t.type.name).join(", ");
    const habilidades = data.abilities.map((a) => a.ability.name).join(", ");
    const ataques = data.moves.slice(0, 4).map((m) => m.move.name);
    const stats = {};
    data.stats.forEach((s) => {
      stats[s.stat.name] = s.base_stat;
    });

    // Nuevos detalles
    const id = data.id;
    const experiencia = data.base_experience;

    detailDiv.innerHTML = `
      <div class="col-md-6">
        <div class="card">
          <img src="${imagen}" class="card-img-top" alt="${nombre}" />
          <div class="card-body">
            <h3 class="card-title">#${id} - ${nombre}</h3>
            <p><strong>Altura:</strong> ${altura} m</p>
            <p><strong>Peso:</strong> ${peso} kg</p>
            <p><strong>Experiencia base:</strong> ${experiencia}</p>
            <p><strong>Tipo:</strong> ${tipos}</p>
            <p><strong>Habilidades:</strong> ${habilidades}</p>
            <p><strong>Estadísticas base:</strong></p>
            <ul>
              <li>HP: ${stats.hp}</li>
              <li>Ataque: ${stats.attack}</li>
              <li>Defensa: ${stats.defense}</li>
              <li>Velocidad: ${stats.speed}</li>
              <li>Ataque especial: ${stats["special-attack"]}</li>
              <li>Defensa especial: ${stats["special-defense"]}</li>
            </ul>
            <p><strong>Ataques:</strong></p>
            <ul>
              ${ataques.map((ataque) => `<li>${ataque}</li>`).join("")}
            </ul>
            <p><strong>Version shiny:</strong></p>
            <div class="d-flex gap-3">
              <img src="${imagenShiny}" alt="Shiny" height="80" />
            </div>
            <a href="index.html" class="btn btn-secondary mt-3">← Volver</a>
          </div>
        </div>
      </div>
    `;
  })
  .catch((error) => {
    console.error("Error al obtener detalles:", error);
    document.getElementById("pokemonDetail").innerHTML = `
      <div class="alert alert-danger" role="alert">
        No se pudo cargar la información del Pokémon.
      </div>
    `;
  });