# 🗂️ Media Vault

> *Your movies, your games, your music — all in one vault.*

Media Vault is a JavaFX desktop app that helps you catalog, organize, and reflect on everything you watch, play, and listen to. Track what you've finished, what you're currently obsessed with, and what's next on your list — then rate it, review it, and never lose track of it again. 

---

## ✨ Features

- 🎞️ **Multi-type media tracking** — log movies, videogames, and music artists all in one unified library
- 📌 **Status tracking** — mark entries as *Planned*, *In Progress*, or *Completed*
- ⭐ **Ratings & reviews** — rate completed entries (1-5 stars) and leave your own personal review
- 🎧 **Music artist discographies** — track full albums per artist, with per-album listening progress
- 📊 **Library summary & filtering** — see your stats at a glance, and filter by status or media type
- 👤 **User profiles** — each profile keeps its own personal library

---

## 🛠️ Tech Stack

| | |
|---|---|
| **Language** | Java ☕ |
| **GUI Framework** | JavaFX 🖼️ |
| **Architecture** | Model-View-Controller (MVC) 🏗️ |

---

## 📁 Project Structure

```
Media-Vault-App/
├── model/            🧠  Core domain classes (Media, Movie, Videogame, MusicArtist, Library, Profile, Album, Status)
├── view/              🎨  JavaFX screens and UI components (LoginView, MainView, etc.)
├── controller/        🕹️  Event handling and business logic (LoginController, LibraryController, etc.)
└── MediaVaultApp.java 🚪  Application entry point
```

---

## 🎭 Media Types

Under the hood, every entry in your vault extends a shared abstract `Media` class — but each type brings its own flavor:

| Type | What It Tracks |
|---|---|
| 🎬 **Movie** | Director, release year, description |
| 🎮 **Videogame** | Developer, release year, description, hours played |
| 🎤 **Music Artist** | Description, full discography (albums with genre, year, track count, and listening progress) |

---

## 👩‍💻 Authors

| DE GUZMAN, Mavrick
| NONO, Shenelle Andrea
