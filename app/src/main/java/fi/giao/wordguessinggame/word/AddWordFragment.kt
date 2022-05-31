package fi.giao.wordguessinggame.word

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fi.giao.wordguessinggame.R
import fi.giao.wordguessinggame.databinding.FragmentAddWordBinding

class AddWordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentAddWordBinding>(inflater,R.layout.fragment_add_word,container,false)
        return binding.root
    }
}