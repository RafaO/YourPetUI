package com.keller.yourpetui.petdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.keller.yourpetui.model.Pet
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun PetDetailScreen(pet: Pet) = Column(Modifier.padding(16.dp)) {
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
