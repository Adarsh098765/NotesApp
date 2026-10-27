# Notes App: Clean Architecture with Jetpack Compose

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-7F52FF?logo=kotlin&logoColor=white) ![Jetpack](https://img.shields.io/badge/Jetpack-Compose-4285F4?logo=jetpackcompose&logoColor=white) ![Room](https://img.shields.io/badge/Room-database-4479A1?logo=sqlite&logoColor=white) ![Hilt](https://img.shields.io/badge/Hilt-DI-2496ED) ![Architecture](https://img.shields.io/badge/Architecture-Clean%20+%20MVVM-0a8a4a)

A notes app built as a **Clean Architecture reference**: a domain layer with single-purpose use cases, a Room-backed data layer, and an MVVM presentation layer in Jetpack Compose.

## Features

- Create, edit and delete notes, each with a colour.
- **Undo delete**: a snackbar restores the last deleted note.
- **Sort** by title, date or colour, **ascending or descending**, using a sort section you can show or hide.
- Reactive list: notes stream from Room as a `Flow`, so the UI updates on its own.

## Architecture

```
presentation/   NotesScreen, AddEditNoteScreen (Compose)
                NotesViewModel, AddEditNoteViewModel  ── UI state + one-off UiEvents
                        │
domain/         use cases: GetNotes, GetNote, AddNote, DeleteNote
                NoteOrder / OrderType (sealed classes)
                NoteRepository (interface)
                        │
data/           NoteRepositoryImpl ── NoteDao ── NoteDB (Room)
```

- **Unidirectional data flow**: screens send `NotesEvent` / `AddEditNoteEvent`, the ViewModel reduces them into state, and one-off effects (snackbar, navigate back) go out through `UiEvent`.
- **Sealed classes** model sort order (`NoteOrder.Title/Date/Color` × `OrderType.Ascending/Descending`).
- **Hilt** (`AppModule`) provides the database, repository and use cases.

## Tech stack

Kotlin · Jetpack Compose (Material 3) · Room · Hilt · Coroutines & Flow · Navigation Compose · MVVM · Clean Architecture

## Run it

Clone the repo, open it in Android Studio and run on any device or emulator (API 24+).

---

Built by **[Adarsh Thakur](https://adarsh098765.github.io/adarshthakur.github.io/)**, an Android engineer working with Kotlin and Jetpack Compose · [LinkedIn](https://www.linkedin.com/in/adarsh-thakur-b31281171/)

