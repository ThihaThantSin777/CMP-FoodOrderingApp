package org.thiha.thant.sin.foa.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_IMAGE_LOADING_SIZE
import org.thiha.thant.sin.foa.core.MARGIN_SMALL

@Composable
fun AppNetworkImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    size: Dp,
    shape: Shape = RoundedCornerShape(MARGIN_SMALL),
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Box(
        modifier = modifier.size(size).clip(shape).border(borderWidth, borderColor, shape),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = contentScale,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.requiredSize(DEFAULT_ORDER_IMAGE_LOADING_SIZE),
                )
            },
            error = {
                Icon(
                    imageVector = Icons.Default.Info, contentDescription = null, tint = Color.Gray
                )
            },
        )
    }
}
