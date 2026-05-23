package com.example.myfirstapplication.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.activities.RecipeDetailsActivity
import com.example.myfirstapplication.databinding.ItemRecipeBinding
import com.example.myfirstapplication.models.Recipe

class RecipeAdapter(
    private val recipeList: List<Recipe>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(
        val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.tvRecipeTitle.text = recipe.title

            binding.root.setOnClickListener { context ->
                val intent = Intent(binding.root.context, RecipeDetailsActivity::class.java).apply {
                    putExtra("title", recipe.title)
                    putExtra("ingredients", recipe.ingredients)
                    putExtra("instructions", recipe.instructions)
                }
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int = recipeList.size
}