package it.kimoterru.walls.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.kimoterru.walls.R
import it.kimoterru.walls.ui.home.categories.Categories

class CategoriesAdapter(private val categories: List<Categories>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoriesView: ImageView = view.findViewById(R.id.card_categories)
        val nameView: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categories[position]
        holder.categoriesView.setImageResource(item.icon)
        holder.nameView.text = item.name
    }

    override fun getItemCount() = categories.size
}