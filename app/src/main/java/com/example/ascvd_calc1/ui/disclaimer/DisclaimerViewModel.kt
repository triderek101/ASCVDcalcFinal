package com.example.ascvd_calc1.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisclaimerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = """
            **For Medical Professionals Only**
            This application is tailored specifically for CLINICIANS. If you are not a medical professional, please consult your physician for advice.

            **Note to Fellow Medical Professionals**,
            The incorporated calculators are derived from established literature (references provided). While I've aimed to replicate these calculators accurately, my intent is not to supplant personal judgment. If you believe there's an error in representation or elucidation, I'd appreciate your input! My annotations and explanations stem from my own understanding and teachings on the crucial subject of primary prevention of cardiovascular disease. Your feedback would be invaluable in refining and expanding my perspectives and enhancing the pursuit of patient-centered evidence-based medicine.

            It's important to highlight:
            - This application isn't intended to replace the requisite expertise, training, or experience in cholesterol and cardiovascular risk assessment.
            - These calculators serve as rapid reference aids. You should be well-acquainted with them and comprehend their clinical application.
            - Multiple cardiac risk assessment tools exist. It's essential to discern the merits and limitations of each and determine their appropriate usage.
            - As with all calculators, these tools provide general guidance for the average patient. However, they don't dictate the management of any specific patient. Personalized care is pivotal.

        """.trimIndent()
    }
    val text: LiveData<String> = _text
}
