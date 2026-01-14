def multiplicacion(a, b):
    if a == 0 or b == 0:      # caso base
        return 0
    else:                    # caso recursivo
        return a + multiplicacion(a, b - 1)


print(multiplicacion(4, 5))