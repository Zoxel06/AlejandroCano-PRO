tamanio = int(input("Introduce el tamaño de la matriz: "))

listaListas = []

for n in range(tamanio):
    listaNumeros = []
    for m in range(tamanio):
        valor = int(input(f"Introduce el valor para la posición [{n}][{m}]: "))
        listaNumeros.append(valor)
    listaListas.append(listaNumeros)


print("Matriz final:")
for fila in listaListas:
    print(fila)
