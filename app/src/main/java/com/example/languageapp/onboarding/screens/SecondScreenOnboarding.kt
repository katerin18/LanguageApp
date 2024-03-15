package com.example.languageapp.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.languageapp.R
import com.example.languageapp.onboarding.OnBoardingStateManager

class SecondScreenOnboarding : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second_screen_onboarding, container, false)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        val btnMore = view.findViewById<Button>(R.id.btn_more)
        val textViewSkip = view.findViewById<TextView>(R.id.second_skip_text_view)

        btnMore.setOnClickListener {
            viewPager?.currentItem = 2
        }

        textViewSkip.setOnClickListener {
            OnBoardingStateManager.onBoardingCompleted(requireActivity())
            findNavController().navigate(R.id.action_viewPagerFragment_to_chooseLanguageFragment)
        }

        return view
    }
}