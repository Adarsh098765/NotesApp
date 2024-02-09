package com.demo.notesappandroidcompose.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.notesappandroidcompose.feature_note.domain.model.Note
import com.demo.notesappandroidcompose.feature_note.domain.usecases.NoteUseCases
import com.demo.notesappandroidcompose.feature_note.domain.utils.NoteOrder
import com.demo.notesappandroidcompose.feature_note.domain.utils.OrderType
import com.demo.notesappandroidcompose.feature_note.presentation.notes.NotesEvent
import com.demo.notesappandroidcompose.feature_note.presentation.notes.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    init {
        fetchNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                fetchNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                deleteNoteAndUpdateState(event.note)
            }

            is NotesEvent.RestoreNote -> {
                restoreRecentDeletedNote()
            }

            is NotesEvent.ToggleOrderSection -> {
                toggleOrderSectionVisibility()
            }
        }
    }

    private fun fetchNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

    private fun deleteNoteAndUpdateState(note: Note) {
        viewModelScope.launch {
            try {
                noteUseCases.deleteNote(note)
                recentDeletedNote = note
            } catch (e: Exception) {
                // Handle error (e.g., display error message)
            }
        }
    }

    private fun restoreRecentDeletedNote() {
        recentDeletedNote?.let { deletedNote ->
            viewModelScope.launch {
                try {
                    noteUseCases.addNote(deletedNote)
                    recentDeletedNote = null
                } catch (e: Exception) {
                    // Handle error (e.g., display error message)
                }
            }
        }
    }

    private fun toggleOrderSectionVisibility() {
        _state.value = state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
    }
}
