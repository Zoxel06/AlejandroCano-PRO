tamanio = int(input("Introduce el tamanio de la matriz: "))

listaNumeros = []

nImpar = 1

for n in range (tamanio+1):
    if (listaNumeros != []):
        print(listaNumeros)
        listaNumeros = []
    for m in range(tamanio):
        
        if (nImpar % 2 == 0):
            nImpar += 1
        
        listaNumeros.append(nImpar)
        nImpar += 1
