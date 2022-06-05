package fi.giao.wordguessinggame.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import fi.giao.wordguessinggame.R
import fi.giao.wordguessinggame.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    private lateinit var binding: FragmentGameOverBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_over,container,false)
        val score:Int  = GameOverFragmentArgs.fromBundle(requireArguments()).score
        binding.scoreFinal.text = resources.getString(R.string.score,score)
        binding.buttonPlayAgain.setOnClickListener {
            findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToTitleFragment())
        }
        return binding.root
    }
}