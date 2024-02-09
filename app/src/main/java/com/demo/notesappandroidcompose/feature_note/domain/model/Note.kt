package com.demo.notesappandroidcompose.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.notesappandroidcompose.ui.theme.BabyBlue
import com.demo.notesappandroidcompose.ui.theme.Purple80
import com.demo.notesappandroidcompose.ui.theme.PurpleGrey80
import com.demo.notesappandroidcompose.ui.theme.RedPink
import com.demo.notesappandroidcompose.ui.theme.Violet

@Entity
data class Note(
  val title : String,
  val content: String,
  val timestamp: Long,
  val color: Int,
  @PrimaryKey val id: Int? = null
){
    companion object{
      val noteColors = listOf(Purple80, PurpleGrey80, Violet, RedPink, BabyBlue)
    }
}

class InvalidNoteException(message:String): Exception(message)
