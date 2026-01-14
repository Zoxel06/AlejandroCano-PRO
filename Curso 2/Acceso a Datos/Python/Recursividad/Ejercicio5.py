def resta(a, b):
    if b == 0:               # caso base
        return a
    else:                    # caso recursivo
        return resta(a - 1, b - 1)


print(resta(10, 4))