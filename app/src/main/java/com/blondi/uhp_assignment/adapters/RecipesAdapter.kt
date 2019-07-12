package com.blondi.uhp_assignment.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blondi.uhp_assignment.R
import com.blondi.uhp_assignment.models.Recipes
import com.squareup.picasso.Picasso

class RecipesAdapter : RecyclerView.Adapter<RecipesViewHolder>() {

    private val data : MutableList<Recipes> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RecipesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.recipeTitle.text=data[position].title
        Picasso.get().load(data[position].image_url).placeholder(R.drawable.loading).into(holder.recipeImage)
        holder.recipeRating.text=data[position].social_rank.toString()
    }

    fun setData(recipes : MutableList<Recipes>?){
        this.data.clear()
        if (recipes != null) {
            this.data.addAll(recipes)
        }
        notifyDataSetChanged()
    }
}