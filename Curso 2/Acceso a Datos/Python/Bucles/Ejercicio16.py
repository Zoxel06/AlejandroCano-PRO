import math

n1 = int(input("Introduce un numero: "))
n2 = int(input("Introduce otro numero: "))

mcd = math.gcd(n1,n2)

print("El maximo comun divisor de",n1,"y",n2,"es",mcd)