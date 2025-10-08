package controller

import model.Arma
import model.ArmaBiologica
import model.ArmaTradicional
import model.Humano
import model.Mutante
import model.Superheroe

class Shield {

    var listaSuperheroes = arrayListOf<Superheroe>()
    var listaArmas = arrayListOf<Arma>()

    fun agregarSuperheroe(superheroe: Superheroe){
        println("=== CREAR SUPERHÉROE ===")
        println("1. Humano")
        println("2. Mutante")
        print("Seleccione tipo: ")

        when (readln().toInt()) {
            // 🧍‍♂️ HUMANO
            1 -> {
                print("ID: ")
                val idSuperheroe = readln()
                print("Nombre: ")
                val nombre = readln()
                print("Nivel: ")
                val nivel = readln().toInt()
                print("Resistencia: ")
                val resistencia = readln().toInt()

                println("=== CREAR ARMA TRADICIONAL ===")
                print("ID: ")
                val idArma = readln()
                print("Nombre del arma: ")
                val nombreArma = readln()
                print("Nivel de potencia: ")
                val potencia = readln().toInt()
                print("Nivel de daño: ")
                val dano = readln().toInt()
                print("Peso: ")
                val peso = readln().toFloat()

                val arma = ArmaTradicional(idArma, nombreArma, potencia, dano, peso)
                val humano = Humano(idSuperheroe, nombre, nivel, resistencia, arma)
                listaSuperheroes.add(humano)
                listaArmas.add(arma)
                println("✅ Superhéroe humano agregado con éxito.\n")
            }

            // 🧬 MUTANTE
            2 -> {
                print("ID: ")
                val idSuperheroe = readln()
                print("Nombre: ")
                val nombre = readln()
                print("Nivel: ")
                val nivel = readln().toInt()

                println("=== CREAR ARMA BIOLÓGICA ===")
                print("ID: ")
                val idArma = readln()
                print("Nombre del arma: ")
                val nombreArma = readln()
                print("Nivel de potencia: ")
                val potencia = readln().toInt()
                print("Nivel de daño: ")
                val dano = readln().toInt()
                print("Descripción: ")
                val descripcion = readln()

                val arma = ArmaBiologica(idArma, nombreArma, potencia, dano, descripcion)
                val mutante = Mutante(idSuperheroe, nombre, nivel, arma)
                listaSuperheroes.add(mutante)
                listaArmas.add(arma)
                println("✅ Superhéroe mutante agregado con éxito.\n")
            }

            else -> println("❌ Tipo inválido.")
        }
    }



    }

