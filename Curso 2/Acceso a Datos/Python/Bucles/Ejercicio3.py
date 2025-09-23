from sympy import isprime

print("Numeros primos entre el 1 y el 50: ")
for num in range(1, 51):
   if (isprime(num)):
       print(num)