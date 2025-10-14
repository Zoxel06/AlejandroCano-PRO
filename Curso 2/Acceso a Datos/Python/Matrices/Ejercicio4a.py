tamanio = int(input("Introduce el tamanio de la matriz: "))

listaCeros = []

for n in range (tamanio):
    if (listaCeros != []):
        listaCeros = []
    for m in range(tamanio):
        listaCeros.append(0)
    print(listaCeros)