package com.demo.notesappandroidcompose.feature_note.domain.usecases

import com.demo.notesappandroidcompose.feature_note.domain.model.Note
import com.demo.notesappandroidcompose.feature_note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}