numero = int(input("Introduce un numero: "))
suma = 0

n = numero

while n > 0:
    suma += n%10
    n //= 10
    
print("La suma de los digitos de",numero,"es",suma)