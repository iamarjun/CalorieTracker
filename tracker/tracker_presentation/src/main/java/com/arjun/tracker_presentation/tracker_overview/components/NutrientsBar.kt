package com.arjun.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.arjun.calorietracker.ui.theme.CarbColor
import com.arjun.calorietracker.ui.theme.FatColor
import com.arjun.calorietracker.ui.theme.ProteinColor

@Composable
fun NutrientsBar(
    modifier: Modifier,
    carbs: Int,
    proteins: Int,
    fats: Int,
    calories: Int,
    caloriesGoal: Int,
) {

    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error

    val carbRatio = remember {
        Animatable(0f)
    }
    val proteinRatio = remember {
        Animatable(0f)
    }
    val fatRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {
        carbRatio.animateTo((carbs * 4f) / caloriesGoal)
    }
    LaunchedEffect(key1 = proteins) {
        proteinRatio.animateTo((proteins * 4f) / caloriesGoal)
    }
    LaunchedEffect(key1 = fats) {
        fatRatio.animateTo((fats * 9f) / caloriesGoal)
    }

    Canvas(modifier = modifier) {
        if (calories <= caloriesGoal) {
            val carbsWidth = carbRatio.value * size.width
            val proteinsWidth = proteinRatio.value * size.width
            val fatsWidth = fatRatio.value * size.width

            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinsWidth + fatsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        } else {
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}