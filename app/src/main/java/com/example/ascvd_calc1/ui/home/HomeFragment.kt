package com.example.ascvd_calc1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.ascvd_calc1.Asvcd2018Calc
import com.example.ascvd_calc1.R
import com.example.ascvd_calc1.Asvcd2013Calc


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        Log.d("HomeFragment", "onCreate executed")

        val toggleSwitch: SwitchCompat = view.findViewById<SwitchCompat>(R.id.toggleSwitch)

        toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                view.findViewById<View>(R.id.ascvd2018Layout).visibility = View.VISIBLE
                view.findViewById<View>(R.id.ascvd2013Layout).visibility = View.GONE
            } else {
                view.findViewById<View>(R.id.ascvd2018Layout).visibility = View.GONE
                view.findViewById<View>(R.id.ascvd2013Layout).visibility = View.VISIBLE
            }
        }

        // Here is your onClick code

        val calculateButton2013: Button = view.findViewById(R.id.buttonCalculate_2013)
        val ascvdRiskTextView2013: TextView = view.findViewById(R.id.textViewASCVDRisk_2013)  // Adjust if the ID is different for 2013

        calculateButton2013.setOnClickListener {
            val ageEditText: EditText = view.findViewById(R.id.editTextAge_2013)
            val age: Int = ageEditText.text.toString().toInt()

            val systolicBpEditText: EditText = view.findViewById(R.id.editTextSystolicBP_2013)
            val sbp: Int = systolicBpEditText.text.toString().toInt()

            val cholesterolEditText: EditText = view.findViewById(R.id.editTextTotalCholesterol_2013)
            val cholesterol: Int = cholesterolEditText.text.toString().toInt()

            val hdlEditText: EditText = view.findViewById(R.id.editTextHDLCholesterol_2013)
            val hdl: Int = hdlEditText.text.toString().toInt()

            val biologicalSexGroup: RadioGroup = view.findViewById(R.id.radioGroupBiologicalSex_2013)
            val biologicalSex: Asvcd2013Calc.Gender = when (biologicalSexGroup.checkedRadioButtonId) {
                R.id.radioButtonMale_2013 -> Asvcd2013Calc.Gender.MALE
                R.id.radioButtonFemale_2013 -> Asvcd2013Calc.Gender.FEMALE
                else -> Asvcd2013Calc.Gender.MALE
            }

            val blackGroup: RadioGroup = view.findViewById(R.id.radioGroupBlack_2013)
            val isBlack: Asvcd2013Calc.Race = when (blackGroup.checkedRadioButtonId) {
                R.id.radioButtonAfricanAmerican_2013 -> Asvcd2013Calc.Race.BLACK
                R.id.radioButtonWhite_2013 -> Asvcd2013Calc.Race.WHITE
                else -> Asvcd2013Calc.Race.OTHER
            }
            val isSmokingGroup: RadioGroup = view.findViewById(R.id.radioGroupSmokingTobacco_2013)
            val isSmoking: Asvcd2013Calc.SmokingStatus = when (isSmokingGroup.checkedRadioButtonId) {
                R.id.radioButtonSmokingYes_2013 -> Asvcd2013Calc.SmokingStatus.YES
                R.id.radioButtonSmokingNo_2013 -> Asvcd2013Calc.SmokingStatus.NO
                else -> Asvcd2013Calc.SmokingStatus.NO
            }

            val hasDiabetesGroup: RadioGroup = view.findViewById(R.id.radioGroupDiabetes_2013)
            val hasDiabetes: Asvcd2013Calc.DiabetesStatus = when (hasDiabetesGroup.checkedRadioButtonId) {
                R.id.radioButtonDiabetesYes_2013 -> Asvcd2013Calc.DiabetesStatus.YES
                R.id.radioButtonDiabetesNo_2013 -> Asvcd2013Calc.DiabetesStatus.NO
                else -> Asvcd2013Calc.DiabetesStatus.NO
            }

            val radioGroupBloodPressure: RadioGroup = view.findViewById(R.id.radioGroupBloodPressure_2013)
            val bloodPressureMedication: Asvcd2013Calc.BpMedStatus = when (radioGroupBloodPressure.checkedRadioButtonId) {
                R.id.radioButtonBPYes_2013 -> Asvcd2013Calc.BpMedStatus.YES
                R.id.radioButtonBPNo_2013 -> Asvcd2013Calc.BpMedStatus.NO
                else -> Asvcd2013Calc.BpMedStatus.NO
            }
            val calculator = Asvcd2013Calc()
            val ascvdRisk2013: Double = calculator.calculateValue( isBlack, biologicalSex, age, sbp,
                bloodPressureMedication,hasDiabetes, isSmoking, cholesterol, hdl)

            ascvdRiskTextView2013.text = "2013 10-year ASCVD risk: ${"%.2f".format(ascvdRisk2013)}%"
        }

        val calculateButton: Button = view.findViewById(R.id.buttonCalculate)
        val ascvdRiskTextView: TextView = view.findViewById(R.id.textViewASCVDRisk)

        calculateButton.setOnClickListener {
            val ageEditText: EditText = view.findViewById(R.id.editTextAge)
            val age: Int = ageEditText.text.toString().toInt()

            val systolicBpEditText: EditText = view.findViewById(R.id.editTextSystolicBP)
            val sbp: Int = systolicBpEditText.text.toString().toInt()

            val cholesterolEditText: EditText = view.findViewById(R.id.editTextTotalCholesterol)
            val cholesterol: Int = cholesterolEditText.text.toString().toInt()

            val hdlEditText: EditText = view.findViewById(R.id.editTextHDLCholesterol)
            val hdl: Int = hdlEditText.text.toString().toInt()

            val biologicalSexGroup: RadioGroup = view.findViewById(R.id.radioGroupBiologicalSex)
            val biologicalSex: Asvcd2018Calc.Gender = when (biologicalSexGroup.checkedRadioButtonId) {
                R.id.radioButtonMale -> Asvcd2018Calc.Gender.MALE
                R.id.radioButtonFemale -> Asvcd2018Calc.Gender.FEMALE
                else -> Asvcd2018Calc.Gender.MALE
            }

            val blackGroup: RadioGroup = view.findViewById(R.id.radioGroupBlack)
            val isBlack: Asvcd2018Calc.Race = when (blackGroup.checkedRadioButtonId) {
                R.id.radioButtonAfricanAmerican -> Asvcd2018Calc.Race.BLACK
                R.id.radioButtonWhite -> Asvcd2018Calc.Race.WHITE
                else -> Asvcd2018Calc.Race.OTHER
            }

            val isSmokingGroup: RadioGroup = view.findViewById(R.id.radioGroupSmokingTobacco)
            val isSmoking: Asvcd2018Calc.SmokingStatus = when (isSmokingGroup.checkedRadioButtonId) {
                R.id.radioButtonSmokingYes -> Asvcd2018Calc.SmokingStatus.YES
                R.id.radioButtonSmokingNo -> Asvcd2018Calc.SmokingStatus.NO
                else -> Asvcd2018Calc.SmokingStatus.NO
            }

            val hasDiabetesGroup: RadioGroup = view.findViewById(R.id.radioGroupDiabetes)
            val hasDiabetes: Asvcd2018Calc.DiabetesStatus = when (hasDiabetesGroup.checkedRadioButtonId) {
                R.id.radioButtonDiabetesYes -> Asvcd2018Calc.DiabetesStatus.YES
                R.id.radioButtonDiabetesNo -> Asvcd2018Calc.DiabetesStatus.NO
                else -> Asvcd2018Calc.DiabetesStatus.NO
            }

            val radioGroupBloodPressure: RadioGroup = view.findViewById(R.id.radioGroupBloodPressure)
            val bloodPressureMedication: Asvcd2018Calc.BpMedStatus = when (radioGroupBloodPressure.checkedRadioButtonId) {
                R.id.radioButtonBPYes -> Asvcd2018Calc.BpMedStatus.YES
                R.id.radioButtonBPNo -> Asvcd2018Calc.BpMedStatus.NO
                else -> Asvcd2018Calc.BpMedStatus.NO
            }

            // ... your previous code (UI element definitions)

            val calculator = Asvcd2018Calc()
            val ascvdRisk: Double = calculator.calculateValue(
                isBlack, biologicalSex, age, sbp,
                bloodPressureMedication, hasDiabetes, isSmoking, cholesterol, hdl
            )

            ascvdRiskTextView.text = "10-year ASCVD risk: ${"%.2f".format(ascvdRisk)}%"

        }

        return view
    }
}


enum class CalculatorType {
    ASCVD_2013, ASCVD_2018
}
