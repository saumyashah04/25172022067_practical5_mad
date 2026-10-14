package com.example.a25172022067_practical5_mad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a25172022067_practical5_mad.ui.theme.Practical5Theme

@Composable
fun FormField(
    label: String,
    textState: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPasswordField: Boolean = false,
    isNumber: Boolean = false,
    placeholder: String? = null
) {
    val keyboardType = when {
        isPasswordField -> KeyboardType.Password
        isNumber || label.contains("Phone", ignoreCase = true) -> KeyboardType.Phone
        label.contains("Email", ignoreCase = true) -> KeyboardType.Email
        else -> KeyboardType.Text
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333),
            modifier = Modifier.weight(1.1f)
        )
        OutlinedTextField(
            value = textState,
            onValueChange = { newValue ->
                if (!isNumber || newValue.all { it.isDigit() }) {
                    onTextChange(newValue)
                }
            },
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(2f),
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color(0xFF222222)),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = {
                Text(
                    text = placeholder ?: "Enter $label",
                    fontSize = 14.sp,
                    color = Color(0xFF8E8E93)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF485682),
                unfocusedBorderColor = Color(0xFFC5C5CE),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormFieldPreview() {
    Practical5Theme {
        FormField(
            label = "Password",
            textState = "",
            onTextChange = {},
            isPasswordField = true
        )
    }
}
