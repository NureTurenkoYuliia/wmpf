package com.example.myfirstapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.R
import com.example.myfirstapplication.adapters.RecipeAdapter
import com.example.myfirstapplication.database.RecipeDatabase
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {

    private lateinit var recyclerRecipes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recipe)
        recyclerRecipes = findViewById(R.id.recyclerRecipes)

        val btnAddRecipe = findViewById<Button>(R.id.btnAddRecipe)
        recyclerRecipes.layoutManager = LinearLayoutManager(this)

        btnAddRecipe.setOnClickListener {
            startActivity(
                Intent(this, AddRecipeActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        loadRecipes()
    }

    private fun loadRecipes() {
        lifecycleScope.launch {

            val recipes =
                RecipeDatabase
                    .getDatabase(this@RecipeActivity)
                    .recipeDao()
                    .getAllRecipes()

            recyclerRecipes.adapter =
                RecipeAdapter(recipes)
        }
    }
}