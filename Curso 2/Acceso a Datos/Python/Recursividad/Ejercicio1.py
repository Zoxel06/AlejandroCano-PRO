def factorial(n):
    if n == 0 or n == 1:      # caso base
        return 1
    else:                    # caso recursivo
        return n * factorial(n - 1)


print(factorial(5))