package com.example.orufy.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.orufy.navigation.NavRoutes
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.orufy.utils.validateAndFormatUrl
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.runtime.LaunchedEffect
import com.example.orufy.data.local.db.AppDatabase
import com.example.orufy.data.local.entity.UrlHistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    var urlText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WebToNative") },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavRoutes.History.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.History,
                            contentDescription = "History"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .statusBarsPadding()
        ) {

            UrlCarousel()

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = urlText,
                onValueChange = { urlText = it },
                label = { Text("Enter URL") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val validUrl = validateAndFormatUrl(urlText)

                    if (validUrl == null) {
                        Toast.makeText(
                            context,
                            "Please enter a valid URL",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        // 1️⃣ Save to database (background thread)
                        coroutineScope.launch(Dispatchers.IO) {
                            db.urlHistoryDao().insertUrl(
                                UrlHistoryEntity(
                                    url = validUrl,
                                    timestamp = System.currentTimeMillis()
                                )
                            )
                        }

                        // 2️⃣ Navigate to WebView
                        val encodedUrl = URLEncoder.encode(
                            validUrl,
                            StandardCharsets.UTF_8.toString()
                        )

                        navController.navigate("webview/$encodedUrl")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Open")
            }

        }
    }
}