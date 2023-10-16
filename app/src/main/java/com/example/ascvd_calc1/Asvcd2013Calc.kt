package com.example.ascvd_calc1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.exp
import android.util.Log
import java.time.temporal.ValueRange
import kotlin.math.pow
import kotlin.math.round

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
            Race.OTHER to Gender.FEMALE -> -3.114
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
    private fun getLnTxedSystolicBp(race: Race, gender: Gender, bpMedStatus: BpMedStatus): Double {
        if (bpMedStatus == BpMedStatus.NO)
            return 0.0

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
    private fun getLnAgeXLnTxedSystolicBp(race: Race, gender: Gender, bpMedStatus: BpMedStatus): Double {
        if (bpMedStatus == BpMedStatus.NO)
            return 0.0

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
    private fun getLnUntxedSystolicBp(race: Race, gender: Gender, bpMedStatus: BpMedStatus): Double {
        if (bpMedStatus == BpMedStatus.YES)
            return 0.0

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
    private fun getLnAgeXLnUntxedSystolicBp(race: Race, gender: Gender, bpMedStatus: BpMedStatus): Double {
        if (bpMedStatus == BpMedStatus.YES)
            return 0.0

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
    private fun getCurrentSmoker(race: Race, gender: Gender, isSmoking: SmokingStatus): Double {
        if (isSmoking == SmokingStatus.NO)
            return 0.0
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
    private fun getLnAgeXCurrentSmoker(race: Race, gender: Gender, isSmoking: SmokingStatus): Double {
        if (isSmoking == SmokingStatus.NO)
            return 0.0

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
    private fun getDiabetes(race: Race, gender: Gender, hasDiabetes: DiabetesStatus): Double {
        if (hasDiabetes == DiabetesStatus.NO)
            return 0.0
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

    private fun meanCoeffValue (race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 61.18
            Race.WHITE to Gender.FEMALE -> -29.18
            Race.BLACK to Gender.MALE -> 19.54
            Race.BLACK to Gender.FEMALE -> 86.61
            Race.OTHER to Gender.MALE -> 61.18
            Race.OTHER to Gender.FEMALE -> -29.18
            else -> throw IllegalArgumentException("Invalid combination of race and gender")

        }
    }
    private fun meanSurvivalBaselines (race: Race, gender: Gender): Double {
        return when (race to gender) {
            Race.WHITE to Gender.MALE -> 0.9144
            Race.WHITE to Gender.FEMALE -> 0.9665
            Race.BLACK to Gender.MALE -> 0.8954
            Race.BLACK to Gender.FEMALE -> 0.9533
            Race.OTHER to Gender.MALE -> 0.9144
            Race.OTHER to Gender.FEMALE -> 0.9665
            else -> throw IllegalArgumentException("Invalid combination of race and gender")

        }
    }
    fun calculateValue(
        race: Race, gender: Gender, age: Int, sbp: Int,
        bpMedStatus: BpMedStatus, hasDiabetes: DiabetesStatus,
        smokingStatus: SmokingStatus, chol: Int, hdl: Int
    ): Double {

        // Manvita 2013 Changes

        // Basic equations to make math easier
        val lnAge = kotlin.math.ln(age.toDouble())
        val lnChol = kotlin.math.ln(chol.toDouble())
        val lnHdl = kotlin.math.ln(hdl.toDouble())
        val lnSbp = kotlin.math.ln(sbp.toDouble())


        val getLnAgeY = getLnAgeY(race, gender)*(kotlin.math.ln(age.toDouble()))
        Log.d("AppLog", "getLnAgeY: $getLnAgeY")

        val lnAgeSquared = lnAge * lnAge  // This squares the natural log value
        val getLnAgeSquaredValue = getLnAgeSquared(race, gender) * lnAgeSquared
        Log.d("AppLog", "getLnAgeSquared: $getLnAgeSquaredValue")

        val getLnTotalChol = getLnTotalChol(race, gender)*(kotlin.math.ln(chol.toDouble()))
        //val LnAgeXLnTotalChol = getLnAgeY * getLnTotalChol
        Log.d("AppLog", "getLnTotalChol: $getLnTotalChol")

        val getLnAgeXLnTotalCholValue = getLnAgeXLnTotalChol(race, gender) * (lnAge * lnChol)
        Log.d("AppLog", "getLnAgeXLnTotalChol: $getLnAgeXLnTotalCholValue")

        val getLnHDLC = getLnHDLC(race, gender) * kotlin.math.ln(hdl.toDouble())
        Log.d("AppLog", "getLnHDLC: $getLnHDLC")

        val getLnAgeXLnHDLCValue = getLnAgeXLnHDLC(race, gender) * (lnAge * lnHdl)
        Log.d("AppLog", "getLnAgeXLnHDLC: $getLnAgeXLnHDLCValue")

        val getLnTxedSystolicBp = getLnTxedSystolicBp(race, gender, bpMedStatus)*kotlin.math.ln(sbp.toDouble())
        val a = getLnTxedSystolicBp(race,gender,bpMedStatus)
        val b = kotlin.math.ln(sbp.toDouble())
        Log.d("AppLog", "getLnTxedSystolicBp(race, gender): $a")
        Log.d("AppLog", "Kotlin.math.ln(hdl.toDouble()): $b")
        Log.d("AppLog", "getLnTxedSystolicBp: $getLnTxedSystolicBp")


        val getLnAgeXLnTxedSystolicBpValue = getLnAgeXLnTxedSystolicBp(race, gender,bpMedStatus) * (lnAge * lnSbp)
        Log.d("AppLog", "getLnAgeXLnTxedSystolicBp: $getLnAgeXLnTxedSystolicBpValue")

        val getLnUntxedSystolicBp = getLnUntxedSystolicBp(race, gender,bpMedStatus)*kotlin.math.ln(sbp.toDouble())
        Log.d("AppLog", "getLnUntxedSystolicBp: $getLnUntxedSystolicBp")

        val getLnAgeXLnUntxedSystolicBpValue = getLnAgeXLnUntxedSystolicBp(race, gender,bpMedStatus) * (lnAge * lnSbp)
        Log.d("AppLog", "getLnAgeXLnUntxedSystolicBp: $getLnAgeXLnUntxedSystolicBpValue")

        val getCurrentSmoker = getCurrentSmoker(race, gender, smokingStatus)
        Log.d("AppLog", "getCurrentSmoker: $getCurrentSmoker")

        val getLnAgeXCurrentSmoker = getLnAgeXCurrentSmoker(race, gender, smokingStatus) * lnAge
        Log.d("AppLog", "getLnAgeXCurrentSmoker: $getLnAgeXCurrentSmoker")

        val getDiabetes = getDiabetes(race, gender, hasDiabetes)
        Log.d("AppLog", "getDiabetes: $getDiabetes")


        // Calculating the sum of all the terms
        val totalSum: Double = getLnAgeY + getLnAgeSquaredValue + getLnTotalChol +
                getLnAgeXLnTotalCholValue + getLnHDLC + getLnAgeXLnHDLCValue + getLnTxedSystolicBp +
                getLnAgeXLnTxedSystolicBpValue +
                getLnUntxedSystolicBp +
                getLnAgeXLnUntxedSystolicBpValue +
                getCurrentSmoker +
                getLnAgeXCurrentSmoker +
                getDiabetes
        Log.d("AppLog", "Calculating the sum of the terms: $totalSum")

        // Calculating the difference to total sum and meanCoeffValue
        val indSumMeanCoeffValue: Double = totalSum - meanCoeffValue(race, gender)
        Log.d("AppLog", "Calculating the negative sum of the terms: $indSumMeanCoeffValue")

        val survivalBaseline = kotlin.math.exp(indSumMeanCoeffValue)
        Log.d("AppLog", "Exponential of the negative sum of the terms: $survivalBaseline")

        val finalterm: Double = meanSurvivalBaselines(race, gender).pow(survivalBaseline)
        Log.d("AppLog", "Result of raising meanSurvivalBaseline to the power of survivalBaseline: $finalterm")
/*
        // Calculating the negative sum of the terms
        val negTotalSum: Double = 0 - totalSum
        Log.d("AppLog", "Calculating the negative sum of the terms: $negTotalSum")

        // Calculating the exponent of negative sum of terms
        val expNegSum: Double = exp(negTotalSum)
        Log.d("AppLog", "Calculating the exponent of negative sum of terms: $expNegSum")

        // Calculating the exponent of negative sum of terms plus 1
        val expNegSumPlusOne: Double = expNegSum + 1
        Log.d("AppLog", "Calculating the exponent of negative sum of terms plus 1: $expNegSumPlusOne")
*/
        // Calculating the risk percentage term
        // Calculating the risk percentage term
        val riskTermRaw: Double = (1-finalterm)*100
        val riskTerm: Double = (round(riskTermRaw * 10) / 10) // Round to the nearest tenth
        Log.d("AppLog", "Calculating the risk percentage term: $riskTerm")

//Returning the percentage of the risk term
        return riskTerm.coerceIn(0.0, 100.0)

    }

}