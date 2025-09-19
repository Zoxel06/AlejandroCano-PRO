import random

random = random.randint(1,11)

respuesta = int(input("Adivina el numero aleatorio entre 1 y 10"))

if(random==respuesta):
    print("Has acertado")
else:
    print("Respuesta incorrecta")