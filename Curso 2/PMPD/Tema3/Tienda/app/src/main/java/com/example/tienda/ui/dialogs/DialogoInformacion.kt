package com.example.tienda.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogoInformacion : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Informacion de la app")
        builder.setMessage("Esta app ha sido realizada para analizar datos de recycler view")
        builder.setPositiveButton("OK", { _, _ -> {} })
        Log.v("dialogo", "Pulsado el boton positivo")
        builder.setNegativeButton("NO", { _, _ -> {} })
        Log.v("dialogo", "Pulsado el boton negativo")
        builder.setNeutralButton("CANCEL", { _, _ -> {} })
        Log.v("dialogo", "Pulsado el boton cancelar")
        return builder.create()
    }

}