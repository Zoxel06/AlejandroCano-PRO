tamanio = int(input("Introduce el tamanio de la matriz: "))

listaCeros = []

for n in range (tamanio+1):
    if (listaCeros != []):
        print(listaCeros)
        listaCeros = []
    for m in range(tamanio):
        listaCeros.append(0)