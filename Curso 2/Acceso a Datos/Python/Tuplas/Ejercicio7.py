a = input("Introduce la primera letra: ")
x = int(input("Introduce el número correspondiente: "))

b = input("Introduce la segunda letra: ")
y = int(input("Introduce el número correspondiente: "))

c = input("Introduce la tercera letra: ")
z = int(input("Introduce el número correspondiente: "))

mi_tupla = ((a, x), (b, y), (c, z))

for letra, numero in mi_tupla:
    print(f"El valor {letra} vale {numero}")
