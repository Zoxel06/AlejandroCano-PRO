mi_tupla = (1, 2, 3, "cuatro", "cinco")

elementos = ["cuatro", "2", 2]
for valor in elementos:
    if valor in mi_tupla:
        print(f"{valor} esta en la tupla")
