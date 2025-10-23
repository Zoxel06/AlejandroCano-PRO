mi_tupla = (1, 2, 3, "cuatro", "cinco")
esta = False

valor = input("Introduce un valor: ")

if valor.isdigit():
    valor = int(valor)

for elemento in mi_tupla:
    if valor == elemento:
        print(f"{elemento} esta en la tupla")
        esta = True

if esta == False:
    print(f"{valor} no esta en la tupla")
