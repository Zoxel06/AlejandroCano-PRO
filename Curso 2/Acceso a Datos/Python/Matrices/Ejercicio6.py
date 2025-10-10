tamanio = int(input("Introduce el tamaño de la matriz: "))

matriz = []

for n in range(tamanio):
    listaNumeros = []
    for m in range(tamanio):
        valor = n + m
        listaNumeros.append(valor)
    matriz.append(listaNumeros)


for n in matriz:
    print(n)