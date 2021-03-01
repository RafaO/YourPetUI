package com.keller.yourpetui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keller.yourpetui.model.Pet
import com.keller.yourpetui.ui.YourPetUITheme
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourPetUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Content(getPets())
                }
            }
        }
    }
}

private fun getPets() = listOf(
    Pet("Suso", "https://picsum.photos/id/237/200/300"),
    Pet("Bella", "https://picsum.photos/id/237/200/300")
)

@Composable
fun Pet(pet: Pet) = Column(modifier = Modifier.padding(16.dp)) {
    GlideImage(
        data = pet.imageUrl,
        contentDescription = "image for $pet.name",
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
    Text(pet.name, style = MaterialTheme.typography.h6)
}


@Composable
private fun Content(pets: List<Pet>) = Column {
    Text(
        "Pets in adoption",
        style = MaterialTheme.typography.h1
    )
    PetsList(pets)
}

@Composable
private fun PetsList(pets: List<Pet>) = LazyColumn {
    items(
        count = pets.size,
        itemContent = { Pet(pets[it]) },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        Content(getPets())
    }
}
