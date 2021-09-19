package it.kimoterru.walls.ui.emptySaved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.FragmentEmptySavedWallpaperBinding

class EmptySavedFragment : Fragment(R.layout.fragment_empty_saved_wallpaper) {
    private var _binding: FragmentEmptySavedWallpaperBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEmptySavedWallpaperBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Navigation.findNavController(requireView()).navigate(R.id.action_fragment_saved_to_fragment_empty_saved)

        //initObservers()
    }
}