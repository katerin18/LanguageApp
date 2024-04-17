package com.example.languageapp.gamesScreens.firstGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.languageapp.R
import java.util.Locale

class FirstGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_game_screen, container, false)

        val editTextAnswer = view.findViewById<EditText>(R.id.editText_first_answer)
        val buttonCheck = view.findViewById<Button>(R.id.button_check_first_answer)

        buttonCheck.setOnClickListener {
            val userAnswer = editTextAnswer.text.toString()
            if (userAnswer.lowercase(Locale.ROOT) == "racoon") {
                Toast.makeText(context, "That's right!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Wrong answer! This is racoon...", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return view
    }
}