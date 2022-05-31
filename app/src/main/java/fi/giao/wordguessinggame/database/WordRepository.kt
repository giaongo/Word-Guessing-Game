package fi.giao.wordguessinggame.database

import fi.giao.wordguessinggame.database.WordDatabase
import fi.giao.wordguessinggame.database.WordItem

class WordRepository private constructor(private val wordDao: WordDao)  {
    companion object {
        @Volatile  private  var instance: WordRepository?=  null
        fun getInstance(wordDao: WordDao)  = instance  ?:  synchronized(this)  {
            instance  ?: WordRepository(wordDao).also { instance =  it }
        }
    }

    suspend fun upsert(item: WordItem) = wordDao.upsert(item)
    fun getAllWordItems() = wordDao.getAllWords()
}