package com.example.a25172022067_practical5_mad

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a25172022067_practical5_mad.ui.theme.Practical5Theme

@Composable
fun LoginActivityUI(
    onNavigateToRegister: () -> Unit = {},
    showMsg: ((String) -> Unit)? = null
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FA))
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.guni_pink_logo),
            contentDescription = "GUNI Logo",
            modifier = Modifier
                .height(85.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFECECF0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 18.dp)
            ) {
                FormField(
                    label = "Email",
                    textState = email,
                    onTextChange = { email = it },
                    placeholder = "Enter Email"
                )

                Spacer(modifier = Modifier.height(6.dp))

                FormField(
                    label = "Password",
                    textState = password,
                    onTextChange = { password = it },
                    isPasswordField = true,
                    placeholder = "Enter Password"
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Forgot Password?",
                    fontSize = 13.sp,
                    color = Color(0xFF555566),
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            showMsg?.invoke("Please enter email and password")
                        } else {
                            showMsg?.invoke("Login Successful")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF485682))
                ) {
                    Text(
                        text = "Login",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                fontSize = 14.sp,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(
                onClick = { onNavigateToRegister() }
            ) {
                Text(
                    text = "SIGN UP",
                    color = Color(0xFFE91E63),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginActivityUIPreview() {
    Practical5Theme {
        LoginActivityUI()
    }
}
