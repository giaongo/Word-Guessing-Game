package fi.giao.wordguessinggame.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import fi.giao.wordguessinggame.R
import fi.giao.wordguessinggame.databinding.FragmentTitleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {
      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
          binding.apply {
              couplePlay.setOnClickListener { view:View ->
                  view.findNavController().navigate(R.id.action_titleFragment_to_doublePlayFragment)
              }
              singlePlay.setOnClickListener { view:View ->
                  view.findNavController().navigate(R.id.action_titleFragment_to_singlePlayFragment)
              }
          }

          return binding.root
    }
}