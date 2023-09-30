package com.example.ascvd_calc1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ascvd_calc1.databinding.FragmentHomeBinding
import com.example.ascvd_calc1.ui.home.HomeViewModel

class Calc2013Fragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var topAppBar: Toolbar

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(Calc2013ViewModel::class.java)

        // Inflate the layout for this fragment using the traditional method

        //topAppBar = findViewById(R.id.topAppBar)

        //setSupportActionBar(topAppBar) // Set the top app bar as the support action bar
        //supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable the back button

        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val root = inflater.inflate(com.example.ascvd_calc1.R.layout.asvcd_2013_calc, container, false)

        //val topAppBar: Toolbar? = view?.findViewById(R.id.topAppBar)
        //val topAppBar: Toolbar = this.view.findViewById(R.id.topAppBar)
        //(activity as AppCompatActivity).setSupportActionBar(topAppBar)

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}