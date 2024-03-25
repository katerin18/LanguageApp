package com.example.languageapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val userDataLogicImpl = UserDataLogicImpl()
        val authorizedEmailPass = userDataLogicImpl.getAuthorizedEmailPass(requireActivity())

        val recyclerView = view.findViewById<RecyclerView>(R.id.rec_view_top_users)

        val textViewName = view.findViewById<TextView>(R.id.name_textView)
        val textViewNumOfPoints = view.findViewById<TextView>(R.id.textView_numOf_points)
        val imageViewPhoto = view.findViewById<ImageView>(R.id.user_image)

        val btnFirstGame = view.findViewById<Button>(R.id.btn_first_game)
        val btnSecondGame = view.findViewById<Button>(R.id.btn_second_game)
        val btnThirdGame = view.findViewById<Button>(R.id.btn_third_game)
        val btnFourthGame = view.findViewById<Button>(R.id.btn_fourth_game)

        lifecycleScope.launch {
            val authorizedUserData = userDataLogicImpl.getExistedUser(
                email = authorizedEmailPass[0]!!,
                password = authorizedEmailPass[1]!!
            )
            textViewName.text =
                "${authorizedUserData[0].firstname} ${authorizedUserData[0].lastname}"
            textViewNumOfPoints.text = authorizedUserData[0].score.toString()
            Picasso.get().load(authorizedUserData[0].userImage).into(imageViewPhoto)
        }

        lifecycleScope.launch {
            val topUsersData = userDataLogicImpl.getTopUsers()
            recyclerView.adapter = TopUsersAdapter(topUsersData)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }


        btnFirstGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_firstGameFragment)
        }

        btnSecondGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_secondGameFragment)
        }

        btnThirdGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_thirdGameFragment)
        }

        btnFourthGame.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fourthGameFragment)
        }

        return view
    }

}