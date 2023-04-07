package com.arjun.tracker_presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.arjun.core_ui.LocalSpacing

@Composable
fun UnitDisplay(
    modifier: Modifier,
    amount: Int,
    amountTextSize: TextUnit = 20.sp,
    amountTextColor: Color = MaterialTheme.colorScheme.onBackground,
    unit: String,
    unitTextSize: TextUnit = 14.sp,
    unitTextColor: Color = MaterialTheme.colorScheme.onBackground,
) {

    val localSpacing = LocalSpacing.current
    Row(modifier = modifier) {
        Text(
            modifier = modifier.alignBy(LastBaseline),
            text = amount.toString(),
            style = MaterialTheme.typography.headlineLarge,
            fontSize = amountTextSize,
            color = amountTextColor
        )
        Spacer(modifier = modifier.width(localSpacing.spaceExtraSmall))
        Text(
            modifier = modifier.alignBy(LastBaseline),
            text = unit,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = unitTextSize,
            color = unitTextColor
        )
    }

}