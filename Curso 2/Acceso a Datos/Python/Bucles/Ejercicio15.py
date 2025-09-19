cadena = str(input("Introduce una cadena: "))
letra = str(input("Que letra quieres buscar: "))
contador = 0

for l in cadena:
    if(l == letra):
        contador = contador + 1
        
print("La letra",letra,"aparece",contador,"veces")