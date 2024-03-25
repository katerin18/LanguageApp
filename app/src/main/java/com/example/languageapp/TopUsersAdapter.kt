package com.example.languageapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.languageapp.signUpIn.UserModel
import com.squareup.picasso.Picasso

class TopUsersAdapter(private val topUsersList: List<UserModel>) :
    RecyclerView.Adapter<TopUsersAdapter.TopUsersViewHolder>() {
    class TopUsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewAvatar: ImageView = itemView.findViewById(R.id.card_user_avatar)
        var textViewUserName: TextView = itemView.findViewById(R.id.card_user_name)
        var textViewScore: TextView = itemView.findViewById(R.id.card_user_score)
        val textPoints: TextView = itemView.findViewById(R.id.textView_points)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopUsersViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_top_users, parent, false)
        return TopUsersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return topUsersList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TopUsersViewHolder, position: Int) {
        val currentItem = topUsersList[position]
        Picasso.get().load(currentItem.userImage).into(holder.imageViewAvatar)
        holder.textViewUserName.text = "${currentItem.firstname} ${currentItem.lastname}"
        holder.textViewScore.text = currentItem.score.toString()
        holder.textPoints.text = " points"
    }

}