import csv
from typing import Dict, Optional

# ==========================
# Valores
# ==========================

TiposValidos = ["ATERRIZAJE", "DESPEGUE"]
PrioridadesValidas = [0, 1, 2]
EstadosValidos = ["EN_COLA", "ASIGNADO", "COMPLETADO", "CANCELADO"]

CategoriasPistaValidas = ["CORTA", "ESTANDAR", "LARGA"]
EstadosPistaPosibles = ["LIBRE", "OCUPADA", "DESHABILITADA"]

# ==========================
# funciones para evitar posibles errores al comparar
# ==========================

#Esto convierte los campos del CSV correspondientes a float sin espacios
def ConvertirFloat(valor: str) -> Optional[float]:
    if valor is None:
        return None
    valor = valor.strip()
    if valor == "" or valor.lower() == "nan":
        return None
    return float(valor)

#Esto convierte los campos del CSV correspondientes a int sin espacios
def ConvertirInt(valor: str) -> int:
    if valor is None:
        return 0
    valor = valor.strip()
    if valor == "" or valor.lower() == "nan":
        return 0
    return int(float(valor))

#Esto convierte los campos del CSV correspondientes a String en mayuscula y sin espacios
def NormalizarCadena(cadena: str) -> str:
    if cadena is None:
        return ""
    return cadena.strip().upper()

# ==========================
# VUELOS
# ==========================

def ValidarYNormalizarVuelo(fila: Dict) -> Optional[Dict]:
    idVuelo = fila.get("id", "").strip() or fila.get("id_vuelo", "").strip()  
    if idVuelo == "":                                          #.strip elimina posibles espacio
        print("[ADVERTENCIA] Vuelo sin id, se ignora.")        #al final de los textos
        return None

#esto obtiene el tipo de vuelo, pone el texto de manera que no tenga espacios
#comprueba que sea un tipo permitido y si no lo es, lo avisa con una alerta
    tipo = NormalizarCadena(fila.get("tipo", ""))
    if tipo not in TiposValidos:
        print(f"[ADVERTENCIA] Tipo invalido en vuelo {idVuelo}: {tipo}. Se ignora.")
        return None

#convierte los valores del diccioneario fila para poder calcular con ellos
    eta = ConvertirFloat(fila.get("eta", ""))
    etd = ConvertirFloat(fila.get("etd", ""))

#lee la proriedad, la convierte a numeero, si no es valida, la cambia a 0
    prioridad = ConvertirInt(fila.get("prioridad", "0"))
    if prioridad not in PrioridadesValidas:
        print(f"[ADVERTENCIA] Prioridad invalida en vuelo {idVuelo}: {prioridad}. Se pone 0.")
        prioridad = 0

    combustible = ConvertirFloat(fila.get("combustible", ""))
    if tipo == "ATERRIZAJE":
        if combustible is None:
            print(f"[ADVERTENCIA] Vuelo {idVuelo} de ATERRIZAJE sin combustible. Se pone 0.")
            combustible = 0
    else:
        combustible = None

#esto impide que el programa falle por datos incorrectos
    estado = NormalizarCadena(fila.get("estado", "EN_COLA"))
    if estado == "":
        estado = "EN_COLA"
    if estado not in EstadosValidos:
        print(f"[ADVERTENCIA] Estado invalido en vuelo {idVuelo}: {estado}. Se pone EN_COLA.")
        estado = "EN_COLA"

    if tipo == "ATERRIZAJE" and eta is None:
        print(f"[ADVERTENCIA] Vuelo {idVuelo} ATERRIZAJE sin ETA. Se pone 0.")
        eta = 0.0
    if tipo == "DESPEGUE" and etd is None:
        print(f"[ADVERTENCIA] Vuelo {idVuelo} DESPEGUE sin ETD. Se pone 0.")
        etd = 0.0

   
   
   
   
   
   #crea el objeto vuelo con sus variables correspondientes
    vuelo = {
        "id": idVuelo,
        "tipo": tipo,
        "eta": eta,
        "etd": etd,
        "prioridad": prioridad,
        "combustible": combustible,
        "estado": estado
    }

    return vuelo

# ==========================
# PISTAS
# ==========================

#igual que con los vuelos ---> para evitar errores, etc...
def ValidarYNormalizarPista(fila: Dict) -> Optional[Dict]:
    idPista = fila.get("id_pista", "").strip() or fila.get("id", "").strip()
    if idPista == "":
        print("[ADVERTENCIA] Pista sin id, se ignora.")
        return None

    categoria = NormalizarCadena(fila.get("categoria", ""))
    if categoria not in CategoriasPistaValidas:
        print(f"[ADVERTENCIA] Categoria invalida en pista {idPista}: {categoria}. Se pone ESTANDAR.")
        categoria = "ESTANDAR"

    tiempoUso = ConvertirInt(fila.get("tiempo_uso", "0"))

    habilitadaNumero = ConvertirInt(fila.get("habilitada", "1"))
    habilitada = 1 if habilitadaNumero != 0 else 0

    if habilitada == 0:
        estadoPista = "DESHABILITADA"
    else:
        estadoPista = "LIBRE"

#creacion del objeto pista
    pista = {
        "idPista": idPista,
        "categoria": categoria,
        "tiempoUso": tiempoUso,
        "habilitada": habilitada,
        "estadoPista": estadoPista,
        "vueloAsignadoId": None,
        "ocupadaHasta": None
    }

    return pista

# ==========================
# IMPORTACION CSV
# ==========================

#Funcion para importar vuelos desde el archivo csv
def ImportarVuelosCSV(rutaCSV: str) -> Dict[str, Dict]:
    vuelosActivos: Dict[str, Dict] = {}

    try:
        with open(rutaCSV, "r") as archivo:
            lector = csv.DictReader(archivo)

            for fila in lector:
                vuelo = ValidarYNormalizarVuelo(fila)
                if vuelo is None:
                    continue

                idVuelo = vuelo["id"]
                if idVuelo in vuelosActivos:
                    print(f"[ADVERTENCIA] Id de vuelo duplicado ({idVuelo}), se ignora el duplicado.")
                    continue

                vuelosActivos[idVuelo] = vuelo

        print(f"[OK] Cargados {len(vuelosActivos)} vuelos desde {rutaCSV}.")

    except FileNotFoundError:
        print(f"[ERROR] No se encontro el fichero {rutaCSV}.")
    except Exception as error:
        print(f"[ERROR] Error leyendo {rutaCSV}: {error}")

    return vuelosActivos

# Funcion para importar pistas desde el archivo csv
def ImportarPistasCSV(rutaCSV: str) -> Dict[str, Dict]:
    pistasActivas: Dict[str, Dict] = {}

    try:
        with open(rutaCSV, "r") as archivo:
            lector = csv.DictReader(archivo)

            for fila in lector:
                pista = ValidarYNormalizarPista(fila)
                if pista is None:
                    continue

                idPista = pista["idPista"]
                if idPista in pistasActivas:
                    print(f"[ADVERTENCIA] Id de pista duplicado ({idPista}), se ignora el duplicado.")
                    continue

                pistasActivas[idPista] = pista

        print(f"[OK] Cargadas {len(pistasActivas)} pistas desde {rutaCSV}.")

    except FileNotFoundError:
        print(f"[ERROR] No se encontro el fichero {rutaCSV}.")
    except Exception as error:
        print(f"[ERROR] Error leyendo {rutaCSV}: {error}")

    return pistasActivas

# ==========================
# VISUALIZACION VUELOS
# ==========================

def MostrarVuelos(vuelosActivos: Dict[str, Dict]) -> None:
    if not vuelosActivos:
        print("No hay vuelos activos.")
        return

    print("\n--- LISTA DE VUELOS ACTIVOS ---")
    for vuelo in vuelosActivos.values():
        print(
            f"Id: {vuelo['id']} | Tipo: {vuelo['tipo']} | "
            f"ETA: {vuelo['eta']} | ETD: {vuelo['etd']} | "
            f"Prioridad: {vuelo['prioridad']} | Combustible: {vuelo['combustible']} | "
            f"Estado: {vuelo['estado']}"
        )
    print("--------------------------------\n")

# ==========================
# VISUALIZACION PISTAS
# ==========================

def MostrarPistas(pistasActivas: Dict[str, Dict]) -> None:
    if not pistasActivas:
        print("No hay pistas cargadas.")
        return

    print("\n--- LISTA DE PISTAS ---")
    for pista in pistasActivas.values():
        print(
            f"IdPista: {pista['idPista']} | Categoria: {pista['categoria']} | "
            f"TiempoUso: {pista['tiempoUso']} | Habilitada: {pista['habilitada']} | "
            f"EstadoPista: {pista['estadoPista']} | VueloAsignadoId: {pista['vueloAsignadoId']} | "
            f"OcupadaHasta: {pista['ocupadaHasta']}"
        )
    print("------------------------\n")

# ==========================
# ASIGNACION DE PISTAS
# ==========================

def AsignarPistaAVuelo(pistasActivas: Dict[str, Dict], vuelosActivos: Dict[str, Dict], tiempoActual: int) -> None:
    idVuelo = input("Id del vuelo a asignar: ").strip()

    #Restricciones y comprobaciones
    #Comprobacion de la existencia del vuelo
    if idVuelo not in vuelosActivos:
        print("No existe un vuelo con ese id.")
        return

    vuelo = vuelosActivos[idVuelo]

    #Comprobacion del estado del vuelo
    if vuelo["estado"] not in ["EN_COLA", "ASIGNADO"]:
        print("El vuelo no esta en un estado asignable.")
        return

    idPista = input("Id de la pista: ").strip()

    #Comprobacion de la existencia de la pista
    if idPista not in pistasActivas:
        print("No existe una pista con ese id.")
        return

    pista = pistasActivas[idPista]

    #Comprobacion del estado de la pista
    if pista["habilitada"] == 0 or pista["estadoPista"] == "DESHABILITADA":
        print("La pista esta deshabilitada.")
        return

    #Comprobacion de si la pista esta ocupada
    if pista["estadoPista"] == "OCUPADA":
        print("La pista esta ocupada.")
        return

    ocupadaHasta = tiempoActual + pista["tiempoUso"]

    pista["estadoPista"] = "OCUPADA"
    pista["vueloAsignadoId"] = idVuelo
    pista["ocupadaHasta"] = ocupadaHasta

    vuelo["estado"] = "ASIGNADO"

    print(f"Pista {idPista} asignada al vuelo {idVuelo} hasta el minuto {ocupadaHasta}.")

# ==========================
# SIMULACION
# ==========================

def AvanzarTiempo(vuelosActivos: Dict[str, Dict], pistasActivas: Dict[str, Dict], tiempoActual: int, minutos: int) -> int:
    nuevoTiempo = tiempoActual + minutos
    print(f"\n[SIMULACION] Avanzando tiempo de {tiempoActual} a {nuevoTiempo}...\n")

    # Actualizar vuelos segun su estado
    for vuelo in vuelosActivos.values():
        if vuelo["tipo"] == "ATERRIZAJE" and vuelo["estado"] in ["EN_COLA", "ASIGNADO"]:
            if vuelo["combustible"] is not None:
                vuelo["combustible"] -= minutos
                if vuelo["combustible"] <= 0:
                    vuelo["combustible"] = 0
                    vuelo["estado"] = "CANCELADO"
                    print(f"[AVISO] Vuelo {vuelo['id']} se queda sin combustible -> CANCELADO.")

        if vuelo["tipo"] == "ATERRIZAJE":
            if vuelo["estado"] == "EN_COLA" and vuelo["eta"] is not None and nuevoTiempo >= vuelo["eta"]:
                vuelo["estado"] = "COMPLETADO"
                print(f"[INFO] Vuelo {vuelo['id']} ha aterrizado -> COMPLETADO.")
        else:
            if vuelo["estado"] == "EN_COLA" and vuelo["etd"] is not None and nuevoTiempo >= vuelo["etd"]:
                vuelo["estado"] = "COMPLETADO"
                print(f"[INFO] Vuelo {vuelo['id']} ha despegado -> COMPLETADO.")

    # Actualizar pistas segun su ocupacion
    for pista in pistasActivas.values():
        if pista["estadoPista"] == "OCUPADA" and pista["ocupadaHasta"] is not None and nuevoTiempo >= pista["ocupadaHasta"]:
            print(f"[INFO] Pista {pista['idPista']} vuelve a estar LIBRE.")
            pista["estadoPista"] = "LIBRE"
            pista["vueloAsignadoId"] = None
            pista["ocupadaHasta"] = None

    return nuevoTiempo

# ==========================
# FLUJOS Y ASIGNACION AUTOMATICA
# ==========================

def ActualizarFlujos(vuelosActivos: Dict[str, Dict]):
    """
    Construye y devuelve dos listas (flujoAterrizaje, flujoDespegue)
    con los ids de los vuelos que están en EN_COLA y pendientes de ser asignados.
    - Incluimos solo vuelos con estado 'EN_COLA' porque son los que esperan turno.
    - Esto permite evaluar la política de selección sobre los que realmente esperan.
    """
    flujoAterrizaje = []
    flujoDespegue = []

    for idVuelo, vuelo in vuelosActivos.items():
        # Solo consideramos aquellos que están en cola esperando (no ASIGNADOS ni COMPLETADOS)
        if vuelo["estado"] != "EN_COLA":
            continue

        if vuelo["tipo"] == "ATERRIZAJE":
            flujoAterrizaje.append(idVuelo)
        elif vuelo["tipo"] == "DESPEGUE":
            flujoDespegue.append(idVuelo)
        # Si hubiera otros tipos, se ignorarían

    return flujoAterrizaje, flujoDespegue

def SeleccionarVueloParaAsignar(listaIds: list, vuelosActivos: Dict[str, Dict], tiempoSimulado: int):
    """
    Dada una lista de ids candidatos (mezcla de aterrizaje y despegue) devuelve
    el id del vuelo que debe ser atendido primero según la política:
      1) prioridad == 2 (emergencias)
      2) aterrizajes con menor combustible
      3) mayor retraso respecto a ETA/ETD (si ya superado)
      4) desempate alfabético (id menor)
    Se usa una ordenación estable en dos pasos para respetar el desempate alfabético.
    Devuelve None si la lista está vacía.
    """
    if not listaIds:
        return None

    # Construimos lista de objetos (para facilitar acceso a campos)
    candidatos = [vuelosActivos[idv] for idv in listaIds if idv in vuelosActivos]

    # Paso 1: ordenar por id ascendente (tiebreak alfabético). Usamos sort estable.
    candidatos.sort(key=lambda v: v["id"])

    # Paso 2: ordenar por criterios de prioridad (estable, reverse=True para poner lo mejor al principio)
    # Definimos claves:
    # - prioridad (mayor mejor)
    # - esAterrizajeConCombustible: para poner antes aterrizajes con poco combustible
    # - combustibleOrLarge: usamos -combustible para que menos combustible sea mayor en la ordenación
    # - retraso: mayor retraso primero
    def clavePrioridad(v):
        # prioridad numérica (0/1/2). mayor = más urgente
        prioridad = v.get("prioridad", 0)

        # indicador de aterrizaje (1 si aterrizaje y combustible aplicable, 0 si no)
        esAterrizaje = 1 if v.get("tipo") == "ATERRIZAJE" else 0

        # combustible: si es None (despegue) ponemos un valor grande para que no compita con aterrizajes
        combustible = v.get("combustible")
        if combustible is None:
            combustibleForSort = 1_000_000  # muy alto -> no priorizar por combustible
            combustibleKey = -1_000_000     # -1_000_000 para que este valor sea muy bajo en -combustible
        else:
            combustibleForSort = combustible
            combustibleKey = -combustible   # menor combustible => mayor valor al ordenar reverse=True

        # retraso: cuánto ha pasado desde ETA/ETD (solo si el tiempo ya lo ha superado)
        tiempoPrevisto = v.get("eta") if v.get("tipo") == "ATERRIZAJE" else v.get("etd")
        if tiempoPrevisto is None:
            retraso = 0
        else:
            retraso = max(0, tiempoSimulado - tiempoPrevisto)  # 0 si no está retrasado

        # Construimos la tupla de ordenación
        # ordenamos por: prioridad, (si es aterrizaje y combustible), -combustible (menos combustible = mayor),
        # retraso (mayor mejor)
        return (prioridad, esAterrizaje, combustibleKey, retraso)

    # Orden estable: ya tenemos id ascendente; ahora ordenamos por la clave de prioridad
    candidatos.sort(key=clavePrioridad, reverse=True)

    # Primero de la lista es el candidato elegido
    if candidatos:
        return candidatos[0]["id"]
    return None

def AsignarPistaDirecta(idPista: str, idVuelo: str, pistasActivas: Dict[str, Dict], vuelosActivos: Dict[str, Dict], tiempoActual: int):
    """
    Asigna la pista `idPista` al vuelo `idVuelo` sin pedir input por consola.
    Actualiza pista y vuelo (estado, ocupadaHasta, vueloAsignadoId).
    Usa la semántica ya definida en el proyecto.
    """
    pista = pistasActivas.get(idPista)
    vuelo = vuelosActivos.get(idVuelo)
    if pista is None or vuelo is None:
        return False  # no existe

    # comprobaciones de seguridad (por si se llama desde otra parte)
    if pista["habilitada"] == 0 or pista["estadoPista"] == "DESHABILITADA":
        return False
    if pista["estadoPista"] == "OCUPADA":
        return False
    if vuelo["estado"] not in ["EN_COLA", "ASIGNADO"]:
        return False

    ocupadaHasta = tiempoActual + pista["tiempoUso"]
    pista["estadoPista"] = "OCUPADA"
    pista["vueloAsignadoId"] = idVuelo
    pista["ocupadaHasta"] = ocupadaHasta

    vuelo["estado"] = "ASIGNADO"

    # Mensaje informativo (igual que AsignarPistaAVuelo)
    print(f"[AUTO] Pista {idPista} asignada AUTOMÁTICAMENTE al vuelo {idVuelo} hasta el minuto {ocupadaHasta}.")
    return True

def AsignacionAutomaticaPistas(pistasActivas: Dict[str, Dict], vuelosActivos: Dict[str, Dict], tiempoSimulado: int):
    """
    Revisa todas las pistas y asigna automáticamente vuelos en cola a las pistas libres.
    - Construye los flujos de espera (solo EN_COLA).
    - Para cada pista LIBRE y habilitada intenta seleccionar el mejor vuelo según la política.
    - Asigna iterativamente: cada vez que se asigna un vuelo, lo eliminamos de los flujos para no reasignarlo.
    """
    # 1) construir flujos
    flujoAterrizaje, flujoDespegue = ActualizarFlujos(vuelosActivos)

    # para facilitar selección sobre *todos* los candidatos, mezclamos las dos listas
    candidatosIds = flujoAterrizaje + flujoDespegue

    # 2) recorrer pistas (orden fijo por id para determinismo)
    #    usamos sorted sobre las claves para tener un orden predecible
    for idPista in sorted(pistasActivas.keys()):
        pista = pistasActivas[idPista]

        # solo intentamos asignar si la pista está habilitada y LIBRE
        if pista["habilitada"] == 0 or pista["estadoPista"] == "DESHABILITADA":
            continue
        if pista["estadoPista"] != "LIBRE":
            continue

        # Recalcular candidatos válidos (los que aún estén en estado EN_COLA)
        candidatos = [vid for vid in candidatosIds if vid in vuelosActivos and vuelosActivos[vid]["estado"] == "EN_COLA"]

        if not candidatos:
            # no hay vuelos en cola -> nada que asignar
            continue

        # Seleccionar mejor candidato según política
        elegido = SeleccionarVueloParaAsignar(candidatos, vuelosActivos, tiempoSimulado)
        if elegido is None:
            continue

        # Asignar la pista al vuelo elegido
        correcto = AsignarPistaDirecta(idPista, elegido, pistasActivas, vuelosActivos, tiempoSimulado)
        if correcto:
            # si se asignó, hay que quitarlo de la lista de candidatos para no reasignarlo a otra pista
            if elegido in candidatosIds:
                candidatosIds.remove(elegido)
            # también quitar de flujoAterrizaje/flujoDespegue si estuviera
            if elegido in flujoAterrizaje:
                flujoAterrizaje.remove(elegido)
            if elegido in flujoDespegue:
                flujoDespegue.remove(elegido)

    # fin de asignaciones automáticas
    return

# ==========================
# FUNCION PRINCIPAL
# ==========================

def Main():
    #Guardamos los csv en variables
    rutaCSVvuelos = "vuelos.csv"
    rutaCSVpistas = "pistas.csv"

    #Usamos las funciones para importar los csv guardados
    vuelosActivos = ImportarVuelosCSV(rutaCSVvuelos)
    pistasActivas = ImportarPistasCSV(rutaCSVpistas)
    tiempoSimulado = 0

    #Menu interactivo
    while True:
        print("=== MENU SIMULADOR ===")
        print(f"Tiempo simulado actual: {tiempoSimulado}")
        print("1) Mostrar vuelos activos")
        print("2) Mostrar pistas")
        print("3) Avanzar tiempo (+1 minuto)")
        print("4) Avanzar tiempo (+5 minutos)")
        print("5) Asignar pista a vuelo")
        print("0) Salir")
        opcion = input("Opcion: ")

        if opcion == "1":
            MostrarVuelos(vuelosActivos)
        elif opcion == "2":
            MostrarPistas(pistasActivas)
        elif opcion == "3":
            tiempoSimulado = AvanzarTiempo(vuelosActivos, pistasActivas, tiempoSimulado, 1)
        elif opcion == "4":
            tiempoSimulado = AvanzarTiempo(vuelosActivos, pistasActivas, tiempoSimulado, 5)
        elif opcion == "5":
            AsignarPistaAVuelo(pistasActivas, vuelosActivos, tiempoSimulado)
        elif opcion == "0":
            print("Saliendo del simulador...")
            break
        else:
            print("Opcion no valida.\n")

Main()