package com.keller.yourpetui

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.keller.yourpetui.Navigation.Companion.ARG_PET
import com.keller.yourpetui.Navigation.Companion.ROUTE_PETS_LIST
import com.keller.yourpetui.Navigation.Companion.ROUTE_PET_DETAILS
import com.keller.yourpetui.model.Pet
import com.keller.yourpetui.petdetails.PetDetailScreen
import com.keller.yourpetui.petlist.PetsListScreen
import com.keller.yourpetui.ui.YourPetUITheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourPetUITheme {
                Surface(color = MaterialTheme.colors.background) { ComposeNavigation(this) }
            }
        }
    }
}

@Composable
fun ComposeNavigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ROUTE_PETS_LIST) {
        composable(ROUTE_PETS_LIST) {
            PetsListScreen(getPets()) {
                navController.currentBackStackEntry?.arguments?.putSerializable(ARG_PET, it)
                navController.navigate(ROUTE_PET_DETAILS)
            }
        }
        composable(ROUTE_PET_DETAILS) {
            val pet = navController
                .previousBackStackEntry?.arguments?.getSerializable(ARG_PET) as Pet
            PetDetailScreen(context, pet)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YourPetUITheme {
        PetsListScreen(getPets()) {}
    }
}
