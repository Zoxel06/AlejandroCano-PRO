altura = int(input("Escribe la altura del rombo: "))

if altura % 2 == 0:
    print("La altura no puede ser par")
else:
    for i in range(1,altura + 1, 2):
        espacios = (altura - i) // 2 
        print(" " * espacios + "*" *i)

    for i in range(altura -2, 0, -2):
        espacios = (altura - i) // 2
        print(" " * espacios + "*" *i)