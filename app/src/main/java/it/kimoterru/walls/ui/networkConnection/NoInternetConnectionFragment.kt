package it.kimoterru.walls.ui.networkConnection

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.kimoterru.walls.R

class NoInternetConnectionFragment : Fragment(R.layout.fragment_no_internet_connection) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //go back when repeat clicked
        view.findViewById<TextView>(R.id.repeat).setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }
}