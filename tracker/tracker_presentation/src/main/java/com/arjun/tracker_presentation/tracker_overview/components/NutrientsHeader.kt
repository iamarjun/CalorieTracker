package com.arjun.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjun.calorietracker.ui.theme.CarbColor
import com.arjun.calorietracker.ui.theme.FatColor
import com.arjun.calorietracker.ui.theme.ProteinColor
import com.arjun.core_ui.LocalSpacing
import com.arjun.tracker_presentation.R
import com.arjun.tracker_presentation.composables.UnitDisplay
import com.arjun.tracker_presentation.tracker_overview.TrackerContract

@Composable
fun NutrientsHeader(
    modifier: Modifier,
    state: TrackerContract.State
) {
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(targetValue = state.totalCalories)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceExtraLarge)
    ) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            UnitDisplay(
                modifier = modifier.align(Alignment.Bottom),
                amount = animatedCalorieCount.value,
                amountTextColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 40.sp,
                unit = stringResource(id = R.string.kcal),
                unitTextColor = MaterialTheme.colors.onPrimary,
            )
            Column(modifier = modifier) {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary,
                )
                UnitDisplay(
                    modifier = modifier,
                    amount = animatedCalorieCount.value,
                    amountTextColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unit = stringResource(id = R.string.kcal),
                    unitTextColor = MaterialTheme.colors.onPrimary,
                )
            }
        }
        Spacer(modifier = modifier.height(spacing.spaceSmall))
        NutrientsBar(
            modifier = modifier,
            carbs = state.totalCarbs,
            proteins = state.totalProtein,
            fats = state.totalFat,
            calories = state.totalCalories,
            caloriesGoal = state.caloriesGoal
        )
        Spacer(modifier = modifier.height(spacing.spaceLarge))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientsBarInfo(
                modifier = modifier.size(100.dp),
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(id = R.string.carbs),
                color = CarbColor
            )
            NutrientsBarInfo(
                modifier = modifier.size(100.dp),
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(id = R.string.protein),
                color = ProteinColor
            )
            NutrientsBarInfo(
                modifier = modifier.size(100.dp),
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(id = R.string.fat),
                color = FatColor
            )
        }
    }
}