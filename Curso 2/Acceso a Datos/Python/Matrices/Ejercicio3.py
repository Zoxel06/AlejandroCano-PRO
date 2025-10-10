longitud = int(input("De cuanto quieres que sea el cuadrado: "))

a = 1
numeros = []

for n1 in range(0,longitud+1):
    if (numeros != []):
        print(numeros)
        numeros = []
        
    for n2 in range(0,longitud):
        numeros.append(n2)