def crearArchivo():
    with open("archivotexto.txt","w") as f:
        f.write("Este es un archivo txt")
        f.close
    print("Archivo creado con exito")

def leerArchivoCompleto():
    try:
        with open("archivotexto.txt","r") as f:
            print(f.read())
        
        print("Archivo leido correctamente")
    except:
        print("No se ha encontrado el archivo")


opcion = 0

while opcion is not 10:

    print("1. Crear archivo")
    print("2. Leer archivo completo")
    print("10. Salir")
    opcion = int(input("Elije una opcion: "))


    if opcion == 1:
        crearArchivo()
    elif opcion == 2:
        leerArchivoCompleto()
    elif opcion == 3:
        print()
    elif opcion == 4:
        print()
    elif opcion == 5:
        print()
    elif opcion == 6:
        print()
    elif opcion == 7:
        print()
    elif opcion == 8:
        print()
    elif opcion == 9:
        print()
    elif opcion == 10:
        print("Saliendo...")
    else:
        print("Opcion no permitida, intentalo de nuevo")