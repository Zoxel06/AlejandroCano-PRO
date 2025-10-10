matriz = [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]]

repetirCentral = input("Quieres repetir el numero central (si / no): ")

resultado = 0
for i in range(matriz):
    resultado += matriz[i][i]
    resultado += matriz[i][4-i]
    

if repetirCentral == "si" or resultado == "Si" or resultado == "SI":
    resultado -= 13

print(resultado)