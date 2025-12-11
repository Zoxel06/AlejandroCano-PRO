# simulador_completo.py
import csv
import os
import time
from typing import Dict, Optional, Tuple, List

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

# --------------------------
# UTILIDADES
# --------------------------
def registrar_evento(linea: str):
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
    idv = str(fila.get("id", "") or fila.get("id_vuelo", "")).strip()
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

    # campos extra para trazabilidad
    return {
        "id": idv,
        "tipo": tipo,
        "eta": eta,
        "etd": etd,
        "prioridad": prioridad,
        "combustible": combustible,
        "estado": estado,
        # tracking
        "t_asignacion": None,
        "t_completado": None,
        "t_entrada_cola": None
    }

def validar_normalizar_pista(fila: Dict) -> Optional[Dict]:
    idp = str(fila.get("id_pista", "") or fila.get("id", "")).strip()
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
        "uso_count": 0  # para informe
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
                # marcar tiempo de entrada en cola 0 por defecto (se actualizará al iniciar)
                v["t_entrada_cola"] = 0
                vuelos[v["id"]] = v
    except FileNotFoundError:
        print(f"[ERROR] No se encontró {ruta}.")
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
        print(f"[ERROR] No se encontró {ruta}.")
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
        print(f"Id:{v['id']} Tipo:{v['tipo']} ETA:{v['eta']} ETD:{v['etd']} Pri:{v['prioridad']} Comb:{v['combustible']} Estado:{v['estado']}")
    print("--------------\n")

def mostrar_pistas(pistas: Dict[str, Dict]):
    if not pistas:
        print("No hay pistas.")
        return
    print("\n--- PISTAS ---")
    for p in sorted(pistas.values(), key=lambda x: x["idPista"]):
        print(f"Id:{p['idPista']} Cat:{p['categoria']} Estado:{p['estadoPista']} Uso:{p['tiempoUso']} Vuelo:{p['vueloAsignadoId']} OcupadaHasta:{p['ocupadaHasta']}")
    print("--------------\n")

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

def seleccionar_vuelo_para_asignar(lista_ids: List[str], vuelos: Dict[str, Dict], tiempo_simulado: int) -> Optional[str]:
    if not lista_ids:
        return None
    # Construir objetos candidatos
    candidatos = [vuelos[i] for i in lista_ids if i in vuelos]
    # Tiebreak alfabético primero (estable)
    candidatos.sort(key=lambda v: v["id"])
    def clave(v):
        prioridad = v.get("prioridad", 0)
        es_aterrizaje = 1 if v.get("tipo") == "ATERRIZAJE" else 0
        combustible = v.get("combustible")
        if combustible is None:
            combustible_key = -10**9
        else:
            combustible_key = -combustible  # menor combustible => mayor prioridad
        hora_prevista = v.get("eta") if v.get("tipo") == "ATERRIZAJE" else v.get("etd")
        retraso = max(0, tiempo_simulado - hora_prevista) if hora_prevista is not None else 0
        return (prioridad, es_aterrizaje, combustible_key, retraso)
    candidatos.sort(key=clave, reverse=True)
    return candidatos[0]["id"] if candidatos else None

# --------------------------
# ASIGNACIÓN Y LIBERACIÓN
# --------------------------
def asignar_pista_directa(id_pista: str, id_vuelo: str, pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_actual: int) -> bool:
    pista = pistas.get(id_pista)
    vuelo = vuelos.get(id_vuelo)
    if pista is None or vuelo is None:
        return False
    if pista["habilitada"] == 0 or pista["estadoPista"] == "DESHABILITADA":
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

def asignacion_automatica_pistas(pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_simulado: int):
    flujoAterrizaje, flujoDespegue = actualizar_flujos(vuelos)
    candidatos_ids = flujoAterrizaje + flujoDespegue
    for id_pista in sorted(pistas.keys()):
        pista = pistas[id_pista]
        if pista["habilitada"] == 0 or pista["estadoPista"] == "DESHABILITADA":
            continue
        if pista["estadoPista"] != "LIBRE":
            continue
        # recalcular candidatos válidos
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
# AVANZAR TIEMPO (SIMULACIÓN)
# --------------------------
def avanzar_tiempo(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_actual: int, minutos: int) -> int:
    nuevo_tiempo = tiempo_actual + minutos
    # 1) Reducir combustible y detectar emergencias/cancelaciones
    for v in vuelos.values():
        if v["tipo"] == "ATERRIZAJE" and v["estado"] in ["EN_COLA", "ASIGNADO"]:
            # si combustible es None -> se ignora (no aplicable)
            if v["combustible"] is None:
                continue
            v["combustible"] = max(0.0, v["combustible"] - minutos)
            # emergencia si combustible <=5 y prioridad < 2
            if v["combustible"] <= 5 and v.get("prioridad", 0) < 2:
                v["prioridad"] = 2
                registrar_evento(f"[t={tiempo_actual}] EMERGENCIA id_vuelo={v['id']} prioridad=2 motivo=combustible<=5")
            # cancelado si combustible == 0
            if v["combustible"] <= 0 and v["estado"] != "CANCELADO":
                v["estado"] = "CANCELADO"
                v["t_completado"] = nuevo_tiempo
                registrar_evento(f"[t={nuevo_tiempo}] CANCELADO id_vuelo={v['id']} motivo=sin_combustible")

    # 2) Liberar pistas cuyo ocupadaHasta <= nuevo_tiempo
    pistas_liberadas = []
    for p in pistas.values():
        if p["estadoPista"] == "OCUPADA" and p["ocupadaHasta"] is not None and nuevo_tiempo >= p["ocupadaHasta"]:
            vuelo_id = p["vueloAsignadoId"]
            p["estadoPista"] = "LIBRE"
            p["vueloAsignadoId"] = None
            p["ocupadaHasta"] = None
            pistas_liberadas.append((p["idPista"], vuelo_id))

    # 3) Cuando una pista se libera marcamos el vuelo como COMPLETADO (si no estaba CANCELADO)
    for id_pista, vuelo_id in pistas_liberadas:
        if vuelo_id and vuelo_id in vuelos:
            v = vuelos[vuelo_id]
            if v["estado"] != "CANCELADO":
                v["estado"] = "COMPLETADO"
                v["t_completado"] = nuevo_tiempo
                registrar_evento(f"[t={nuevo_tiempo}] COMPLETADO id_vuelo={vuelo_id} pista={id_pista}")

    # 4) Asignaciones automáticas tras liberar pistas
    asignacion_automatica_pistas(pistas, vuelos, nuevo_tiempo)

    return nuevo_tiempo

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
            espera = v["t_asignacion"] - (v["eta"] if v["tipo"] == "ATERRIZAJE" else v["etd"])
            esperas.append(max(0, espera))
        if v.get("t_asignacion") is not None or v.get("t_completado") is not None:
            detalle.append(v)

    tiempo_medio_espera = (sum(esperas) / len(esperas)) if esperas else 0.0

    uso_pistas = {p['idPista']: p.get("uso_count", 0) for p in pistas.values()}

    # Escribir informe
    with open(LOG_INFORME, "w", encoding="utf-8") as f:
        f.write("RESUMEN\n")
        f.write(f"- Tiempo simulado (min): {tiempo_simulado}\n")
        f.write(f"- Vuelos atendidos: {total_atendidos}\n")
        f.write(f"- Tiempo medio de espera (min): {tiempo_medio_espera:.2f}\n")
        f.write("- Uso de pistas: " + ", ".join(f"{k}={v}" for k, v in uso_pistas.items()) + "\n")
        f.write(f"- Emergencias gestionadas: {emergencias}\n")
        f.write("- Detalle de vuelos completados:\n")
        for v in sorted(vuelos.values(), key=lambda x: x["t_completado"] or 10**9):
            if v["estado"] == "COMPLETADO":
                f.write(f"• {v['id']} ({v['tipo']}) t_inicio={v['t_asignacion']} t_fin={v['t_completado']}\n")

    # registrar fin de simulacion en eventos.log
    registrar_evento(f"[t={tiempo_simulado}] FIN_SIMULACION vuelos_atendidos={total_atendidos}")

# --------------------------
# GUARDAR ESTADO CSV
# --------------------------
def guardar_estado_csv(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], ruta: str = ESTADO_CSV):
    with open(ruta, "w", newline="", encoding="utf-8") as f:
        w = csv.writer(f)
        w.writerow(["tipo", "id", "estado", "t_asignacion", "t_completado"])
        for v in vuelos.values():
            w.writerow(["vuelo", v["id"], v["estado"], v["t_asignacion"], v["t_completado"]])
        for p in pistas.values():
            w.writerow(["pista", p["idPista"], p["estadoPista"], p["ocupadaHasta"], ""])

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
        eta = float(input("ETA (minuto simulado): ").strip() or 0)
        etd = None
    else:
        etd = float(input("ETD (minuto simulado): ").strip() or 0)
        eta = None
    prioridad = int(input("Prioridad (0/1/2): ").strip() or 0)
    combustible = None
    if tipo == "ATERRIZAJE":
        combustible = float(input("Combustible (minutos): ").strip() or 0)
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
# MENU / MAIN
# --------------------------
def main():
    # limpiar log eventos al iniciar (opcional)
    if os.path.exists(LOG_EVENTOS):
        os.remove(LOG_EVENTOS)

    ruta_vuelos = "vuelos.csv"
    ruta_pistas = "pistas.csv"

    vuelos = importar_vuelos_csv(ruta_vuelos)
    pistas = importar_pistas_csv(ruta_pistas)

    tiempo = 0
    # registrar carga inicial y marcar t_entrada_cola=0 para todos
    for v in vuelos.values():
        v["t_entrada_cola"] = 0
        registrar_evento(f"[t=0] EN_COLA id_vuelo={v['id']} tipo={v['tipo']}")
    registrar_evento(f"[t=0] CARGA_INICIAL vuelos={len(vuelos)} pistas={len(pistas)}")

    # intentar asignaciones iniciales
    asignacion_automatica_pistas(pistas, vuelos, tiempo)

    while True:
        print("\n=== MENU SIMULADOR ===")
        print(f"Tiempo simulado actual: {tiempo}")
        print("1) Mostrar vuelos activos")
        print("2) Mostrar pistas")
        print("3) Añadir vuelo manual")
        print("4) Avanzar 1 minuto")
        print("5) Avanzar N minutos")
        print("6) Ejecutar automático (cada 5s = 1min sim) por N minutos")
        print("7) Generar informe ahora")
        print("8) Guardar estado.csv")
        print("0) Salir (genera informe y cierra)")
        opcion = input("Opcion: ").strip()

        if opcion == "1":
            mostrar_vuelos(vuelos)
        elif opcion == "2":
            mostrar_pistas(pistas)
        elif opcion == "3":
            añadir_vuelo_manual(vuelos, tiempo)
        elif opcion == "4":
            tiempo = avanzar_tiempo(vuelos, pistas, tiempo, 1)
        elif opcion == "5":
            n = int(input("Cuantos minutos avanzar?: ").strip() or 0)
            if n > 0:
                tiempo = avanzar_tiempo(vuelos, pistas, tiempo, n)
        elif opcion == "6":
            n = int(input("Cuantos minutos simular automáticamente?: ").strip() or 0)
            if n > 0:
                print(f"Ejecutando modo automático por {n} minutos (1min sim = 5s reales)...")
                for _ in range(n):
                    tiempo = avanzar_tiempo(vuelos, pistas, tiempo, 1)
                    time.sleep(5)
                print("Modo automático finalizado.")
        elif opcion == "7":
            generar_informe(vuelos, pistas, tiempo)
            print(f"Informe escrito en {LOG_INFORME}")
        elif opcion == "8":
            guardar_estado_csv(vuelos, pistas)
            print(f"Estado guardado en {ESTADO_CSV}")
        elif opcion == "0":
            generar_informe(vuelos, pistas, tiempo)
            print("Simulación finalizada y informe generado.")
            break
        else:
            print("Opción no válida.")

if __name__ == "__main__":
    main()
