package fi.giao.wordguessinggame.ui

import android.os.CountDownTimer
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

    private  val _timer  = MutableLiveData<Long>()
    val timer: LiveData<Long>
        get() = _timer

    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean>
        get() = _gameFinished

    private var index  = -1
    private var wordList: List<WordItem>

    private fun getAllWordsItems() = repository.getAllWordItems()

    companion object {
        const val TIMER  = 30000L
        const val INTERVAL = 1000L
        const val END = 0L
    }
    init {
        _score.value = 0
        _gameFinished.value = false
        wordList = getAllWordsItems().shuffled()
        nextWord()
        object:  CountDownTimer(TIMER, INTERVAL){
            override fun onTick(p0: Long) {
                _timer.value = p0 / INTERVAL
            }

            override fun onFinish() {
                _timer.value =  END
                _gameFinished.value = true
            }
        }.start()
    }
    private fun nextWord() {
        if(index < (wordList.size.minus(1))) {
            index++
        } else {
            index = 0
        }
        _word.value =  wordList[index].word
        _wordDescription.value = wordList[index].description
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