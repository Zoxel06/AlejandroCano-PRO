import os

ruta = "Ficheros/archivo.txt"
os.makedirs("Ficheros", exist_ok=True)


def crear_archivo_clasico():
    f = open(ruta, "w")
    f.write("Esto es un archivo de texto en Python de forma clásica")
    f.close()
    print("Archivo creado (forma clásica).")


def crear_archivo_with():
    with open(ruta, "w") as f:
        f.write("Esto es un archivo de texto en Python usando with as")
    print("Archivo creado (with as).")


def leer_todo():
    try:
        with open(ruta, "r") as f:
            print(f.read())
    except:
        print("El archivo no existe.")


def leer_lineas():
    try:
        with open(ruta, "r") as f:
            for linea in f:
                print(linea.strip())
    except:
        print("El archivo no existe.")


def leer_con_readlines():
    try:
        with open(ruta, "r") as f:
            print(f.readlines())
    except:
        print("El archivo no existe.")


def escribir_final():
    try:
        with open(ruta, "r+") as f:
            f.seek(0, 2)
            f.write("\nEscribiendo una nueva línea al final")
        print("Línea añadida al final.")
    except:
        print("El archivo no existe.")


def escribir_inicio():
    try:
        with open(ruta, "r+") as f:
            contenido = f.read()
            f.seek(0, 0)
            f.write("Escribiendo una nueva línea al principio\n")
            f.write(contenido)
        print("Línea añadida al principio.")
    except:
        print("El archivo no existe.")


def escribir_con_writelines():
    try:
        Lista = ["\nEscribiendo \n", "listas \n", "en ficheros"]
        with open(ruta, "r+") as f:
            f.seek(0, 2)
            f.writelines(Lista)
        print("Lista escrita con writelines().")
    except:
        print("El archivo no existe.")


def ejemplo_seek():
    with open("Ficheros/ejemplo_seek.txt", "w") as f:
        f.write("Primera línea\nSegunda línea\nTercera línea")

    with open("Ficheros/ejemplo_seek.txt", "r") as f:
        print(f.readline().strip())
        f.seek(0)
        print(f.readline().strip())
        f.seek(15)
        print(f.readline().strip())
        f.seek(0, 2)
        print(f.tell())


def excepcion_existente():
    try:
        with open(ruta, "r") as f:
            print(f.read())
    except:
        print("No existe el fichero.")


def excepcion_no_existente():
    try:
        with open("Ficheros/no_existe.txt", "r") as f:
            print(f.read())
    except:
        print("No existe el fichero.")


def menu():
    while True:
        print("1. Crear archivo (forma clasica)")
        print("2. Crear archivo (with as)")
        print("3. Leer archivo completo (read)")
        print("4. Leer linea por linea")
        print("5. Leer con readlines()")
        print("6. Escribir al final (r+)")
        print("7. Escribir al principio (r+)")
        print("8. Escribir lista con writelines()")
        print("9. Demostración de seek()")
        print("10. Excepcion: fichero existente")
        print("11. Excepcion: fichero no existente")
        print("10. Salir")

        opcion = input("Selecciona una opción: ")

        if opcion == "1":
            crear_archivo_clasico()
        elif opcion == "2":
            crear_archivo_with()
        elif opcion == "3":
            leer_todo()
        elif opcion == "4":
            leer_lineas()
        elif opcion == "5":
            leer_con_readlines()
        elif opcion == "6":
            escribir_final()
        elif opcion == "7":
            escribir_inicio()
        elif opcion == "8":
            escribir_con_writelines()
        elif opcion == "9":
            ejemplo_seek()
        elif opcion == "10":
            excepcion_existente()
        elif opcion == "11":
            excepcion_no_existente()
        elif opcion == "10":
            print("Saliendo...")
            break
        else:
            print("Opción no valida")



menu()
