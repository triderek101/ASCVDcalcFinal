package com.example.ascvd_calc1

import android.util.Log
import kotlin.math.exp
/*
class calculator_2018(isBlack: Race, biologicalSex: Gender, age: Int, sbp: Int, bloodPressureMedication: BpMedStatus,
    hasDiabetes: DiabetesStatus, isSmoking: SmokingStatus, cholesterol : Int, hdl: Int) {

    // Constructor code goes here
    val isBlack: Race
    val biologicalSex: Gender
    val age: Int
    val sbp: Int
    val bloodPressureMedication: BpMedStatus
    val hasDiabetes: DiabetesStatus
    val isSmoking: SmokingStatus
    val cholesterol: Int
    val hdl: Int

    init {
        this.isBlack = isBlack
        this.biologicalSex = biologicalSex
        this.age = age
        this.sbp = sbp
        this.bloodPressureMedication = bloodPressureMedication
        this.hasDiabetes = hasDiabetes
        this.isSmoking = isSmoking
        this.cholesterol = cholesterol
        this.hdl = hdl
    }
}

    // Enum for user input options
    enum class Race { WHITE, BLACK, OTHER }
    enum class Gender { MALE, FEMALE }
    enum class BpMedStatus { YES, NO }
    enum class DiabetesStatus { YES, NO }
    enum class SmokingStatus { YES, NO }

    // Function to get the intercept coefficient based on race and gender
    private fun getInterceptCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> -11.67998
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> -12.82311
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> -11.67998
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> -12.82311
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> -11.67998
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> -12.82311
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the age coefficient based on race and gender
    private fun getAgeCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> 0.064200
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 0.106501
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> 0.064200
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 0.106501
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 0.064200
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 0.106501
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the black race coefficient based on race and gender
    private fun getBlackRaceCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> 0.000000
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 0.000000
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> 0.482835
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 0.432440
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 0.000000
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 0.000000
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the SBP coefficient based on race and gender
    fun getSbpCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> 0.038950
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 0.017666
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> 0.038950
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 0.017666
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 0.038950
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 0.017666
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the SBP squared coefficient based on race and gender
    private fun getSbpSquaredCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> -0.000061
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 0.000056
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> -0.000061
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 0.000056
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 0.000061
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 0.000056
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the BP medication coefficient based on race, gender, and BP med status
    private fun getBpMedCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender, bpMedStatus: Asvcd2018Calc.BpMedStatus): Double {
        if (bpMedStatus == Asvcd2018Calc.BpMedStatus.NO) {
            return 0.0
        }
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> 2.055533
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 0.731678
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> 2.055533
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 0.731678
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 2.055533
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 0.731678
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the diabetes coefficient based on race, gender, and diabetes status
    private fun getDiabetesCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender, diabetesStatus: Asvcd2018Calc.DiabetesStatus): Double {
        if (diabetesStatus == Asvcd2018Calc.DiabetesStatus.NO) {
            return 0.0
        }
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> 0.842209
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 0.943970
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> 0.842209
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 0.943970
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 0.842209
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 0.943970
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the smoking coefficient based on race, gender, and smoking status
    private fun getSmokingCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender, smokingStatus: Asvcd2018Calc.SmokingStatus): Double {
        if (smokingStatus == Asvcd2018Calc.SmokingStatus.NO) {
            return 0.0
        }
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> 0.895589
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 1.009790
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> 0.895589
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 1.009790
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 0.895589
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 1.009790
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Chol total-to-HDL ratio coefficient based on race and gender
    private fun getCholToHDLCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race to gender) {
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.MALE -> 0.193307
            Asvcd2018Calc.Race.WHITE to Asvcd2018Calc.Gender.FEMALE -> 0.151318
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.MALE -> 0.193307
            Asvcd2018Calc.Race.BLACK to Asvcd2018Calc.Gender.FEMALE -> 0.151318
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.MALE -> 0.193307
            Asvcd2018Calc.Race.OTHER to Asvcd2018Calc.Gender.FEMALE -> 0.151318
            else -> throw IllegalArgumentException("Invalid combination of race and gender")
        }
    }

    // Function to get the Age if Black coefficient based on gender
    private fun getAgeIfBlackCoefficient(gender: Asvcd2018Calc.Gender): Double {
        return when (gender) {
            Asvcd2018Calc.Gender.MALE -> 0.0
            Asvcd2018Calc.Gender.FEMALE -> -0.008580
        }
    }

    // Function to get the Systolic BP if taking BP med coefficient based on gender and race
    private fun getSystolicBpIfOnMedCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race) {
            Asvcd2018Calc.Race.WHITE -> when (gender) {
                Asvcd2018Calc.Gender.MALE -> -0.014207
                Asvcd2018Calc.Gender.FEMALE -> -0.003647
            }
            Asvcd2018Calc.Race.BLACK -> when (gender) {
                Asvcd2018Calc.Gender.MALE -> -0.014207
                Asvcd2018Calc.Gender.FEMALE -> -0.003647
            }
            Asvcd2018Calc.Race.OTHER -> when(gender){
                Asvcd2018Calc.Gender.MALE -> -0.014207
                Asvcd2018Calc.Gender.FEMALE -> -0.003647
            }
        }
    }

    // Function to get the Systolic BP if Black coefficient based on gender
    private fun getSystolicBpIfBlackCoefficient(gender: Asvcd2018Calc.Gender): Double {
        return when (gender) {
            Asvcd2018Calc.Gender.MALE -> 0.011609
            Asvcd2018Calc.Gender.FEMALE -> 0.006208
        }
    }

    // Function to get the Black Race & Taking BP Med coefficient based on race, gender, and BP med status
    private fun getBlackRaceAndBpMedCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender, bpMedStatus: Asvcd2018Calc.BpMedStatus): Double {
        return when (race) {
            Asvcd2018Calc.Race.OTHER -> 0.0
            Asvcd2018Calc.Race.WHITE -> 0.0
            Asvcd2018Calc.Race.BLACK -> {
                when (gender) {
                    Asvcd2018Calc.Gender.MALE -> if (bpMedStatus == Asvcd2018Calc.BpMedStatus.YES) -0.119460 else 0.0
                    Asvcd2018Calc.Gender.FEMALE -> if (bpMedStatus == Asvcd2018Calc.BpMedStatus.YES) 0.152968 else 0.0
                }
            }
        }
    }

    // Function to get the Age x Systolic BP coefficient based on race and gender
    private fun getAgeSbpCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        return when (race) {
            Asvcd2018Calc.Race.WHITE -> {
                when (gender) {
                    Asvcd2018Calc.Gender.MALE -> 0.000025
                    Asvcd2018Calc.Gender.FEMALE -> -0.000153
                }
            }
            Asvcd2018Calc.Race.BLACK -> {
                when (gender) {
                    Asvcd2018Calc.Gender.MALE -> 0.000025
                    Asvcd2018Calc.Gender.FEMALE -> -0.000153
                }
            }
            Asvcd2018Calc.Race.OTHER -> {
                when (gender){
                    Asvcd2018Calc.Gender.MALE -> 0.000025
                    Asvcd2018Calc.Gender.FEMALE -> -0.000153
                }
            }
        }
    }

    //  Function to get the Smoker if Black coefficient based on race, gender, and smoking status
    private fun getSmokerIfBlackCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender, smokingStatus: Asvcd2018Calc.SmokingStatus): Double {
        if (race == Asvcd2018Calc.Race.BLACK && smokingStatus == Asvcd2018Calc.SmokingStatus.YES) {
            return when (gender) {
                Asvcd2018Calc.Gender.MALE -> -0.226771
                Asvcd2018Calc.Gender.FEMALE -> -0.092231
            }
        }
        return 0.0
    }

    // Function to get the Diabetes if Black coefficient based on race and gender
    private fun getDiabetesIfBlackCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender, diabetesStatus: Asvcd2018Calc.DiabetesStatus): Double {
        if (race == Asvcd2018Calc.Race.BLACK && diabetesStatus == Asvcd2018Calc.DiabetesStatus.YES) {
            return when (gender) {
                Asvcd2018Calc.Gender.MALE -> -0.077214
                Asvcd2018Calc.Gender.FEMALE -> 0.115232
            }
        }
        return 0.0
    }

    // Function to get the Chol total-to-HDL ratio if Black coefficient based on race and gender
    private fun getCholToHDLRatioIfBlackCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        if (race == Asvcd2018Calc.Race.BLACK) {
            return when (gender) {
                Asvcd2018Calc.Gender.MALE -> -0.117749
                Asvcd2018Calc.Gender.FEMALE -> 0.070498
            }
        }
        return 0.0
    }

    // Function to get the Systolic BP if on BP med if Black coefficient based on race and gender
    fun getSystolicBpIfOnMedIfBlackCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        if (race == Asvcd2018Calc.Race.BLACK) {
            return when (gender) {
                Asvcd2018Calc.Gender.MALE -> 0.004190
                Asvcd2018Calc.Gender.FEMALE -> -0.000173
            }
        }
        return 0.0
    }


    // Function to get the Age x Systolic BP if Black coefficient based on race and gender
    private fun getAgeSbpIfBlackCoefficient(race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender): Double {
        if (race == Asvcd2018Calc.Race.BLACK) {
            return when (gender) {
                Asvcd2018Calc.Gender.MALE -> -0.000199
                Asvcd2018Calc.Gender.FEMALE -> -0.000094
            }
        }
        return 0.0
    }

    fun calculateValue(
        race: Asvcd2018Calc.Race, gender: Asvcd2018Calc.Gender, age: Int, sbp: Int,
        bpMedStatus: Asvcd2018Calc.BpMedStatus, diabetesStatus: Asvcd2018Calc.DiabetesStatus,
        smokingStatus: Asvcd2018Calc.SmokingStatus, chol: Double, hdl: Double
    ): Double {

        // Get the respective coefficients
        val interceptCoefficient = getInterceptCoefficient(race, gender)
        Log.d("AppLog", "interceptCoefficient: $interceptCoefficient")

        val ageCoefficient = getAgeCoefficient(race, gender)*age
        Log.d("AppLog", "ageCoefficient: $ageCoefficient")

        val blackRaceCoefficient = getBlackRaceCoefficient(race, gender)
        Log.d("AppLog", "blackRaceCoefficient: $blackRaceCoefficient")

        val sbpSquaredCoefficient = getSbpSquaredCoefficient(race, gender)
        Log.d("AppLog", "sbpSquaredCoefficient: $sbpSquaredCoefficient")

        val sbpCoefficient = getSbpCoefficient(race, gender)*sbp
        Log.d("AppLog", "sbpCoefficient: $sbpCoefficient")

        val bpMedCoefficient = getBpMedCoefficient(race, gender, bpMedStatus)
        Log.d("AppLog", "bpMedCoefficient: $bpMedCoefficient")

        val diabetesCoefficient = getDiabetesCoefficient(race, gender, diabetesStatus)
        Log.d("AppLog", "diabetesCoefficient: $diabetesCoefficient")

        val smokingCoefficient = getSmokingCoefficient(race, gender, smokingStatus)
        Log.d("AppLog", "smokingCoefficient: $smokingCoefficient")

        // Calculate pt-values for SBP and SBP squared
        val sbpSquaredValue = sbp.toDouble() * sbp.toDouble() * sbpSquaredCoefficient
        val sbpValue = sbp.toDouble() * sbpCoefficient
        Log.d("AppLog", "Calculate pt-values for SBP and SBP squared: $sbpSquaredValue")

        // Computing the pt-value for Chol total-to-HDL ratio
        val cholToHDLValue: Double = (chol/hdl).toDouble()
        Log.d("AppLog", "Computing the pt-value for Chol total-to-HDL ratio: $cholToHDLValue")

        // Get the Chol total-to-HDL ratio coefficient and compute the product with pt-value
        val cholToHDLCoefficient = getCholToHDLCoefficient(race, gender)
        val cholToHDLProduct = cholToHDLValue * cholToHDLCoefficient
        Log.d("AppLog", "Get the Chol total-to-HDL ratio coefficient and compute the product with pt-value: $cholToHDLProduct")

        // Computing the pt-value for Age if Black
        val ageIfBlackValue = if (race == Asvcd2018Calc.Race.BLACK) age.toDouble() else 0.0
        Log.d("AppLog", "Computing the pt-value for Age if Black: $ageIfBlackValue")

        // Get the Age if Black coefficient and compute the product with pt-value
        val ageIfBlackCoefficient = getAgeIfBlackCoefficient(gender)
        val ageIfBlackProduct = ageIfBlackValue * ageIfBlackCoefficient
        Log.d("AppLog", "Get the Age if Black coefficient and compute the product with pt-value: $ageIfBlackProduct")

        // Computing the pt-value for Systolic BP if taking BP med
        val systolicBpIfOnMedValue = if (bpMedStatus == Asvcd2018Calc.BpMedStatus.YES) sbp.toDouble() else 0.0
        Log.d("AppLog", "Computing the pt-value for Systolic BP if taking BP med: $systolicBpIfOnMedValue")

        // Get the Systolic BP if taking BP med coefficient and compute the product with pt-value
        val systolicBpIfOnMedCoefficient = getSystolicBpIfOnMedCoefficient(race, gender)
        Log.d("AppLog", "Get the Systolic BP if taking BP med coefficient systolicBpIfOnMedCoefficient: $systolicBpIfOnMedCoefficient")

        val systolicBpIfOnMedProduct = systolicBpIfOnMedValue * systolicBpIfOnMedCoefficient
        Log.d("AppLog", "Get the Systolic BP if taking BP med coefficient and compute the product with pt-value: $systolicBpIfOnMedProduct")

        // Computing the pt-value for Systolic BP if Black
        val systolicBpIfBlackValue = if (race == Asvcd2018Calc.Race.BLACK) sbp.toDouble() else 0.0
        Log.d("AppLog", "Computing the pt-value for Systolic BP if Black: $systolicBpIfBlackValue")

        // Get the Systolic BP if Black coefficient and compute the product with pt-value
        val systolicBpIfBlackCoefficient = getSystolicBpIfBlackCoefficient(gender)
        val systolicBpIfBlackProduct = systolicBpIfBlackValue * systolicBpIfBlackCoefficient
        Log.d("AppLog", "Get the Systolic BP if Black coefficient and compute the product with pt-value: $systolicBpIfBlackProduct")

        // Get the Black Race & Taking BP Med coefficient
        val blackRaceAndBpMedCoefficient =
            getBlackRaceAndBpMedCoefficient(race, gender, bpMedStatus)
        Log.d("AppLog", "Get the Black Race & Taking BP Med coefficient: $blackRaceAndBpMedCoefficient")

        // Calculate the Age x Systolic BP product
        val ageSbpProduct = age * sbp.toDouble()
        Log.d("AppLog", "Calculate the Age x Systolic BP product: $ageSbpProduct")

        // Get the Age x Systolic BP coefficient
        val ageSbpCoefficient = getAgeSbpCoefficient(race, gender)
        Log.d("AppLog", "Get the Age x Systolic BP coefficient: $ageSbpCoefficient")

        // Calculate the value for the Age x Systolic BP factor
        val ageSbpValue = ageSbpProduct * ageSbpCoefficient
        Log.d("AppLog", "Calculate the value for the Age x Systolic BP factor: $ageSbpValue")

        // Calculate the Diabetes if Black value
        val diabetesIfBlackValue = getDiabetesIfBlackCoefficient(race, gender, diabetesStatus)
        Log.d("AppLog", "Calculate the Diabetes if Black value: $diabetesIfBlackValue")

        // Calculate the Smoker if Black value
        val smokerIfBlackValue = getSmokerIfBlackCoefficient(race, gender, smokingStatus)
        Log.d("AppLog", "Calculate the Smoker if Black value: $smokerIfBlackValue")

        // Calculate the Chol total-to-HDL ratio if Black value
        val cholToHDLIfBlackCoefficient = getCholToHDLRatioIfBlackCoefficient(race, gender)
        val cholToHDLIfBlackValue = cholToHDLIfBlackCoefficient * (chol / hdl)
        Log.d("AppLog", "Calculate the Chol total-to-HDL ratio if Black value: $cholToHDLIfBlackValue")

        // Calculate the Systolic BP if on BP med if Black value, only if the user is on BP medication
        val systolicBpIfOnMedIfBlackCoefficient = if (bpMedStatus == Asvcd2018Calc.BpMedStatus.YES) {
            getSystolicBpIfOnMedIfBlackCoefficient(race, gender)
        } else {
            0.0
        }
        val systolicBpIfOnMedIfBlackValue = systolicBpIfOnMedIfBlackCoefficient * sbp
        Log.d("AppLog", "Calculate the Systolic BP if on BP med if Black value, only if the user is on BP medication: $systolicBpIfOnMedIfBlackValue")

        // Calculate the Age x Systolic BP if black value
        val ageSbpIfBlackCoefficient = getAgeSbpIfBlackCoefficient(race, gender)
        val ageSbpIfBlackValue = ageSbpIfBlackCoefficient * age * sbp
        Log.d("AppLog", "Calculate the Age x Systolic BP if black value: $ageSbpIfBlackValue")

        // Calculating the sum of all the terms
        val totalSum: Double = interceptCoefficient + ageCoefficient + blackRaceCoefficient + bpMedCoefficient +
                diabetesCoefficient + smokingCoefficient + sbpSquaredValue + sbpCoefficient + cholToHDLProduct +
                ageIfBlackProduct + systolicBpIfOnMedProduct + systolicBpIfBlackProduct +
                blackRaceAndBpMedCoefficient + ageSbpValue + diabetesIfBlackValue +
                smokerIfBlackValue + cholToHDLIfBlackValue + systolicBpIfOnMedIfBlackValue +
                ageSbpIfBlackValue
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

*/
