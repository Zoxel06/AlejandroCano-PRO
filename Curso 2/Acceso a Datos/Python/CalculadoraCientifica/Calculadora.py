import math
from colorama import Fore, Back, Style, init
 
def suma(n1,n2):
    return n1+n2

def resta(n1,n2):
    return n1-n2

def multiplicacion(n1,n2):
    return n1*n2

def division(n1,n2):
    return n1/n2

def valorAbsoluto(n1):
    return math.fabs(n1)

def seno(n1):
    return math.sin(n1)

def coseno(n1):
    return math.cos(n1)
    
def tangente(n1):
    return math.tan(n1)
    
def logaritmo10(n1):
    return math.log10(n1)
    
def logaritmoNeperiano(n1):
    return math.log(n1)
    
def eElevado(n1):
    return math.exp(n1)
    
def raizCuadrada(n1):
    return math.sqrt(n1)

opcion = 0
    

print("\n"*3)
print(Fore.YELLOW+Style.BRIGHT+"---------------------------"+Style.RESET_ALL)
print(Fore.BLUE+Style.BRIGHT+"🤓 CALCULADORA CIENTIFICA 🤓"+Style.RESET_ALL)
print(Fore.YELLOW+Style.BRIGHT+"---------------------------\n"+Style.RESET_ALL)

print(Fore.YELLOW+"Introduce la contrasenia"+Style.RESET_ALL)
contrasenia = "1234"
contraseniaInput = input()
if contraseniaInput == contrasenia:
    print(Fore.GREEN+Style.BRIGHT+"\n✔️  Contrasenia correcta, acceso concedido ✔️\n\n"+Style.RESET_ALL)
    
    while opcion != 14:

        print(Fore.BLUE+Style.BRIGHT+"⬇️  Elije una opcion⬇️\n")
        print(Fore.CYAN+Style.BRIGHT+"1. Suma")
        print("2. Resta")
        print("3. Multiplicación")
        print("4. División")
        print("5. Valor absoluto")
        print("6. Seno")
        print("7. Coseno")
        print("8. Tangente")
        print("9. Logaritmo en base 10")
        print("10. Logaritmo neperiano")
        print("11. e elevado a x")
        print("12. Raiz cuadrada"+Style.RESET_ALL)
        print(Fore.YELLOW+Style.BRIGHT+"13. Cambiar contrasenia"+Style.RESET_ALL)
        print(Fore.RED+Style.BRIGHT+"14. Salir\n"+Style.RESET_ALL)

        opcion = int(input(Fore.BLUE+Style.BRIGHT+"Opcion: "+Style.RESET_ALL))
        print("")

        if opcion == 1:
            print(Fore.YELLOW+"Introduce el primer numero"+Style.RESET_ALL)
            a = float(input())
            print(Fore.YELLOW+"Introduce el segundo numero"+Style.RESET_ALL)
            b = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"La suma de los dos numeros es",suma(a,b),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 2:
            print(Fore.YELLOW+"Introduce el primer numero"+Style.RESET_ALL)
            a = float(input())
            print(Fore.YELLOW+"Introduce el segundo numero"+Style.RESET_ALL)
            b = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"La resta de los dos numeros es",resta(a,b),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 3:
            print(Fore.YELLOW+"Introduce el primer numero"+Style.RESET_ALL)
            a = float(input())
            print(Fore.YELLOW+"Introduce el segundo numero"+Style.RESET_ALL)
            b = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"La multiplicacion de los dos numeros es",multiplicacion(a,b),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 4:
            print(Fore.YELLOW+"Introduce el primer numero"+Style.RESET_ALL)
            a = float(input())
            print(Fore.YELLOW+"Introduce el segundo numero"+Style.RESET_ALL)
            b = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"La division de los dos numeros es",division(a,b),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 5:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"El valor absoluto del numero es",valorAbsoluto(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 6:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"El seno del numero es",seno(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 7:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"El coseno del numero es",coseno(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 8:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"La tangente del numero es",tangente(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 9:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"El logaritmo en base 10 del numero es",logaritmo10(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 10:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"El logaritmo neperiano del numero es",logaritmoNeperiano(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 11:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"e elevado al numero es",eElevado(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 12:
            print(Fore.YELLOW+"Introduce el numero"+Style.RESET_ALL)
            a = float(input())
            print("")
            print(Fore.MAGENTA+Style.BRIGHT+"La raiz cuadrada del numero es",raizCuadrada(a),"\n\n"+Style.RESET_ALL)
            
        elif opcion == 13:
            contraseniaAntigua = str(input(Fore.YELLOW+"Introduce la contrasenia antigua\n"+Style.RESET_ALL))
            if contrasenia == contraseniaAntigua:     
                print(Fore.YELLOW+"Introduce la nueva contrasenia"+Style.RESET_ALL)
                contrasenia = int(input())
            else:
                print(Fore.RED+Style.BRIGHT+"\nContrasenia erronea, no puedes cambiarla\n\n"+Style.RESET_ALL)
        elif opcion == 14:
            print(Fore.RED+Style.BRIGHT+"Saliendo...\n\n"+Style.RESET_ALL)
            
            
else:
    print(Fore.RED+Style.BRIGHT+"\n❌  Contrasenia erronea, acceso denegado ❌\n\n"+Style.RESET_ALL)
    print(Style.RESET_ALL)