package com.spendwise.app.presentation.statistics.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.spendwise.app.domain.model.CurrencyStat

@Composable
fun BarChart(
    stats: List<CurrencyStat>,
    modifier: Modifier = Modifier
) {
    val animationProgress = remember { Animatable(0f) }

    LaunchedEffect(stats) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val density = LocalDensity.current
        val maxAmount = stats.maxOfOrNull { it.totalAmountInCny } ?: 1.0
        val barCount = stats.size
        val barWidth = with(density) { (maxWidth / (barCount * 2)).toPx() }

        Canvas(modifier = Modifier.fillMaxSize()) {
            stats.forEachIndexed { index, stat ->
                val barHeight = ((stat.totalAmountInCny / maxAmount) * size.height * animationProgress.value).toFloat()
                val xOffset = (index * 2 + 0.5f) * barWidth

                drawRect(
                    color = stat.color,
                    topLeft = Offset(x = xOffset, y = size.height - barHeight),
                    size = Size(width = barWidth, height = barHeight)
                )
            }
        }
    }
}
