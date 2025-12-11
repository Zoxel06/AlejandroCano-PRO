# simulador_completo_final.py
import csv
import os
import time
import functools
from typing import Dict, List, Optional, Tuple

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
        "t_entrada_cola": None
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
        "tiempo_rehabilitacion": None
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
        print(f"Id:{v['id']} Tipo:{v['tipo']} ETA:{v['eta']} ETD:{v['etd']} "
              f"Pri:{v['prioridad']} Comb:{v['combustible']} Estado:{v['estado']} "
              f"t_entrada_cola:{v.get('t_entrada_cola')}")
    print("--------------\n")

def mostrar_pistas(pistas: Dict[str, Dict]):
    if not pistas:
        print("No hay pistas.")
        return
    print("\n--- PISTAS ---")
    for p in sorted(pistas.values(), key=lambda x: x["idPista"]):
        print(f"Id:{p['idPista']} Cat:{p['categoria']} Estado:{p['estadoPista']} "
              f"Uso:{p['tiempoUso']} Vuelo:{p['vueloAsignadoId']} OcupadaHasta:{p['ocupadaHasta']} "
              f"Rehabilitacion:{p['tiempo_rehabilitacion']}")
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

def _cmp_vuelos(a: Dict, b: Dict, tiempo_simulado: int) -> int:
    # Emergencias
    pa = a.get("prioridad",0)
    pb = b.get("prioridad",0)
    if pa==2 and pb!=2: return -1
    if pb==2 and pa!=2: return 1
    # Aterrizajes con menor combustible
    if a["tipo"]=="ATERRIZAJE" and b["tipo"]=="ATERRIZAJE":
        ca = a.get("combustible",float("inf"))
        cb = b.get("combustible",float("inf"))
        if ca!=cb: return -1 if ca<cb else 1
    # Mayor retraso
    def retraso(v):
        hora_prevista = v.get("eta") if v.get("tipo")=="ATERRIZAJE" else v.get("etd")
        if hora_prevista is None: return 0
        return max(0, tiempo_simulado - hora_prevista)
    ra = retraso(a)
    rb = retraso(b)
    if ra!=rb: return -1 if ra>rb else 1
    # Desempate por id
    if a["id"]<b["id"]: return -1
    if a["id"]>b["id"]: return 1
    return 0

def seleccionar_vuelo_para_asignar(lista_ids: List[str], vuelos: Dict[str, Dict], tiempo_simulado: int) -> Optional[str]:
    if not lista_ids: return None
    candidatos = [vuelos[i] for i in lista_ids if i in vuelos and vuelos[i]["estado"]=="EN_COLA"]
    if not candidatos: return None
    candidatos_sorted = sorted(candidatos, key=functools.cmp_to_key(lambda x,y: _cmp_vuelos(x,y,tiempo_simulado)))
    return candidatos_sorted[0]["id"] if candidatos_sorted else None

# --------------------------
# ASIGNACIÓN Y LIBERACIÓN
# --------------------------
def asignar_pista_directa(id_pista: str, id_vuelo: str, pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_actual: int) -> bool:
    pista = pistas.get(id_pista)
    vuelo = vuelos.get(id_vuelo)
    if pista is None or vuelo is None: return False
    if pista["habilitada"]==0 or pista["estadoPista"]=="DESHABILITADA": return False
    if pista["estadoPista"]=="OCUPADA": return False
    if vuelo["estado"] not in ["EN_COLA","ASIGNADO"]: return False
    ocupada_hasta = tiempo_actual + pista["tiempoUso"]
    pista["estadoPista"]="OCUPADA"
    pista["vueloAsignadoId"]=id_vuelo
    pista["ocupadaHasta"]=ocupada_hasta
    pista["uso_count"] = pista.get("uso_count",0)+1
    vuelo["estado"]="ASIGNADO"
    if vuelo["t_asignacion"] is None:
        vuelo["t_asignacion"]=tiempo_actual
    registrar_evento(f"[t={tiempo_actual}] ASIGNACION id_vuelo={id_vuelo} pista={id_pista} tipo={vuelo['tipo']}")
    return True

def liberar_pista(pista: Dict, pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_actual: int):
    id_pista = pista["idPista"]
    vuelo_id = pista["vueloAsignadoId"]
    pista["estadoPista"]="LIBRE"
    pista["vueloAsignadoId"]=None
    pista["ocupadaHasta"]=None
    # Habilitar si estaba cerrada temporal
    if pista.get("tiempo_rehabilitacion") is not None and tiempo_actual>=pista["tiempo_rehabilitacion"]:
        pista["habilitada"]=1
        pista["estadoPista"]="LIBRE"
        pista["tiempo_rehabilitacion"]=None
        registrar_evento(f"[t={tiempo_actual}] PISTA_HABILITADA id_pista={id_pista}")
    if vuelo_id and vuelo_id in vuelos:
        v=vuelos[vuelo_id]
        if v["estado"]!="CANCELADO":
            v["estado"]="COMPLETADO"
            v["t_completado"]=tiempo_actual
            registrar_evento(f"[t={tiempo_actual}] COMPLETADO id_vuelo={vuelo_id} pista={id_pista}")

def asignacion_automatica_pistas(pistas: Dict[str, Dict], vuelos: Dict[str, Dict], tiempo_simulado: int):
    flujoA,flujoD=actualizar_flujos(vuelos)
    candidatos_ids=flujoA+flujoD
    for id_pista in sorted(pistas.keys()):
        pista=pistas[id_pista]
        if pista["habilitada"]==0 or pista["estadoPista"]=="DESHABILITADA": continue
        if pista["estadoPista"]!="LIBRE": continue
        candidatos=[vid for vid in candidatos_ids if vid in vuelos and vuelos[vid]["estado"]=="EN_COLA"]
        if not candidatos: continue
        elegido=seleccionar_vuelo_para_asignar(candidatos,vuelos,tiempo_simulado)
        if elegido is None: continue
        ok=asignar_pista_directa(id_pista,elegido,pistas,vuelos,tiempo_simulado)
        if ok:
            if elegido in candidatos_ids: candidatos_ids.remove(elegido)
            if elegido in flujoA: flujoA.remove(elegido)
            if elegido in flujoD: flujoD.remove(elegido)

# --------------------------
# AVANZAR TIEMPO
# --------------------------
def _procesar_minuto(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_actual: int):
    # Consumo combustible, emergencia, cancelación
    vuelos_a_cancelar=[]
    for v in vuelos.values():
        if v["tipo"]=="ATERRIZAJE" and v["estado"] in ["EN_COLA","ASIGNADO"]:
            if v["combustible"] is None: continue
            v["combustible"]=max(0.0,v["combustible"]-1.0)
            if v["combustible"]<=5 and v.get("prioridad",0)<2:
                v["prioridad"]=2
                registrar_evento(f"[t={tiempo_actual}] EMERGENCIA id_vuelo={v['id']} prioridad=2 motivo=combustible<=5")
            if v["combustible"]<=0 and v["estado"]!="CANCELADO":
                vuelos_a_cancelar.append(v["id"])
    for vid in vuelos_a_cancelar:
        v=vuelos.get(vid)
        if v is None: continue
        if v["estado"]=="ASIGNADO":
            for p in pistas.values():
                if p.get("vueloAsignadoId")==vid:
                    p["estadoPista"]="LIBRE"
                    p["vueloAsignadoId"]=None
                    p["ocupadaHasta"]=None
                    break
        v["estado"]="CANCELADO"
        v["t_completado"]=tiempo_actual
        registrar_evento(f"[t={tiempo_actual}] CANCELADO id_vuelo={vid} motivo=sin_combustible")
    # Liberar pistas
    for p in pistas.values():
        if p["estadoPista"]=="OCUPADA" and p["ocupadaHasta"] is not None and tiempo_actual>=p["ocupadaHasta"]:
            liberar_pista(p,pistas,vuelos,tiempo_actual)
    # Asignación automática
    asignacion_automatica_pistas(pistas,vuelos,tiempo_actual)

def avanzar_tiempo(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_actual: int, minutos: int) -> int:
    for _ in range(minutos):
        tiempo_actual+=1
        _procesar_minuto(vuelos,pistas,tiempo_actual)
    return tiempo_actual

# --------------------------
# CIERRE TEMPORAL PISTA
# --------------------------
def cerrar_pista_temporal(pistas: Dict[str, Dict], id_pista: str, duracion: int, tiempo_actual: int):
    pista=pistas.get(id_pista)
    if pista is None: return False
    pista["tiempo_rehabilitacion"]=tiempo_actual+duracion
    pista["habilitada"]=0
    pista["estadoPista"]="DESHABILITADA"
    registrar_evento(f"[t={tiempo_actual}] PISTA_CIERRE_TEMPORAL id_pista={id_pista} duracion={duracion}")
    return True

# --------------------------
# GENERAR INFORME
# --------------------------
def generar_informe(vuelos: Dict[str, Dict], pistas: Dict[str, Dict], tiempo_simulado: int):
    total_atendidos=sum(1 for v in vuelos.values() if v["estado"]=="COMPLETADO")
    emergencias=sum(1 for v in vuelos.values() if v.get("prioridad")==2 and v.get("t_completado") is not None)
    esperas=[]
    detalle=[]
    for v in vuelos.values():
        if v.get("t_asignacion") is not None:
            prevista=v["eta"] if v["tipo"]=="ATERRIZAJE" else v["etd"]
            entrada=v.get("t_entrada_cola") or 0
            base=max(prevista or 0, entrada)
            espera=v["t_asignacion"]-base
            esperas.append(max(0,espera))
        if v.get("t_asignacion") is not None or v.get("t_completado") is not None:
            detalle.append(v)
    tiempo_medio_espera=(sum(esperas)/len(esperas)) if esperas else 0.0
    uso_pistas={p['idPista']:p['uso_count'] for p in pistas.values()}

    with open(LOG_INFORME,"w",encoding="utf-8") as f:
        f.write("RESUMEN\n")
        f.write(f"- Tiempo simulado (min): {tiempo_simulado}\n")
        f.write(f"- Vuelos atendidos: {total_atendidos}\n")
        f.write(f"- Tiempo medio de espera (min): {tiempo_medio_espera:.2f}\n")
        f.write("- Uso de pistas: " + ", ".join(f"{k}={v}" for k,v in uso_pistas.items()) + "\n")
        f.write(f"- Emergencias gestionadas: {emergencias}\n")
        f.write("\nDETALLE VUELOS\n")
        f.write("id,tipo,prioridad,estado,eta,etd,combustible,t_asignacion,t_completado\n")
        for v in vuelos.values():
            f.write(f"{v['id']},{v['tipo']},{v['prioridad']},{v['estado']},{v['eta']},{v['etd']},{v['combustible']},{v.get('t_asignacion')},{v.get('t_completado')}\n")
    print(f"[INFO] Informe generado en {LOG_INFORME}")

# --------------------------
# GUARDAR ESTADO CSV
# --------------------------
def guardar_estado_csv(vuelos: Dict[str, Dict], pistas: Dict[str, Dict]):
    with open(ESTADO_CSV,"w",newline='',encoding='utf-8') as f:
        campos_vuelos=["id","tipo","eta","etd","prioridad","combustible","estado","t_asignacion","t_completado"]
        escritor=csv.DictWriter(f,fieldnames=campos_vuelos)
        escritor.writeheader()
        for v in vuelos.values():
            escritor.writerow({k:v.get(k) for k in campos_vuelos})
        # opcional: guardar estado de pistas si se desea
    print(f"[INFO] Estado guardado en {ESTADO_CSV}")
