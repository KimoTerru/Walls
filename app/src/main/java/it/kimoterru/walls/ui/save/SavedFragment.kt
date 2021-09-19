package it.kimoterru.walls.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.FragmentSavedBinding

class SavedFragment : Fragment(R.layout.fragment_saved) {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSavedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Navigation.findNavController(requireView()).navigate(R.id.action_fragment_saved_to_fragment_empty_saved)

        //initObservers()
    }
}