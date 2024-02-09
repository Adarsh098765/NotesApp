package com.demo.notesappandroidcompose.feature_note.domain.usecases

import com.demo.notesappandroidcompose.feature_note.domain.usecases.AddNote
import com.demo.notesappandroidcompose.feature_note.domain.usecases.DeleteNote
import com.demo.notesappandroidcompose.feature_note.domain.usecases.GetNote
import com.demo.notesappandroidcompose.feature_note.domain.usecases.GetNotes

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getIndividualNote : GetNote
)
