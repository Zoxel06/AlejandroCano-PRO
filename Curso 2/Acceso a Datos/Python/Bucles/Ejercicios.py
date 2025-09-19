#Ejercicio1
suma = 0

for n in range (1,11):
    suma = suma + n

print(suma)


#Ejercicio2
num = int(input("Ingresa un numero: "))
factorial = 1

for n in range(1,num+1):
    factorial = factorial*n
    
print("El factorial de ",num, " es ", factorial)


#Ejercicio3
print("Numeros primos entre el 1 y el 50: ")
for num in range(2, 51):
    primo = True
    for i in range(2, num):
        if num % i == 0:
            primo = False
            break
    if primo:
        print(num)


#Ejercicio4
numero = int(input("Introduce un numero: "))
suma = 0

n = numero

while n > 0:
    suma += n%10
    n //= 10
    
print("La suma de los digitos de",numero,"es",suma)


#Ejercicio5
suma = 0

for n in range(1,101):
    if (n%2==0):
        suma = suma + n
        
print(suma)


#Ejercicio6
base = int(input("Dime la base del triangulo: "))
altura = int(input("Dime la altura del triangulo: "))

area = (base * altura) / 2
print(area)


#Ejercicio7
modulo3 = "Fizz"
modulo5 = "Buzz"

for n in range(1,101):
    if(n%3 == 0 and n%5 == 0):
        print(n, ":", modulo3, " y ", modulo5)
    elif(n%3 == 0):
        print(n, ":" ,modulo3)
    elif(n%5 == 0):
        print(n, ":", modulo5)
    else:
        print(n)


#Ejercicio8
import random

random = random.randint(1,11)

respuesta = int(input("Adivina el numero aleatorio entre 1 y 10"))

if(random==respuesta):
    print("Has acertado")
else:
    print("Respuesta incorrecta")


#Ejercicio10
cadena = str(input("Introduce una cadena: "))

for vocal in cadena:
    if (vocal == "a" or vocal == "e" or vocal == "i" or vocal == "o" or vocal == "u"):
        print(vocal)


#Ejercicio11
palabra = str(input("Introduce una palabra para ver si es palindromo: "))
palabraReves = ""
contador = 0

for p in reversed (palabra):
    
    palabraReves = palabraReves + p
    
print(palabraReves)

for i in range (0,len(palabra)-1):
    if palabra[i] != palabraReves[i]:
        print("La palabra no es palindromo")
        contador = 0
        break
    else:
        contador = contador + 1


if(contador != 0):
    print("La palabra es palindroma")


#Ejercicio12
radio = int(input("Introduce el radio de el circulo: "))

area = 3.14 * radio**2

print(area)


#Ejercicio13
numero = int(input("Introduce el numero del que quieras hacer la tabla de multiplicar: "))

for n in range (1,11):
    print(numero * n)


#Ejercicio14
suma = 0

for n in range(1,101):
    if(n%2 != 0):
        suma = suma + n
        
print(suma)


#Ejercicio15
cadena = str(input("Introduce una cadena: "))
letra = str(input("Que letra quieres buscar: "))
contador = 0

for l in cadena:
    if(l == letra):
        contador = contador + 1
        
print("La letra",letra,"aparece",contador,"veces")


#Ejercicio16
import math

n1 = int(input("Introduce un numero: "))
n2 = int(input("Introduce otro numero: "))

mcd = math.gcd(n1,n2)

print("El maximo comun divisor de",n1,"y",n2,"es",mcd)


#Ejercicio18
import random

respuesta = ""
random = random.randint(1,3)
print(random)

if(random == 1):
    respuesta = "piedra"
elif(random == 2):
    respuesta = "papel"
elif(random == 3):
    respuesta = "tijeras"

eleccion = str(input("Introduce tu eleccion (piedra, papel o tijeras): "))

if(eleccion == respuesta):
    print("Empate")
elif(respuesta == "piedra" and eleccion == "papel"):
    print("Has ganado")
elif(respuesta == "piedra" and eleccion == "tijeras"):
    print("Has perdido")
elif(respuesta == "papel" and eleccion == "piedra"):
    print("Has perdido")
elif(respuesta == "papel" and eleccion == "tijeras"):
    print("Has ganado")
elif(respuesta == "tijeras" and eleccion == "piedra"):
    print("Has ganado")
elif(respuesta == "tijeras" and eleccion == "papel"):
    print("Has perdido")

