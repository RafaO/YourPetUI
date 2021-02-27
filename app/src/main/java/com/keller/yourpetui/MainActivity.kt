package com.keller.yourpetui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.keller.yourpetui.model.Pet
import com.keller.yourpetui.ui.YourPetUITheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourPetUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Content(listOf(Pet("Suso"), Pet("Bella")))
                }
            }
        }
    }
}

@Composable
fun Pet(pet: Pet) = Text(pet.name)

@Composable
private fun Content(pets: List<Pet>) = LazyColumn {
    items(
        count = pets.size,
        itemContent = { Pet(pets[it]) },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        Content(listOf(Pet("Suso"), Pet("Bella")))
    }
}
