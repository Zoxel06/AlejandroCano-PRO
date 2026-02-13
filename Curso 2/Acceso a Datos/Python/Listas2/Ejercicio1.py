 # =========================================
# SIMULACIÓN DE LISTAS CON PUNTEROS
# =========================================

# Diccionario global que almacena la lista
lista = {}
inicio = 0
contador_id = 1  # Simula la dirección de memoria


# -----------------------------------------
# crear_nodo
# -----------------------------------------
def crear_nodo(valor):
    global contador_id
    nodo = {
        "dato": valor,
        "sig": 99,
        "ant": 0
    }
    lista[contador_id] = nodo
    contador_id += 1
    return contador_id - 1


# -----------------------------------------
# insertar_inicio
# -----------------------------------------
def insertar_inicio(valor):
    global inicio
    nuevo = crear_nodo(valor)

    if inicio == 0:
        inicio = nuevo
    else:
        lista[nuevo]["sig"] = inicio
        lista[inicio]["ant"] = nuevo
        inicio = nuevo


# -----------------------------------------
# insertar_final
# -----------------------------------------
def insertar_final(valor):
    global inicio

    if inicio == 0:
        print("No se puede insertar al final: lista vacía")
        return

    nuevo = crear_nodo(valor)
    actual = inicio

    while lista[actual]["sig"] != 99:
        actual = lista[actual]["sig"]

    lista[actual]["sig"] = nuevo
    lista[nuevo]["ant"] = actual


# -----------------------------------------
# insertar_nodo (después de un valor)
# -----------------------------------------
def insertar_nodo(valor, despues_de):
    actual = inicio

    while actual != 99:
        if lista[actual]["dato"] == despues_de:
            nuevo = crear_nodo(valor)
            sig = lista[actual]["sig"]
            lista[actual]["sig"] = nuevo
            lista[nuevo]["ant"] = actual
            lista[nuevo]["sig"] = sig
            if sig != 99:
                lista[sig]["ant"] = nuevo
            return
        actual = lista[actual]["sig"]

    print("Nodo no encontrado")


# -----------------------------------------
# contar_nodos
# -----------------------------------------
def contar_nodos():
    contador = 0
    actual = inicio
    while actual != 99 and actual != 0:
        contador += 1
        actual = lista[actual]["sig"]
    return contador


# -----------------------------------------
# imprimir_valor_lista
# -----------------------------------------
def imprimir_valor_lista():
    actual = inicio
    while actual != 99 and actual != 0:
        print(lista[actual]["dato"], end=" -> ")
        actual = lista[actual]["sig"]
    print("NULL")


# -----------------------------------------
# imprimir_lista_completa
# -----------------------------------------
def imprimir_lista_completa():
    for k, v in lista.items():
        print(f"ID:{k} | Dato:{v['dato']} | Ant:{v['ant']} | Sig:{v['sig']}")


# -----------------------------------------
# imprimir_reves
# -----------------------------------------
def imprimir_reves():
    actual = inicio
    if actual == 0:
        return

    while lista[actual]["sig"] != 99:
        actual = lista[actual]["sig"]

    while actual != 0:
        print(lista[actual]["dato"], end=" <- ")
        actual = lista[actual]["ant"]
    print("NULL")


# -----------------------------------------
# buscar_nodo
# -----------------------------------------
def buscar_nodo(valor):
    actual = inicio
    while actual != 99:
        if lista[actual]["dato"] == valor:
            print(f"Nodo encontrado con ID {actual}")
            return actual
        actual = lista[actual]["sig"]
    print("Nodo no encontrado")
    return None


# -----------------------------------------
# eliminar_nodo
# -----------------------------------------
def eliminar_nodo(valor):
    global inicio
    actual = inicio

    while actual != 99:
        if lista[actual]["dato"] == valor:
            ant = lista[actual]["ant"]
            sig = lista[actual]["sig"]

            if ant != 0:
                lista[ant]["sig"] = sig
            else:
                inicio = sig

            if sig != 99:
                lista[sig]["ant"] = ant

            del lista[actual]
            print("Nodo eliminado")
            return
        actual = lista[actual]["sig"]

    print("Nodo no encontrado")


# -----------------------------------------
# copiar_lista
# -----------------------------------------
def copiar_lista(tipo):
    nombre = f"lista_{tipo}.txt"
    with open(nombre, "w") as f:
        actual = inicio
        while actual != 99 and actual != 0:
            nodo = lista[actual]
            f.write(f"ID:{actual} Dato:{nodo['dato']} Ant:{nodo['ant']} Sig:{nodo['sig']}\n")
            actual = nodo["sig"]

    print(f"Lista copiada en {nombre}")

# =========================================
# MENÚ PRINCIPAL
# =========================================

def menu():
    while True:
        print("\n====== MENÚ LISTAS CON PUNTEROS ======")
        print("1. Insertar nodo al inicio")
        print("2. Insertar nodo al final")
        print("3. Insertar nodo entre dos nodos")
        print("4. Eliminar nodo")
        print("5. Buscar nodo")
        print("6. Contar nodos")
        print("7. Imprimir valores de la lista")
        print("8. Imprimir lista completa")
        print("9. Imprimir lista al revés")
        print("10. Copiar lista a fichero")
        print("0. Salir")

        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            valor = int(input("Introduce valor (1-98): "))
            if 1 <= valor <= 98:
                insertar_inicio(valor)
            else:
                print("Valor no válido")

        elif opcion == "2":
            valor = int(input("Introduce valor (1-98): "))
            if 1 <= valor <= 98:
                insertar_final(valor)
            else:
                print("Valor no válido")

        elif opcion == "3":
            valor = int(input("Valor del nuevo nodo (1-98): "))
            despues = int(input("Insertar después de: "))
            insertar_nodo(valor, despues)

        elif opcion == "4":
            valor = int(input("Valor del nodo a eliminar: "))
            eliminar_nodo(valor)

        elif opcion == "5":
            valor = int(input("Valor a buscar: "))
            buscar_nodo(valor)

        elif opcion == "6":
            print("Número de nodos:", contar_nodos())

        elif opcion == "7":
            imprimir_valor_lista()

        elif opcion == "8":
            imprimir_lista_completa()

        elif opcion == "9":
            imprimir_reves()

        elif opcion == "10":
            print("Tipos disponibles:")
            print("enlazada")
            print("enlazada_d")
            print("circular")
            print("circular_d")
            tipo = input("Introduce el tipo de lista: ")
            copiar_lista(tipo)

        elif opcion == "0":
            print("Saliendo del programa...")
            break

        else:
            print("Opción no válida")


menu()