package fi.giao.wordguessinggame.game

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fi.giao.wordguessinggame.R
import fi.giao.wordguessinggame.database.WordDatabase
import fi.giao.wordguessinggame.database.WordRepository
import fi.giao.wordguessinggame.databinding.FragmentSinglePlayBinding
import fi.giao.wordguessinggame.ui.WordViewModel
import fi.giao.wordguessinggame.ui.WordViewModelFactory

class SinglePlayFragment : Fragment() {
    private lateinit var binding: FragmentSinglePlayBinding
    private lateinit var wordViewModel: WordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_single_play,container,false)
        val wordDatabase = WordDatabase(requireActivity())
        val wordRepository = WordRepository.getInstance(wordDatabase.getWordDao())
        val wordViewModelFactory = WordViewModelFactory(wordRepository)
        wordViewModel  = ViewModelProvider(this,wordViewModelFactory)[WordViewModel::class.java]
        initializeWord()
        startTimeAndEnd()
        return binding.root
    }

    private fun initializeWord()  {
        binding.description.text = wordViewModel.wordDescription.value
        wordViewModel.run {
            score.observe(viewLifecycleOwner, Observer {
                binding.scoreSingle.text = getString(R.string.score, it)
            })
            wordDescription.observe(viewLifecycleOwner, Observer {
                binding.description.text = it
            })
        }

        binding.checkButton.setOnClickListener { compareAnswer() }
    }

    private fun compareAnswer() {
        val  answer = binding.editTextGuess.text
        if(answer.contentEquals(wordViewModel.word.value,true))  {
            wordViewModel.onCorrect()
            Toast.makeText(requireContext(),"Correct",Toast.LENGTH_SHORT).show()
        }  else  {
            wordViewModel.onSkip()
        }
        binding.editTextGuess.text = null
    }
    private fun startTimeAndEnd(){
        wordViewModel.timer.observe(viewLifecycleOwner, Observer {
            binding.time.text = DateUtils.formatElapsedTime(it)
        })
        wordViewModel.gameFinished.observe(viewLifecycleOwner, Observer {
            if(it) {
                findNavController().navigate(SinglePlayFragmentDirections.actionSinglePlayFragmentToGameOverFragment(wordViewModel.score.value!!))
            }
        })
    }
}