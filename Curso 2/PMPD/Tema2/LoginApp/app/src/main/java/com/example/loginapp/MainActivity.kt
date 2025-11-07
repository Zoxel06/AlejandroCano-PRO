package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityMainBinding
import com.example.loginapp.model.Usuario
import com.example.loginapp.ui.activity.SecondActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    private fun acciones() {
        binding.botonLogin.setOnClickListener(this)
        binding.botonGit.setOnClickListener(this)
        binding.botonGoogle.setOnClickListener(this)
        binding.botonFacebook.setOnClickListener(this)
        binding.checkRecordar.setOnCheckedChangeListener(this)
        binding.spinnerPerfil.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    binding.botonGoogle.visibility = View.INVISIBLE
                    binding.botonGit.visibility = View.INVISIBLE
                    binding.botonFacebook.visibility = View.INVISIBLE
                    when (position) {
                        0 -> {
                            binding.botonGoogle.visibility = View.VISIBLE
                        }

                        1 -> {
                            binding.botonGit.visibility = View.VISIBLE
                        }

                        2 -> {
                            binding.botonFacebook.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            binding.botonLogin.id -> {
                if (binding.editCorreo.text.isNotEmpty() && binding.editPass.text.isNotEmpty()) {
                    if (binding.editCorreo.text.toString()
                            .equals("admin@admin.com") && binding.editPass.text.toString()
                            .equals("admin", true)
                    ) {
                        val intent: Intent = Intent(applicationContext, SecondActivity::class.java)

                        val usuario: Usuario = Usuario(binding.editCorreo.text.toString(),
                            binding.editPass.text.toString(),
                            binding.spinnerPerfil.selectedItem.toString())

                        intent.putExtra("usuario",usuario)

                        startActivity(intent)
                    } else {
                        Snackbar.make(binding.root, resources.getString(R.string.text_data), Snackbar.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Snackbar.make(binding.root, "Faltan datos", Snackbar.LENGTH_SHORT).show()
                    //Se puede poner binding.root o v como parametro de vista
                }

            }

            binding.botonGit.id -> {}
            binding.botonGoogle.id -> {}
            binding.botonFacebook.id -> {}

        }

    }

    override fun onCheckedChanged(
        buttonView: CompoundButton,
        isChecked: Boolean
    ) {

    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}