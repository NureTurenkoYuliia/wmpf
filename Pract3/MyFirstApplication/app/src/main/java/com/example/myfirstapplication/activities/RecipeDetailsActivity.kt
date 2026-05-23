package com.example.myfirstapplication.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapplication.R

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recipe_details)

        val tvDetails =
            findViewById<TextView>(R.id.tvRecipeDetails)

        val title =
            intent.getStringExtra("title")

        val ingredients =
            intent.getStringExtra("ingredients")

        val instructions =
            intent.getStringExtra("instructions")

        val recipeText = getString(R.string.recipe_details, title, ingredients, instructions)

        tvDetails.text = recipeText
    }
}