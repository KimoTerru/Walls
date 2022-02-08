package it.kimoterru.walls.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import it.kimoterru.walls.R
import it.kimoterru.walls.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initObservers()
        fragmentComponent()
    }

    private fun fragmentComponent() {
        binding.settings.setOnClickListener(this)
        binding.about.setOnClickListener(this)
    } //For any garbage associated with onViewCreated

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.settings -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_fragment_profile_to_fragment_settings)
            }
            R.id.about -> {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_fragment_profile_to_activity_about)
            }
        }
    }
}