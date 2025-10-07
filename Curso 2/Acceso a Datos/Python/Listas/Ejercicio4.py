listaPalabras = ["manzana","pera","naranja"]

palabra = str(input("Introduce una palabra"))

if palabra in listaPalabras:
    print("La palabra que has introducido se encuentra en la lista")
else:
    print("La palabra que has introducido no se encuentra en la lista")