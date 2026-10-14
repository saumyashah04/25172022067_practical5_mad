package com.example.a25172022067_practical5_mad

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.a25172022067_practical5_mad.ui.theme.Practical5Theme

fun navigatetoregistrationScreen(navController: NavController) {
    navController.navigate("register")
}

@Composable
fun RegisterScreen(navController: NavController, context: Context = LocalContext.current) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            RegisterUI(
                navController = navController,
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun RegisterUI(
    navController: NavController? = null,
    onNavigateToLogin: () -> Unit = { navController?.navigate("login") },
    onRegisterSuccess: () -> Unit = { navController?.navigate("login") },
    showMsg: ((String) -> Unit)? = null
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7FA))
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(6.dp))

        Image(
            painter = painterResource(id = R.drawable.guni_pink_logo),
            contentDescription = "GUNI Logo",
            modifier = Modifier
                .height(85.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                FormField(
                    label = "Name",
                    textState = name,
                    onTextChange = { name = it },
                    placeholder = "Enter Name"
                )

                Spacer(modifier = Modifier.height(2.dp))

                FormField(
                    label = "Phone Number",
                    textState = phoneNumber,
                    onTextChange = { phoneNumber = it },
                    isNumber = true,
                    placeholder = "Enter Phone Number"
                )

                Spacer(modifier = Modifier.height(2.dp))

                FormField(
                    label = "City",
                    textState = city,
                    onTextChange = { city = it },
                    placeholder = "Enter City"
                )

                Spacer(modifier = Modifier.height(2.dp))

                FormField(
                    label = "Email",
                    textState = email,
                    onTextChange = { email = it },
                    placeholder = "Enter Email"
                )

                Spacer(modifier = Modifier.height(2.dp))

                FormField(
                    label = "Password",
                    textState = password,
                    onTextChange = { password = it },
                    isPasswordField = true,
                    placeholder = "Enter Password"
                )

                Spacer(modifier = Modifier.height(2.dp))

                FormField(
                    label = "Confirm Password",
                    textState = confirmPassword,
                    onTextChange = { confirmPassword = it },
                    isPasswordField = true,
                    placeholder = "Enter Confirm Password"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        when {
                            name.isBlank() || phoneNumber.isBlank() || city.isBlank() ||
                            email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                                showMsg?.invoke("Please fill in all fields")
                            }
                            password != confirmPassword -> {
                                showMsg?.invoke("Passwords do not match")
                            }
                            phoneNumber.length < 10 -> {
                                showMsg?.invoke("Please enter a valid 10-digit phone number")
                            }
                            else -> {
                                showMsg?.invoke("Registration Successful! Welcome, $name")
                                onRegisterSuccess()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF485682))
                ) {
                    Text(
                        text = "Register",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Do you have an account?",
                fontSize = 14.sp,
                color = Color(0xFF333333)
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(
                onClick = {
                    onNavigateToLogin()
                }
            ) {
                Text(
                    text = "LOGIN",
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
fun RegisterPreview() {
    Practical5Theme {
        val navController = rememberNavController()
        RegisterUI(navController = navController)
    }
}
