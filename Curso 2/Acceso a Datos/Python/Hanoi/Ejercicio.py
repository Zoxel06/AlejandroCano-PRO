import turtle
import time

# CONFIGURACIÓN GENERAL
turtle.setup(900, 400)
turtle.speed(0)
turtle.hideturtle()
turtle.tracer(0, 0)

# POSICIONES DE LAS TORRES
torres_x = [-300, 0, 300]
torres = [[], [], []]

# DIBUJAR TORRES
def dibujar_torres():
    turtle.clear()
    for x in torres_x:
        turtle.penup()
        turtle.goto(x, -150)
        turtle.pendown()
        turtle.goto(x, 100)

# DIBUJAR DISCOS
def dibujar_discos():
    for i in range(3):
        y = -140
        for disco in torres[i]:
            turtle.penup()
            turtle.goto(torres_x[i] - disco * 10, y)
            turtle.pendown()
            turtle.begin_fill()
            turtle.forward(disco * 20)
            turtle.left(90)
            turtle.forward(20)
            turtle.left(90)
            turtle.forward(disco * 20)
            turtle.left(90)
            turtle.forward(20)
            turtle.left(90)
            turtle.end_fill()
            y += 22

def refrescar():
    dibujar_torres()
    dibujar_discos()
    turtle.update()
    time.sleep(0.5)

# MOVER UN DISCO
def mover(origen, destino):
    disco = torres[origen].pop()
    torres[destino].append(disco)
    refrescar()

# ALGORITMO RECURSIVO DE HANOI
def hanoi(n, origen, auxiliar, destino):
    if n == 1:
        mover(origen, destino)
    else:
        hanoi(n - 1, origen, destino, auxiliar)
        mover(origen, destino)
        hanoi(n - 1, auxiliar, origen, destino)

# INICIALIZACIÓN
def iniciar(n):
    torres[0] = list(range(n, 0, -1))
    torres[1] = []
    torres[2] = []
    refrescar()
    hanoi(n, 0, 1, 2)

# EJECUCIÓN
iniciar(4)

turtle.done()
