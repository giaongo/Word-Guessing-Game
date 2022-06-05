package fi.giao.wordguessinggame.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fi.giao.wordguessinggame.R
import fi.giao.wordguessinggame.database.WordDatabase
import fi.giao.wordguessinggame.database.WordRepository
import fi.giao.wordguessinggame.databinding.FragmentDoublePlayBinding
import fi.giao.wordguessinggame.ui.WordViewModel
import fi.giao.wordguessinggame.ui.WordViewModelFactory
class DoublePlayFragment : Fragment() {
    private lateinit var binding: FragmentDoublePlayBinding
    private lateinit var wordViewModel: WordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_double_play,container,false)

        val wordDatabase = WordDatabase(requireActivity())
        val wordRepository  = WordRepository.getInstance(wordDatabase.getWordDao())
        val wordViewModelFactory = WordViewModelFactory(wordRepository)
        wordViewModel = ViewModelProvider(this,wordViewModelFactory)[WordViewModel::class.java]
        wordViewModel.word.observe(viewLifecycleOwner, Observer {
            binding.word.text  = it
        })
        wordViewModel.score.observe(viewLifecycleOwner, Observer {
            binding.scoreDouble.text = requireActivity().getString(R.string.score,it)
            if(it ==  5) {
                findNavController().navigate(DoublePlayFragmentDirections.actionDoublePlayFragmentToGameOverFragment(it))
            }
        })
        onCorrectButtonClicked()
        onSkipButtonClick()
        return binding.root
    }
    private fun  onCorrectButtonClicked() {
        binding.correctButton.setOnClickListener{
            wordViewModel.onCorrect()
        }
    }
    private fun onSkipButtonClick() {
        binding.skipButton.setOnClickListener{
            wordViewModel.onSkip()
        }
    }

}