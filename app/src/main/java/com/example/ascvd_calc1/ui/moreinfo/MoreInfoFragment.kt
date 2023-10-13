package com.example.ascvd_calc1.ui.moreinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ascvd_calc1.databinding.FragmentMoreinfoBinding

class MoreInfoFragment : Fragment() {

    private var _binding: FragmentMoreinfoBinding? = null
    private val binding get() = _binding!!

    // Dummy data for the expandable list
    private val listDataHeader: MutableList<String> = ArrayList()
    private val listDataChild: HashMap<String, List<String>> = HashMap()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreinfoBinding.inflate(inflater, container, false)
        val rootView = binding.root

        prepareListData()

        val listAdapter = MoreInfoExpandableListAdapter(requireContext(), listDataHeader, listDataChild)
        binding.expandableListView.setAdapter(listAdapter)

        return rootView
    }

    private fun prepareListData() {
        // Adding header data
        listDataHeader.add("More about Framingham 2008")
        listDataHeader.add("More about ASCVD 2013")
        listDataHeader.add("More about proposed revised PCE 2018")
        listDataHeader.add("What about race?")
        listDataHeader.add("Why can't I enter LDL, diastolic BP?")
        listDataHeader.add("Where are the treatment thresholds?")
        listDataHeader.add("Where are the target LDL's?")
        listDataHeader.add("Can I plug in a coronary calcium score?")
        listDataHeader.add("What does 24.3% 10-yr cardiac risk mean?")
        listDataHeader.add("If you really want treatment guidance")
        listDataHeader.add("Do we need another risk calculator app?")
        // ... add more topics as needed

        // Adding child data
        val topic1: MutableList<String> = ArrayList()
        topic1.add("Your text for 'More about Framingham 2008' goes here.")
        val topic2: MutableList<String> = ArrayList()
        topic2.add("Your text for 'More about ASCVD 2013' goes here.")
        val topic3: MutableList<String> = ArrayList()
        topic3.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic4: MutableList<String> = ArrayList()
        topic4.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic5: MutableList<String> = ArrayList()
        topic5.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic6: MutableList<String> = ArrayList()
        topic6.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic7: MutableList<String> = ArrayList()
        topic7.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic8: MutableList<String> = ArrayList()
        topic8.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic9: MutableList<String> = ArrayList()
        topic9.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic10: MutableList<String> = ArrayList()
        topic10.add("Your text for 'More about proposed revised PCE 2018' goes here.")
        val topic11: MutableList<String> = ArrayList()
        topic11.add("Your text for 'More about proposed revised PCE 2018' goes here.")



        // ... add more topic texts as needed

        listDataChild[listDataHeader[0]] = topic1
        listDataChild[listDataHeader[1]] = topic2
        listDataChild[listDataHeader[2]] = topic3
        listDataChild[listDataHeader[3]] = topic4
        listDataChild[listDataHeader[4]] = topic5
        listDataChild[listDataHeader[5]] = topic6
        listDataChild[listDataHeader[6]] = topic7
        listDataChild[listDataHeader[7]] = topic8
        listDataChild[listDataHeader[8]] = topic9
        listDataChild[listDataHeader[9]] = topic10
        listDataChild[listDataHeader[10]] = topic11

        // ... link more topics to their texts as needed
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
