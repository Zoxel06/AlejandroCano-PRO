incrementador = 0

for n1 in range (1,11):
    print("Tabla del ",n1,":")
    incrementador += 1
    for n2 in range (1,11):
        n2 *= incrementador
        
        print(n2)