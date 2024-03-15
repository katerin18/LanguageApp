package com.example.languageapp.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.languageapp.R
import com.example.languageapp.onboarding.screens.FirstScreenOnboarding
import com.example.languageapp.onboarding.screens.SecondScreenOnboarding
import com.example.languageapp.onboarding.screens.ThirdScreenOnboarding

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf (
            FirstScreenOnboarding(),
            SecondScreenOnboarding(),
            ThirdScreenOnboarding()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val vPager = view.findViewById<ViewPager2>(R.id.viewPager)
        vPager.adapter = adapter

        return view
    }
}