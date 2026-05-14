package com.spendwise.app.presentation.statistics.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.spendwise.app.domain.model.CategoryStat

@Composable
fun PieChart(
    stats: List<CategoryStat>,
    modifier: Modifier = Modifier
) {
    val animationProgress = remember { Animatable(0f) }

    LaunchedEffect(stats) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(32.dp)
    ) {
        val width = size.width
        val height = size.height
        val radius = width / 2f
        val innerRadius = radius * 0.6f // For Donut style

        var startAngle = -90f

        stats.forEach { stat ->
            val sweepAngle = stat.percentage * 360f * animationProgress.value
            
            drawArc(
                color = stat.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                style = Stroke(width = (radius - innerRadius))
            )
            
            startAngle += sweepAngle
        }
    }
}
