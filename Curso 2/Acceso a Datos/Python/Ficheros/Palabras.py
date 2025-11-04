from collections import Counter
import os

entrada = "Ficheros/palabras.txt"
salida1 = "Ficheros/salida.txt"
salida2 = "Ficheros/salida2.txt"


with open(entrada, "r") as f:
    texto = f.read()


palabras = texto.split()
total_palabras = len(palabras)


vocales = sum(1 for c in texto.lower() if c in "aeiouáéíóú")
total_vocales = vocales


conteo = Counter(palabras)


repetidas = {}
for pal, cant in conteo.items():
    if cant > 1:
        repetidas[pal] = cant



if len(repetidas) > 0:
    # Calcular la frecuencia máxima
    max_frecuencia = 0
    for cantidad in repetidas.values():
        if cantidad > max_frecuencia:
            max_frecuencia = cantidad

    # Buscar las palabras que tienen esa frecuencia máxima
    mas_repetidas = []
    for palabra, cantidad in repetidas.items():
        if cantidad == max_frecuencia:
            mas_repetidas.append(palabra)
else:
    max_frecuencia = 0
    mas_repetidas = []



with open(salida1, "w") as f:
    f.write(f"Numero total de palabras: {total_palabras}\n")
    f.write(f"Numero total de vocales: {total_vocales}\n")
    f.write("Palabras que se repiten y cuantas veces:\n")
    for palabra, cantidad in repetidas.items():
        f.write(f"  {palabra}: {cantidad}\n")
    f.write(f"Palabras que mas se repiten ({max_frecuencia} veces): {', '.join(mas_repetidas)}\n")


with open(salida2, "w") as f:
    f.write("Palabras que se repiten y cuantas veces:\n")
    for palabra, cantidad in repetidas.items():
        f.write(f"  {palabra}: {cantidad}\n")
    f.write(f"Palabras que mas se repiten ({max_frecuencia} veces): {', '.join(mas_repetidas)}\n")
