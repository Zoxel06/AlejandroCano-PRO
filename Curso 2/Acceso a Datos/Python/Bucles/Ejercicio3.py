print("Numeros primos entre el 1 y el 50: ")
for num in range(2, 51):
    primo = True
    for i in range(2, num):
        if num % i == 0:
            primo = False
            break
    if primo:
        print(num)