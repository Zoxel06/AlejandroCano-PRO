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