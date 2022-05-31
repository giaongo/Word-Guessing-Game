package fi.giao.wordguessinggame.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(word:WordItem)

    @Query("SELECT * FROM word_items")
    fun getAllWords(): List<WordItem>
}