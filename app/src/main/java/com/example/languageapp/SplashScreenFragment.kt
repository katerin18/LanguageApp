package com.example.languageapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.languageapp.onboarding.AUTHORIZED_STATE_KEY
import com.example.languageapp.onboarding.FINISHED_STATE_KEY
import com.example.languageapp.onboarding.PREF_AUTH_NAME
import com.example.languageapp.onboarding.PREF_COMPLETE_NAME

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if (isOnBoardingCompleted()) {
                if (isUserAuthorized()) {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
                } else {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginScreenFragment)
                }
            } else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            }
        }, 2000)

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    private fun isOnBoardingCompleted(): Boolean {
        val sharedPref =
            requireActivity().getSharedPreferences(PREF_COMPLETE_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(FINISHED_STATE_KEY, false)
    }

    private fun isUserAuthorized(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences(PREF_AUTH_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(AUTHORIZED_STATE_KEY, false)
    }
}