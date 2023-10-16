package com.example.ascvd_calc1.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ascvd_calc1.Asvcd2018Calc
import com.example.ascvd_calc1.R
import com.example.ascvd_calc1.Asvcd2013Calc

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ascvd_calc, container, false)

        // Set default values for EditText fields
        val ageEditText: EditText = view.findViewById(R.id.editTextAge)
        val defaultAge = 50 // Set default age value
        ageEditText.setText(defaultAge.toString())

        val systolicBpEditText: EditText = view.findViewById(R.id.editTextSystolicBP)
        val defaultSystolicBP = 130 // Set default systolic blood pressure value
        systolicBpEditText.setText(defaultSystolicBP.toString())

        val cholesterolEditText: EditText = view.findViewById(R.id.editTextTotalCholesterol)
        val defaultCholesterol = 180 // Set default cholesterol value
        cholesterolEditText.setText(defaultCholesterol.toString())

        val hdlEditText: EditText = view.findViewById(R.id.editTextHDLCholesterol)
        val defaultHDL = 30 // Set default HDL value
        hdlEditText.setText(defaultHDL.toString())

        // Set default value for the RadioGroup for Biological Sex
        val biologicalSexGroup: RadioGroup = view.findViewById(R.id.radioGroupBiologicalSex)
        val defaultBiologicalSexId = R.id.radioButtonMale // Set default selection (male)
        biologicalSexGroup.check(defaultBiologicalSexId)

        // Set default value for the RadioGroup for Race
        val blackGroup: RadioGroup = view.findViewById(R.id.radioGroupRace)
        val defaultRaceId = R.id.radioButtonWhite // Set default selection (white)
        blackGroup.check(defaultRaceId)

        // Set default values for Smoking and Diabetes RadioGroups
        val smokingGroup: RadioGroup = view.findViewById(R.id.radioGroupSmokingTobacco)
        val defaultSmokingId = R.id.radioButtonSmokingNo // Set default selection (No smoking)
        smokingGroup.check(defaultSmokingId)

        val diabetesGroup: RadioGroup = view.findViewById(R.id.radioGroupDiabetes)
        val defaultDiabetesId = R.id.radioButtonDiabetesNo // Set default selection (No diabetes)
        diabetesGroup.check(defaultDiabetesId)

        // Set default value for the RadioGroup for Blood Pressure Medication
        val bloodPressureGroup: RadioGroup = view.findViewById(R.id.radioGroupBloodPressure)
        val defaultBloodPressureId = R.id.radioButtonBPNo // Set default selection (No blood pressure medication)
        bloodPressureGroup.check(defaultBloodPressureId)

        // Attach the listener to each UI component
        val textChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                calculateAndDisplayRisk2013(view)
                calculateAndDisplayRisk2018(view)
            }
        }

        ageEditText.addTextChangedListener(textChangedListener)
        systolicBpEditText.addTextChangedListener(textChangedListener)
        cholesterolEditText.addTextChangedListener(textChangedListener)
        hdlEditText.addTextChangedListener(textChangedListener)

        // Attach a listener to the RadioGroups for dynamic updates
        val radioGroupListener = RadioGroup.OnCheckedChangeListener { _, _ ->
            calculateAndDisplayRisk2013(view)
            calculateAndDisplayRisk2018(view)
        }

        biologicalSexGroup.setOnCheckedChangeListener(radioGroupListener)
        blackGroup.setOnCheckedChangeListener(radioGroupListener)
        smokingGroup.setOnCheckedChangeListener(radioGroupListener)
        diabetesGroup.setOnCheckedChangeListener(radioGroupListener)
        bloodPressureGroup.setOnCheckedChangeListener(radioGroupListener)

        // Initial calculation
        calculateAndDisplayRisk2013(view)
        calculateAndDisplayRisk2018(view)

        return view
    }

    private fun calculateAndDisplayRisk2013(view: View) {
        val ageEditText: EditText = view.findViewById(R.id.editTextAge)
        val systolicBpEditText: EditText = view.findViewById(R.id.editTextSystolicBP)
        val cholesterolEditText: EditText = view.findViewById(R.id.editTextTotalCholesterol)
        val hdlEditText: EditText = view.findViewById(R.id.editTextHDLCholesterol)

        val ageText = ageEditText.text.toString()
        val systolicBpText = systolicBpEditText.text.toString()
        val cholesterolText = cholesterolEditText.text.toString()
        val hdlText = hdlEditText.text.toString()

        // Check if any of the input fields are empty or contain non-numeric values
        if (ageText.isEmpty() || systolicBpText.isEmpty() || cholesterolText.isEmpty() || hdlText.isEmpty()) {
            // Handle the case where one or more fields are empty
            return
        }

        try {
            val age = ageText.toInt()
            val systolicBp = systolicBpText.toInt()
            val cholesterol = cholesterolText.toInt()
            val hdl = hdlText.toInt()

            val biologicalSexGroup: RadioGroup = view.findViewById(R.id.radioGroupBiologicalSex)
            val biologicalSex: Asvcd2013Calc.Gender = when (biologicalSexGroup.checkedRadioButtonId) {
                R.id.radioButtonMale -> Asvcd2013Calc.Gender.MALE
                R.id.radioButtonFemale -> Asvcd2013Calc.Gender.FEMALE
                else -> Asvcd2013Calc.Gender.MALE
            }

            val blackGroup: RadioGroup = view.findViewById(R.id.radioGroupRace)
            val isBlack: Asvcd2013Calc.Race = when (blackGroup.checkedRadioButtonId) {
                R.id.radioButtonBlack -> Asvcd2013Calc.Race.BLACK
                R.id.radioButtonWhite -> Asvcd2013Calc.Race.WHITE
                else -> Asvcd2013Calc.Race.OTHER
            }

            val smokingGroup: RadioGroup = view.findViewById(R.id.radioGroupSmokingTobacco)
            val isSmoking: Asvcd2013Calc.SmokingStatus = when (smokingGroup.checkedRadioButtonId) {
                R.id.radioButtonSmokingYes -> Asvcd2013Calc.SmokingStatus.YES
                R.id.radioButtonSmokingNo -> Asvcd2013Calc.SmokingStatus.NO
                else -> Asvcd2013Calc.SmokingStatus.NO
            }

            val diabetesGroup: RadioGroup = view.findViewById(R.id.radioGroupDiabetes)
            val hasDiabetes: Asvcd2013Calc.DiabetesStatus = when (diabetesGroup.checkedRadioButtonId) {
                R.id.radioButtonDiabetesYes -> Asvcd2013Calc.DiabetesStatus.YES
                R.id.radioButtonDiabetesNo -> Asvcd2013Calc.DiabetesStatus.NO
                else -> Asvcd2013Calc.DiabetesStatus.NO
            }

            val radioGroupBloodPressure: RadioGroup = view.findViewById(R.id.radioGroupBloodPressure)
            val bloodPressureMedication: Asvcd2013Calc.BpMedStatus = when (radioGroupBloodPressure.checkedRadioButtonId) {
                R.id.radioButtonBPYes -> Asvcd2013Calc.BpMedStatus.YES
                R.id.radioButtonBPNo -> Asvcd2013Calc.BpMedStatus.NO
                else -> Asvcd2013Calc.BpMedStatus.NO
            }

            val calculator = Asvcd2013Calc()

            val ascvdRisk2013: Double = calculator.calculateValue(
                isBlack, biologicalSex, age, systolicBp,
                bloodPressureMedication, hasDiabetes, isSmoking, cholesterol, hdl
            )

            val ascvdRiskTextView2013: TextView = view.findViewById(R.id.textViewASCVDRisk2013)

            ascvdRiskTextView2013.text = "ASCVD 2013: ${"%.1f".format(ascvdRisk2013)}%"
        } catch (e: NumberFormatException) {
            // Handle the case where input values cannot be parsed as integers
            // You can display an error message or handle it as needed
        }
    }

    private fun calculateAndDisplayRisk2018(view: View) {
        val ageEditText: EditText = view.findViewById(R.id.editTextAge)
        val systolicBpEditText: EditText = view.findViewById(R.id.editTextSystolicBP)
        val cholesterolEditText: EditText = view.findViewById(R.id.editTextTotalCholesterol)
        val hdlEditText: EditText = view.findViewById(R.id.editTextHDLCholesterol)

        val ageText = ageEditText.text.toString()
        val systolicBpText = systolicBpEditText.text.toString()
        val cholesterolText = cholesterolEditText.text.toString()
        val hdlText = hdlEditText.text.toString()

        // Check if any of the input fields are empty or contain non-numeric values
        if (ageText.isEmpty() || systolicBpText.isEmpty() || cholesterolText.isEmpty() || hdlText.isEmpty()) {
            // Handle the case where one or more fields are empty
            return
        }

        try {
            val age = ageText.toInt()
            val systolicBp = systolicBpText.toInt()
            val cholesterol = cholesterolText.toInt()
            val hdl = hdlText.toInt()

            val biologicalSexGroup: RadioGroup = view.findViewById(R.id.radioGroupBiologicalSex)
            val biologicalSex: Asvcd2018Calc.Gender = when (biologicalSexGroup.checkedRadioButtonId) {
                R.id.radioButtonMale -> Asvcd2018Calc.Gender.MALE
                R.id.radioButtonFemale -> Asvcd2018Calc.Gender.FEMALE
                else -> Asvcd2018Calc.Gender.MALE
            }

            val blackGroup: RadioGroup = view.findViewById(R.id.radioGroupRace)
            val isBlack: Asvcd2018Calc.Race = when (blackGroup.checkedRadioButtonId) {
                R.id.radioButtonBlack -> Asvcd2018Calc.Race.BLACK
                R.id.radioButtonWhite -> Asvcd2018Calc.Race.WHITE
                else -> Asvcd2018Calc.Race.OTHER
            }

            val smokingGroup: RadioGroup = view.findViewById(R.id.radioGroupSmokingTobacco)
            val isSmoking: Asvcd2018Calc.SmokingStatus = when (smokingGroup.checkedRadioButtonId) {
                R.id.radioButtonSmokingYes -> Asvcd2018Calc.SmokingStatus.YES
                R.id.radioButtonSmokingNo -> Asvcd2018Calc.SmokingStatus.NO
                else -> Asvcd2018Calc.SmokingStatus.NO
            }

            val diabetesGroup: RadioGroup = view.findViewById(R.id.radioGroupDiabetes)
            val hasDiabetes: Asvcd2018Calc.DiabetesStatus = when (diabetesGroup.checkedRadioButtonId) {
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

            val calculator = Asvcd2018Calc()

            val ascvdRisk: Double = calculator.calculateValue(
                isBlack, biologicalSex, age, systolicBp,
                bloodPressureMedication, hasDiabetes, isSmoking, cholesterol, hdl
            )

            val ascvdRiskTextView: TextView = view.findViewById(R.id.textViewASCVDRisk)
            ascvdRiskTextView.text = "revised PCE 2018: ${"%.1f".format(ascvdRisk)}%"
        } catch (e: NumberFormatException) {
            // Handle the case where input values cannot be parsed as integers
            // You can display an error message or handle it as needed
        }
    }
}
