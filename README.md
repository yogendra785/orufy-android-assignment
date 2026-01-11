📱 Orufy Android Assignment (Jetpack Compose)
📌 Overview

This project is a 3-screen Android application built as part of the Android Developer Internship Assignment for Orufy Technologies Pvt. Ltd.

The app demonstrates:

Modern Jetpack Compose UI

Safe navigation with arguments

WebView integration inside Compose

Local data persistence using Room

Mock API upload using Beeceptor

The focus of this assignment is clean architecture, edge-case handling, and real-world Android practices.

🛠 Tech Stack

Language: Kotlin

UI: Jetpack Compose (Material 3)

Navigation: Navigation Compose

Local Storage: Room Database

WebView: AndroidView interoperability

Networking: Retrofit
📱 Screens & Features
🏠 Home Screen

URL input field

Input validation:

Empty input

Invalid URL format

Leading/trailing spaces

Automatically prepends https:// if missing

Image carousel (UI-only, 3 static images with dot indicators)

History button in top app bar

Saves every opened URL with timestamp in local database
🌐 WebView Screen

Loads the provided URL inside the app

Built using AndroidView inside Compose

Displays the currently loaded URL in the top app bar

Handles modern web requirements (JavaScript, DOM storage)

Buttons:

Back: returns to Home while retaining last URL

Close: clears navigation stack and resets Home

📜 History Screen

Displays all previously opened URLs

Shows formatted timestamps

Data updates automatically using Room + Flow

Buttons:

Clear History: deletes all stored URLs

Upload: uploads stored data to a mock API (Beeceptor)
🧠 Key Implementation Highlights

State-driven UI: Compose state updates UI automatically

Safe navigation: URL arguments are encoded & decoded to prevent crashes

Room + Flow: Live updates without manual refresh

Coroutines: All DB and network operations run on Dispatchers.IO

WebView stability: Uses remember to avoid reloads during recomposition

🧪 Testing Checklist

Open without URL → validation error

Invalid URL → validation error

google.com → opens correctly in WebView

URLs persist after app restart

History clears instantly

Upload visible in Beeceptor request logs
🌐 Mock API (Beeceptor)

A mock backend endpoint was created using Beeceptor to simulate uploading history data.

Example endpoint:

https://orufy-history-api.free.beeceptor.com/


This allows testing API integration without a real backend.
com.example.orufy
│
├── home        // HomeScreen + carousel
├── webview     // WebViewScreen
├── history     // HistoryScreen
├── navigation  // NavGraph & routes
├── data
│   ├── local   // Room (Entity, DAO, DB)
│   └── remote  // Retrofit API
└── utils       // URL validation
▶️ How to Run

Clone the repository

Open in Android Studio

Sync Gradle

Run on emulator or physical device (Internet required)

👤 Author

Yogendra Singh
Android Developer (Internship Assignment)
GitHub: https://github.com/yogendra785

Mock API: Beeceptor

Async: Kotlin Coroutines + Flow
