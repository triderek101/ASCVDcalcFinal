package com.example.ascvd_calc1.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = """
            This app is inspired and guided by Dr. Josh Steinberg's 'CV Risk' Application.
            
            CONTENT & DESIGN:
            Derek Barnett BS, Penn State College of Medicine
            Celeste Sakhile MSE
            Manvita Mareboina BS, Penn State College of Medicine
            Linda Qiu BS, Penn State College of Medicine
            Katrina Bakhl BS, Penn State College of Medicine
            Joshua Steinberg MD, UHS Wilson Family
            
            VERSION: 1.1
            BUILD: 1
            COPYRIGHT: September, 2023
            
            DEDICATION: 
            To Dr. Steinberg for encouraging and guiding our team in learning a new skill 
            
            FEEDBACK: 
            dbarnett@pennstatehealth.psu.edu
        """.trimIndent()
    }

    val text: LiveData<String> = _text
}
