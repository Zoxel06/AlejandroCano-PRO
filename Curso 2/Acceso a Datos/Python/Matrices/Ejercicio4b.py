tamanio = int(input("Introduce el tamanio de la matriz: "))

listaNumeros = []

nImpar = 1

for n in range (tamanio):
    if (listaNumeros != []):
        listaNumeros = []
    for m in range(tamanio):
        listaNumeros.append(nImpar)
        nImpar += 2
        
    print(listaNumeros)
