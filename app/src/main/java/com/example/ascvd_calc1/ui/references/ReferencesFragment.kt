package com.example.ascvd_calc1.ui.references

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ascvd_calc1.databinding.FragmentReferencesBinding

class ReferencesFragment : Fragment() {

    private var _binding: FragmentReferencesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ReferencesViewModel::class.java)

        _binding = FragmentReferencesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReferences
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}