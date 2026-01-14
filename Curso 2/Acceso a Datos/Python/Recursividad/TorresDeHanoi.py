def hanoi(n, inicio, auxiliar, destino):
    if  n == 1:
        print(f"Disco 1 de {inicio} a {destino}")
    else:
        hanoi(n-1, inicio, destino, auxiliar)
        print(f"Disco {n} de {inicio} a {destino}")
        hanoi(n-1, auxiliar, inicio, destino)

hanoi(3, 'Torre 1', 'Torre 2', 'Torre 3')