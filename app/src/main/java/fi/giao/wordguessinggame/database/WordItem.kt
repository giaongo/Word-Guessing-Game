package fi.giao.wordguessinggame.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_items")
data class WordItem (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var word: String,
    var description: String
    )
