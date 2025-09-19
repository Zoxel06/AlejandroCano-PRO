modulo3 = "Fizz"
modulo5 = "Buzz"

for n in range(1,101):
    if(n%3 == 0 and n%5 == 0):
        print(n, ":", modulo3, " y ", modulo5)
    elif(n%3 == 0):
        print(n, ":" ,modulo3)
    elif(n%5 == 0):
        print(n, ":", modulo5)
    else:
        print(n)