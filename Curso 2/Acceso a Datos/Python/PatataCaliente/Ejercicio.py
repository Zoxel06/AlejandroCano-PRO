import random
import time

tiempo = random.randint(8,15)
numero = random.randint(1,100)

print(f"Tienes {tiempo} segundos para adivinar el numero")

inicio = time.time()

adivinado = False

while (time.time() - inicio) < tiempo:
    
    respuesta = int(input("Introduce un numero: "))
    
    tiempoRestante = tiempo - (int)(time.time() - inicio)
          
    if respuesta > numero:
        print("El numero es menor")
        print(f"Tienes {tiempoRestante} segundos restantes")
    elif respuesta < numero:
        print(f"Tienes {tiempoRestante} segundos restantes")
        print("El numero es mayor")
    else:
        print("Enhorabuena, has acertado")
        adivinado = True
        break
 
    
if not adivinado:
    print(f"Se acabó el tiempo, el numero secreto era {numero}")