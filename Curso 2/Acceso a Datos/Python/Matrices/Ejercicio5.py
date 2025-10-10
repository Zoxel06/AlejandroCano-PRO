tamanio = int(input("Introduce el tamaño de la matriz: "))

matriz1 = []
matriz2 = []
matrizSumada = []

for n in range(tamanio):
    listaNumeros = []
    for m in range(tamanio):
        valor = int(input(f"Introduce el valor para la posición [{n}][{m}]: "))
        listaNumeros.append(valor)
    matriz1.append(listaNumeros)


for n in range(tamanio):
    listaNumeros = []
    for m in range(tamanio):
            valor = int(input(f"Introduce el valor para la posición [{n}][{m}]: "))
            listaNumeros.append(valor)
    matriz2.append(listaNumeros)


for n in range(tamanio):
    listaNumeros = []
    for m in range(tamanio):
        suma = matriz1[n][m] + matriz2[n][m]
        listaNumeros.append(suma)
    matrizSumada.append(listaNumeros)


for n in matrizSumada:
    print(n)