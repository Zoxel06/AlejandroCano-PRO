import heapq
import math

# ======================================================
# REPRESENTACIÓN DEL GRAFO (SIN POO)
# ======================================================

def crear_grafo():
    """
    Crea un grafo dirigido y ponderado.
    Representación:
    {
        nodo: { vecino: coste }
    }
    """
    return {}

def agregar_nodo(grafo, nodo):
    if nodo not in grafo:
        grafo[nodo] = {}

def agregar_arista(grafo, origen, destino, coste):
    """
    Añade una arista dirigida origen -> destino con coste.
    """
    if coste < 0:
        raise ValueError("El coste no puede ser negativo")

    agregar_nodo(grafo, origen)
    agregar_nodo(grafo, destino)

    grafo[origen][destino] = coste

def vecinos(grafo, nodo):
    """
    Devuelve los vecinos de un nodo y sus costes.
    """
    return grafo.get(nodo, {})

def coste(grafo, origen, destino):
    """
    Devuelve el coste de ir de origen a destino.
    """
    return grafo[origen][destino]

def nodos(grafo):
    """
    Devuelve la lista de nodos del grafo.
    """
    return list(grafo.keys())


# ======================================================
# ALGORITMO DE DIJKSTRA
# ======================================================

def dijkstra(grafo, origen, destino):
    """
    Calcula la ruta más corta entre origen y destino usando Dijkstra.

    Devuelve:
    - coste total
    - ruta (lista de nodos)
    - (None, None) si no existe camino
    """

    distancias = {nodo: math.inf for nodo in nodos(grafo)}
    predecesores = {}

    distancias[origen] = 0
    cola_prioridad = [(0, origen)]

    while cola_prioridad:
        distancia_actual, nodo_actual = heapq.heappop(cola_prioridad)

        if nodo_actual == destino:
            break

        if distancia_actual > distancias[nodo_actual]:
            continue

        for vecino_nodo, c in vecinos(grafo, nodo_actual).items():
            nueva_distancia = distancia_actual + c

            if nueva_distancia < distancias[vecino_nodo]:
                distancias[vecino_nodo] = nueva_distancia
                predecesores[vecino_nodo] = nodo_actual
                heapq.heappush(cola_prioridad, (nueva_distancia, vecino_nodo))

    if distancias[destino] == math.inf:
        return None, None

    ruta = reconstruir_camino(predecesores, origen, destino)
    return distancias[destino], ruta


def reconstruir_camino(predecesores, origen, destino):
    """
    Reconstruye el camino óptimo a partir del diccionario de predecesores.
    """
    camino = [destino]

    while destino != origen:
        destino = predecesores[destino]
        camino.append(destino)

    camino.reverse()
    return camino


def dijkstra_con_intermedios(grafo, origen, destino, intermedios):
    """
    Calcula la ruta más corta permitiendo destinos intermedios.

    Devuelve:
    - coste total
    - ruta completa
    - (None, None) si algún tramo no tiene solución
    """

    puntos = [origen] + intermedios + [destino]
    ruta_total = []
    coste_total = 0

    for i in range(len(puntos) - 1):
        coste_tramo, ruta_tramo = dijkstra(grafo, puntos[i], puntos[i + 1])

        if ruta_tramo is None:
            return None, None

        if i > 0:
            ruta_tramo = ruta_tramo[1:]  # evitar repetir nodos

        ruta_total.extend(ruta_tramo)
        coste_total += coste_tramo

    return coste_total, ruta_total
