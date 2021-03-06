package com.keller.yourpetui.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.keller.yourpetui.model.Pet
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun PetCard(pet: Pet, onPetClicked: (Pet) -> Unit) = Column(
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
