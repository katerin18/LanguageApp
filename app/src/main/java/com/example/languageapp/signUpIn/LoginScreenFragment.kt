package com.example.languageapp.signUpIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.languageapp.R
import com.example.languageapp.onboarding.LoginStateManager.userAuthorized
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_screen, container, false)

        val loginButton = view.findViewById<Button>(R.id.btn_login)
        val signUpTextView = view.findViewById<TextView>(R.id.tView_signup)
        val emailEditText = view.findViewById<EditText>(R.id.editText_email_login)
        val passEditText = view.findViewById<EditText>(R.id.editText_pass_login)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passEditText.text.toString()

            if (isDataCorrect(email, password)) {
                lifecycleScope.launch {
                    if (getUserData(email, password).isNotEmpty()) {
                        userAuthorized(requireContext(), email)
                        findNavController().navigate(R.id.action_loginScreenFragment_to_homeFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Check your data or sign up.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Enter a valid data!", Toast.LENGTH_SHORT).show()
            }
        }

        signUpTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginScreenFragment_to_signUpScreen)
        }

        return view
    }

    private fun isDataCorrect(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
        // TODO: realize checking if the data is correct
    }

    private suspend fun getUserData(email: String, password: String): List<NewUser> =
        withContext(Dispatchers.IO) {
            val supabaseClient = createSupabaseClient(
                supabaseUrl = PROJECT_URL,
                supabaseKey = SUPABASE_KEY
            ) { install(Postgrest) }

            val userData: List<NewUser> = async {
                supabaseClient.from("users")
                    .select(
                        columns = Columns.list(
                            "firstname",
                            "lastname",
                            "email",
                            "password",
                            "score"
                        )
                    ) {
                        filter {
                            eq("email", email)
                            eq("password", password)
                        }
                    }.decodeList<NewUser>()
            }.await()

            userData
        }
}