let divCards = document.querySelector("#pokemonCards");
console.log(divCards);

// Cargar los Pokémon del 21 al 120
fetch("https://pokeapi.co/api/v2/pokemon/?limit=151")
  .then((response) => {
    return response.json();
  })
  .then((data) => {
    console.log("Contesta OK");

    data.results.forEach((pokemon, index) => {
      const pokemonId = 1 + index; // Ajustar ID al offset

      divCards.innerHTML += `
        <div class="col">
          <div class="card h-100">
            <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonId}.png" class="card-img-top" alt="${pokemon.name}" />
            <div class="card-body">
              <h5 class="card-title">${pokemon.name.toUpperCase()}</h5>
              <a class="btn btn-primary" href="./details.html" onclick="verDetalle('${pokemon.url}')">Ver detalles</a>
            </div>
          </div>
        </div>`;
    });
  })
  .catch((error) => {
    console.error("Contesta ERROR");
    console.log(error);
  });

// Necesario que esté en el global (window) para que funcione el onclick inline
window.verDetalle = function (url) {
  localStorage.setItem("valor", url);
};