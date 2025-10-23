def desempaquetar_tupla(tupla):
    (a, x), (b, y), (c, z) = tupla
    print(f"El valor {a} vale {x}")
    print(f"El valor {b} vale {y}")
    print(f"El valor {c} vale {z}")

mi_tupla = (("a", 1), ("b", 2), ("c", 3))
desempaquetar_tupla(mi_tupla)
