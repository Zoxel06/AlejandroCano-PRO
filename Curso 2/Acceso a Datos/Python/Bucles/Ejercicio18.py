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