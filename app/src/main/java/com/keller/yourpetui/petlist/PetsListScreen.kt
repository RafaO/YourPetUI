package com.keller.yourpetui.petlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.keller.yourpetui.model.Pet
import com.keller.yourpetui.ui.composable.PetCard

@Composable
fun PetsListScreen(pets: List<Pet>, onPetClicked: (Pet) -> Unit) = Column {
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
        itemContent = { PetCard(pets[it], onPetClicked) },
    )
}
