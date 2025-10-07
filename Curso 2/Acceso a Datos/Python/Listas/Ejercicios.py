#Ejercicio 1

listaFrutas = ["manzana","platano","naranja","pera","sandia"]

print(listaFrutas)
print(listaFrutas[0])
print(listaFrutas[-1])


#Ejercicio 2

lista = []

lista.append(1)
lista.append(2)
lista.append(3)
lista.remove(2)

print(lista)


#Ejercicio 3

listaNumeros = [1,2,3,4,5]

for item in listaNumeros:
    print(item*2)
    
    
#Ejercicio 4

listaPalabras = ["manzana","pera","naranja"]

palabra = str(input("Introduce una palabra"))

if palabra in listaPalabras:
    print("La palabra que has introducido se encuentra en la lista")
else:
    print("La palabra que has introducido no se encuentra en la lista")
    
    
#Ejercicio 5

listaNumeros = [1,2,3,4,5,6,7,8,9,10]

print(listaNumeros[:3])
print(listaNumeros[-3:])
print(listaNumeros[2:7])