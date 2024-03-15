package com.example.languageapp.chooseLanguage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.languageapp.R

class LanguageRecyclerAdapter(private val list: List<LanguageModel>) :
    RecyclerView.Adapter<LanguageRecyclerAdapter.LanguageViewHolder>() {

    class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewLanguage: TextView = itemView.findViewById(R.id.text_view_language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return LanguageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.textViewLanguage.text = list[position].languageName
    }
}