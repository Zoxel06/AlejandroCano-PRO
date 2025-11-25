package com.example.calculadora

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private var num1 = 0.0
    private var operador = ""
    private var nuevoNumero = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Array de botones
        val botones = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9,
            binding.btnSum, binding.btnRes, binding.btnMul, binding.btnDiv,
            binding.btnIgual, binding.btnC, binding.btnPunto, binding.btnMasMenos,
            binding.btnDel, binding.btnSin, binding.btnCos, binding.btnTan,
            binding.btnSqrt, binding.btnPow, binding.btnLog
        )

        // Asignar listener
        botones.forEach { it?.setOnClickListener(this) }
    }

    override fun onClick(view: View) {
        val texto = (view as? View)?.let { (it as? androidx.appcompat.widget.AppCompatButton)?.text.toString() } ?: return

        when (texto) {

            "C" -> {
                binding.txtResultado.text = ""
                num1 = 0.0
                operador = ""
                nuevoNumero = true
            }

            "←" -> {
                val t = binding.txtResultado.text.toString()
                if (t.isNotEmpty()) binding.txtResultado.text = t.dropLast(1)
            }

            "±" -> {
                if (binding.txtResultado.text.toString().isNotEmpty()) {
                    val num = binding.txtResultado.text.toString().toDouble()
                    binding.txtResultado.text = (num * -1).toString()
                }
            }

            "+", "-", "×", "÷" -> {
                if (binding.txtResultado.text.toString().isNotEmpty()) {
                    num1 = binding.txtResultado.text.toString().toDouble()
                    operador = texto
                    nuevoNumero = true
                }
            }

            "=" -> {
                if (binding.txtResultado.text.toString().isNotEmpty() && operador.isNotEmpty()) {
                    val num2 = binding.txtResultado.text.toString().toDouble()
                    val resultado = when (operador) {
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "×" -> num1 * num2
                        "÷" -> {
                            if (num2 == 0.0) {
                                binding.txtResultado.text = "Error"
                                return
                            }
                            num1 / num2
                        }
                        else -> 0.0
                    }
                    binding.txtResultado.text = resultado.toString()
                    operador = ""
                    nuevoNumero = true
                }
            }

            "." -> {
                if (nuevoNumero) {
                    binding.txtResultado.text = "0."
                    nuevoNumero = false
                } else if (!binding.txtResultado.text.toString().contains(".")) {
                    binding.txtResultado.append(".")
                }
            }

            // -------- CIENTÍFICAS ----------
            "sin" -> aplicarFuncion(sin(Math.toRadians(getNumero())))
            "cos" -> aplicarFuncion(cos(Math.toRadians(getNumero())))
            "tan" -> aplicarFuncion(tan(Math.toRadians(getNumero())))
            "√" -> aplicarFuncion(sqrt(getNumero()))
            "x²" -> aplicarFuncion(getNumero().pow(2))
            "log" -> aplicarFuncion(log10(getNumero()))

            else -> { // números
                if (nuevoNumero) {
                    binding.txtResultado.text = texto
                    nuevoNumero = false
                } else {
                    binding.txtResultado.append(texto)
                }
            }
        }
    }

    private fun getNumero(): Double {
        return binding.txtResultado.text.toString().toDoubleOrNull() ?: 0.0
    }

    private fun aplicarFuncion(resultado: Double) {
        binding.txtResultado.text = resultado.toString()
        nuevoNumero = true
    }
}
