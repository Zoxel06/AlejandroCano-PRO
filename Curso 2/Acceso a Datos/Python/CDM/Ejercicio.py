from colorama import init, Fore, Style
import datetime


cdm = {
    "almacen_central": {
        "inventario": {
            1: {"nombre": "Conos deportivos", "marca": "SportPro", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            2: {"nombre": "Petos entrenamiento", "marca": "Atletic", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            3: {"nombre": "Tablas natación", "marca": "Arena", "fecha_alta": "2025-01-01", "estado": "mantenimiento", "reservado_por": None},
            4: {"nombre": "Aros de gimnasia", "marca": "Decathlon", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            5: {"nombre": "Cuerdas de saltar", "marca": "Nike", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            6: {"nombre": "Colchoneta fitness", "marca": "Domyos", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            7: {"nombre": "Muñequeras tenis", "marca": "Wilson", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            8: {"nombre": "Toallas deportivas", "marca": "Reebok", "fecha_alta": "2025-01-01", "estado": "mantenimiento", "reservado_por": None},
            9: {"nombre": "Gafas natación", "marca": "Speedo", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            10: {"nombre": "Cronómetro", "marca": "Casio", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            11: {"nombre": "Silbatos", "marca": "ProSport", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            12: {"nombre": "Chanclas piscina", "marca": "Adidas", "fecha_alta": "2025-01-01", "estado": "mantenimiento", "reservado_por": None},
            13: {"nombre": "Bandas elásticas", "marca": "ElastoFit", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            14: {"nombre": "Bañadores competición", "marca": "Speedo", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None},
            15: {"nombre": "Guantes gimnasio", "marca": "Domyos", "fecha_alta": "2025-01-01", "estado": "disponible", "reservado_por": None}
        }
    },
    "distritos": {
        "Norte": {
            "inventario": {
                16: {"nombre": "Balón baloncesto", "marca": "Spalding", "fecha_alta": "2025-01-02", "estado": "disponible", "reservado_por": None},
                17: {"nombre": "Raqueta tenis", "marca": "Wilson", "fecha_alta": "2025-01-02", "estado": "disponible", "reservado_por": None},
                18: {"nombre": "Palas pádel", "marca": "Bullpadel", "fecha_alta": "2025-01-02", "estado": "mantenimiento", "reservado_por": None},
                19: {"nombre": "Gorras natación", "marca": "Speedo", "fecha_alta": "2025-01-02", "estado": "disponible", "reservado_por": None},
                20: {"nombre": "Mancuernas 5kg", "marca": "Domyos", "fecha_alta": "2025-01-02", "estado": "disponible", "reservado_por": None},
                21: {"nombre": "Balón fútbol", "marca": "Adidas", "fecha_alta": "2025-01-02", "estado": "disponible", "reservado_por": None},
                22: {"nombre": "Pesas rusas", "marca": "Kettler", "fecha_alta": "2025-01-02", "estado": "mantenimiento", "reservado_por": None},
                23: {"nombre": "Set conos pádel", "marca": "Atletic", "fecha_alta": "2025-01-02", "estado": "disponible", "reservado_por": None},
                24: {"nombre": "Balón balonmano", "marca": "Molten", "fecha_alta": "2025-01-02", "estado": "disponible", "reservado_por": None},
                25: {"nombre": "Bañador natación", "marca": "Arena", "fecha_alta": "2025-01-02", "estado": "mantenimiento", "reservado_por": None}
            },
            "actividades": {
                "natacion": {"cupos": 5, "usuarios": []},
                "baloncesto": {"cupos": 5, "usuarios": []},
                "tenis": {"cupos": 5, "usuarios": []},
                "padel": {"cupos": 5, "usuarios": []},
                "fitness": {"cupos": 5, "usuarios": []}
            }
        },
        "Sur": {
            "inventario": {
                26: {"nombre": "Pelotas tenis", "marca": "Head", "fecha_alta": "2025-01-03", "estado": "disponible", "reservado_por": None},
                27: {"nombre": "Balón baloncesto", "marca": "Nike", "fecha_alta": "2025-01-03", "estado": "disponible", "reservado_por": None},
                28: {"nombre": "Raqueta pádel", "marca": "StarVie", "fecha_alta": "2025-01-03", "estado": "mantenimiento", "reservado_por": None},
                29: {"nombre": "Flotadores", "marca": "Arena", "fecha_alta": "2025-01-03", "estado": "disponible", "reservado_por": None},
                30: {"nombre": "Esterilla fitness", "marca": "Domyos", "fecha_alta": "2025-01-03", "estado": "disponible", "reservado_por": None},
                31: {"nombre": "Balón balonmano", "marca": "Molten", "fecha_alta": "2025-01-03", "estado": "mantenimiento", "reservado_por": None},
                32: {"nombre": "Mancuernas 8kg", "marca": "Domyos", "fecha_alta": "2025-01-03", "estado": "disponible", "reservado_por": None},
                33: {"nombre": "Palas mini pádel", "marca": "Kombat", "fecha_alta": "2025-01-03", "estado": "disponible", "reservado_por": None},
                34: {"nombre": "Gafas natación", "marca": "Speedo", "fecha_alta": "2025-01-03", "estado": "disponible", "reservado_por": None},
                35: {"nombre": "Pesas tobillo", "marca": "ProFit", "fecha_alta": "2025-01-03", "estado": "mantenimiento", "reservado_por": None}
            },
            "actividades": {
                "natacion": {"cupos": 5, "usuarios": []},
                "baloncesto": {"cupos": 5, "usuarios": []},
                "tenis": {"cupos": 5, "usuarios": []},
                "padel": {"cupos": 5, "usuarios": []},
                "fitness": {"cupos": 5, "usuarios": []}
            }
        },
        "Este": {
            "inventario": {
                36: {"nombre": "Raqueta tenis", "marca": "Babolat", "fecha_alta": "2025-01-04", "estado": "mantenimiento", "reservado_por": None},
                37: {"nombre": "Pesas rusas", "marca": "Kettler", "fecha_alta": "2025-01-04", "estado": "disponible", "reservado_por": None},
                38: {"nombre": "Aletas natación", "marca": "Speedo", "fecha_alta": "2025-01-04", "estado": "disponible", "reservado_por": None},
                39: {"nombre": "Balón baloncesto", "marca": "Spalding", "fecha_alta": "2025-01-04", "estado": "disponible", "reservado_por": None},
                40: {"nombre": "Esterilla yoga", "marca": "Domyos", "fecha_alta": "2025-01-04", "estado": "mantenimiento", "reservado_por": None},
                41: {"nombre": "Pelotas pádel", "marca": "Wilson", "fecha_alta": "2025-01-04", "estado": "disponible", "reservado_por": None},
                42: {"nombre": "Gomas fitness", "marca": "ElastoFit", "fecha_alta": "2025-01-04", "estado": "disponible", "reservado_por": None},
                43: {"nombre": "Churros piscina", "marca": "Decathlon", "fecha_alta": "2025-01-04", "estado": "disponible", "reservado_por": None},
                44: {"nombre": "Bañador niña", "marca": "Arena", "fecha_alta": "2025-01-04", "estado": "disponible", "reservado_por": None},
                45: {"nombre": "Casco baloncesto", "marca": "ProHoops", "fecha_alta": "2025-01-04", "estado": "mantenimiento", "reservado_por": None}
            },
            "actividades": {
                "natacion": {"cupos": 5, "usuarios": []},
                "baloncesto": {"cupos": 5, "usuarios": []},
                "tenis": {"cupos": 5, "usuarios": []},
                "padel": {"cupos": 5, "usuarios": []},
                "fitness": {"cupos": 5, "usuarios": []}
            }
        },
        "Oeste": {
            "inventario": {
                46: {"nombre": "Balón baloncesto", "marca": "Adidas", "fecha_alta": "2025-01-05", "estado": "disponible", "reservado_por": None},
                47: {"nombre": "Raqueta tenis", "marca": "Babolat", "fecha_alta": "2025-01-05", "estado": "mantenimiento", "reservado_por": None},
                48: {"nombre": "Aletas natación", "marca": "Arena", "fecha_alta": "2025-01-05", "estado": "disponible", "reservado_por": None},
                49: {"nombre": "Comba", "marca": "Nike", "fecha_alta": "2025-01-05", "estado": "disponible", "reservado_por": None},
                50: {"nombre": "Bañador hombre", "marca": "Speedo", "fecha_alta": "2025-01-05", "estado": "mantenimiento", "reservado_por": None},
                51: {"nombre": "Pelotas tenis", "marca": "Wilson", "fecha_alta": "2025-01-05", "estado": "disponible", "reservado_por": None},
                52: {"nombre": "Pala pádel", "marca": "Bullpadel", "fecha_alta": "2025-01-05", "estado": "disponible", "reservado_por": None},
                53: {"nombre": "Mancuernas 10kg", "marca": "Domyos", "fecha_alta": "2025-01-05", "estado": "disponible", "reservado_por": None},
                54: {"nombre": "Flotadores", "marca": "Speedo", "fecha_alta": "2025-01-05", "estado": "disponible", "reservado_por": None},
                55: {"nombre": "Churros piscina", "marca": "Arena", "fecha_alta": "2025-01-05", "estado": "mantenimiento", "reservado_por": None}
            },
            "actividades": {
                "natacion": {"cupos": 5, "usuarios": []},
                "baloncesto": {"cupos": 5, "usuarios": []},
                "tenis": {"cupos": 5, "usuarios": []},
                "padel": {"cupos": 5, "usuarios": []},
                "fitness": {"cupos": 5, "usuarios": []}
            }
        }
    },
    "usuarios": {}
}



from colorama import init, Fore, Style
import datetime

init(autoreset=True)

# ------------------------
# Función 1: Alta usuario
# ------------------------
def altaUsuario():
    print(Fore.CYAN + Style.BRIGHT + "\n--- Alta de usuario ---")
    nombre = input("Introduce nombre del usuario: ")

    while True:
        distrito = input("Introduce distrito (Norte/Sur/Este/Oeste): ")
        if distrito in cdm["distritos"]:
            centro_distrito = cdm["distritos"][distrito]
            break
        print(Fore.RED + "Distrito no válido.")

    actividades_disponibles = list(centro_distrito["actividades"].keys())
    print(Fore.YELLOW + f"Actividades disponibles: {', '.join(actividades_disponibles)}")
    
    while True:
        actividad = input("Introduce actividad principal: ").lower()
        if actividad in actividades_disponibles:
            if len(centro_distrito["actividades"][actividad]["usuarios"]) < 5:
                break
            else:
                print(Fore.RED + "Cupo máximo alcanzado.")
        else:
            print(Fore.RED + "Actividad no válida.")

    password = input("Introduce contraseña: ")

    nuevo_id = max(cdm["usuarios"].keys(), default=0) + 1
    cdm["usuarios"][nuevo_id] = {
        "nombre": nombre,
        "password": password,
        "distrito": distrito,
        "actividad": actividad,
        "reservas": []
    }
    centro_distrito["actividades"][actividad]["usuarios"].append(nuevo_id)
    print(Fore.GREEN + f"Usuario {nombre} dado de alta con ID {nuevo_id}.")

# ------------------------
# Función 2: Baja usuario
# ------------------------
def bajaUsuario():
    try:
        user_id = int(input("Introduce ID del usuario a dar de baja: "))
        if user_id in cdm["usuarios"]:
            user = cdm["usuarios"][user_id]
            distrito = user["distrito"]
            actividad = user["actividad"]
            cdm["distritos"][distrito]["actividades"][actividad]["usuarios"].remove(user_id)
            del cdm["usuarios"][user_id]
            print(Fore.GREEN + f"Usuario {user_id} eliminado correctamente.")
        else:
            print(Fore.RED + "ID de usuario no encontrado.")
    except ValueError:
        print(Fore.RED + "ID no válido.")

# ------------------------
# Función 3: Acceder a almacén central
# ------------------------
def verAlmacenCentral():
    print(Fore.CYAN + Style.BRIGHT + "\n--- Inventario del almacén central ---")
    for id_mat, mat in cdm["almacen_central"]["inventario"].items():
        estado = mat['estado']
        if estado == "disponible":
            color = Fore.GREEN
        elif estado == "reservado":
            color = Fore.YELLOW
        else:
            color = Fore.RED
        print(f"{id_mat}: {mat['nombre']} - {mat['marca']} - {color}{mat['estado']}{Style.RESET_ALL}")

# ------------------------
# Función 4: Acceder a distrito
# ------------------------
def verCentroDistrito():
    distrito = input("Introduce distrito (Norte/Sur/Este/Oeste): ")
    if distrito in cdm["distritos"]:
        centro = cdm["distritos"][distrito]
        print(Fore.CYAN + Style.BRIGHT + f"\n--- Inventario del centro {distrito} ---")
        for id_mat, mat in centro["inventario"].items():
            color = Fore.GREEN if mat['estado'] == "disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
            print(f"{id_mat}: {mat['nombre']} - {mat['marca']} - {color}{mat['estado']}{Style.RESET_ALL}")
        opcion = input("¿Desea ver materiales de otros distritos? (s/n): ").lower()
        if opcion == "s":
            for dist, info in cdm["distritos"].items():
                if dist != distrito:
                    for id_mat, mat in info["inventario"].items():
                        color = Fore.GREEN if mat['estado'] == "disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
                        print(f"{id_mat} ({dist}): {mat['nombre']} - {mat['marca']} - {color}{mat['estado']}{Style.RESET_ALL}")
    else:
        print(Fore.RED + "Distrito no válido.")

# ------------------------
# Función 5: Acceder a actividad
# ------------------------
def accederActividad():
    user_id = int(input("Introduce tu ID de usuario: "))
    password = input("Introduce contraseña: ")
    if user_id in cdm["usuarios"]:
        user = cdm["usuarios"][user_id]
        if password == user["password"]:
            print(Fore.GREEN + f"Acceso permitido a la actividad {user['actividad']} del distrito {user['distrito']}.")
        else:
            print(Fore.RED + "Contraseña incorrecta.")
    else:
        print(Fore.RED + "Usuario no encontrado.")

# ------------------------
# Función 6: Listar materiales
# ------------------------
def listarMateriales(user_id):
    if user_id not in cdm["usuarios"]:
        print(Fore.RED + "Usuario no encontrado.")
        return
    user = cdm["usuarios"][user_id]
    distrito = user["distrito"]
    actividad = user["actividad"]
    centro = cdm["distritos"][distrito]

    print(Fore.CYAN + Style.BRIGHT + f"\n--- Materiales del centro {distrito} ---")
    for id_mat, mat in centro["inventario"].items():
        color = Fore.GREEN if mat['estado']=="disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
        print(f"{id_mat}: {mat['nombre']} - {mat['marca']} - {color}{mat['estado']}{Style.RESET_ALL}")

    opcion = input("Ver solo materiales de tu actividad? (s/n): ").lower()
    if opcion == "s":
        print(Fore.MAGENTA + f"\n--- Materiales de la actividad {actividad} ---")
        for id_mat, mat in centro["inventario"].items():
            if actividad in mat['nombre'].lower():
                color = Fore.GREEN if mat['estado']=="disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
                print(f"{id_mat}: {mat['nombre']} - {mat['marca']} - {color}{mat['estado']}{Style.RESET_ALL}")

# ------------------------
# Función 7: Buscar material
# ------------------------
def buscarMaterial():
    termino = input("Introduce nombre/marca/estado a buscar: ").lower()
    encontrado = False

    for id_mat, mat in cdm["almacen_central"]["inventario"].items():
        if termino in mat["nombre"].lower() or termino in mat["marca"].lower() or termino in mat["estado"].lower():
            color = Fore.GREEN if mat['estado']=="disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
            print(f"{id_mat} (Almacén central): {mat['nombre']} - {mat['marca']} - {color}{mat['estado']}{Style.RESET_ALL}")
            encontrado = True

    for dist, info in cdm["distritos"].items():
        for id_mat, mat in info["inventario"].items():
            if termino in mat["nombre"].lower() or termino in mat["marca"].lower() or termino in mat["estado"].lower():
                color = Fore.GREEN if mat['estado']=="disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
                print(f"{id_mat} ({dist}): {mat['nombre']} - {mat['marca']} - {color}{mat['estado']}{Style.RESET_ALL}")
                encontrado = True

    if not encontrado:
        print(Fore.RED + "No se encontró ningún material con ese término.")


# ------------------------
# Función 8: Reservar material
# ------------------------
def reservarMaterial(user_id):
    if user_id not in cdm["usuarios"]:
        print(Fore.RED + "Usuario no encontrado.")
        return
    user = cdm["usuarios"][user_id]
    if len(user["reservas"]) >= 3:
        print(Fore.RED + "Ya tienes 3 reservas activas.")
        return
    material_id = int(input("Introduce ID del material a reservar: "))
    mat = None
    for dist, info in cdm["distritos"].items():
        if material_id in info["inventario"]:
            mat = info["inventario"][material_id]
            break
    if not mat and material_id in cdm["almacen_central"]["inventario"]:
        mat = cdm["almacen_central"]["inventario"][material_id]
    if not mat:
        print(Fore.RED + "Material no encontrado.")
        return
    if mat["estado"] != "disponible":
        print(Fore.RED + "Material no disponible.")
        return
    mat["estado"] = "reservado"
    mat["reservado_por"] = user_id
    user["reservas"].append({"id": material_id, "fecha": datetime.date.today()})
    print(Fore.GREEN + f"Material {mat['nombre']} reservado por {user['nombre']}.")

# ------------------------
# Función 9: Ver reservas
# ------------------------
def verReservas(user_id):
    if user_id not in cdm["usuarios"]:
        print(Fore.RED + "Usuario no encontrado.")
        return
    user = cdm["usuarios"][user_id]
    print(Fore.CYAN + Style.BRIGHT + f"\n--- Reservas de {user['nombre']} ---")
    if not user["reservas"]:
        print(Fore.YELLOW + "No tienes reservas activas.")
        return
    for r in user["reservas"]:
        mat_id = r["id"]
        for dist, info in cdm["distritos"].items():
            if mat_id in info["inventario"]:
                mat = info["inventario"][mat_id]
                color = Fore.GREEN if mat['estado']=="disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
                print(f"{mat['nombre']} ({dist}, {user['actividad']}) - Reservado el {r['fecha']} - {color}{mat['estado']}{Style.RESET_ALL}")
        if mat_id in cdm["almacen_central"]["inventario"]:
            mat = cdm["almacen_central"]["inventario"][mat_id]
            color = Fore.GREEN if mat['estado']=="disponible" else (Fore.YELLOW if mat['estado']=="reservado" else Fore.RED)
            print(f"{mat['nombre']} (Almacén central) - Reservado el {r['fecha']} - {color}{mat['estado']}{Style.RESET_ALL}")

# ------------------------
# Función 10: Devolver material
# ------------------------
def devolverMaterial(user_id):
    if user_id not in cdm["usuarios"]:
        print(Fore.RED + "Usuario no encontrado.")
        return
    user = cdm["usuarios"][user_id]
    mat_id = int(input("Introduce ID del material a devolver: "))
    mat = None
    for dist, info in cdm["distritos"].items():
        if mat_id in info["inventario"]:
            mat = info["inventario"][mat_id]
            break
    if not mat and mat_id in cdm["almacen_central"]["inventario"]:
        mat = cdm["almacen_central"]["inventario"][mat_id]
    if not mat:
        print(Fore.RED + "Material no encontrado.")
        return
    if mat.get("reservado_por") != user_id:
        print(Fore.RED + "No tienes reservado este material.")
        return
    mat["estado"] = "disponible"
    mat["reservado_por"] = None
    user["reservas"] = [r for r in user["reservas"] if r["id"] != mat_id]
    print(Fore.GREEN + f"Material {mat['nombre']} devuelto correctamente.")




def menu():
    opcion = 0
    while opcion != 11:
        print(Fore.BLUE + Style.BRIGHT + "\n" + "="*35)
        print(Fore.CYAN + Style.BRIGHT + "        MENÚ CDM - Gestión")
        print(Fore.BLUE + Style.BRIGHT + "="*35)

        print(Fore.YELLOW + "1." + Style.RESET_ALL + " Alta de usuario")
        print(Fore.YELLOW + "2." + Style.RESET_ALL + " Baja de usuario")
        print(Fore.YELLOW + "3." + Style.RESET_ALL + " Acceder al almacén central")
        print(Fore.YELLOW + "4." + Style.RESET_ALL + " Acceder a un distrito y centro general")
        print(Fore.YELLOW + "5." + Style.RESET_ALL + " Acceder a una actividad específica")
        print(Fore.YELLOW + "6." + Style.RESET_ALL + " Listar materiales de la ubicación actual")
        print(Fore.YELLOW + "7." + Style.RESET_ALL + " Buscar material")
        print(Fore.YELLOW + "8." + Style.RESET_ALL + " Reservar material")
        print(Fore.YELLOW + "9." + Style.RESET_ALL + " Ver mis reservas")
        print(Fore.YELLOW + "10." + Style.RESET_ALL + " Devolver material")
        print(Fore.YELLOW + "11." + Style.RESET_ALL + " Salir")

        print(Fore.BLUE + Style.BRIGHT + "="*35)

        try:
            opcion = int(input(Fore.CYAN + "Elige una opción: " + Style.RESET_ALL))
        except ValueError:
            print(Fore.RED + "Por favor, introduce un número válido.")
            continue

        if opcion == 1:
            altaUsuario()
        elif opcion == 2:
            bajaUsuario()
        elif opcion == 3:
            verAlmacenCentral()
        elif opcion == 4:
            verCentroDistrito()
        elif opcion == 5:
            accederActividad()
        elif opcion == 6:
            try:
                user_id = int(input("Introduce tu ID: "))
                listarMateriales(user_id)
            except ValueError:
                print(Fore.RED + "ID no válido.")
        elif opcion == 7:
            buscarMaterial()
        elif opcion == 8:
            try:
                user_id = int(input("Introduce tu ID: "))
                reservarMaterial(user_id)
            except ValueError:
                print(Fore.RED + "ID no válido.")
        elif opcion == 9:
            try:
                user_id = int(input("Introduce tu ID: "))
                verReservas(user_id)
            except ValueError:
                print(Fore.RED + "ID no válido.")
        elif opcion == 10:
            try:
                user_id = int(input("Introduce tu ID: "))
                devolverMaterial(user_id)
            except ValueError:
                print(Fore.RED + "ID no válido.")
        elif opcion == 11:
            print(Fore.CYAN + Style.BRIGHT + "Saliendo...")
        else:
            print(Fore.RED + "Opción no válida, intenta de nuevo.")



menu()