package fi.giao.wordguessinggame.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.giao.wordguessinggame.database.WordItem
import fi.giao.wordguessinggame.database.WordRepository

class WordViewModel (private val repository: WordRepository): ViewModel() {
    private val _word  =  MutableLiveData<String>()
    val word : LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _wordDescription =  MutableLiveData<String>()
    val wordDescription: LiveData<String>
        get() = _wordDescription

    private var index  = -1
    var wordList: List<WordItem>

    fun getAllWordsItems() = repository.getAllWordItems()

    init {
        _score.value = 0
        wordList = getAllWordsItems().shuffled()
        nextWord()
    }
    private fun nextWord() {
        if(index < (wordList.size.minus(1))) {
            index++
        } else {
            index = 0
        }
        _word.value =  wordList.get(index).word
        _wordDescription.value = wordList.get(index).description
    }

    fun onSkip()  {
        if(_score.value!! > 0) {
            _score.value = score.value?.minus(1)
        }
        nextWord()
    }
    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }
}