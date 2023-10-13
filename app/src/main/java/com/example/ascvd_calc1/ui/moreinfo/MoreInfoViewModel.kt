package com.example.ascvd_calc1.ui.moreinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoreInfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Your text for More Info goes here."
    }
    val text: LiveData<String> = _text
}
