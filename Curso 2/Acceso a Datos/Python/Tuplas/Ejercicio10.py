ciudades_temperaturas = (
    ("Madrid", (30, 32, 31)),
    ("Barcelona", (20, 26, 21)),
    ("Valencia", (28, 29, 27))
)

ciudad = input("Introduce una ciudad: ")

for nombre, temps in ciudades_temperaturas:
    if nombre.lower() == ciudad.lower():
        media = sum(temps) / len(temps)
        print(round(media, 1))
