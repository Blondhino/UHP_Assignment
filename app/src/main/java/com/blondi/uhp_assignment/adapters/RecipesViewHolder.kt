package com.blondi.uhp_assignment.adapters

import android.support.v7.widget.RecyclerView
import android.view.TextureView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blondi.uhp_assignment.R
import kotlinx.android.synthetic.main.list_item.view.*

class RecipesViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    val recipeImage = itemView.findViewById<ImageView>(R.id.recipeImage)
    val recipeTitle = itemView.findViewById<TextView>(R.id.recipeTitle)
    val recipeRating = itemView.findViewById<TextView>(R.id.recipeRating)
}
