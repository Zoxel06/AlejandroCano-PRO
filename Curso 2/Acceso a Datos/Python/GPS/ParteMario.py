import sqlite3
import csv
import os
from datetime import datetime

# =====================================================
# CONFIGURACIÓN GENERAL
# =====================================================

DB_PATH = "gps.db"
CSV_PATH = "mapa.csv"

# =====================================================
# CONEXIÓN A SQLITE
# =====================================================

def conectar_bd():
    """
    Crea y devuelve una conexión a la base de datos SQLite.
    """
    return sqlite3.connect(DB_PATH)

# =====================================================
# CREACIÓN DEL ESQUEMA DE LA BASE DE DATOS
# =====================================================

def crear_tablas(conn):
    """
    Crea todas las tablas necesarias si no existen.
    """
    cursor = conn.cursor()

    # Tabla de nodos (ciudades / intersecciones)
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS nodos (
        id INTEGER PRIMARY KEY
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

    # Tabla de histórico de rutas
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS historico (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        origen INTEGER NOT NULL,
        destino INTEGER NOT NULL,
        fecha_hora TEXT NOT NULL,
        coste_total REAL NOT NULL,
        ruta TEXT NOT NULL,
        uso_alternativa INTEGER NOT NULL
    )
    """)

    conn.commit()

# =====================================================
# CARGA INICIAL DEL CSV (SOLO UNA VEZ)
# =====================================================

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
    El CSV NO se vuelve a usar después.
    """
    cursor = conn.cursor()

    with open(CSV_PATH, newline='', encoding='utf-8') as fichero:
        lector = csv.reader(fichero)
        for fila in lector:
            origen, destino, coste = fila
            origen = int(origen)
            destino = int(destino)
            coste = float(coste)

            # Insertar nodos si no existen
            cursor.execute(
                "INSERT OR IGNORE INTO nodos (id) VALUES (?)",
                (origen,)
            )
            cursor.execute(
                "INSERT OR IGNORE INTO nodos (id) VALUES (?)",
                (destino,)
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

# =====================================================
# FUNCIONES DAO PARA QUE OTROS MÓDULOS CONSULTEN EL GRAFO
# =====================================================

def obtener_nodos(conn):
    """
    Devuelve una lista de todos los nodos del mapa.
    """
    cursor = conn.cursor()
    cursor.execute("SELECT id FROM nodos")
    return [fila[0] for fila in cursor.fetchall()]

def obtener_aristas_con_coste(conn):
    """
    Devuelve todas las aristas con su coste asociado.
    Formato: (origen, destino, coste)
    """
    cursor = conn.cursor()
    cursor.execute("""
    SELECT a.origen, a.destino, p.coste
    FROM aristas a
    JOIN pesos p ON a.id = p.arista_id
    """)
    return cursor.fetchall()

# =====================================================
# HISTÓRICO DE RUTAS
# =====================================================

def guardar_ruta_historica(conn, origen, destino, coste_total, ruta, uso_alternativa):
    """
    Guarda una ruta calculada en el histórico.
    """
    cursor = conn.cursor()
    cursor.execute("""
    INSERT INTO historico (
        origen, destino, fecha_hora,
        coste_total, ruta, uso_alternativa
    )
    VALUES (?, ?, ?, ?, ?, ?)
    """, (
        origen,
        destino,
        datetime.now().isoformat(),
        coste_total,
        ruta,
        int(uso_alternativa)
    ))
    conn.commit()

def consultar_ruta_historica(conn, origen, destino):
    """
    Busca si ya existe una ruta previa entre origen y destino.
    """
    cursor = conn.cursor()
    cursor.execute("""
    SELECT coste_total, ruta, uso_alternativa
    FROM historico
    WHERE origen = ? AND destino = ?
    ORDER BY fecha_hora DESC
    LIMIT 1
    """, (origen, destino))
    return cursor.fetchone()

# =====================================================
# FUNCIÓN PRINCIPAL (ORQUESTACIÓN)
# =====================================================

def main():
    conn = conectar_bd()
    crear_tablas(conn)

    if base_esta_vacia(conn):
        cargar_csv_en_bd(conn)
        print("Base de datos creada y cargada desde CSV")
    else:
        print("Base de datos ya inicializada")

    # IMPORTANTE:
    # Aquí NO se llama a Dijkstra ni a la interfaz.
    # Otros compañeros usarán las funciones DAO.

    conn.close()

# =====================================================
# PUNTO DE ENTRADA
# =====================================================

if __name__ == "__main__":
    main()
