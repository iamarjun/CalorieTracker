package com.arjun.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.arjun.core_ui.LocalSpacing

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    isEnabled: Boolean,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        shape = RoundedCornerShape(100.dp)
    ) {
        Text(
            text = text,
            modifier = modifier.padding(LocalSpacing.current.spaceMedium),
            style = textStyle,
            color = MaterialTheme.colors.onPrimary
        )
    }

}