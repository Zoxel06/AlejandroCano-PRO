def potencia(n, m):
    if m == 0:               # caso base
        return 1
    else:                    # caso recursivo
        return n * potencia(n, m - 1)


print(potencia(2, 3))