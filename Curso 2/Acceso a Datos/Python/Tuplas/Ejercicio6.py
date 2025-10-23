letra = input("Introduce una letra: ")
numero = input("Introduce un número: ")

if numero.isdigit():
    numero= int(numero)

mi_tupla = (letra, numero)

print(mi_tupla)
