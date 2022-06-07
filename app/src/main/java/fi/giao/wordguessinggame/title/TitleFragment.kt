package fi.giao.wordguessinggame.title

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import fi.giao.wordguessinggame.R
import fi.giao.wordguessinggame.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
          val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
          binding.apply {
              couplePlay.setOnClickListener { view:View ->
                  view.findNavController().navigate(R.id.action_titleFragment_to_doublePlayFragment)
              }
              singlePlay.setOnClickListener { view:View ->
                  view.findNavController().navigate(R.id.action_titleFragment_to_singlePlayFragment)
              }
          }
        setHasOptionsMenu(true)
          return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}