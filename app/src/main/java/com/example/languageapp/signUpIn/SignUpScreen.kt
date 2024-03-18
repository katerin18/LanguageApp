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

class SignUpScreen : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up_screen, container, false)

        val firstNameEditText = view.findViewById<EditText>(R.id.editText_firstname)
        val lastNameEditText = view.findViewById<EditText>(R.id.editText_lastname)
        val emailEditText = view.findViewById<EditText>(R.id.editText_email)

        val signInTextView = view.findViewById<TextView>(R.id.tView_signIn)
        val continueButton = view.findViewById<Button>(R.id.btn_continue_signUp)

        signInTextView.setOnClickListener {
            findNavController().navigate(R.id.action_signUpScreen_to_loginScreenFragment)
        }

        continueButton.setOnClickListener {
            val firstNameData = firstNameEditText.text.toString()
            val lastNameData = lastNameEditText.text.toString()
            val emailData = emailEditText.text.toString()

            val newUserModel = NewUser(
                firstname = firstNameData,
                lastname = lastNameData,
                email = emailData
            )
            if (isCorrectEnteredData(newUserModel)) {
                viewModel.setRegistrationData(newUserModel)
                findNavController().navigate(R.id.action_signUpScreen_to_signUpPassword)
            } else {
                Toast.makeText(requireContext(), "Enter correct data!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun isCorrectEnteredData(user: NewUser): Boolean {
        return user.firstname.isNotEmpty() && user.lastname.isNotEmpty() && user.email.isNotEmpty()
        // TODO: realize correct data checking
    }
}