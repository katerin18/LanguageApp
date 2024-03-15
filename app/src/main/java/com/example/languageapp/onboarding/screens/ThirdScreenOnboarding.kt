package com.example.languageapp.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.languageapp.R
import com.example.languageapp.onboarding.OnBoardingStateManager

class ThirdScreenOnboarding : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third_screen_onboarding, container, false)
        val btnFinish = view.findViewById<Button>(R.id.btn_choose_lang)
        val textViewSkip = view.findViewById<TextView>(R.id.third_skip_text_view)
        btnFinish.setOnClickListener {
            OnBoardingStateManager.onBoardingCompleted(requireActivity())
            findNavController().navigate(R.id.action_viewPagerFragment_to_chooseLanguageFragment)
        }
        textViewSkip.setOnClickListener {
            OnBoardingStateManager.onBoardingCompleted(requireActivity())
            findNavController().navigate(R.id.action_viewPagerFragment_to_chooseLanguageFragment)
        }

        return view
    }
}