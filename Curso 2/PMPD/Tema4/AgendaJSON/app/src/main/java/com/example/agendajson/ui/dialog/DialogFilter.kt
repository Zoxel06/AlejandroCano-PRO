package com.example.agendajson.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.agendajson.MainActivity

class DialogFilter : DialogFragment() {

    private lateinit var opciones: Array<String>
    private lateinit var listener: OnDialogoFiltrarListener
    private var posicion = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        opciones = arrayOf("Male", "Female", "All")
        listener = context as MainActivity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Filtrar por género")
        builder.setSingleChoiceItems(opciones, -1) { _, pos ->
            posicion = pos
        }

        builder.setPositiveButton("OK") { _, _ ->
            if (posicion != -1) {
                listener.onGeneroSelected(opciones[posicion].lowercase())
            }
        }

        return builder.create()
    }

    interface OnDialogoFiltrarListener {
        fun onGeneroSelected(genero: String)
    }
}
