package com.example.myfirstapplication.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myfirstapplication.R
import com.example.myfirstapplication.database.RecipeDatabase
import com.example.myfirstapplication.models.Recipe
import kotlinx.coroutines.launch

class AddRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_recipe)

        val etTitle = findViewById<EditText>(R.id.etTitle)

        val etIngredients = findViewById<EditText>(R.id.etIngredients)

        val etInstructions = findViewById<EditText>(R.id.etInstructions)

        val btnSave = findViewById<Button>(R.id.btnSaveRecipe)

        btnSave.setOnClickListener {

            val recipe = Recipe(
                title = etTitle.text.toString(),
                ingredients = etIngredients.text.toString(),
                instructions = etInstructions.text.toString()
            )

            lifecycleScope.launch {

                RecipeDatabase
                    .getDatabase(this@AddRecipeActivity)
                    .recipeDao()
                    .insertRecipe(recipe)

                finish()
            }
        }
    }
}