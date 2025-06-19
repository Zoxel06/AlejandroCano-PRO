// https://pokeapi.co/api/v2/pokemon/?offset=0&limit=100

// fetch(url)
    // then -> contestas ok
    // catch -> contestas error



    let divPokemons = document.querySelector("#divPokemons")

fetch("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=100")
.then((response)=> {
    console.log("Contesta OK")
    return response.json()
})
.then((data)=> {
    console.log(data.results)
    data.results.forEach(item => {
        let nodoDiv = document.createElement("div")
        nodoDiv.className = "col m-3"
        nodoDiv.innerHTML = `<div class="card p-2">
        <div class="card-body">
          ${item.name}
          <div style="text-align:center" class="list-group">
  <button type="button" style="text-align:center" class="list-group-item list-group-item-action active" aria-current="true">
    Info
  </button>
  </div>
        </div>
      </div>`
        console.log(item.name)
        divPokemons.append(nodoDiv)
    });

})
.catch((error)=>{
    console.log("Contesta ERROR")
})



