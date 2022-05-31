package fi.giao.wordguessinggame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.giao.wordguessinggame.database.WordRepository

class WordViewModelFactory (private val repository: WordRepository) :  ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordViewModel(repository) as T
    }

}