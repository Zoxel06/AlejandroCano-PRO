#!/usr/bin/env python3
# simulador_aereo_completo.py
"""
Simulador Aéreo - Consola
Versión completa con:
- carga vuelos/pistas desde CSV
- logs (eventos.log) y informe (informe.log)
- reloj simulado (1 min sim = 5s reales en modo automático)
- asignación según prioridad / combustible / atraso / id
- cierre temporal de pistas N minutos
- guardar/cargar estado completo en estado.json
- menú interactivo
- cancelaciones manuales
- replay desde logs
"""

import csv
import os
import time
import json
from typing import Dict, Optional, Tuple, List
import functools

# --------------------------
# CONSTANTES / VALIDACIONES
# --------------------------
TiposValidos = ["ATERRIZAJE", "DESPEGUE"]
PrioridadesValidas = [0, 1, 2]
EstadosValidos = ["EN_COLA", "ASIGNADO", "COMPLETADO", "CANCELADO"]
CategoriasPistaValidas = ["CORTA", "ESTANDAR", "LARGA"]

LOG_EVENTOS = "eventos.log"
LOG_INFORME = "informe.log"
ESTADO_CSV = "estado.csv"
ESTADO_JSON = "estado.json"
VUELOS_CSV_DEFECTO = "vuelos.csv"
PISTAS_CSV_DEFECTO = "pistas.csv"

# --------------------------
# UTILIDADES
# --------------------------
def registrar_evento(linea: str):
    """Añade una línea al fichero de eventos (append)."""
    with open(LOG_EVENTOS, "a", encoding="utf-8") as f:
        f.write(linea + "\n")

def convertir_float(valor: str) -> Optional[float]:
    if valor is None:
        return None
    v = str(valor).strip()
    if v == "" or v.lower() == "nan":
        return None
    try:
        return float(v)
    except:
        return None

def convertir_int(valor: str) -> int:
    if valor is None:
        return 0
    v = str(valor).strip()
    if v == "" or v.lower() == "nan":
        return 0
    try:
        return int(float(v))
    except:
        return 0

def normalizar_cadena(s: str) -> str:
    if s is None:
        return ""
    return str(s).strip().upper()

# --------------------------
# VALIDAR / NORMALIZAR
# --------------------------
def validar_normalizar_vuelo(fila: Dict) -> Optional[Dict]:
    idv = str(fila.get("id", "") or fila.get("id_vuelo", "")).strip().upper()
    if idv == "":
        print("[ADVERTENCIA] Vuelo sin id -> ignorado")
        return None
    tipo = normalizar_cadena(fila.get("tipo", ""))
    if tipo not in TiposValidos:
        print(f"[ADVERTENCIA] Tipo inválido en vuelo {idv}: '{tipo}' -> ignorado")
        return None
    eta = convertir_float(fila.get("eta", ""))
    etd = convertir_float(fila.get("etd", ""))
    prioridad = convertir_int(fila.get("prioridad", "0"))
    if prioridad not in PrioridadesValidas:
        prioridad = 0
    combustible = convertir_float(fila.get("combustible", ""))
    if tipo == "ATERRIZAJE":
        if combustible is None:
            combustible = 0.0
    else:
        combustible = None
    estado = normalizar_cadena(fila.get("estado", "EN_COLA"))
    if estado == "" or estado not in EstadosValidos:
        estado = "EN_COLA"
    if tipo == "ATERRIZAJE" and eta is None:
        eta = 0.0
    if tipo == "DESPEGUE" and etd is None:
        etd = 0.0
    return {
        "id": idv,
        "tipo": tipo,
        "eta": eta,
        "etd": etd,
        "prioridad": prioridad,
        "combustible": combustible,
        "estado": estado,
        "t_asignacion": None,
        "t_completado": None,
        "t_entrada_cola": None,
        "nota": fila.get("nota", "")
    }

def validar_normalizar_pista(fila: Dict) -> Optional[Dict]:
    idp = str(fila.get("id_pista", "") or fila.get("id", "")).strip().upper()
    if idp == "":
        print("[ADVERTENCIA] Pista sin id -> ignorada")
        return None
    categoria = normalizar_cadena(fila.get("categoria", "ESTANDAR"))
    if categoria not in CategoriasPistaValidas:
        categoria = "ESTANDAR"
    tiempo_uso = convertir_int(fila.get("tiempo_uso", "0"))
    habilitada_num = convertir_int(fila.get("habilitada", "1"))
    habilitada = 1 if habilitada_num != 0 else 0
    estado_pista = "LIBRE" if habilitada == 1 else "DESHABILITADA"
    return {
        "idPista": idp,
        "categoria": categoria,
        "tiempoUso": tiempo_uso,
        "habilitada": habilitada,
        "estadoPista": estado_pista,
        "vueloAsignadoId": None,
        "ocupadaHasta": None,
        "uso_count": 0,
        "deshabilitadaHasta": None
    }

# --------------------------
# IMPORTAR CSV
# --------------------------
def importar_vuelos_csv(ruta: str) -> Dict[str, Dict]:
    vuelos = {}
    try:
        with open(ruta, newline='', encoding='utf-8') as f:
            lector = csv.DictReader(f)
            for fila in lector:
                v = validar_normalizar_vuelo(fila)
                if v is None:
                    continue
                if v["id"] in vuelos:
                    print(f"[ADVERTENCIA] Id de vuelo duplicado ({v['id']}) -> ignorado")
                    continue
                v["t_entrada_cola"] = 0
                vuelos[v["id"]] = v
    except FileNotFoundError:
        print(f"[ERROR] No se encontró {ruta}. Se continuará con lista vacía.")
    return vuelos

def importar_pistas_csv(ruta: str) -> Dict[str, Dict]:
    pistas = {}
    try:
        with open(ruta, newline='', encoding='utf-8') as f:
            lector = csv.DictReader(f)
            for fila in lector:
                p = validar_normalizar_pista(fila)
                if p is None:
                    continue
                if p["idPista"] in pistas:
                    print(f"[ADVERTENCIA] Id de pista duplicado ({p['idPista']}) -> ignorada")
                    continue
                pistas[p["idPista"]] = p
    except FileNotFoundError:
        print(f"[ERROR] No se encontró {ruta}. Se continuará con lista vacía.")
    return pistas

# --------------------------
# MOSTRAR
# --------------------------
def mostrar_vuelos(vuelos: Dict[str, Dict]):
    if not vuelos:
        print("No hay vuelos.")
        return
    print("\n--- VUELOS ---")
    for v in sorted(vuelos.values(), key=lambda x: x["id"]):
        print(f"Id:{v['id']} Tipo:{v['tipo']} ETA:{v['eta']} ETD:{v['etd']} Pri:{v['prioridad']} Comb:{v['combustible']} Estado:{v['estado']} t_entrada_cola:{v.get('t_entrada_cola')} t_asig:{v.get('t_asignacion')} t_fin:{v.get('t_completado')}")
    print("--------------\n")

def mostrar_pistas(pistas: Dict[str, Dict]):
    if not pistas:
        print("No hay pistas.")
        return
    print("\n--- PISTAS ---")
    for p in sorted(pistas.values(), key=lambda x: x["idPista"]):
        dh = f" deshabilitadaHasta={p['deshabilitadaHasta']}" if p.get("deshabilitadaHasta") is not None else ""
        print(f"Id:{p['idPista']} Cat:{p['categoria']} Estado:{p['estadoPista']} Uso:{p['tiempoUso']} Vuelo:{p['vueloAsignadoId']} OcupadaHasta:{p['ocupadaHasta']} UsoCount:{p.get('uso_count',0)}{dh}")
    print("--------------\n")

def mostrar_colas(vuelos: Dict[str, Dict], tiempo_simulado: int):
    flA, flD = actualizar_flujos(vuelos)
    print("\n--- COLAS (EN_COLA) ---")
    print("Aterrizaje:", flA)
    print("Despegue  :", flD)
    print("-----------------------\n")

# --------------------------
# FLUJOS Y SELECCIÓN
# --------------------------
def actualizar_flujos(vuelos: Dict[str, Dict]) -> Tuple[List[str], List[str]]:
    aterrizaje = []
    despegue = []
    for idv, v in vuelos.items():
        if v["estado"] != "EN_COLA":
            continue
        if v["tipo"] == "ATERRIZAJE":
            aterrizaje.append(idv)
        else:
            despegue.append(idv)
    return aterrizaje, despegue

def _cmp_vuelos(a: Dict, b: Dict, tiempo_simulado: int) -> int:
    pa = a.get("prioridad", 0)
    pb = b.get("prioridad", 0)
    if pa == 2 and pb != 2:
        return -1
    if pb == 2 and pa != 2:
        return 1
    if a["tipo"] == "ATERRIZAJE" and b["tipo"] == "ATERRIZAJE":
        ca = a.get("combustible", float("inf"))
        cb = b.get("combustible", float("inf"))
        if ca != cb:
            return -1 if ca < cb else 1
    else:
        if a["tipo"] == "ATERRIZAJE" and b["tipo"] != "ATERRIZAJE":
            return -1
        if b["tipo"] == "ATERRIZAJE" and a["tipo"] != "ATERRIZAJE":
            return 1
    def retraso(v):
        hora_prevista = v.get("eta") if v.get("tipo") == "ATERRIZAJE" else v.get("etd")
        if hora_prevista is None:
            return 0
        return max(0, int(tiempo_simulado - hora_prevista))
    ra = retraso(a)
    rb = retraso(b)
    if ra != rb:
        return -1 if ra > rb else 1
    if a["id"] < b["id"]:
        return -1
    if a["id"] > b["id"]:
        return 1
    return 0

def seleccionar_vuelo_para_asignar(lista_ids: List[str], vuelos: Dict[str, Dict], tiempo_simulado: int) -> Optional[str]:
    if not lista_ids:
        return None
    candidatos = [vuelos[i] for i in lista_ids if i in vuelos and vuelos[i]["estado"] == "EN_COLA"]
    if not candidatos:
        return None
    candidatos_sorted = sorted(candidatos, key=functools.cmp_to_key(lambda x,y: _cmp_vuelos(x,y,tiempo_simulado)))
    return candidatos_sorted[0]["id"] if candidatos_sorted else None

# --------------------------
# ASIGNACIÓN Y LIBERACIÓN
# --------------------------
def asignar_pista_directa(id_pista: str, id_vuelo: str, pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_actual: int) -> bool:
    pista = pistas.get(id_pista)
    vuelo = vuelos.get(id_vuelo)
    if pista is None or vuelo is None:
        return False
    if pista["habilitada"] == 0 or pista["estadoPista"].startswith("DESHABILITADA"):
        return False
    if pista["estadoPista"] == "OCUPADA":
        return False
    if vuelo["estado"] not in ["EN_COLA", "ASIGNADO"]:
        return False
    ocupada_hasta = tiempo_actual + pista["tiempoUso"]
    pista["estadoPista"] = "OCUPADA"
    pista["vueloAsignadoId"] = id_vuelo
    pista["ocupadaHasta"] = ocupada_hasta
    pista["uso_count"] = pista.get("uso_count", 0) + 1
    vuelo["estado"] = "ASIGNADO"
    if vuelo["t_asignacion"] is None:
        vuelo["t_asignacion"] = tiempo_actual
    registrar_evento(f"[t={tiempo_actual}] ASIGNACION id_vuelo={id_vuelo} pista={id_pista} tipo={vuelo['tipo']}")
    return True

def liberar_pista(pista: Dict, pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_actual: int):
    id_pista = pista["idPista"]
    vuelo_id = pista["vueloAsignadoId"]
    pista["estadoPista"] = "LIBRE" if pista.get("habilitada",1) == 1 else "DESHABILITADA"
    pista["vueloAsignadoId"] = None
    pista["ocupadaHasta"] = None
    if vuelo_id and vuelo_id in vuelos:
        v = vuelos[vuelo_id]
        if v["estado"] != "CANCELADO":
            v["estado"] = "COMPLETADO"
            v["t_completado"] = tiempo_actual
            registrar_evento(f"[t={tiempo_actual}] COMPLETADO id_vuelo={vuelo_id} pista={id_pista}")

def asignacion_automatica_pistas(pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_simulado: int):
    flujoAterrizaje, flujoDespegue = actualizar_flujos(vuelos)
    candidatos_ids = flujoAterrizaje + flujoDespegue
    for id_pista in sorted(pistas.keys()):
        pista = pistas[id_pista]
        if pista.get("deshabilitadaHasta") is not None:
            if tiempo_simulado < pista["deshabilitadaHasta"]:
                continue
        if pista["habilitada"] == 0 or pista["estadoPista"].startswith("DESHABILITADA"):
            continue
        if pista["estadoPista"] != "LIBRE":
            continue
        candidatos = [vid for vid in candidatos_ids if vid in vuelos and vuelos[vid]["estado"] == "EN_COLA"]
        if not candidatos:
            continue
        elegido = seleccionar_vuelo_para_asignar(candidatos, vuelos, tiempo_simulado)
        if elegido is None:
            continue
        ok = asignar_pista_directa(id_pista, elegido, pistas, vuelos, tiempo_simulado)
        if ok:
            if elegido in candidatos_ids:
                candidatos_ids.remove(elegido)
            if elegido in flujoAterrizaje:
                flujoAterrizaje.remove(elegido)
            if elegido in flujoDespegue:
                flujoDespegue.remove(elegido)

# --------------------------
# AVANZAR TIEMPO
# --------------------------
def _procesar_minuto(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_actual: int):
    vuelos_a_cancelar = []
    for v in vuelos.values():
        if v["tipo"] == "ATERRIZAJE" and v["estado"] in ["EN_COLA", "ASIGNADO"]:
            if v["combustible"] is None:
                continue
            v["combustible"] = max(0.0, v["combustible"] - 1.0)
            if v["combustible"] <= 5 and v.get("prioridad", 0) < 2:
                v["prioridad"] = 2
                registrar_evento(f"[t={tiempo_actual}] EMERGENCIA id_vuelo={v['id']} prioridad=2 motivo=combustible<=5")
            if v["combustible"] <= 0 and v["estado"] != "CANCELADO":
                vuelos_a_cancelar.append(v["id"])
    for vid in vuelos_a_cancelar:
        v = vuelos.get(vid)
        if v is None:
            continue
        if v["estado"] == "ASIGNADO":
            for p in pistas.values():
                if p.get("vueloAsignadoId") == vid:
                    p["estadoPista"] = "LIBRE"
                    p["vueloAsignadoId"] = None
                    p["ocupadaHasta"] = None
                    break
        v["estado"] = "CANCELADO"
        v["t_completado"] = tiempo_actual
        registrar_evento(f"[t={tiempo_actual}] CANCELADO id_vuelo={vid} motivo=sin_combustible")
    for p in pistas.values():
        if p.get("deshabilitadaHasta") is not None:
            if tiempo_actual >= p["deshabilitadaHasta"]:
                p["deshabilitadaHasta"] = None
                p["habilitada"] = 1
                if p["estadoPista"].startswith("DESHABILITADA"):
                    p["estadoPista"] = "LIBRE"
                registrar_evento(f"[t={tiempo_actual}] PISTA_REHABILITADA id_pista={p['idPista']}")
    pistas_liberadas = []
    for p in pistas.values():
        if p["estadoPista"] == "OCUPADA" and p["ocupadaHasta"] is not None and tiempo_actual >= p["ocupadaHasta"]:
            pistas_liberadas.append(p["idPista"])
    for idp in pistas_liberadas:
        p = pistas[idp]
        liberar_pista(p, pistas, vuelos, tiempo_actual)
    asignacion_automatica_pistas(pistas, vuelos, tiempo_actual)

def avanzar_tiempo(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_actual: int, minutos: int) -> int:
    if minutos <= 0:
        return tiempo_actual
    for _ in range(minutos):
        tiempo_actual += 1
        _procesar_minuto(vuelos, pistas, tiempo_actual)
    return tiempo_actual

# --------------------------
# GENERAR INFORME
# --------------------------
def generar_informe(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_simulado: int):
    total_atendidos = sum(1 for v in vuelos.values() if v["estado"] == "COMPLETADO")
    emergencias = sum(1 for v in vuelos.values() if v.get("prioridad") == 2 and v.get("t_completado") is not None)
    esperas = []
    detalle = []
    for v in vuelos.values():
        if v.get("t_asignacion") is not None:
            prevista = v["eta"] if v["tipo"] == "ATERRIZAJE" else v["etd"]
            entrada = v.get("t_entrada_cola") if v.get("t_entrada_cola") is not None else 0
            base = prevista if prevista is not None else entrada
            base = max(base, entrada)
            espera = v["t_asignacion"] - base
            esperas.append(max(0, espera))
        if v.get("t_asignacion") is not None or v.get("t_completado") is not None:
            detalle.append(v)
    tiempo_medio_espera = (sum(esperas) / len(esperas)) if esperas else 0.0
    uso_pistas = {p['idPista']: p.get("uso_count", 0) for p in pistas.values()}
    with open(LOG_INFORME, "w", encoding="utf-8") as f:
        f.write("RESUMEN\n")
        f.write(f"- Tiempo simulado (min): {tiempo_simulado}\n")
        f.write(f"- Vuelos atendidos: {total_atendidos}\n")
        f.write(f"- Tiempo medio de espera (min): {tiempo_medio_espera:.2f}\n")
        f.write("- Uso de pistas: " + ", ".join(f"{k}={v}" for k, v in uso_pistas.items()) + "\n")
        f.write(f"- Emergencias gestionadas: {emergencias}\n")
        f.write("- Detalle de vuelos completados:\n")
        for v in sorted(vuelos.values(), key=lambda x: (x["t_completado"] if x.get("t_completado") is not None else 10**9)):
            if v["estado"] == "COMPLETADO":
                f.write(f"• {v['id']} ({v['tipo']}) t_inicio={v['t_asignacion']} t_fin={v['t_completado']}\n")
    registrar_evento(f"[t={tiempo_simulado}] FIN_SIMULACION vuelos_atendidos={total_atendidos}")

# --------------------------
# GUARDAR / CARGAR ESTADO
# --------------------------
def guardar_estado_csv(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], ruta: str = ESTADO_CSV):
    with open(ruta, "w", newline="", encoding="utf-8") as f:
        w = csv.writer(f)
        w.writerow(["tipo", "id", "estado", "t_asignacion", "t_completado"])
        for v in vuelos.values():
            w.writerow(["vuelo", v["id"], v["estado"], v["t_asignacion"], v["t_completado"]])
        for p in pistas.values():
            w.writerow(["pista", p["idPista"], p["estadoPista"], p["ocupadaHasta"], ""])

def guardar_estado_json(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_simulado: int, ruta: str = ESTADO_JSON):
    estado = {
        "tiempo_simulado": tiempo_simulado,
        "vuelos": vuelos,
        "pistas": pistas
    }
    with open(ruta, "w", encoding="utf-8") as f:
        json.dump(estado, f, ensure_ascii=False, indent=2)
    print(f"[INFO] Estado guardado en {ruta}")

def cargar_estado_json(ruta: str = ESTADO_JSON) -> Optional[Tuple[Dict[str, Dict], Dict[str, Dict], int]]:
    try:
        with open(ruta, "r", encoding="utf-8") as f:
            estado = json.load(f)
        vuelos = estado.get("vuelos", {})
        pistas = estado.get("pistas", {})
        tiempo = int(estado.get("tiempo_simulado", 0))
        for v in vuelos.values():
            if isinstance(v.get("combustible"), str):
                v["combustible"] = convertir_float(v.get("combustible"))
            if isinstance(v.get("prioridad"), str):
                v["prioridad"] = convertir_int(v.get("prioridad"))
        for p in pistas.values():
            if isinstance(p.get("tiempoUso"), str):
                p["tiempoUso"] = convertir_int(p.get("tiempoUso"))
            if isinstance(p.get("habilitada"), str):
                p["habilitada"] = convertir_int(p.get("habilitada"))
        print(f"[INFO] Estado cargado desde {ruta}")
        return vuelos, pistas, tiempo
    except FileNotFoundError:
        print(f"[INFO] No existe {ruta}")
        return None
    except Exception as e:
        print(f"[ERROR] Error cargando estado json: {e}")
        return None

# --------------------------
# AÑADIR VUELO MANUAL
# --------------------------
def añadir_vuelo_manual(vuelos: Dict[str, Dict], tiempo_actual: int):
    idv = input("Id vuelo (ej. IB123): ").strip().upper()
    if idv == "":
        print("Id vacío. Cancelado.")
        return
    if idv in vuelos:
        print("Id ya existe.")
        return
    tipo = input("Tipo (ATERRIZAJE/DESPEGUE): ").strip().upper()
    if tipo not in TiposValidos:
        print("Tipo inválido.")
        return
    if tipo == "ATERRIZAJE":
        try:
            eta = float(input("ETA (minuto simulado): ").strip() or 0)
        except:
            eta = 0.0
        etd = None
    else:
        try:
            etd = float(input("ETD (minuto simulado): ").strip() or 0)
        except:
            etd = 0.0
        eta = None
    try:
        prioridad = int(input("Prioridad (0/1/2): ").strip() or 0)
    except:
        prioridad = 0
    combustible = None
    if tipo == "ATERRIZAJE":
        try:
            combustible = float(input("Combustible (minutos): ").strip() or 0)
        except:
            combustible = 0.0
    vuelo = {
        "id": idv,
        "tipo": tipo,
        "eta": eta,
        "etd": etd,
        "prioridad": prioridad if prioridad in PrioridadesValidas else 0,
        "combustible": combustible,
        "estado": "EN_COLA",
        "t_asignacion": None,
        "t_completado": None,
        "t_entrada_cola": tiempo_actual
    }
    vuelos[idv] = vuelo
    registrar_evento(f"[t={tiempo_actual}] EN_COLA id_vuelo={idv} tipo={tipo}")
    print(f"Vuelo {idv} añadido y en cola.")

# --------------------------
# CANCELACIÓN MANUAL
# --------------------------
def cancelar_vuelo_manual(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_actual: int):
    idv = input("Id del vuelo a cancelar: ").strip().upper()
    if idv not in vuelos:
        print("Vuelo no encontrado.")
        return
    v = vuelos[idv]
    if v["estado"] in ["COMPLETADO", "CANCELADO"]:
        print(f"Vuelo {idv} ya {v['estado']}. No se puede cancelar.")
        return
    if v["estado"] == "ASIGNADO":
        for p in pistas.values():
            if p.get("vueloAsignadoId") == idv:
                p["estadoPista"] = "LIBRE"
                p["vueloAsignadoId"] = None
                p["ocupadaHasta"] = None
                break
    v["estado"] = "CANCELADO"
    v["t_completado"] = tiempo_actual
    registrar_evento(f"[t={tiempo_actual}] CANCELADO_MANUAL id_vuelo={idv}")
    print(f"Vuelo {idv} cancelado manualmente.")

# --------------------------
# REPLAY DESDE LOG
# --------------------------
def replay_desde_log(ruta: str = LOG_EVENTOS):
    if not os.path.exists(ruta):
        print(f"No existe {ruta}")
        return
    vuelos = {}
    pistas = {}
    tiempo = 0
    with open(ruta, encoding="utf-8") as f:
        for linea in f:
            linea = linea.strip()
            if linea == "":
                continue
            tiempo_str = linea.split("]")[0].replace("[t=", "")
            try:
                tiempo = int(tiempo_str)
            except:
                continue
            if "EN_COLA" in linea:
                partes = linea.split()
                idv = [p.split("=")[1] for p in partes if p.startswith("id_vuelo=")][0]
                tipo = [p.split("=")[1] for p in partes if p.startswith("tipo=")][0]
                if idv not in vuelos:
                    vuelos[idv] = {
                        "id": idv,
                        "tipo": tipo,
                        "eta": 0 if tipo=="ATERRIZAJE" else None,
                        "etd": 0 if tipo=="DESPEGUE" else None,
                        "prioridad": 0,
                        "combustible": 10 if tipo=="ATERRIZAJE" else None,
                        "estado": "EN_COLA",
                        "t_asignacion": None,
                        "t_completado": None,
                        "t_entrada_cola": tiempo
                    }
            elif "ASIGNACION" in linea:
                partes = linea.split()
                idv = [p.split("=")[1] for p in partes if p.startswith("id_vuelo=")][0]
                idp = [p.split("=")[1] for p in partes if p.startswith("pista=")][0]
                if idv in vuelos:
                    vuelos[idv]["estado"] = "ASIGNADO"
                    vuelos[idv]["t_asignacion"] = tiempo
                pistas[idp] = {"idPista": idp, "estadoPista": "OCUPADA", "vueloAsignadoId": idv, "uso_count":1, "habilitada":1, "tiempoUso":5, "ocupadaHasta": tiempo+5, "deshabilitadaHasta":None, "categoria":"ESTANDAR"}
            elif "COMPLETADO" in linea or "CANCELADO" in linea:
                partes = linea.split()
                idv = [p.split("=")[1] for p in partes if p.startswith("id_vuelo=")][0]
                if idv in vuelos:
                    vuelos[idv]["estado"] = "COMPLETADO" if "COMPLETADO" in linea else "CANCELADO"
                    vuelos[idv]["t_completado"] = tiempo
    print(f"Replay cargado desde {ruta}. Tiempo simulado actual: {tiempo}")
    return vuelos, pistas, tiempo

# --------------------------
# MENÚ / MAIN
# --------------------------
def main():
    if os.path.exists(LOG_EVENTOS):
        os.remove(LOG_EVENTOS)

    ruta_vuelos = VUELOS_CSV_DEFECTO
    ruta_pistas = PISTAS_CSV_DEFECTO

    tiempo = 0
    vuelos = {}
    pistas = {}
    cargado = cargar_estado_json(ESTADO_JSON)
    if cargado is not None:
        votos = input("Se encontró un estado previo en 'estado.json'. ¿Cargarlo? (S/n): ").strip().lower()
        if votos in ["", "s", "si", "y", "yes"]:
            vuelos, pistas, tiempo = cargado
            for v in vuelos.values():
                if "t_entrada_cola" not in v or v["t_entrada_cola"] is None:
                    v["t_entrada_cola"] = 0
            for p in pistas.values():
                if "deshabilitadaHasta" not in p:
                    p["deshabilitadaHasta"] = None
            registrar_evento(f"[t={tiempo}] CARGA_ESTADO_DESDE_JSON vuelos={len(vuelos)} pistas={len(pistas)}")
        else:
            vuelos = importar_vuelos_csv(ruta_vuelos)
            pistas = importar_pistas_csv(ruta_pistas)
            tiempo = 0
            for v in vuelos.values():
                if v.get("t_entrada_cola") is None:
                    v["t_entrada_cola"] = 0
                    registrar_evento(f"[t=0] EN_COLA id_vuelo={v['id']} tipo={v['tipo']}")
            registrar_evento(f"[t=0] CARGA_INICIAL vuelos={len(vuelos)} pistas={len(pistas)}")
    else:
        vuelos = importar_vuelos_csv(ruta_vuelos)
        pistas = importar_pistas_csv(ruta_pistas)
        tiempo = 0
        for v in vuelos.values():
            if v.get("t_entrada_cola") is None:
                v["t_entrada_cola"] = 0
            registrar_evento(f"[t=0] EN_COLA id_vuelo={v['id']} tipo={v['tipo']}")
        registrar_evento(f"[t=0] CARGA_INICIAL vuelos={len(vuelos)} pistas={len(pistas)}")

    asignacion_automatica_pistas(pistas, vuelos, tiempo)

    while True:
        print("\n=== MENU SIMULADOR ===")
        print(f"Tiempo simulado actual: {tiempo}")
        print("1) Mostrar vuelos activos")
        print("2) Mostrar pistas")
        print("3) Mostrar colas")
        print("4) Añadir vuelo manual")
        print("5) Avanzar 1 minuto")
        print("6) Avanzar N minutos")
        print("7) Ejecutar automático (cada 5s = 1min sim) por N minutos")
        print("8) Generar informe ahora")
        print("9) Guardar estado.csv")
        print("10) Guardar estado completo (estado.json)")
        print("11) Cerrar pista temporal N minutos")
        print("12) Toggle habilitar/deshabilitar pista (manual)")
        print("13) Cargar estado desde estado.json")
        print("14) Cancelar vuelo manual")
        print("15) Replay desde eventos.log")
        print("0) Salir (genera informe y cierra)")
        opcion = input("Opcion: ").strip()
        if opcion == "1":
            mostrar_vuelos(vuelos)
        elif opcion == "2":
            mostrar_pistas(pistas)
        elif opcion == "3":
            mostrar_colas(vuelos, tiempo)
        elif opcion == "4":
            añadir_vuelo_manual(vuelos, tiempo)
        elif opcion == "5":
            tiempo = avanzar_tiempo(vuelos, pistas, tiempo, 1)
            print(f"Avanzado a t={tiempo}")
        elif opcion == "6":
            try:
                n = int(input("Cuantos minutos avanzar?: ").strip() or 0)
            except:
                n = 0
            if n > 0:
                tiempo = avanzar_tiempo(vuelos, pistas, tiempo, n)
                print(f"Avanzado a t={tiempo}")
        elif opcion == "7":
            try:
                n = int(input("Cuantos minutos simular automáticamente?: ").strip() or 0)
            except:
                n = 0
            if n > 0:
                print(f"Ejecutando modo automático por {n} minutos (1min sim = 5s reales)...")
                for i in range(n):
                    tiempo = avanzar_tiempo(vuelos, pistas, tiempo, 1)
                    time.sleep(5)
                print("Modo automático finalizado.")
        elif opcion == "8":
            generar_informe(vuelos, pistas, tiempo)
            print(f"Informe escrito en {LOG_INFORME}")
        elif opcion == "9":
            guardar_estado_csv(vuelos, pistas)
            print(f"Estado guardado en {ESTADO_CSV}")
        elif opcion == "10":
            guardar_estado_json(vuelos, pistas, tiempo)
        elif opcion == "11":
            pid = input("Id pista a cerrar temporalmente (ej. R1): ").strip().upper()
            try:
                minutos = int(input("Minutos de cierre temporal: ").strip() or 0)
            except:
                minutos = 0
            if minutos > 0:
                cerrar_pista_temporal(pistas, tiempo, pid, minutos)
            else:
                print("Minutos inválidos.")
        elif opcion == "12":
            pid = input("Id pista a toggle (ej. R1): ").strip().upper()
            if pid in pistas:
                p = pistas[pid]
                if p["habilitada"] == 1:
                    p["habilitada"] = 0
                    p["estadoPista"] = "DESHABILITADA" if p["vueloAsignadoId"] is None else "OCUPADA_DESHABILITADA"
                    p["deshabilitadaHasta"] = None
                    registrar_evento(f"[t={tiempo}] PISTA_DESHABILITADA id_pista={pid}")
                    print(f"Pista {pid} deshabilitada.")
                else:
                    p["habilitada"] = 1
                    p["estadoPista"] = "LIBRE" if p["vueloAsignadoId"] is None else "OCUPADA"
                    p["deshabilitadaHasta"] = None
                    registrar_evento(f"[t={tiempo}] PISTA_HABILITADA id_pista={pid}")
                    print(f"Pista {pid} habilitada.")
            else:
                print("Pista no encontrada.")
        elif opcion == "13":
            cargado = cargar_estado_json(ESTADO_JSON)
            if cargado is not None:
                vuelos, pistas, tiempo = cargado
                for v in vuelos.values():
                    if v.get("t_entrada_cola") is None:
                        v["t_entrada_cola"] = 0
                for p in pistas.values():
                    if "deshabilitadaHasta" not in p:
                        p["deshabilitadaHasta"] = None
                print("Estado cargado desde estado.json.")
            else:
                print("No se pudo cargar estado.json.")
        elif opcion == "14":
            cancelar_vuelo_manual(vuelos, pistas, tiempo)
        elif opcion == "15":
            resultado = replay_desde_log()
            if resultado is not None:
                vuelos, pistas, tiempo = resultado
        elif opcion == "0":
            generar_informe(vuelos, pistas, tiempo)
            print("Simulación finalizada. Informe generado.")
            break
        else:
            print("Opción inválida.")

# --------------------------
# CIERRE TEMPORAL PISTA
# --------------------------
def cerrar_pista_temporal(pistas: Dict[str, Dict], tiempo_actual: int, id_pista: str, minutos: int):
    if id_pista not in pistas:
        print("Pista no encontrada.")
        return
    p = pistas[id_pista]
    p["deshabilitadaHasta"] = tiempo_actual + minutos
    p["estadoPista"] = "DESHABILITADA" if p.get("vueloAsignadoId") is None else "OCUPADA_DESHABILITADA"
    registrar_evento(f"[t={tiempo_actual}] PISTA_CIERRE_TEMPORAL id_pista={id_pista} duracion={minutos}")
    print(f"Pista {id_pista} cerrada temporalmente por {minutos} minutos.")

# --------------------------
# ENTRY POINT
# --------------------------
if __name__ == "__main__":
    main()
