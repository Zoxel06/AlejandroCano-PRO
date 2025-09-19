num = int(input("Ingresa un numero: "))
factorial = 1

for n in range(1,num+1):
    factorial = factorial*n
    
print("El factorial de ",num, " es ", factorial)