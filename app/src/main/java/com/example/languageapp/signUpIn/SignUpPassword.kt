package com.example.languageapp.signUpIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.languageapp.R

class SignUpPassword : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sign_up_password, container, false)

        val textViewSignIn = view.findViewById<TextView>(R.id.tView_signIn_third)
        val buttonSignUp = view.findViewById<Button>(R.id.btn_complete_signUp)
        val editTextPass = view.findViewById<EditText>(R.id.editText_pass)
        val editTextConfirmedPass = view.findViewById<EditText>(R.id.editText_confirm_pass)

        textViewSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpPassword_to_loginScreenFragment)
        }

        buttonSignUp.setOnClickListener {
            val password = editTextPass.text.toString()
            val confirmedPassword = editTextConfirmedPass.text.toString()

            if (isPasswordCorrect(password)) {
                if (password == confirmedPassword) {
                    val userWithPassword = viewModel.combineWithPassword(password)
                    if (userWithPassword != null) {
                        viewModel.registerNewUser(userWithPassword)

                        findNavController().navigate(R.id.action_signUpPassword_to_homeFragment)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Passwords aren't the same!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Password should contain at least 8 characters!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return view
    }


    private fun isPasswordCorrect(password: String): Boolean {
        return password.isNotEmpty()
        // TODO: realize correct password checking
    }
}