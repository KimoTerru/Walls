package it.kimoterru.walls.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.kimoterru.walls.R
import it.kimoterru.walls.ui.home.categories.Categories

class CategoriesAdapter(context: Context?, states: List<Categories>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val categories: List<Categories> = states

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoriesView: ImageView = view.findViewById<View>(R.id.card_categories) as ImageView
        val nameView: TextView = view.findViewById<View>(R.id.name) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.card_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categories: Categories = categories[position]
        holder.categoriesView.setImageResource(categories.icon)
        holder.nameView.text = categories.name
    }

    override fun getItemCount() = categories.size
}