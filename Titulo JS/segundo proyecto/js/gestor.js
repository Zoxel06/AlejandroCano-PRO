/*

CREAR UN PROGRAMA QUE PERMITA:
- CREAR 3 EQUIPOS, LOS CUALES TIENEN UN NOMBRE, UN PRESUPUESTO Y UNA LISTA DE JUGADORES
- CREAR UNA LISTA DE 10 JUGADORES: CADA JUGADOR TIENE NOMBRE, VALOR, POSICION
- FICHA 5 JUGADORES POR CADA EQUIPO (SI EL EQUIPO NO TIENE SUFICIENTE PRESUPUESTO NO PODRA Y TIENE QUE SALTAR UN AVISO)
- MOSTRAR TODOS LOS JUGADORES DE CADA EQUIPO
- MOSTRAR DE TODOS LOS JUGADORES INICIALES, AQUELLOS QUE TENGAN UN VALOR SUPERIOR A 90

CLASES: JUGADOR, EQUIPO, GESTORLIGA

 */

const jugadores = [
    new Jugador("Mark Evans", 97, "Portero", "Japón"),
    new Jugador("Axel Blaze", 98, "Delantero", "Japón"),
    new Jugador("Jude Sharp", 96, "Centrocampista", "Japón"),
    new Jugador("Nathan Swift", 88, "Defensa", "Japón"),
    new Jugador("Jack Wallside", 84, "Defensa", "Japón"),
    new Jugador("Shawn Froste", 94, "Delantero", "Japón"),
    new Jugador("Kevin Dragonfly", 88, "Delantero", "Japón"),
    new Jugador("Hurley Kane", 86, "Centrocampista", "Japón"),
    new Jugador("Bobby Shearer", 81, "Centrocampista", "EE.UU."),
    new Jugador("Erik Eagle", 90, "Centrocampista", "EE.UU."),
    new Jugador("David Samford", 83, "Centrocampista", "Japón"),
    new Jugador("Tod Ironside", 79, "Portero", "Japón"),
    new Jugador("Darren LaChance", 88, "Portero", "Japón"),
    new Jugador("Xavier Foster", 94, "Delantero", "Japón"),
    new Jugador("Gazelle", 90, "Delantero", "Desconocida"),
    new Jugador("Torch", 91, "Delantero", "Desconocida"),
    new Jugador("Byron Love", 92, "Centrocampista", "Inglaterra"),
    new Jugador("Claude Beacons", 86, "Defensa", "Japón"),
    new Jugador("Caleb Stonewall", 89, "Centrocampista", "Japón"),
    new Jugador("Janus", 85, "Delantero", "Desconocida"),
    new Jugador("Zoolan", 84, "Defensa", "Desconocida"),
    new Jugador("Dvalin", 90, "Delantero", "Islandia"),
    new Jugador("Helios", 88, "Centrocampista", "Grecia"),
    new Jugador("Nero", 87, "Defensa", "Italia"),
    new Jugador("Apollo", 89, "Centrocampista", "Grecia"),
    new Jugador("Desarm", 92, "Delantero", "Desconocida"),
    new Jugador("Gran", 91, "Centrocampista", "Desconocida"),
    new Jugador("Beta", 90, "Delantero", "Desconocida"),
    new Jugador("Gamma", 88, "Defensa", "Desconocida"),
    new Jugador("Bellatrix", 86, "Centrocampista", "Desconocida")
  ];

  let equipo = new Equipo("Inazuma Japón", 200)
  equipo.ficharJugador(jugadores[0])
  equipo.ficharJugador(jugadores[1])
  

equipo.mostrarDatos()
equipo.mostrarJugadores()


// Los jugadores que valen mas de 90
jugadores
  .filter((item) => item.valor >= 90)
  .forEach((item => item.mostrarDatos()))