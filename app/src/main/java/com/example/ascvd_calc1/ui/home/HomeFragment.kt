package com.example.ascvd_calc1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ascvd_calc1.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        Log.d("HomeFragment", "onCreate executed")

        val toggleSwitch: Switch = view.findViewById(R.id.toggleSwitch)
        val navController = findNavController()

        toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                view.findViewById<View>(R.id.ascvd2018Layout).visibility = View.VISIBLE
                view.findViewById<View>(R.id.ascvd2013Layout).visibility = View.GONE
            } else {
                view.findViewById<View>(R.id.ascvd2018Layout).visibility = View.GONE
                view.findViewById<View>(R.id.ascvd2013Layout).visibility = View.VISIBLE
            }
        }


        return view
    }
}
