package com.example.ascvd_calc1.ui.moreinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ascvd_calc1.databinding.FragmentMoreinfoBinding
import com.example.ascvd_calc1.R

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
        listDataHeader.add("Do we need another risk calculator app?")
        listDataHeader.add("*What about the Other race?")

        // Adding child data
        val topic1: MutableList<String> = ArrayList()
        topic1.add(getString(R.string.more_about_framingham_2008))

        val topic2: MutableList<String> = ArrayList()
        topic2.add(getString(R.string.more_about_ascvd_2013))

        val topic3: MutableList<String> = ArrayList()
        topic3.add(getString(R.string.more_about_proposed_revised_pce_2018))

        val topic4: MutableList<String> = ArrayList()
        topic4.add(getString(R.string.about_race_in_cardiovascular_risk))

        val topic5: MutableList<String> = ArrayList()
        topic5.add(getString(R.string.why_cant_enter_ldl_diastolic_bp))

        val topic6: MutableList<String> = ArrayList()
        topic6.add(getString(R.string.where_are_treatment_thresholds))

        val topic7: MutableList<String> = ArrayList()
        topic7.add(getString(R.string.where_are_target_ldls))

        val topic8: MutableList<String> = ArrayList()
        topic8.add(getString(R.string.can_i_plug_in_cac_score))

        val topic9: MutableList<String> = ArrayList()
        topic9.add(getString(R.string.what_does_24_3_percent_cardiac_risk_mean))

        val topic10: MutableList<String> = ArrayList()
        topic10.add(getString(R.string.this_app_offers_features))

        val topic11: MutableList<String> = ArrayList()
        topic11.add(getString(R.string.race_calculation_note))

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
