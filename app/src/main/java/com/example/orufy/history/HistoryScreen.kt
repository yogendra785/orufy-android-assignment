package com.example.orufy.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.orufy.data.local.db.AppDatabase
import com.example.orufy.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavHostController) {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val coroutineScope = rememberCoroutineScope()

    val historyList by db.urlHistoryDao()
        .getAllUrls()
        .collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {

                    // Upload Button
                    IconButton(
                        onClick = {
                            coroutineScope.launch(Dispatchers.IO) {
                                try {
                                    RetrofitInstance.api.uploadHistory(historyList)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.CloudUpload,
                            contentDescription = "Upload History"
                        )
                    }

                    // Clear Button
                    IconButton(
                        onClick = {
                            coroutineScope.launch(Dispatchers.IO) {
                                db.urlHistoryDao().clearAll()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Clear History"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (historyList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No history found")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(historyList) { item ->
                    HistoryItem(
                        url = item.url,
                        timestamp = item.timestamp
                    )
                }
            }
        }
    }
}

@Composable
fun HistoryItem(
    url: String,
    timestamp: Long
) {
    val formattedTime = remember(timestamp) {
        SimpleDateFormat(
            "dd MMM yyyy, hh:mm a",
            Locale.getDefault()
        ).format(Date(timestamp))
    }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = url,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.bodySmall
        )
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

