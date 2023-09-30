package com.example.ascvd_calc1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.exp
import android.util.Log

class Asvcd2013Calc : AppCompatActivity() {

    // Enum for Race
    enum class Race {
        WHITE, BLACK, OTHER
    }

    // Enum for Gender
    enum class Gender {
        MALE, FEMALE
    }

    // Enum for Blood Pressure Medication Status
    enum class BpMedStatus {
        YES, NO
    }

    // Enum for Diabetes Status
    enum class DiabetesStatus {
        YES, NO
    }

    // Enum for Smoking Status
    enum class SmokingStatus {
        YES, NO
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asvcd_2013_calc)

        val calculateButton: Button = findViewById(R.id.buttonCalculate_2013)
        val ascvdRiskTextView: TextView = findViewById(R.id.textViewASCVDRisk_2013)

        calculateButton.setOnClickListener {
            val ageEditText: EditText = findViewById(R.id.editTextAge_2013)
            val age: Int = ageEditText.text.toString().toInt()

            val systolicBpEditText: EditText = findViewById(R.id.editTextSystolicBP_2013)
            val sbp: Int = systolicBpEditText.text.toString().toInt()

            val cholesterolEditText: EditText = findViewById(R.id.editTextTotalCholesterol_2013)
            val cholesterol: Int = cholesterolEditText.text.toString().toInt()

            val hdlEditText: EditText = findViewById(R.id.editTextHDLCholesterol_2013)
            val hdl: Int = hdlEditText.text.toString().toInt()

            val biologicalSexGroup: RadioGroup = findViewById(R.id.radioGroupBiologicalSex_2013)
            val biologicalSex: Gender = when (biologicalSexGroup.checkedRadioButtonId) {
                R.id.radioButtonMale -> Gender.MALE
                R.id.radioButtonFemale -> Gender.FEMALE
                else -> Gender.MALE
            }

            val blackGroup: RadioGroup = findViewById(R.id.radioGroupBlack_2013)
            val isBlack: Race = when (blackGroup.checkedRadioButtonId) {
                R.id.radioButtonAfricanAmerican -> Race.BLACK
                R.id.radioButtonWhite -> Race.WHITE
                else -> Race.OTHER
            }
            val isSmokingGroup: RadioGroup = findViewById(R.id.radioGroupSmokingTobacco_2013)
            val isSmoking: SmokingStatus = when (isSmokingGroup.checkedRadioButtonId) {
                R.id.radioButtonSmokingYes -> SmokingStatus.YES
                R.id.radioButtonSmokingNo -> SmokingStatus.NO
                else -> SmokingStatus.NO
            }

            val hasDiabetesGroup: RadioGroup = findViewById(R.id.radioGroupDiabetes_2013)
            val hasDiabetes: DiabetesStatus = when (hasDiabetesGroup.checkedRadioButtonId) {
                R.id.radioButtonDiabetesYes -> DiabetesStatus.YES
                R.id.radioButtonDiabetesNo -> DiabetesStatus.NO
                else -> DiabetesStatus.NO
            }

            val radioGroupBloodPressure: RadioGroup = findViewById(R.id.radioGroupBloodPressure_2013)
            val bloodPressureMedication: BpMedStatus = when (radioGroupBloodPressure.checkedRadioButtonId) {
                R.id.radioButtonBPYes -> BpMedStatus.YES
                R.id.radioButtonBPNo -> BpMedStatus.NO
                else -> BpMedStatus.NO
            }

            val totalCholesterolEditText: EditText = findViewById(R.id.editTextTotalCholesterol_2013)
            val totalCholesterol: Int = totalCholesterolEditText.text.toString().toInt()

            val hdlCholesterolEditText: EditText = findViewById(R.id.editTextHDLCholesterol_2013)
            val hdlCholesterol: Int = hdlCholesterolEditText.text.toString().toInt()

            val systolicBloodPressureEditText: EditText = findViewById(R.id.editTextSystolicBP_2013)
            val systolicBloodPressure: Int = systolicBloodPressureEditText.text.toString().toInt()

            val ascvdRisk: Double = calculateValue( isBlack, biologicalSex, age, sbp,
                bloodPressureMedication, hasDiabetes, isSmoking, cholesterol, hdl)

            ascvdRiskTextView.text = "10-year ASCVD risk: ${"%.2f".format(ascvdRisk)}%"
        }
    }

    // Manvita 2013 changes
    // Function to get the Ln age (y) coefficient-2013 based on race and gender
    private fun getLnAgeY(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 12.344
            Race.WHITE to Gender.FEMALE -> -29.799
            Race.BLACK to Gender.MALE -> 2.469
            Race.BLACK to Gender.FEMALE -> 17.114
            Race.OTHER to Gender.MALE -> 12.344
            Race.OTHER to Gender.FEMALE -> -29.799
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln age squared coefficient-2013 based on race and gender
    private fun getLnAgeSquared(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 0.000
            Race.WHITE to Gender.FEMALE -> 4.884
            Race.BLACK to Gender.MALE -> 0.000
            Race.BLACK to Gender.FEMALE -> 0.000
            Race.OTHER to Gender.MALE -> 0.000
            Race.OTHER to Gender.FEMALE -> 4.884
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln Total Chol (mg/dL) ratio coefficient based on race and gender
    private fun getLnTotalChol(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 11.853
            Race.WHITE to Gender.FEMALE -> 13.540
            Race.BLACK to Gender.MALE -> 0.302
            Race.BLACK to Gender.FEMALE -> 0.940
            Race.OTHER to Gender.MALE -> 11.853
            Race.OTHER to Gender.FEMALE -> 13.540
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln Age x Ln Total Chol  coefficient based on race and gender
    private fun getLnAgeXLnTotalChol(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> -2.664
            Race.WHITE to Gender.FEMALE -> -3.114
            Race.BLACK to Gender.MALE -> 0.000
            Race.BLACK to Gender.FEMALE -> 0.000
            Race.OTHER to Gender.MALE -> -2.664
            Race.OTHER to Gender.FEMALE -> 0.000
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln HDL-C (mg/dL) coefficient based on race and gender
    private fun getLnHDLC(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> -7.990
            Race.WHITE to Gender.FEMALE -> -13.578
            Race.BLACK to Gender.MALE -> -0.307
            Race.BLACK to Gender.FEMALE -> -18.920
            Race.OTHER to Gender.MALE -> -7.990
            Race.OTHER to Gender.FEMALE -> -13.578
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln Age x Ln Total Chol  coefficient based on race and gender
    private fun getLnAgeXLnHDLC(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 1.769
            Race.WHITE to Gender.FEMALE -> 3.149
            Race.BLACK to Gender.MALE -> 0.000
            Race.BLACK to Gender.FEMALE -> 4.475
            Race.OTHER to Gender.MALE -> 1.769
            Race.OTHER to Gender.FEMALE -> 3.149
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }


    // Function to get the Ln Txed Systolic BP (mm Hg)  coefficient based on race and gender
    private fun getLnTxedSystolicBp(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 1.797
            Race.WHITE to Gender.FEMALE -> 2.019
            Race.BLACK to Gender.MALE -> 1.916
            Race.BLACK to Gender.FEMALE -> 29.291
            Race.OTHER to Gender.MALE -> 1.797
            Race.OTHER to Gender.FEMALE -> 2.019
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }


    // Function to get the Ln Age x Ln Tx'ed Systolic BP  coefficient based on race and gender
    private fun getLnAgeXLnTxedSystolicBp(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 0.000
            Race.WHITE to Gender.FEMALE -> 0.000
            Race.BLACK to Gender.MALE -> 0.000
            Race.BLACK to Gender.FEMALE -> -6.432
            Race.OTHER to Gender.MALE -> 0.000
            Race.OTHER to Gender.FEMALE -> 0.000
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln Untx'ed Systolic BP (mm Hg) coefficient based on race and gender
    private fun getLnUntxedSystolicBp(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 1.764
            Race.WHITE to Gender.FEMALE -> 1.957
            Race.BLACK to Gender.MALE -> 1.809
            Race.BLACK to Gender.FEMALE -> 27.82
            Race.OTHER to Gender.MALE -> 1.764
            Race.OTHER to Gender.FEMALE -> 1.957
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln Age x Ln Untxed Systolic BP coefficient based on race and gender
    private fun getLnAgeXLnUntxedSystolicBp(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 0.000
            Race.WHITE to Gender.FEMALE -> 0.000
            Race.BLACK to Gender.MALE -> 0.000
            Race.BLACK to Gender.FEMALE -> -6.087
            Race.OTHER to Gender.MALE -> 0.000
            Race.OTHER to Gender.FEMALE -> 0.000
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }


    // Function to get the Current Smoker (1=Yes, 0=No) coefficient based on race and gender
    private fun getCurrentSmoker(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 7.837
            Race.WHITE to Gender.FEMALE -> 7.574
            Race.BLACK to Gender.MALE -> 0.549
            Race.BLACK to Gender.FEMALE -> 0.691
            Race.OTHER to Gender.MALE -> 7.837
            Race.OTHER to Gender.FEMALE -> 7.574
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Ln Age x Current Smoker coefficient based on race and gender
    private fun getLnAgeXCurrentSmoker(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> -1.795
            Race.WHITE to Gender.FEMALE -> -1.665
            Race.BLACK to Gender.MALE -> 0.000
            Race.BLACK to Gender.FEMALE -> 0.000
            Race.OTHER to Gender.MALE -> -1.795
            Race.OTHER to Gender.FEMALE -> -1.665
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }


    // Function to get the Diabetes (1=Yes, 0=No) coefficient based on race and gender
    private fun getDiabetes(race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 0.658
            Race.WHITE to Gender.FEMALE -> 0.661
            Race.BLACK to Gender.MALE -> 0.645
            Race.BLACK to Gender.FEMALE -> 0.874
            Race.OTHER to Gender.MALE -> 0.658
            Race.OTHER to Gender.FEMALE -> 0.661
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }


    fun calculateValue(
        race: Race, gender: Gender, age: Int, sbp: Int,
        bpMedStatus: BpMedStatus, diabetesStatus: DiabetesStatus,
        smokingStatus: SmokingStatus, chol: Int, hdl: Int
    ): Double {

        // Manvita 2013 Changes
        val getLnAgeY = getLnAgeY(race, gender)*age
        Log.d("AppLog", "getLnAgeY: $getLnAgeY")

        val getLnAgeSquared = getLnAgeSquared(race, gender)*age
        Log.d("AppLog", "getLnAgeSquared: $getLnAgeSquared")

        val getLnTotalChol = getLnTotalChol(race, gender)
        //val LnAgeXLnTotalChol = getLnAgeY * getLnTotalChol
        Log.d("AppLog", "getLnTotalChol: $getLnTotalChol")

        val getLnAgeXLnTotalChol = getLnAgeXLnTotalChol(race, gender)*age
        Log.d("AppLog", "getLnAgeXLnTotalChol: $getLnAgeXLnTotalChol")

        val getLnHDLC = getLnHDLC(race, gender)*age
        Log.d("AppLog", "getLnHDLC: $getLnHDLC")

        val getLnAgeXLnHDLC = getLnAgeXLnHDLC(race, gender)*age
        Log.d("AppLog", "getLnAgeXLnHDLC: $getLnAgeXLnHDLC")

        val getLnTxedSystolicBp = getLnTxedSystolicBp(race, gender)*age
        Log.d("AppLog", "getLnTxedSystolicBp: $getLnTxedSystolicBp")


        val getLnAgeXLnTxedSystolicBp = getLnAgeXLnTxedSystolicBp(race, gender)*age
        Log.d("AppLog", "getLnAgeXLnTxedSystolicBp: $getLnAgeXLnTxedSystolicBp")

        val getLnUntxedSystolicBp = getLnUntxedSystolicBp(race, gender)*age
        Log.d("AppLog", "getLnUntxedSystolicBp: $getLnUntxedSystolicBp")

        val getLnAgeXLnUntxedSystolicBp = getLnAgeXLnUntxedSystolicBp(race, gender)*age
        Log.d("AppLog", "getLnAgeXLnUntxedSystolicBp: $getLnAgeXLnUntxedSystolicBp")

        val getCurrentSmoker = getCurrentSmoker(race, gender)*age
        Log.d("AppLog", "getCurrentSmoker: $getCurrentSmoker")

        val getLnAgeXCurrentSmoker = getLnAgeXCurrentSmoker(race, gender)*age
        Log.d("AppLog", "getLnAgeXCurrentSmoker: $getLnAgeXCurrentSmoker")

        val getDiabetes = getDiabetes(race, gender)*age
        Log.d("AppLog", "getDiabetes: $getDiabetes")


        // Calculating the sum of all the terms
        val totalSum: Double = getLnAgeY + getLnAgeSquared + getLnTotalChol +
                getLnAgeXLnTotalChol + getLnHDLC + getLnAgeXLnHDLC + getLnTxedSystolicBp +
                getLnAgeXLnTxedSystolicBp +
                getLnUntxedSystolicBp +
                getLnAgeXLnUntxedSystolicBp +
                getCurrentSmoker +
                getLnAgeXCurrentSmoker +
                getDiabetes
        Log.d("AppLog", "Calculating the sum of the terms: $totalSum")

        // Calculating the negative sum of the terms
        val negTotalSum: Double = 0 - totalSum
        Log.d("AppLog", "Calculating the negative sum of the terms: $negTotalSum")

        // Calculating the exponent of negative sum of terms
        val expNegSum: Double = exp(negTotalSum)
        Log.d("AppLog", "Calculating the exponent of negative sum of terms: $expNegSum")

        // Calculating the exponent of negative sum of terms plus 1
        val expNegSumPlusOne: Double = expNegSum + 1
        Log.d("AppLog", "Calculating the exponent of negative sum of terms plus 1: $expNegSumPlusOne")

        // Calculating the risk percentage term
        val riskTerm: Double = (1 / expNegSumPlusOne) * 100
        Log.d("AppLog", "Calculating the risk percentage term: $riskTerm")

        //Returning the percentage of the risk term
        return riskTerm.coerceIn(0.0, 100.0)
        Log.d("AppLog", "Returning the percentage of the risk term: $riskTerm")

    }

}