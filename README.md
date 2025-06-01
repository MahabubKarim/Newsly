# 📰 Newsly

A modern Android news app built with Jetpack Compose, Hilt, Room, Retrofit, and Kotlin. Newsly aggregates news from various sources using the NewsAPI and presents it in a sleek, responsive UI.

---

## 🚀 Features

- 🧭 Jetpack Compose UI
- 🗂️ Paging support for news articles
- 🔍 Search and filter functionality
- ⚡ Swipe to refresh
- 💉 Dependency injection with Hilt
- 🧠 Offline caching with Room DB
- 🌐 REST API integration using Retrofit
- 🧪 Unit and UI testing with JUnit, Espresso, and MockK

---

## 🧰 Tech Stack

| Category         | Libraries / Tools                                                  |
|------------------|--------------------------------------------------------------------|
| UI               | Jetpack Compose, Material 3, Accompanist                           |
| DI               | Dagger Hilt                                                        |
| Networking       | Retrofit, Gson, OkHttp                                             |
| Caching          | Room Database                                                      |
| Paging           | Jetpack Paging 3                                                   |
| Image Loading    | Coil                                                               |
| Testing          | JUnit, Espresso, MockK, kotlinx.coroutines test                    |
| Build Tools      | Kotlin DSL, Gradle, KSP (Kotlin Symbol Processing)                 |
| Minimum SDK      | 24                                                                 |

---

## 🛠️ Project Setup

### 1. 🔑 Get a NewsAPI Key

Sign up at [https://newsapi.org](https://newsapi.org) and get your free API key.

### 2. 🧪 Set Environment Variable

Set the API key as an environment variable named `NEWS_API_KEY`.

**On macOS/Linux:**
```bash
export NEWS_API_KEY=your_api_key_here
```

**On Windows (Command Prompt):**
```cmd
set NEWS_API_KEY=your_api_key_here
```

> ℹ️ The API key is injected into the app via `buildConfigField`.

---

### 3. 📦 Clone and Build

```bash
git clone https://github.com/your-username/newsly.git
cd newsly
```

Open the project in Android Studio (Hedgehog or later), and it will handle syncing and dependencies automatically.

---

### 4. ▶️ Run the App

- Choose an emulator or connected device
- Click **Run** or use `Shift + F10`

---

## 🧪 Running Tests

```bash
./gradlew test               # Unit tests
./gradlew connectedAndroidTest  # Instrumentation tests
```

---

## 🧩 Modules

- `app` – Main application module

> Planning to modularize further (e.g., `core`, `data`, `feature-*`) in future versions.

---

## 📷 Screenshots

> Add UI screenshots here for the main screen, search, article view, etc.

---

## 🤝 Contributing

Contributions are welcome! Please open issues and submit pull requests.

1. Fork the repo
2. Create a new branch
3. Make your changes
4. Open a PR 🎉

---

## 📄 License

[MIT License](LICENSE)

---

## 🙌 Acknowledgements

- [NewsAPI.org](https://newsapi.org)
- [Android Developers](https://developer.android.com)
- [Coil](https://coil-kt.github.io/coil/)
- [Hilt](https://dagger.dev/hilt/)
