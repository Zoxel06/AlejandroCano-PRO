"""
================================================================================
SISTEMA GPS - SIMULADOR DE RUTAS ÓPTIMAS
================================================================================
Proyecto final de asignatura que implementa un sistema GPS utilizando
grafos ponderados y el algoritmo de Dijkstra para calcular rutas óptimas.

Estructura del código:
1. CONFIGURACIÓN Y CONSTANTES
2. CAPA DAO (Data Access Object) - Conexión y consultas SQL
3. REPRESENTACIÓN DEL GRAFO - Estructura de datos en memoria
4. ALGORITMO DE DIJKSTRA - Cálculo de rutas (SIN SQL)
5. INTERFAZ DE USUARIO - Interacción con el usuario
6. FUNCIÓN PRINCIPAL

Requisitos cumplidos:
- 40 nodos mínimo
- 80 aristas dirigidas mínimo
- 40 aristas estrictamente unidireccionales mínimo
- Base de datos SQLite con patrón DAO
- Histórico de rutas
- Ruta alternativa (diferencia <= 15%)
- Destinos intermedios
================================================================================
"""

import sqlite3
import csv
import heapq
import math
from datetime import datetime
import tkinter as tk
from tkinter import ttk, messagebox

# ================================================================================
# 1. CONFIGURACIÓN Y CONSTANTES
# ================================================================================

DB_PATH = "gps.db"
CSV_PATH = "mapa.csv"
PORCENTAJE_ALTERNATIVA = 15  # Porcentaje máximo de diferencia para ruta alternativa

# Límites geográficos de España
SPAIN_BOUNDS = {
    "lat_min": 35.8,
    "lat_max": 43.8,
    "lon_min": -9.5,
    "lon_max": 4.5
}

# Dimensiones del canvas
CANVAS_WIDTH = 800
CANVAS_HEIGHT = 600


# ================================================================================
# 2. CAPA DAO (Data Access Object)
# ================================================================================
# Esta capa gestiona TODA la interacción con la base de datos.
# El algoritmo de Dijkstra NO contiene SQL ni gestiona conexiones.
# ================================================================================

# ------------------------------------------------------------------------------
# 2.1 Conexión a la base de datos
# ------------------------------------------------------------------------------

def conectar_bd():
    """
    Crea y devuelve una conexión a la base de datos SQLite.
    """
    return sqlite3.connect(DB_PATH)


def cerrar_bd(conn):
    """
    Cierra la conexión a la base de datos.
    """
    conn.close()


# ------------------------------------------------------------------------------
# 2.2 Creación del esquema de la base de datos
# ------------------------------------------------------------------------------

def crear_tablas(conn):
    """
    Crea todas las tablas necesarias si no existen:
    - nodos: ciudades o intersecciones
    - aristas: carreteras dirigidas
    - pesos: coste asociado a cada arista (distancia/tiempo)
    - historico: registro de rutas calculadas
    """
    cursor = conn.cursor()

    # Tabla de nodos (ciudades / intersecciones)
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS nodos (
        id INTEGER PRIMARY KEY,
        nombre TEXT
    )
    """)

    # Tabla de aristas (carreteras dirigidas)
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS aristas (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        origen INTEGER NOT NULL,
        destino INTEGER NOT NULL,
        FOREIGN KEY (origen) REFERENCES nodos(id),
        FOREIGN KEY (destino) REFERENCES nodos(id)
    )
    """)

    # Tabla de pesos (coste asociado a cada arista)
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS pesos (
        arista_id INTEGER PRIMARY KEY,
        coste REAL NOT NULL,
        FOREIGN KEY (arista_id) REFERENCES aristas(id)
    )
    """)

    # Tabla de histórico de rutas calculadas
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS historico (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        origen INTEGER NOT NULL,
        destino INTEGER NOT NULL,
        intermedios TEXT,
        fecha_hora TEXT NOT NULL,
        coste_total REAL NOT NULL,
        ruta TEXT NOT NULL,
        uso_alternativa INTEGER NOT NULL,
        FOREIGN KEY (origen) REFERENCES nodos(id),
        FOREIGN KEY (destino) REFERENCES nodos(id)
    )
    """)

    # Tabla para carreteras cortadas/bloqueadas
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS carreteras_cortadas (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        arista_id INTEGER NOT NULL,
        motivo TEXT,
        fecha_inicio TEXT NOT NULL,
        fecha_fin TEXT,
        FOREIGN KEY (arista_id) REFERENCES aristas(id)
    )
    """)

    conn.commit()


def verificar_esquema_bd(conn):
    """
    Verifica si el esquema de la BD es correcto. Si no, elimina las tablas y las recrea.
    """
    cursor = conn.cursor()
    try:
        # Verificar si la columna 'nombre' existe en la tabla nodos
        cursor.execute("SELECT nombre FROM nodos LIMIT 1")
    except:
        # La columna no existe, eliminar tablas y recrear
        cursor.execute("DROP TABLE IF EXISTS carreteras_cortadas")
        cursor.execute("DROP TABLE IF EXISTS historico")
        cursor.execute("DROP TABLE IF EXISTS pesos")
        cursor.execute("DROP TABLE IF EXISTS aristas")
        cursor.execute("DROP TABLE IF EXISTS nodos")
        conn.commit()
        crear_tablas(conn)


# ------------------------------------------------------------------------------
# 2.3 Carga inicial del CSV a la base de datos
# ------------------------------------------------------------------------------

def base_esta_vacia(conn):
    """
    Comprueba si la base de datos aún no tiene nodos cargados.
    """
    cursor = conn.cursor()
    cursor.execute("SELECT COUNT(*) FROM nodos")
    return cursor.fetchone()[0] == 0


def cargar_csv_en_bd(conn):
    """
    Carga los datos del mapa desde el CSV a SQLite.
    El CSV NO se utiliza después para calcular rutas.
    
    Formato CSV: origen,destino,coste
    """
    cursor = conn.cursor()
    
    # Diccionario para nombres de nodos (simulación de ciudades)
    nombres_ciudades = {
        1: "Madrid", 2: "Barcelona", 3: "Valencia", 4: "Sevilla",
        5: "Zaragoza", 6: "Málaga", 7: "Murcia", 8: "Palma",
        9: "Las Palmas", 10: "Bilbao", 11: "Alicante", 12: "Córdoba",
        13: "Valladolid", 14: "Vigo", 15: "Gijón", 16: "Hospitalet",
        17: "Vitoria", 18: "A Coruña", 19: "Granada", 20: "Elche",
        21: "Oviedo", 22: "Terrassa", 23: "Badalona", 24: "Cartagena",
        25: "Jerez", 26: "Sabadell", 27: "Móstoles", 28: "Santa Cruz",
        29: "Alcalá", 30: "Pamplona", 31: "Fuenlabrada", 32: "Almería",
        33: "Leganés", 34: "San Sebastián", 35: "Burgos", 36: "Santander",
        37: "Castellón", 38: "Getafe", 39: "Albacete", 40: "Alcorcón"
    }

    try:
        with open(CSV_PATH, newline='', encoding='utf-8') as fichero:
            lector = csv.reader(fichero)
            for fila in lector:
                if len(fila) < 3:
                    continue
                    
                origen = int(fila[0])
                destino = int(fila[1])
                coste = float(fila[2])

                # Insertar nodos si no existen
                nombre_origen = nombres_ciudades.get(origen, f"Nodo_{origen}")
                nombre_destino = nombres_ciudades.get(destino, f"Nodo_{destino}")
                
                cursor.execute(
                    "INSERT OR IGNORE INTO nodos (id, nombre) VALUES (?, ?)",
                    (origen, nombre_origen)
                )
                cursor.execute(
                    "INSERT OR IGNORE INTO nodos (id, nombre) VALUES (?, ?)",
                    (destino, nombre_destino)
                )

                # Insertar arista
                cursor.execute(
                    "INSERT INTO aristas (origen, destino) VALUES (?, ?)",
                    (origen, destino)
                )
                arista_id = cursor.lastrowid

                # Insertar coste
                cursor.execute(
                    "INSERT INTO pesos (arista_id, coste) VALUES (?, ?)",
                    (arista_id, coste)
                )

        conn.commit()
        return True
    except FileNotFoundError:
        print(f"Error: No se encontró el archivo {CSV_PATH}")
        return False
    except Exception as e:
        print(f"Error al cargar el CSV: {e}")
        return False


# ------------------------------------------------------------------------------
# 2.4 Funciones DAO para consultas del grafo
# ------------------------------------------------------------------------------

def obtener_nodos(conn):
    """
    Devuelve una lista de todos los nodos del mapa.
    Formato: [(id, nombre), ...]
    """
    cursor = conn.cursor()
    cursor.execute("SELECT id, nombre FROM nodos ORDER BY id")
    return cursor.fetchall()


def obtener_solo_ids_nodos(conn):
    """
    Devuelve solo los IDs de los nodos.
    """
    cursor = conn.cursor()
    cursor.execute("SELECT id FROM nodos ORDER BY id")
    return [fila[0] for fila in cursor.fetchall()]


def obtener_aristas_con_coste(conn):
    """
    Devuelve todas las aristas con su coste asociado.
    Formato: [(origen, destino, coste), ...]
    """
    cursor = conn.cursor()
    cursor.execute("""
    SELECT a.origen, a.destino, p.coste
    FROM aristas a
    JOIN pesos p ON a.id = p.arista_id
    """)
    return cursor.fetchall()


def obtener_aristas_sin_bloqueos(conn):
    """
    Devuelve las aristas que NO están cortadas actualmente.
    Formato: [(origen, destino, coste), ...]
    """
    cursor = conn.cursor()
    fecha_actual = datetime.now().isoformat()
    
    cursor.execute("""
    SELECT a.origen, a.destino, p.coste
    FROM aristas a
    JOIN pesos p ON a.id = p.arista_id
    WHERE a.id NOT IN (
        SELECT arista_id FROM carreteras_cortadas
        WHERE fecha_fin IS NULL OR fecha_fin > ?
    )
    """, (fecha_actual,))
    return cursor.fetchall()


def existe_nodo(conn, nodo_id):
    """
    Verifica si un nodo existe en la base de datos.
    """
    cursor = conn.cursor()
    cursor.execute("SELECT COUNT(*) FROM nodos WHERE id = ?", (nodo_id,))
    return cursor.fetchone()[0] > 0


def obtener_nombre_nodo(conn, nodo_id):
    """
    Obtiene el nombre de un nodo dado su ID.
    """
    cursor = conn.cursor()
    cursor.execute("SELECT nombre FROM nodos WHERE id = ?", (nodo_id,))
    resultado = cursor.fetchone()
    return resultado[0] if resultado else f"Nodo_{nodo_id}"


def contar_nodos(conn):
    """
    Cuenta el número total de nodos.
    """
    cursor = conn.cursor()
    cursor.execute("SELECT COUNT(*) FROM nodos")
    return cursor.fetchone()[0]


def contar_aristas(conn):
    """
    Cuenta el número total de aristas.
    """
    cursor = conn.cursor()
    cursor.execute("SELECT COUNT(*) FROM aristas")
    return cursor.fetchone()[0]


def contar_aristas_unidireccionales(conn):
    """
    Cuenta las aristas que son estrictamente unidireccionales
    (no tienen arista de retorno).
    """
    cursor = conn.cursor()
    cursor.execute("""
    SELECT COUNT(*) FROM aristas a1
    WHERE NOT EXISTS (
        SELECT 1 FROM aristas a2
        WHERE a2.origen = a1.destino AND a2.destino = a1.origen
    )
    """)
    return cursor.fetchone()[0]


# ------------------------------------------------------------------------------
# 2.5 Funciones DAO para el histórico de rutas
# ------------------------------------------------------------------------------

def guardar_ruta_historica(conn, origen, destino, intermedios, coste_total, ruta, uso_alternativa):
    """
    Guarda una ruta calculada en el histórico.
    """
    cursor = conn.cursor()
    
    # Convertir lista de intermedios a string
    intermedios_str = ",".join(map(str, intermedios)) if intermedios else ""
    
    # Convertir ruta a string
    ruta_str = ",".join(map(str, ruta))
    
    cursor.execute("""
    INSERT INTO historico (
        origen, destino, intermedios, fecha_hora,
        coste_total, ruta, uso_alternativa
    )
    VALUES (?, ?, ?, ?, ?, ?, ?)
    """, (
        origen,
        destino,
        intermedios_str,
        datetime.now().isoformat(),
        coste_total,
        ruta_str,
        int(uso_alternativa)
    ))
    conn.commit()


def consultar_ruta_historica(conn, origen, destino, intermedios=None):
    """
    Busca si ya existe una ruta previa entre origen y destino
    (con los mismos intermedios si se especifican).
    Devuelve: (coste_total, ruta_lista, uso_alternativa) o None
    """
    cursor = conn.cursor()
    
    intermedios_str = ",".join(map(str, intermedios)) if intermedios else ""
    
    cursor.execute("""
    SELECT coste_total, ruta, uso_alternativa
    FROM historico
    WHERE origen = ? AND destino = ? AND intermedios = ?
    ORDER BY fecha_hora DESC
    LIMIT 1
    """, (origen, destino, intermedios_str))
    
    resultado = cursor.fetchone()
    
    if resultado:
        coste, ruta_str, uso_alt = resultado
        ruta_lista = [int(x) for x in ruta_str.split(",")]
        return coste, ruta_lista, bool(uso_alt)
    
    return None


def obtener_historico_completo(conn):
    """
    Obtiene todo el histórico de rutas.
    """
    cursor = conn.cursor()
    cursor.execute("""
    SELECT id, origen, destino, intermedios, fecha_hora, 
           coste_total, ruta, uso_alternativa
    FROM historico
    ORDER BY fecha_hora DESC
    """)
    return cursor.fetchall()


# ------------------------------------------------------------------------------
# 2.6 Funciones DAO para carreteras cortadas
# ------------------------------------------------------------------------------

def bloquear_carretera(conn, origen, destino, motivo=""):
    """
    Marca una carretera como cortada/bloqueada.
    """
    cursor = conn.cursor()
    
    # Buscar el ID de la arista
    cursor.execute("""
    SELECT id FROM aristas WHERE origen = ? AND destino = ?
    """, (origen, destino))
    
    resultado = cursor.fetchone()
    if resultado:
        arista_id = resultado[0]
        cursor.execute("""
        INSERT INTO carreteras_cortadas (arista_id, motivo, fecha_inicio)
        VALUES (?, ?, ?)
        """, (arista_id, motivo, datetime.now().isoformat()))
        conn.commit()
        return True
    return False


def desbloquear_carretera(conn, origen, destino):
    """
    Elimina el bloqueo de una carretera.
    """
    cursor = conn.cursor()
    
    cursor.execute("""
    SELECT id FROM aristas WHERE origen = ? AND destino = ?
    """, (origen, destino))
    
    resultado = cursor.fetchone()
    if resultado:
        arista_id = resultado[0]
        cursor.execute("""
        UPDATE carreteras_cortadas 
        SET fecha_fin = ?
        WHERE arista_id = ? AND fecha_fin IS NULL
        """, (datetime.now().isoformat(), arista_id))
        conn.commit()
        return True
    return False


def obtener_carreteras_cortadas(conn):
    """
    Obtiene la lista de carreteras actualmente cortadas.
    """
    cursor = conn.cursor()
    cursor.execute("""
    SELECT a.origen, a.destino, c.motivo, c.fecha_inicio
    FROM carreteras_cortadas c
    JOIN aristas a ON c.arista_id = a.id
    WHERE c.fecha_fin IS NULL
    """)
    return cursor.fetchall()


# ================================================================================
# 3. REPRESENTACIÓN DEL GRAFO (EN MEMORIA)
# ================================================================================
# Esta sección construye el grafo en memoria usando diccionarios.
# Representa listas de adyacencia: {nodo: {vecino: coste, ...}, ...}
# NO contiene SQL ni acceso a base de datos.
# ================================================================================

def crear_grafo():
    """
    Crea un grafo dirigido y ponderado vacío.
    Representación: diccionario de listas de adyacencia.
    """
    return {}


def agregar_nodo(grafo, nodo):
    """
    Añade un nodo al grafo si no existe.
    """
    if nodo not in grafo:
        grafo[nodo] = {}


def agregar_arista(grafo, origen, destino, coste):
    """
    Añade una arista dirigida origen -> destino con coste.
    El coste no puede ser negativo.
    """
    if coste < 0:
        raise ValueError("El coste no puede ser negativo")

    agregar_nodo(grafo, origen)
    agregar_nodo(grafo, destino)

    grafo[origen][destino] = coste


def obtener_vecinos(grafo, nodo):
    """
    Devuelve los vecinos de un nodo y sus costes.
    Formato: {vecino: coste, ...}
    """
    return grafo.get(nodo, {})


def obtener_coste_arista(grafo, origen, destino):
    """
    Devuelve el coste de ir de origen a destino.
    Devuelve infinito si no existe la arista.
    """
    if origen in grafo and destino in grafo[origen]:
        return grafo[origen][destino]
    return math.inf


def obtener_todos_nodos(grafo):
    """
    Devuelve la lista de todos los nodos del grafo.
    """
    return list(grafo.keys())


def construir_grafo_desde_aristas(aristas):
    """
    Construye el grafo en memoria a partir de una lista de aristas.
    Formato de aristas: [(origen, destino, coste), ...]
    
    Esta función es el puente entre el DAO y el grafo en memoria.
    """
    grafo = crear_grafo()
    
    for origen, destino, coste in aristas:
        agregar_arista(grafo, origen, destino, coste)
    
    return grafo


# ================================================================================
# 4. ALGORITMO DE DIJKSTRA
# ================================================================================
# Esta sección implementa el algoritmo de Dijkstra.
# NO contiene SQL, NO gestiona conexiones a base de datos.
# Solo recibe un grafo, origen y destino, y devuelve coste y ruta.
# ================================================================================

def dijkstra(grafo, origen, destino):
    """
    Calcula la ruta más corta entre origen y destino usando Dijkstra.
    
    Parámetros:
        - grafo: estructura de grafo (diccionario de adyacencias)
        - origen: nodo de partida
        - destino: nodo de llegada
    
    Devuelve:
        - (coste_total, ruta): si existe camino
        - (None, None): si no existe camino
    """
    # Verificar que los nodos existen en el grafo
    if origen not in grafo:
        return None, None
    if destino not in grafo:
        return None, None
    
    # Caso especial: origen = destino
    if origen == destino:
        return 0, [origen]
    
    # Inicialización
    nodos_grafo = obtener_todos_nodos(grafo)
    distancias = {nodo: math.inf for nodo in nodos_grafo}
    predecesores = {}
    visitados = set()
    
    distancias[origen] = 0
    
    # Cola de prioridad: (distancia, nodo)
    cola_prioridad = [(0, origen)]
    
    while cola_prioridad:
        distancia_actual, nodo_actual = heapq.heappop(cola_prioridad)
        
        # Si llegamos al destino, terminamos
        if nodo_actual == destino:
            break
        
        # Ignorar si ya fue procesado con menor distancia
        if nodo_actual in visitados:
            continue
        
        visitados.add(nodo_actual)
        
        # Relajar aristas hacia los vecinos
        for vecino, coste in obtener_vecinos(grafo, nodo_actual).items():
            if vecino in visitados:
                continue
                
            nueva_distancia = distancia_actual + coste
            
            if nueva_distancia < distancias[vecino]:
                distancias[vecino] = nueva_distancia
                predecesores[vecino] = nodo_actual
                heapq.heappush(cola_prioridad, (nueva_distancia, vecino))
    
    # Verificar si existe camino
    if distancias[destino] == math.inf:
        return None, None
    
    # Reconstruir el camino
    ruta = reconstruir_camino(predecesores, origen, destino)
    
    return distancias[destino], ruta


def reconstruir_camino(predecesores, origen, destino):
    """
    Reconstruye el camino óptimo a partir del diccionario de predecesores.
    
    Parámetros:
        - predecesores: diccionario {nodo: nodo_anterior}
        - origen: nodo de partida
        - destino: nodo de llegada
    
    Devuelve:
        - Lista de nodos desde origen hasta destino
    """
    camino = [destino]
    actual = destino
    
    while actual != origen:
        if actual not in predecesores:
            return None  # No hay camino
        actual = predecesores[actual]
        camino.append(actual)
    
    camino.reverse()
    return camino


def dijkstra_con_intermedios(grafo, origen, destino, intermedios):
    """
    Calcula la ruta más corta pasando por destinos intermedios.
    
    Parámetros:
        - grafo: estructura de grafo
        - origen: nodo de partida
        - destino: nodo final
        - intermedios: lista de nodos intermedios (en orden)
    
    Devuelve:
        - (coste_total, ruta_completa): si existe camino por todos los tramos
        - (None, None): si algún tramo no tiene solución
    """
    if not intermedios:
        return dijkstra(grafo, origen, destino)
    
    # Construir secuencia de puntos: origen -> intermedios -> destino
    puntos = [origen] + intermedios + [destino]
    ruta_total = []
    coste_total = 0
    
    for i in range(len(puntos) - 1):
        inicio = puntos[i]
        fin = puntos[i + 1]
        
        coste_tramo, ruta_tramo = dijkstra(grafo, inicio, fin)
        
        if ruta_tramo is None:
            return None, None
        
        # Evitar repetir nodos en la unión de tramos
        if i > 0:
            ruta_tramo = ruta_tramo[1:]
        
        ruta_total.extend(ruta_tramo)
        coste_total += coste_tramo
    
    return coste_total, ruta_total


def calcular_ruta_alternativa(grafo, origen, destino, ruta_optima, coste_optimo, porcentaje=None):
    """
    Busca una ruta alternativa que no use la primera arista de la ruta óptima.
    La alternativa debe tener un coste no superior al porcentaje indicado.
    
    Parámetros:
        - grafo: estructura de grafo
        - origen: nodo de partida
        - destino: nodo de llegada
        - ruta_optima: ruta óptima ya calculada
        - coste_optimo: coste de la ruta óptima
        - porcentaje: margen máximo (usa PORCENTAJE_ALTERNATIVA si no se indica)
    
    Devuelve:
        - (coste_alternativo, ruta_alternativa): si existe alternativa válida
        - (None, None): si no existe alternativa dentro del margen
    """
    if porcentaje is None:
        porcentaje = PORCENTAJE_ALTERNATIVA
        
    if len(ruta_optima) < 2:
        return None, None
    
    # Crear copia del grafo sin la primera arista de la ruta óptima
    grafo_modificado = {}
    primer_nodo = ruta_optima[0]
    segundo_nodo = ruta_optima[1]
    
    for nodo, vecinos in grafo.items():
        grafo_modificado[nodo] = {}
        for vecino, coste in vecinos.items():
            # Excluir la primera arista de la ruta óptima
            if nodo == primer_nodo and vecino == segundo_nodo:
                continue
            grafo_modificado[nodo][vecino] = coste
    
    # Calcular ruta alternativa
    coste_alt, ruta_alt = dijkstra(grafo_modificado, origen, destino)
    
    if ruta_alt is None:
        return None, None
    
    # Verificar que está dentro del margen
    limite_superior = coste_optimo * (1 + porcentaje / 100)
    
    if coste_alt <= limite_superior:
        return coste_alt, ruta_alt
    
    return None, None


def calcular_todas_alternativas(grafo, origen, destino, ruta_optima, coste_optimo, max_alternativas=3):
    """
    Busca múltiples rutas alternativas eliminando diferentes aristas.
    
    Devuelve una lista de (coste, ruta) ordenada por coste.
    """
    alternativas = []
    limite_superior = coste_optimo * (1 + PORCENTAJE_ALTERNATIVA / 100)
    
    # Intentar eliminar cada arista de la ruta óptima
    for i in range(len(ruta_optima) - 1):
        nodo_a = ruta_optima[i]
        nodo_b = ruta_optima[i + 1]
        
        # Crear grafo sin esta arista
        grafo_mod = {}
        for nodo, vecinos in grafo.items():
            grafo_mod[nodo] = {}
            for vecino, coste in vecinos.items():
                if nodo == nodo_a and vecino == nodo_b:
                    continue
                grafo_mod[nodo][vecino] = coste
        
        coste_alt, ruta_alt = dijkstra(grafo_mod, origen, destino)
        
        if ruta_alt is not None and coste_alt <= limite_superior:
            # Evitar duplicados
            if ruta_alt not in [alt[1] for alt in alternativas]:
                alternativas.append((coste_alt, ruta_alt))
    
    # Ordenar por coste y limitar número
    alternativas.sort(key=lambda x: x[0])
    return alternativas[:max_alternativas]


# ================================================================================
# 5. INTERFAZ GRÁFICA (GUI) CON TKINTER
# ================================================================================
# Esta sección gestiona toda la interacción con el usuario mediante GUI.
# NO implementa Dijkstra ni gestiona listas de adyacencia.
# Solo usa las otras partes, recoge datos y muestra resultados.
# ================================================================================

class AplicacionGPS:
    """
    Clase principal de la interfaz gráfica del Sistema GPS.
    """
    
    def __init__(self, root):
        self.root = root
        self.root.title("Sistema GPS - Calculador de Rutas Óptimas")
        self.root.geometry("1050x780")
        self.root.resizable(True, True)
        
        # Variables
        self.conn = None
        self.grafo = None
        self.porcentaje_alternativa = PORCENTAJE_ALTERNATIVA
        self.intermedios = []
        self.ruta_alternativa_actual = None
        self.coste_alternativa_actual = None
        self.origen_actual = None
        self.destino_actual = None
        
        # Inicializar sistema
        self.inicializar_sistema()
        
        # Crear interfaz
        self.crear_interfaz()
        
        # Cargar datos iniciales
        self.actualizar_lista_ciudades()
        self.actualizar_estadisticas()
    
    def inicializar_sistema(self):
        self.conn = conectar_bd()
        crear_tablas(self.conn)
        verificar_esquema_bd(self.conn)
        
        if base_esta_vacia(self.conn):
            if not cargar_csv_en_bd(self.conn):
                messagebox.showerror("Error", f"No se pudo cargar el archivo {CSV_PATH}")
        
        aristas = obtener_aristas_sin_bloqueos(self.conn)
        self.grafo = construir_grafo_desde_aristas(aristas)
    
    def crear_interfaz(self):
        # Frame principal con pestañas
        self.notebook = ttk.Notebook(self.root)
        self.notebook.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)
        
        # Pestañas
        self.tab_ruta = ttk.Frame(self.notebook)
        self.notebook.add(self.tab_ruta, text="Calcular Ruta")
        self.crear_tab_ruta()
        
        self.tab_ciudades = ttk.Frame(self.notebook)
        self.notebook.add(self.tab_ciudades, text="Ciudades")
        self.crear_tab_ciudades()
        
        self.tab_historico = ttk.Frame(self.notebook)
        self.notebook.add(self.tab_historico, text="Histórico")
        self.crear_tab_historico()
        
        self.tab_cortadas = ttk.Frame(self.notebook)
        self.notebook.add(self.tab_cortadas, text="Carreteras Cortadas")
        self.crear_tab_cortadas()
        
        self.tab_estadisticas = ttk.Frame(self.notebook)
        self.notebook.add(self.tab_estadisticas, text="Estadísticas")
        self.crear_tab_estadisticas()
        
        self.tab_config = ttk.Frame(self.notebook)
        self.notebook.add(self.tab_config, text="Configuración")
        self.crear_tab_configuracion()
        
        self.tab_mapa = ttk.Frame(self.notebook)
        self.notebook.add(self.tab_mapa, text="Mapa")
        self.crear_tab_mapa()
    
    def crear_tab_ruta(self):
        # Frame izquierdo para entrada de datos
        frame_izq = ttk.LabelFrame(self.tab_ruta, text="Datos de la Ruta", padding=10)
        frame_izq.pack(side=tk.LEFT, fill=tk.Y, padx=5, pady=5)
        
        ttk.Label(frame_izq, text="Ciudad de Origen:").pack(anchor=tk.W, pady=(0, 5))
        self.combo_origen = ttk.Combobox(frame_izq, width=30, state="readonly")
        self.combo_origen.pack(pady=(0, 10))
        
        ttk.Label(frame_izq, text="Ciudad de Destino:").pack(anchor=tk.W, pady=(0, 5))
        self.combo_destino = ttk.Combobox(frame_izq, width=30, state="readonly")
        self.combo_destino.pack(pady=(0, 10))
        
        # Frame para intermedios
        frame_intermedios = ttk.LabelFrame(frame_izq, text="Destinos Intermedios", padding=5)
        frame_intermedios.pack(fill=tk.X, pady=10)
        
        self.lista_intermedios = tk.Listbox(frame_intermedios, height=4, width=28)
        self.lista_intermedios.pack(pady=5)
        
        frame_btns_inter = ttk.Frame(frame_intermedios)
        frame_btns_inter.pack(fill=tk.X)
        
        ttk.Button(frame_btns_inter, text="Añadir", command=self.agregar_intermedio).pack(side=tk.LEFT, padx=2)
        ttk.Button(frame_btns_inter, text="Quitar", command=self.quitar_intermedio).pack(side=tk.LEFT, padx=2)
        ttk.Button(frame_btns_inter, text="Limpiar", command=self.limpiar_intermedios).pack(side=tk.LEFT, padx=2)
        
        ttk.Button(frame_izq, text="CALCULAR RUTA", command=self.calcular_ruta).pack(pady=20, fill=tk.X)
        
        # Frame derecho para resultados
        frame_der = ttk.LabelFrame(self.tab_ruta, text="Resultado", padding=10)
        frame_der.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True, padx=5, pady=5)
        
        self.texto_resultado = tk.Text(frame_der, wrap=tk.WORD, font=("Consolas", 10))
        self.texto_resultado.pack(fill=tk.BOTH, expand=True)
        
        scrollbar = ttk.Scrollbar(self.texto_resultado, orient=tk.VERTICAL, command=self.texto_resultado.yview)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.texto_resultado.config(yscrollcommand=scrollbar.set)
        
        self.frame_alternativa = ttk.Frame(frame_der)
        self.frame_alternativa.pack(fill=tk.X, pady=5)
        
        self.btn_usar_alternativa = ttk.Button(self.frame_alternativa, text="Usar Ruta Alternativa", command=self.usar_ruta_alternativa, width=35)
    
    def crear_tab_ciudades(self):
        columns = ("ID", "Nombre")
        self.tree_ciudades = ttk.Treeview(self.tab_ciudades, columns=columns, show="headings")
        self.tree_ciudades.heading("ID", text="ID")
        self.tree_ciudades.heading("Nombre", text="Nombre de Ciudad")
        self.tree_ciudades.column("ID", width=50, anchor=tk.CENTER)
        self.tree_ciudades.column("Nombre", width=200)
        
        scrollbar = ttk.Scrollbar(self.tab_ciudades, orient=tk.VERTICAL, command=self.tree_ciudades.yview)
        self.tree_ciudades.configure(yscrollcommand=scrollbar.set)
        self.tree_ciudades.pack(side=tk.LEFT, fill=tk.BOTH, expand=True, padx=5, pady=5)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y, pady=5)
    
    def crear_tab_historico(self):
        frame_sup = ttk.Frame(self.tab_historico)
        frame_sup.pack(fill=tk.X, padx=5, pady=5)
        ttk.Button(frame_sup, text="Actualizar Histórico", command=self.actualizar_historico).pack(side=tk.LEFT)
        
        columns = ("ID", "Fecha", "Origen", "Destino", "Coste", "Alternativa")
        self.tree_historico = ttk.Treeview(self.tab_historico, columns=columns, show="headings")
        for col in columns:
            self.tree_historico.heading(col, text=col)
        self.tree_historico.column("ID", width=40, anchor=tk.CENTER)
        self.tree_historico.column("Fecha", width=130)
        self.tree_historico.column("Origen", width=100)
        self.tree_historico.column("Destino", width=100)
        self.tree_historico.column("Coste", width=60, anchor=tk.CENTER)
        self.tree_historico.column("Alternativa", width=70, anchor=tk.CENTER)
        
        scrollbar = ttk.Scrollbar(self.tab_historico, orient=tk.VERTICAL, command=self.tree_historico.yview)
        self.tree_historico.configure(yscrollcommand=scrollbar.set)
        self.tree_historico.pack(side=tk.LEFT, fill=tk.BOTH, expand=True, padx=5, pady=5)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y, pady=5)
    
    def crear_tab_cortadas(self):
        frame_sup = ttk.Frame(self.tab_cortadas)
        frame_sup.pack(fill=tk.X, padx=5, pady=5)
        ttk.Button(frame_sup, text="Bloquear Carretera", command=self.bloquear_carretera_gui).pack(side=tk.LEFT, padx=5)
        ttk.Button(frame_sup, text="Desbloquear Carretera", command=self.desbloquear_carretera_gui).pack(side=tk.LEFT, padx=5)
        ttk.Button(frame_sup, text="Actualizar", command=self.actualizar_cortadas).pack(side=tk.LEFT, padx=5)
        
        columns = ("Origen", "Destino", "Motivo", "Fecha")
        self.tree_cortadas = ttk.Treeview(self.tab_cortadas, columns=columns, show="headings")
        for col in columns:
            self.tree_cortadas.heading(col, text=col)
        
        scrollbar = ttk.Scrollbar(self.tab_cortadas, orient=tk.VERTICAL, command=self.tree_cortadas.yview)
        self.tree_cortadas.configure(yscrollcommand=scrollbar.set)
        self.tree_cortadas.pack(side=tk.LEFT, fill=tk.BOTH, expand=True, padx=5, pady=5)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y, pady=5)
    
    def crear_tab_estadisticas(self):
        frame = ttk.Frame(self.tab_estadisticas, padding=20)
        frame.pack(fill=tk.BOTH, expand=True)
        
        ttk.Label(frame, text="ESTADÍSTICAS DEL MAPA", font=("Helvetica", 16, "bold")).pack(pady=20)
        
        self.label_nodos = ttk.Label(frame, text="Nodos (ciudades): -", font=("Helvetica", 12))
        self.label_nodos.pack(pady=5)
        self.label_aristas = ttk.Label(frame, text="Aristas (carreteras): -", font=("Helvetica", 12))
        self.label_aristas.pack(pady=5)
        self.label_unidireccionales = ttk.Label(frame, text="Aristas unidireccionales: -", font=("Helvetica", 12))
        self.label_unidireccionales.pack(pady=5)
        self.label_bidireccionales = ttk.Label(frame, text="Aristas bidireccionales: -", font=("Helvetica", 12))
        self.label_bidireccionales.pack(pady=5)
        
        ttk.Button(frame, text="Actualizar Estadísticas", command=self.actualizar_estadisticas).pack(pady=20)
    
    def crear_tab_configuracion(self):
        frame = ttk.Frame(self.tab_config, padding=20)
        frame.pack(fill=tk.BOTH, expand=True)
        
        ttk.Label(frame, text="CONFIGURACIÓN", font=("Helvetica", 16, "bold")).pack(pady=20)
        
        frame_porc = ttk.Frame(frame)
        frame_porc.pack(pady=10)
        ttk.Label(frame_porc, text="Porcentaje máximo para ruta alternativa:", font=("Helvetica", 11)).pack(side=tk.LEFT, padx=5)
        self.spin_porcentaje = ttk.Spinbox(frame_porc, from_=1, to=100, width=5)
        self.spin_porcentaje.set(self.porcentaje_alternativa)
        self.spin_porcentaje.pack(side=tk.LEFT, padx=5)
        ttk.Label(frame_porc, text="%", font=("Helvetica", 11)).pack(side=tk.LEFT)
        
        ttk.Button(frame, text="Guardar Configuración", command=self.guardar_configuracion).pack(pady=20)
        
        ttk.Separator(frame, orient=tk.HORIZONTAL).pack(fill=tk.X, pady=20)
        ttk.Label(frame, text="Sistema GPS - Proyecto Final", font=("Helvetica", 10, "italic")).pack()
    
    def crear_tab_mapa(self):
        # Frame superior con controles
        frame_controles = ttk.Frame(self.tab_mapa)
        frame_controles.pack(fill=tk.X, padx=5, pady=5)
        
        ttk.Label(frame_controles, text="MAPA DE ESPAÑA", font=("Helvetica", 14, "bold"), foreground="#2E5A8C").pack(side=tk.LEFT, padx=10)
        ttk.Separator(frame_controles, orient=tk.VERTICAL).pack(side=tk.LEFT, fill=tk.Y, padx=5)
        ttk.Button(frame_controles, text="Actualizar Mapa", command=self.dibujar_mapa).pack(side=tk.LEFT, padx=5)
        ttk.Button(frame_controles, text="Zoom +", command=self.zoom_in).pack(side=tk.LEFT, padx=2)
        ttk.Button(frame_controles, text="Zoom -", command=self.zoom_out).pack(side=tk.LEFT, padx=2)
        
        self.var_mostrar_nombres = tk.BooleanVar(value=True)
        ttk.Checkbutton(frame_controles, text="Mostrar nombres", variable=self.var_mostrar_nombres, 
                        command=self.dibujar_mapa).pack(side=tk.LEFT, padx=10)
        
        self.var_mostrar_costes = tk.BooleanVar(value=False)
        ttk.Checkbutton(frame_controles, text="Mostrar costes", variable=self.var_mostrar_costes,
                        command=self.dibujar_mapa).pack(side=tk.LEFT, padx=5)
        
        # Leyenda
        ttk.Label(frame_controles, text="  |  ").pack(side=tk.LEFT)
        ttk.Label(frame_controles, text="● Nodo", foreground="blue").pack(side=tk.LEFT, padx=5)
        ttk.Label(frame_controles, text="→ Arista", foreground="gray").pack(side=tk.LEFT, padx=5)
        
        # Canvas para dibujar el mapa
        frame_canvas = ttk.Frame(self.tab_mapa)
        frame_canvas.pack(fill=tk.BOTH, expand=True, padx=5, pady=5)
        
        self.canvas_mapa = tk.Canvas(frame_canvas, bg="#9ec5db", highlightthickness=2, highlightbackground="#5a8199")
        self.canvas_mapa.pack(fill=tk.BOTH, expand=True)
        
        # Scrollbars
        scroll_y = ttk.Scrollbar(frame_canvas, orient=tk.VERTICAL, command=self.canvas_mapa.yview)
        scroll_x = ttk.Scrollbar(frame_canvas, orient=tk.HORIZONTAL, command=self.canvas_mapa.xview)
        self.canvas_mapa.configure(yscrollcommand=scroll_y.set, xscrollcommand=scroll_x.set)
        
        # Variables de zoom
        self.zoom_nivel = 1.0
        self.posiciones_nodos = {}
        
        # Dibujar mapa inicial después de que se muestre la ventana
        self.root.after(100, self.dibujar_mapa)
    
    def calcular_posiciones_nodos(self, ancho, alto):
        """
        Calcula las posiciones de los nodos usando coordenadas geográficas
        reales de ciudades españolas sobre el mapa.
        """
        # Coordenadas reales (latitud, longitud) de las 40 ciudades
        coordenadas_geo = {
            1:  (40.4168, -3.7038),   # Madrid
            2:  (41.3874, 2.1686),    # Barcelona
            3:  (39.4699, -0.3763),   # Valencia
            4:  (37.3891, -5.9845),   # Sevilla
            5:  (41.6488, -0.8891),   # Zaragoza
            6:  (36.7213, -4.4214),   # Málaga
            7:  (37.9922, -1.1307),   # Murcia
            8:  (39.5696, 2.6502),    # Palma
            9:  (28.1235, -15.4363),  # Las Palmas
            10: (43.2630, -2.9350),   # Bilbao
            11: (38.3452, -0.4810),   # Alicante
            12: (37.8882, -4.7794),   # Córdoba
            13: (41.6523, -4.7245),   # Valladolid
            14: (42.2406, -8.7207),   # Vigo
            15: (43.5322, -5.6611),   # Gijón
            16: (41.3596, 2.0997),    # Hospitalet
            17: (42.8469, -2.6727),   # Vitoria
            18: (43.3623, -8.4115),   # A Coruña
            19: (37.1773, -3.5986),   # Granada
            20: (38.2699, -0.7125),   # Elche
            21: (43.3614, -5.8493),   # Oviedo
            22: (41.5630, 2.0089),    # Terrassa
            23: (41.4502, 2.2474),    # Badalona
            24: (37.6257, -0.9966),   # Cartagena
            25: (36.6850, -6.1261),   # Jerez
            26: (41.5463, 2.1082),    # Sabadell
            27: (40.3228, -3.8649),   # Móstoles
            28: (28.4636, -16.2518),  # Santa Cruz
            29: (40.4819, -3.3635),   # Alcalá
            30: (42.8125, -1.6458),   # Pamplona
            31: (40.2838, -3.7944),   # Fuenlabrada
            32: (36.8340, -2.4637),   # Almería
            33: (40.3276, -3.7653),   # Leganés
            34: (43.3183, -1.9812),   # San Sebastián
            35: (42.3439, -3.6969),   # Burgos
            36: (43.4623, -3.8099),   # Santander
            37: (39.9864, -0.0513),   # Castellón
            38: (40.3047, -3.7311),   # Getafe
            39: (38.9942, -1.8585),   # Albacete
            40: (40.3489, -3.8249),   # Alcorcón
        }
        
        nodos = obtener_nodos(self.conn)
        if len(nodos) == 0:
            return {}
        
        posiciones = {}
        for nodo_id, nombre in nodos:
            if nodo_id in coordenadas_geo:
                lat, lon = coordenadas_geo[nodo_id]
                cx, cy = self.geo_a_pix(lat, lon, ancho, alto)
                posiciones[nodo_id] = (cx, cy, nombre)
            else:
                # Nodo sin coordenadas conocidas, colocar en centro
                posiciones[nodo_id] = (ancho / 2, alto / 2, nombre)
        
        return posiciones
    
    def dibujar_mapa(self):
        """
        Dibuja el grafo en el canvas.
        """
        self.canvas_mapa.delete("all")
        
        # Obtener tamaño del canvas
        self.canvas_mapa.update_idletasks()
        ancho = self.canvas_mapa.winfo_width()
        alto = self.canvas_mapa.winfo_height()
        
        if ancho < 50 or alto < 50:
            ancho, alto = 800, 600
        
        # Calcular posiciones
        self.posiciones_nodos = self.calcular_posiciones_nodos(ancho, alto)
        
        if not self.posiciones_nodos:
            self.canvas_mapa.create_text(ancho/2, alto/2, text="No hay datos para mostrar", 
                                         font=("Helvetica", 14))
            return

        # Dibujar silueta de España (península + Baleares) y recuadro de Canarias
        self.dibujar_silueta_espana(ancho, alto)
        
        # Obtener aristas
        aristas = obtener_aristas_con_coste(self.conn)
        
        # Dibujar aristas primero (para que queden debajo de los nodos)
        for origen, destino, coste in aristas:
            if origen in self.posiciones_nodos and destino in self.posiciones_nodos:
                x1, y1, _ = self.posiciones_nodos[origen]
                x2, y2, _ = self.posiciones_nodos[destino]
                
                # Calcular punto intermedio para la flecha (no llegar al centro del nodo)
                dx = x2 - x1
                dy = y2 - y1
                dist = math.sqrt(dx*dx + dy*dy)
                if dist > 0:
                    # Acortar la línea para que no entre en el nodo
                    radio_nodo = 15 * self.zoom_nivel
                    factor = (dist - radio_nodo) / dist
                    x2_adj = x1 + dx * factor
                    y2_adj = y1 + dy * factor
                    x1_adj = x1 + dx * (radio_nodo / dist)
                    y1_adj = y1 + dy * (radio_nodo / dist)
                    
                    # Dibujar línea con flecha
                    self.canvas_mapa.create_line(x1_adj, y1_adj, x2_adj, y2_adj, 
                                                  arrow=tk.LAST, fill="#708090", width=1.2,
                                                  arrowshape=(8, 10, 4))
                    
                    # Mostrar coste si está activado
                    if self.var_mostrar_costes.get():
                        mx = (x1 + x2) / 2
                        my = (y1 + y2) / 2
                        self.canvas_mapa.create_text(mx, my, text=f"{coste:.0f}", 
                                                      font=("Helvetica", 7), fill="#999999")
        
        # Dibujar nodos
        radio = int(15 * self.zoom_nivel)
        for nodo_id, (x, y, nombre) in self.posiciones_nodos.items():
            # Círculo del nodo
            self.canvas_mapa.create_oval(x - radio, y - radio, x + radio, y + radio,
                                          fill="#1f78b4", outline="#0f4d77", width=2)
            
            # ID del nodo en el centro
            self.canvas_mapa.create_text(x, y, text=str(nodo_id), 
                                          font=("Helvetica", 8, "bold"), fill="white")
            
            # Nombre del nodo (si está activado)
            if self.var_mostrar_nombres.get():
                self.canvas_mapa.create_text(x, y + radio + 10, text=nombre,
                                              font=("Helvetica", 7), fill="#333333")
        
        # Configurar scroll region
        self.canvas_mapa.configure(scrollregion=self.canvas_mapa.bbox("all"))
    
    def zoom_in(self):
        self.zoom_nivel = min(self.zoom_nivel * 1.2, 3.0)
        self.dibujar_mapa()
    
    def zoom_out(self):
        self.zoom_nivel = max(self.zoom_nivel / 1.2, 0.5)
        self.dibujar_mapa()

    def geo_a_pix(self, lat, lon, ancho, alto):
        """Convierte lat/lon a coordenadas de canvas usando límites geográficos de España."""
        # Conversión geográfica a canvas
        x = (lon - SPAIN_BOUNDS["lon_min"]) / (SPAIN_BOUNDS["lon_max"] - SPAIN_BOUNDS["lon_min"]) * ancho
        y = alto - ((lat - SPAIN_BOUNDS["lat_min"]) / (SPAIN_BOUNDS["lat_max"] - SPAIN_BOUNDS["lat_min"]) * alto)
        
        # Aplicar zoom
        x = x * self.zoom_nivel
        y = y * self.zoom_nivel
        
        return x, y

    def generar_contorno_espana(self):
        """Genera el contorno REAL de España con coordenadas precisas."""
        # Coordenadas simplificadas del contorno de España para dibujar en el canvas
        # Formato: lista de puntos (lon, lat) que forman el perímetro
        
        SPAIN_OUTLINE = [
            # Costa norte (Galicia -> Pais Vasco)
            (-9.3, 42.1), (-9.0, 43.0), (-8.5, 43.3), (-8.0, 43.7), (-7.5, 43.8),
            (-7.0, 43.6), (-6.5, 43.5), (-6.0, 43.5), (-5.5, 43.4), (-5.0, 43.4),
            (-4.5, 43.4), (-4.0, 43.4), (-3.5, 43.4), (-3.0, 43.4), (-2.5, 43.3),
            (-2.0, 43.3), (-1.8, 43.4),
            # Frontera con Francia
            (-1.5, 43.3), (-1.0, 42.8), (-0.5, 42.7), (0.0, 42.7), (0.5, 42.5),
            (1.0, 42.5), (1.5, 42.4), (2.0, 42.4), (2.5, 42.4), (3.0, 42.4), (3.3, 42.3),
            # Costa este (Cataluna -> Valencia -> Murcia)
            (3.3, 42.0), (3.2, 41.5), (2.5, 41.3), (2.0, 41.3), (1.5, 41.1),
            (1.0, 41.0), (0.8, 40.7), (0.5, 40.5), (0.3, 40.0), (0.0, 39.5),
            (-0.3, 39.0), (-0.5, 38.5), (-0.7, 38.0), (-0.8, 37.5), (-1.0, 37.4),
            (-1.5, 37.5), (-2.0, 36.8),
            # Costa sur (Almeria -> Gibraltar -> Huelva)
            (-2.5, 36.7), (-3.0, 36.7), (-3.5, 36.7), (-4.0, 36.5), (-4.5, 36.2),
            (-5.0, 36.0), (-5.3, 36.0), (-5.6, 36.0), (-6.0, 36.2), (-6.5, 36.5),
            (-7.0, 37.0), (-7.5, 37.2),
            # Costa oeste (Portugal -> Galicia)
            (-7.4, 37.5), (-7.5, 38.0), (-7.3, 38.5), (-7.0, 39.0), (-7.2, 39.5),
            (-7.0, 40.0), (-6.8, 40.5), (-7.0, 41.0), (-7.5, 41.5), (-8.0, 41.8),
            (-8.5, 42.0), (-8.8, 42.0), (-9.3, 42.1)
        ]
        
        # Convertir a formato (lat, lon) para compatibilidad con el código existente
        espana = [(lat, lon) for lon, lat in SPAIN_OUTLINE]
        
        # Islas Baleares
        BALEARES = {
            "mallorca": [(2.3, 39.3), (2.5, 39.7), (3.0, 39.8), (3.5, 39.7), (3.5, 39.4), (3.2, 39.2), (2.8, 39.2), (2.3, 39.3)],
            "menorca": [(3.8, 39.9), (4.0, 40.0), (4.3, 40.0), (4.3, 39.85), (4.0, 39.8), (3.8, 39.9)],
            "ibiza": [(1.2, 38.85), (1.3, 39.0), (1.5, 39.0), (1.6, 38.9), (1.4, 38.8), (1.2, 38.85)],
        }
        
        # Convertir Baleares a formato (lat, lon)
        baleares = {}
        for isla, coords in BALEARES.items():
            baleares[isla] = [(lat, lon) for lon, lat in coords]
        
        return {"peninsula": espana, "baleares": baleares}

    def dibujar_silueta_espana(self, ancho, alto):
        """Dibuja la silueta geográfica de España sobre el mapa."""
        contornos = self.generar_contorno_espana()
        if not contornos:
            return
        
        # ESPAÑA PENINSULAR
        puntos_pen = []
        for lat, lon in contornos["peninsula"]:
            px = self.geo_a_pix(lat, lon, ancho, alto)
            if px:
                puntos_pen.extend(px)
        
        if len(puntos_pen) >= 6:
            # Península principal - ESPAÑA con colores realistas
            self.canvas_mapa.create_polygon(puntos_pen, fill="#f4ecd6", outline="#6b5d42", width=3)
        
        # ISLAS BALEARES
        for isla, coords in contornos["baleares"].items():
            pts = []
            for lat, lon in coords:
                px = self.geo_a_pix(lat, lon, ancho, alto)
                if px:
                    pts.extend(px)
            if len(pts) >= 6:
                self.canvas_mapa.create_polygon(pts, fill="#f4ecd6", outline="#6b5d42", width=2)
    
    # ==================== MÉTODOS DE FUNCIONALIDAD ====================
    
    def actualizar_lista_ciudades(self):
        nodos = obtener_nodos(self.conn)
        valores = [f"{n[0]}. {n[1]}" for n in nodos]
        self.combo_origen['values'] = valores
        self.combo_destino['values'] = valores
        
        for item in self.tree_ciudades.get_children():
            self.tree_ciudades.delete(item)
        for nodo_id, nombre in nodos:
            self.tree_ciudades.insert("", tk.END, values=(nodo_id, nombre))
    
    def actualizar_estadisticas(self):
        num_nodos = contar_nodos(self.conn)
        num_aristas = contar_aristas(self.conn)
        num_unidireccionales = contar_aristas_unidireccionales(self.conn)
        num_bidireccionales = num_aristas - num_unidireccionales
        
        self.label_nodos.config(text=f"Nodos (ciudades): {num_nodos}")
        self.label_aristas.config(text=f"Aristas (carreteras): {num_aristas}")
        self.label_unidireccionales.config(text=f"Aristas unidireccionales: {num_unidireccionales}")
        self.label_bidireccionales.config(text=f"Aristas bidireccionales (pares): {num_bidireccionales // 2}")
    
    def actualizar_historico(self):
        for item in self.tree_historico.get_children():
            self.tree_historico.delete(item)
        historico = obtener_historico_completo(self.conn)
        for registro in historico:
            id_reg, origen, destino, intermedios, fecha, coste, ruta, uso_alt = registro
            nombre_origen = obtener_nombre_nodo(self.conn, origen)
            nombre_destino = obtener_nombre_nodo(self.conn, destino)
            self.tree_historico.insert("", tk.END, values=(id_reg, fecha[:16], nombre_origen, nombre_destino, f"{coste:.2f}", "Sí" if uso_alt else "No"))
    
    def actualizar_cortadas(self):
        for item in self.tree_cortadas.get_children():
            self.tree_cortadas.delete(item)
        cortadas = obtener_carreteras_cortadas(self.conn)
        for orig, dest, motivo, fecha in cortadas:
            nombre_orig = obtener_nombre_nodo(self.conn, orig)
            nombre_dest = obtener_nombre_nodo(self.conn, dest)
            self.tree_cortadas.insert("", tk.END, values=(nombre_orig, nombre_dest, motivo or "-", fecha[:16]))
    
    def obtener_id_desde_combo(self, combo):
        valor = combo.get()
        if not valor:
            return None
        try:
            return int(valor.split(".")[0])
        except:
            return None
    
    def agregar_intermedio(self):
        dialog = tk.Toplevel(self.root)
        dialog.title("Añadir Destino Intermedio")
        dialog.geometry("300x150")
        dialog.transient(self.root)
        dialog.grab_set()
        
        ttk.Label(dialog, text="Seleccione ciudad intermedia:").pack(pady=10)
        nodos = obtener_nodos(self.conn)
        valores = [f"{n[0]}. {n[1]}" for n in nodos]
        combo = ttk.Combobox(dialog, values=valores, width=25, state="readonly")
        combo.pack(pady=5)
        
        def confirmar():
            valor = combo.get()
            if valor:
                nodo_id = int(valor.split(".")[0])
                nombre = obtener_nombre_nodo(self.conn, nodo_id)
                self.intermedios.append(nodo_id)
                self.lista_intermedios.insert(tk.END, f"{nodo_id}. {nombre}")
            dialog.destroy()
        
        ttk.Button(dialog, text="Añadir", command=confirmar).pack(pady=10)
    
    def quitar_intermedio(self):
        seleccion = self.lista_intermedios.curselection()
        if seleccion:
            idx = seleccion[0]
            self.lista_intermedios.delete(idx)
            self.intermedios.pop(idx)
    
    def limpiar_intermedios(self):
        self.lista_intermedios.delete(0, tk.END)
        self.intermedios.clear()
    
    def calcular_ruta(self):
        origen = self.obtener_id_desde_combo(self.combo_origen)
        destino = self.obtener_id_desde_combo(self.combo_destino)
        
        if origen is None:
            messagebox.showwarning("Aviso", "Seleccione una ciudad de origen")
            return
        if destino is None:
            messagebox.showwarning("Aviso", "Seleccione una ciudad de destino")
            return
        
        self.texto_resultado.delete(1.0, tk.END)
        self.btn_usar_alternativa.pack_forget()
        self.ruta_alternativa_actual = None
        self.coste_alternativa_actual = None
        self.origen_actual = origen
        self.destino_actual = destino
        
        nombre_origen = obtener_nombre_nodo(self.conn, origen)
        nombre_destino = obtener_nombre_nodo(self.conn, destino)
        
        # Verificar histórico
        ruta_historica = consultar_ruta_historica(self.conn, origen, destino, self.intermedios)
        if ruta_historica:
            coste_hist, ruta_hist, uso_alt = ruta_historica
            self.texto_resultado.insert(tk.END, "=" * 50 + "\n")
            self.texto_resultado.insert(tk.END, "  RUTA ENCONTRADA EN HISTÓRICO\n")
            self.texto_resultado.insert(tk.END, "=" * 50 + "\n\n")
            self.mostrar_ruta_en_texto(coste_hist, ruta_hist, uso_alt)
            if messagebox.askyesno("Histórico", "Esta ruta ya fue calculada.\n¿Desea recalcularla?"):
                self.texto_resultado.delete(1.0, tk.END)
            else:
                return
        
        # Calcular ruta
        if self.intermedios:
            coste_optimo, ruta_optima = dijkstra_con_intermedios(self.grafo, origen, destino, self.intermedios)
        else:
            coste_optimo, ruta_optima = dijkstra(self.grafo, origen, destino)
        
        if ruta_optima is None:
            self.texto_resultado.insert(tk.END, "=" * 50 + "\n")
            self.texto_resultado.insert(tk.END, "  ERROR: NO EXISTE RUTA\n")
            self.texto_resultado.insert(tk.END, "=" * 50 + "\n\n")
            self.texto_resultado.insert(tk.END, f"No se puede ir de {nombre_origen} a {nombre_destino}\n")
            if self.intermedios:
                self.texto_resultado.insert(tk.END, "\n(Revise los destinos intermedios)")
            return
        
        self.texto_resultado.insert(tk.END, "=" * 50 + "\n")
        self.texto_resultado.insert(tk.END, "  RUTA ÓPTIMA CALCULADA\n")
        self.texto_resultado.insert(tk.END, "=" * 50 + "\n\n")
        self.mostrar_ruta_en_texto(coste_optimo, ruta_optima, False)
        
        uso_alternativa = False
        
        # Buscar ruta alternativa
        if not self.intermedios:
            coste_alt, ruta_alt = calcular_ruta_alternativa(self.grafo, origen, destino, ruta_optima, coste_optimo, self.porcentaje_alternativa)
            if ruta_alt is not None:
                diferencia = ((coste_alt - coste_optimo) / coste_optimo) * 100
                self.texto_resultado.insert(tk.END, "\n" + "-" * 50 + "\n")
                self.texto_resultado.insert(tk.END, f"  RUTA ALTERNATIVA (+{diferencia:.1f}%)\n")
                self.texto_resultado.insert(tk.END, "-" * 50 + "\n\n")
                self.mostrar_ruta_en_texto(coste_alt, ruta_alt, True)
                self.ruta_alternativa_actual = ruta_alt
                self.coste_alternativa_actual = coste_alt
                self.btn_usar_alternativa.pack(pady=5)
        
        guardar_ruta_historica(self.conn, origen, destino, self.intermedios, coste_optimo, ruta_optima, uso_alternativa)
        self.texto_resultado.insert(tk.END, "\n\n✓ Ruta guardada en el histórico")
    
    def mostrar_ruta_en_texto(self, coste, ruta, es_alternativa):
        self.texto_resultado.insert(tk.END, f"Coste total: {coste:.2f} km/min\n")
        self.texto_resultado.insert(tk.END, f"Número de paradas: {len(ruta)}\n\n")
        self.texto_resultado.insert(tk.END, "Recorrido:\n")
        for i, nodo_id in enumerate(ruta):
            nombre = obtener_nombre_nodo(self.conn, nodo_id)
            if i == 0:
                self.texto_resultado.insert(tk.END, f"  INICIO -> {nodo_id}. {nombre}\n")
            elif i == len(ruta) - 1:
                self.texto_resultado.insert(tk.END, f"  FIN    -> {nodo_id}. {nombre}\n")
            else:
                self.texto_resultado.insert(tk.END, f"         -> {nodo_id}. {nombre}\n")
    
    def usar_ruta_alternativa(self):
        if self.ruta_alternativa_actual and self.coste_alternativa_actual:
            guardar_ruta_historica(self.conn, self.origen_actual, self.destino_actual, self.intermedios, self.coste_alternativa_actual, self.ruta_alternativa_actual, True)
            messagebox.showinfo("Éxito", "Ruta alternativa guardada en el histórico")
            self.btn_usar_alternativa.pack_forget()
    
    def bloquear_carretera_gui(self):
        dialog = tk.Toplevel(self.root)
        dialog.title("Bloquear Carretera")
        dialog.geometry("350x250")
        dialog.transient(self.root)
        dialog.grab_set()
        
        nodos = obtener_nodos(self.conn)
        valores = [f"{n[0]}. {n[1]}" for n in nodos]
        
        ttk.Label(dialog, text="Origen:").pack(pady=5)
        combo_orig = ttk.Combobox(dialog, values=valores, width=30, state="readonly")
        combo_orig.pack(pady=5)
        
        ttk.Label(dialog, text="Destino:").pack(pady=5)
        combo_dest = ttk.Combobox(dialog, values=valores, width=30, state="readonly")
        combo_dest.pack(pady=5)
        
        ttk.Label(dialog, text="Motivo (opcional):").pack(pady=5)
        entry_motivo = ttk.Entry(dialog, width=33)
        entry_motivo.pack(pady=5)
        
        def confirmar():
            orig_val = combo_orig.get()
            dest_val = combo_dest.get()
            if not orig_val or not dest_val:
                messagebox.showwarning("Aviso", "Seleccione origen y destino")
                return
            origen = int(orig_val.split(".")[0])
            destino = int(dest_val.split(".")[0])
            motivo = entry_motivo.get()
            if bloquear_carretera(self.conn, origen, destino, motivo):
                if origen in self.grafo and destino in self.grafo[origen]:
                    del self.grafo[origen][destino]
                messagebox.showinfo("Éxito", "Carretera bloqueada correctamente")
                self.actualizar_cortadas()
                dialog.destroy()
            else:
                messagebox.showerror("Error", "No se encontró esa carretera")
        
        ttk.Button(dialog, text="Bloquear", command=confirmar).pack(pady=15)
    
    def desbloquear_carretera_gui(self):
        seleccion = self.tree_cortadas.selection()
        if not seleccion:
            messagebox.showwarning("Aviso", "Seleccione una carretera de la lista")
            return
        item = self.tree_cortadas.item(seleccion[0])
        valores = item['values']
        nombre_orig = valores[0]
        nombre_dest = valores[1]
        
        nodos = obtener_nodos(self.conn)
        origen = None
        destino = None
        for nid, nombre in nodos:
            if nombre == nombre_orig:
                origen = nid
            if nombre == nombre_dest:
                destino = nid
        
        if origen and destino:
            if desbloquear_carretera(self.conn, origen, destino):
                aristas = obtener_aristas_sin_bloqueos(self.conn)
                self.grafo = construir_grafo_desde_aristas(aristas)
                messagebox.showinfo("Éxito", "Carretera desbloqueada")
                self.actualizar_cortadas()
            else:
                messagebox.showerror("Error", "No se pudo desbloquear")
    
    def guardar_configuracion(self):
        try:
            nuevo_porcentaje = int(self.spin_porcentaje.get())
            if 1 <= nuevo_porcentaje <= 100:
                self.porcentaje_alternativa = nuevo_porcentaje
                messagebox.showinfo("Éxito", f"Porcentaje actualizado a {nuevo_porcentaje}%")
            else:
                messagebox.showwarning("Aviso", "El porcentaje debe estar entre 1 y 100")
        except ValueError:
            messagebox.showerror("Error", "Introduzca un número válido")
    
    def cerrar(self):
        if self.conn:
            cerrar_bd(self.conn)
        self.root.destroy()


def limpiar_pantalla():
    print("\n" * 2)


def mostrar_cabecera():
    """
    Muestra la cabecera del programa.
    """
    print("=" * 60)
    print("       SISTEMA GPS - CALCULADOR DE RUTAS ÓPTIMAS")
    print("=" * 60)
    print()


def mostrar_menu_principal():
    """
    Muestra el menú principal y devuelve la opción elegida.
    """
    print("\n" + "-" * 40)
    print("MENÚ PRINCIPAL")
    print("-" * 40)
    print("1. Calcular ruta")
    print("2. Ver lista de ciudades")
    print("3. Ver estadísticas del mapa")
    print("4. Ver histórico de rutas")
    print("5. Gestionar carreteras cortadas")
    print("6. Configuración")
    print("0. Salir")
    print("-" * 40)
    
    return input("Seleccione una opción: ").strip()


def mostrar_ciudades(conn):
    """
    Muestra la lista de todas las ciudades disponibles.
    """
    nodos = obtener_nodos(conn)
    
    print("\n" + "=" * 40)
    print("CIUDADES DISPONIBLES")
    print("=" * 40)
    
    for i, (nodo_id, nombre) in enumerate(nodos):
        print(f"  {nodo_id:3d}. {nombre}")
        if (i + 1) % 10 == 0:
            print()
    
    print("=" * 40)
    print(f"Total: {len(nodos)} ciudades")


def mostrar_estadisticas(conn):
    """
    Muestra estadísticas del mapa.
    """
    num_nodos = contar_nodos(conn)
    num_aristas = contar_aristas(conn)
    num_unidireccionales = contar_aristas_unidireccionales(conn)
    num_bidireccionales = num_aristas - num_unidireccionales
    
    print("\n" + "=" * 40)
    print("ESTADÍSTICAS DEL MAPA")
    print("=" * 40)
    print(f"  Nodos (ciudades):             {num_nodos}")
    print(f"  Aristas (carreteras):         {num_aristas}")
    print(f"  Aristas unidireccionales:     {num_unidireccionales}")
    print(f"  Aristas bidireccionales:      {num_bidireccionales // 2} pares")
    print("=" * 40)


def solicitar_nodo(conn, mensaje):
    """
    Solicita al usuario un nodo válido.
    Devuelve el ID del nodo o None si cancela.
    """
    while True:
        entrada = input(mensaje).strip()
        
        if entrada.lower() in ['', 'c', 'cancelar']:
            return None
        
        try:
            nodo_id = int(entrada)
            if existe_nodo(conn, nodo_id):
                return nodo_id
            else:
                print(f"  Error: El nodo {nodo_id} no existe. Intente de nuevo.")
        except ValueError:
            print("  Error: Introduzca un número válido.")


def solicitar_intermedios(conn):
    """
    Solicita al usuario los destinos intermedios.
    Devuelve una lista de IDs de nodos o lista vacía.
    """
    print("\n¿Desea añadir destinos intermedios? (s/n): ", end="")
    respuesta = input().strip().lower()
    
    if respuesta != 's':
        return []
    
    intermedios = []
    print("Introduzca los nodos intermedios (vacío para terminar):")
    
    while True:
        nodo = solicitar_nodo(conn, f"  Intermedio {len(intermedios) + 1}: ")
        if nodo is None:
            break
        intermedios.append(nodo)
        print(f"    -> {obtener_nombre_nodo(conn, nodo)} añadido")
    
    return intermedios


def mostrar_ruta(conn, coste, ruta, es_alternativa=False):
    """
    Muestra una ruta de forma clara.
    """
    tipo = "ALTERNATIVA" if es_alternativa else "ÓPTIMA"
    
    print(f"\n{'*' * 50}")
    print(f"  RUTA {tipo}")
    print(f"{'*' * 50}")
    print(f"  Coste total: {coste:.2f} km/min")
    print(f"  Número de paradas: {len(ruta)}")
    print(f"\n  Recorrido:")
    
    for i, nodo_id in enumerate(ruta):
        nombre = obtener_nombre_nodo(conn, nodo_id)
        if i == 0:
            print(f"    INICIO -> {nodo_id}. {nombre}")
        elif i == len(ruta) - 1:
            print(f"    FIN    -> {nodo_id}. {nombre}")
        else:
            print(f"           -> {nodo_id}. {nombre}")
    
    print(f"{'*' * 50}")


def calcular_ruta_interactivo(conn, grafo):
    """
    Proceso interactivo para calcular una ruta.
    """
    print("\n" + "=" * 50)
    print("CALCULAR NUEVA RUTA")
    print("=" * 50)
    print("(Introduzca 'c' o vacío para cancelar)")
    
    # Solicitar origen
    origen = solicitar_nodo(conn, "\nNodo de ORIGEN: ")
    if origen is None:
        print("Operación cancelada.")
        return
    print(f"  -> Origen: {obtener_nombre_nodo(conn, origen)}")
    
    # Solicitar destino
    destino = solicitar_nodo(conn, "Nodo de DESTINO: ")
    if destino is None:
        print("Operación cancelada.")
        return
    print(f"  -> Destino: {obtener_nombre_nodo(conn, destino)}")
    
    # Solicitar intermedios
    intermedios = solicitar_intermedios(conn)
    
    # Verificar si existe en el histórico
    ruta_historica = consultar_ruta_historica(conn, origen, destino, intermedios)
    
    if ruta_historica:
        coste_hist, ruta_hist, uso_alt = ruta_historica
        print("\n" + "!" * 50)
        print("  Se encontró esta ruta en el histórico.")
        print("!" * 50)
        mostrar_ruta(conn, coste_hist, ruta_hist, uso_alt)
        
        print("\n¿Desea recalcular la ruta? (s/n): ", end="")
        if input().strip().lower() != 's':
            return
        print("Recalculando...")
    
    # Calcular ruta con Dijkstra
    if intermedios:
        coste_optimo, ruta_optima = dijkstra_con_intermedios(grafo, origen, destino, intermedios)
    else:
        coste_optimo, ruta_optima = dijkstra(grafo, origen, destino)
    
    # Verificar si existe camino
    if ruta_optima is None:
        print("\n" + "!" * 50)
        print("  ERROR: No existe ruta posible entre los puntos.")
        print("!" * 50)
        nombre_origen = obtener_nombre_nodo(conn, origen)
        nombre_destino = obtener_nombre_nodo(conn, destino)
        print(f"  No se puede ir de {nombre_origen} a {nombre_destino}")
        if intermedios:
            print("  (Revise los destinos intermedios)")
        return
    
    # Mostrar ruta óptima
    mostrar_ruta(conn, coste_optimo, ruta_optima)
    
    uso_alternativa = False
    
    # Buscar ruta alternativa (solo si no hay intermedios)
    if not intermedios:
        coste_alt, ruta_alt = calcular_ruta_alternativa(
            grafo, origen, destino, ruta_optima, coste_optimo
        )
        
        if ruta_alt is not None:
            diferencia = ((coste_alt - coste_optimo) / coste_optimo) * 100
            
            print("\n" + "-" * 50)
            print(f"  Se encontró una ruta alternativa (+{diferencia:.1f}%)")
            print("-" * 50)
            
            mostrar_ruta(conn, coste_alt, ruta_alt, es_alternativa=True)
            
            print("\n¿Desea usar la ruta alternativa? (s/n): ", end="")
            if input().strip().lower() == 's':
                uso_alternativa = True
                coste_optimo = coste_alt
                ruta_optima = ruta_alt
                print("  -> Usando ruta alternativa")
    
    # Guardar en histórico
    guardar_ruta_historica(conn, origen, destino, intermedios, coste_optimo, ruta_optima, uso_alternativa)
    print("\n  Ruta guardada en el histórico.")


def mostrar_historico(conn):
    """
    Muestra el histórico de rutas calculadas.
    """
    historico = obtener_historico_completo(conn)
    
    print("\n" + "=" * 60)
    print("HISTÓRICO DE RUTAS")
    print("=" * 60)
    
    if not historico:
        print("  No hay rutas en el histórico.")
        return
    
    for registro in historico:
        id_reg, origen, destino, intermedios, fecha, coste, ruta, uso_alt = registro
        
        nombre_origen = obtener_nombre_nodo(conn, origen)
        nombre_destino = obtener_nombre_nodo(conn, destino)
        
        print(f"\n  [{id_reg}] {fecha[:16]}")
        print(f"      {nombre_origen} -> {nombre_destino}")
        if intermedios:
            print(f"      Intermedios: {intermedios}")
        print(f"      Coste: {coste:.2f} | Alternativa: {'Sí' if uso_alt else 'No'}")
    
    print("\n" + "=" * 60)


def gestionar_carreteras_cortadas(conn, grafo):
    """
    Menú para gestionar carreteras cortadas.
    """
    while True:
        print("\n" + "-" * 40)
        print("GESTIÓN DE CARRETERAS CORTADAS")
        print("-" * 40)
        print("1. Ver carreteras cortadas")
        print("2. Bloquear carretera")
        print("3. Desbloquear carretera")
        print("0. Volver")
        print("-" * 40)
        
        opcion = input("Seleccione: ").strip()
        
        if opcion == '0':
            break
        elif opcion == '1':
            cortadas = obtener_carreteras_cortadas(conn)
            print("\n  Carreteras cortadas:")
            if not cortadas:
                print("    (ninguna)")
            else:
                for orig, dest, motivo, fecha in cortadas:
                    nombre_orig = obtener_nombre_nodo(conn, orig)
                    nombre_dest = obtener_nombre_nodo(conn, dest)
                    print(f"    {nombre_orig} -> {nombre_dest}")
                    if motivo:
                        print(f"      Motivo: {motivo}")
        
        elif opcion == '2':
            origen = solicitar_nodo(conn, "Origen de la carretera: ")
            if origen is None:
                continue
            destino = solicitar_nodo(conn, "Destino de la carretera: ")
            if destino is None:
                continue
            motivo = input("Motivo (opcional): ").strip()
            
            if bloquear_carretera(conn, origen, destino, motivo):
                print("  Carretera bloqueada correctamente.")
                # Actualizar grafo en memoria
                if origen in grafo and destino in grafo[origen]:
                    del grafo[origen][destino]
            else:
                print("  Error: No se encontró esa carretera.")
        
        elif opcion == '3':
            origen = solicitar_nodo(conn, "Origen de la carretera: ")
            if origen is None:
                continue
            destino = solicitar_nodo(conn, "Destino de la carretera: ")
            if destino is None:
                continue
            
            if desbloquear_carretera(conn, origen, destino):
                print("  Carretera desbloqueada.")
                # Reconstruir grafo para incluir la carretera
                aristas = obtener_aristas_sin_bloqueos(conn)
                grafo.clear()
                grafo.update(construir_grafo_desde_aristas(aristas))
            else:
                print("  Error: No se encontró bloqueo para esa carretera.")


def menu_configuracion():
    """
    Menú de configuración del sistema.
    """
    global PORCENTAJE_ALTERNATIVA
    
    while True:
        print("\n" + "-" * 40)
        print("CONFIGURACIÓN")
        print("-" * 40)
        print(f"1. Porcentaje ruta alternativa: {PORCENTAJE_ALTERNATIVA}%")
        print("0. Volver")
        print("-" * 40)
        
        opcion = input("Seleccione: ").strip()
        
        if opcion == '0':
            break
        elif opcion == '1':
            try:
                nuevo = float(input(f"Nuevo porcentaje (actual: {PORCENTAJE_ALTERNATIVA}%): "))
                if 0 <= nuevo <= 100:
                    PORCENTAJE_ALTERNATIVA = nuevo
                    print(f"  Porcentaje actualizado a {PORCENTAJE_ALTERNATIVA}%")
                else:
                    print("  Error: El porcentaje debe estar entre 0 y 100.")
            except ValueError:
                print("  Error: Introduzca un número válido.")


# ================================================================================
# 6. FUNCIÓN PRINCIPAL
# ================================================================================

def inicializar_sistema():
    """
    Inicializa el sistema: conexión, tablas y carga de datos.
    Devuelve la conexión a la BD y el grafo en memoria.
    """
    # Conectar a la base de datos
    conn = conectar_bd()
    
    # Crear tablas si no existen
    crear_tablas(conn)
    
    # Cargar datos del CSV si es necesario
    if base_esta_vacia(conn):
        print("Inicializando base de datos desde CSV...")
        if cargar_csv_en_bd(conn):
            print("  Base de datos cargada correctamente.")
        else:
            print("  Error al cargar la base de datos.")
            return None, None
    
    # Construir grafo en memoria desde la BD (excluyendo carreteras cortadas)
    aristas = obtener_aristas_sin_bloqueos(conn)
    grafo = construir_grafo_desde_aristas(aristas)
    
    return conn, grafo


def main():
    """
    Función principal del programa.
    Lanza la interfaz gráfica.
    """
    root = tk.Tk()
    app = AplicacionGPS(root)
    root.protocol("WM_DELETE_WINDOW", app.cerrar)
    root.mainloop()


# ================================================================================
# PUNTO DE ENTRADA
# ================================================================================

if __name__ == "__main__":
    main()


