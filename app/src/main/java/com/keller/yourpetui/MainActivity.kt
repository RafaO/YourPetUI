package com.keller.yourpetui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
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
                    ComposeNavigation()
                }
            }
        }
    }
}

@Composable
fun Detail(pet: Pet) = Column(Modifier.padding(16.dp)) {
    Box(Modifier.height(150.dp)) {
        GlideImage(
            data = pet.imageUrl,
            contentDescription = "image for $pet.name",
            contentScale = ContentScale.Crop
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
        )
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = pet.name,
            style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.onPrimary)
        )
    }
    Text(pet.description, style = MaterialTheme.typography.body1)
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "pets_list"
    ) {
        composable("pets_list") {
            Content(getPets()) {
                navController.currentBackStackEntry?.arguments?.putSerializable("pet", it)
                navController.navigate("detail")
            }
        }
        composable("detail") {
            val pet = navController
                .previousBackStackEntry?.arguments?.getSerializable("pet") as Pet
            Detail(pet)
        }
    }
}

private fun getPets() = listOf(
    Pet(
        "Suso",
        "Suso is a wonderful dog with a very sweet character",
        "https://picsum.photos/id/237/200/150"
    ),
    Pet(
        "Bella",
        "Bella is a old lady that is looking for a family who can give her a loving home",
        "https://picsum.photos/id/1025/200/150"
    )
)

@Composable
fun Pet(pet: Pet, onPetClicked: (Pet) -> Unit) = Column(
    modifier = Modifier.padding(16.dp).clickable { onPetClicked(pet) }) {
    GlideImage(
        data = pet.imageUrl,
        contentDescription = "image for $pet.name",
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
    Text(pet.name, style = MaterialTheme.typography.h6)
}


@Composable
private fun Content(pets: List<Pet>, onPetClicked: (Pet) -> Unit) = Column {
    Text(
        "Pets in adoption",
        style = MaterialTheme.typography.h1
    )
    PetsList(pets, onPetClicked)
}

@Composable
private fun PetsList(pets: List<Pet>, onPetClicked: (Pet) -> Unit) = LazyColumn {
    items(
        count = pets.size,
        itemContent = { Pet(pets[it], onPetClicked) },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        Content(getPets()) {}
    }
}
