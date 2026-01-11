# Orufy Android Assignment

[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-blue)](https://kotlinlang.org) [![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-orange)](https://developer.android.com/jetpack/compose) 

Android assignment demonstrating a small, modern Android app built with Jetpack Compose, Room, Navigation, and WebView integration.

---

## Table of Contents
- About
- Features
- Tech stack
- Screens
- Architecture & Key Components
- Getting started
- Usage
- Project structure
- Testing
- Contributing
- Author
- License

---

## About
A compact 3-screen Android application created as part of the Android Developer Internship assignment for Orufy Technologies. The app shows how to combine Jetpack Compose UI, Navigation (Compose), Room for local persistence, and an embedded WebView, with attention to clean architecture and edge-case handling.

## Features
- Compose-based UI (Material 3)
- Home screen with URL input and image carousel
- WebView screen that loads URLs inside the app
- History screen backed by Room (stores opened URLs with timestamps)
- URL validation and normalization (auto-prepend https:// when missing)
- Upload history to a mock API (Beeceptor)
- Live updates with Room + Kotlin Flow
- Coroutines for background operations

## Tech stack
- Language: Kotlin (100%)
- UI: Jetpack Compose (Material 3)
- Navigation: Navigation Compose
- Persistence: Room (SQLite)
- Networking: Retrofit
- Concurrency: Kotlin Coroutines + Flow
- WebView: AndroidView interoperability

## Screens
- Home
  - URL input with validation
  - Image carousel (3 static images)
  - History button
- WebView
  - Loads and displays the provided URL
  - Supports JavaScript and DOM storage
  - Back and Close controls
- History
  - Shows saved URLs with timestamps
  - Clear History and Upload actions

## Architecture & Key Components
- UI: Stateless Compose screens where possible; state hoisted to ViewModels
- Navigation: NavHost with safe argument encoding/decoding
- Data: Room entities, DAOs and repository pattern
- Networking: Retrofit service for uploading history to mock endpoint
- WebView: Composable wrapper using AndroidView and remember to avoid reloads

## Getting started
Prerequisites
- Android Studio (Arctic Fox or newer recommended)
- JDK 11+
- Android SDK (API 21+)

Clone and open
```bash
git clone https://github.com/yogendra785/orufy-android-assignment.git
cd orufy-android-assignment
# Open the project in Android Studio and run on an emulator or device
```

Build
- Use Android Studio Run/Debug or:
```bash
./gradlew assembleDebug
```

## Usage
1. Launch the app on a device or emulator (internet required for WebView and upload).
2. On Home, enter a URL (e.g. google.com). The input is validated and normalized.
3. Tap to open — the URL is saved in local history with a timestamp.
4. View the page inside the in-app WebView. Use Back to return or Close to reset.
5. Open History to view, clear, or upload saved URLs to the mock API.

## Project structure (high level)
- app/src/main/java/com/example/orufy
  - home — HomeScreen and carousel
  - webview — WebViewScreen
  - history — HistoryScreen
  - navigation — NavGraph & routes
  - data/local — Room (Entities, DAO, Database)
  - data/remote — Retrofit API
  - utils — URL validation and helpers
- app/src/main/res — resources (images, strings, themes)

## Testing
- Unit tests: app/src/test
- Instrumented tests: app/src/androidTest

Suggested manual checks
- Open without URL → validation error
- Invalid URL → validation error
- google.com → opens correctly in WebView
- URLs persist after app restart
- History clears instantly
- Upload visible in Beeceptor request logs

## Mock API (Beeceptor)
A Beeceptor endpoint is used to simulate a backend for uploading history data.
Example endpoint:
https://orufy-history-api.free.beeceptor.com/

## Contributing
Contributions are welcome. Suggested workflow:
1. Fork the repo
2. Create a branch: git checkout -b feature/your-feature
3. Make changes and run tests
4. Push and open a pull request

Please follow Kotlin & Compose style conventions and include tests for new logic.

## Author
Yogendra Singh — Android Developer
GitHub: https://github.com/yogendra785

## License
See LICENSE file in the repository if present. If no license is included, please contact the author for permission before reusing the code.
