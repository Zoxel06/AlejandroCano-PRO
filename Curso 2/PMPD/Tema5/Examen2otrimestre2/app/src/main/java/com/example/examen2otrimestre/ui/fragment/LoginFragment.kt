package com.example.examen2otrimestre.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.examen2otrimestre.R
import com.example.examen2otrimestre.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        database = FirebaseDatabase.getInstance("https://accexamences-default-rtdb.europe-west1.firebasedatabase.app/")
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.buttonLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(
                binding.mail.text.toString(),
                binding.pass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {

                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

                } else {

                    auth.createUserWithEmailAndPassword(
                        binding.mail.text.toString(),
                        binding.pass.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Snackbar.make(
                                binding.root,
                                "Usuario añadido, desea iniciar sesion?",
                                Snackbar.LENGTH_SHORT
                            )
                                .setAction("Aceptar")
                                {
                                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                                }.show()
                        }
                    }

                }
            }

        }
    }
}
