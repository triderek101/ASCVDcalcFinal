package com.example.ascvd_calc1.ui.references

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReferencesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = """
            D'Agostino RB, et. al.
            General Cardiovascular Risk Profile for Use in Primary Care, the Framingham Heart Study.
            Circulation. 2008;117:743-753

            Goff DC, et. al.
            2013 Report on the Assessment of Cardiovascular Risk: Full Work Group Report Supplement
            (contains discussions, appendices, data tables, analyses, and the correct ASCVD PCE formulas), 
            as cited in and linked from...
            
            Goff DC, et. al.
            2013 ACC/AHA Guideline on the Assessment of Cardiovascular Risk, A Report of the American College of Cardiology/American Heart Association Task Force on Practice Guidelines.
            Circulation. 2014;129 [suppl 2]:S49-S73

            Bell K, et. al.
            Evaluation of the Incremental Value of a Coronary Artery Calcium Score Beyond Traditional Cardiovascular Risk Assessment, A Systematic Review and Meta-analysis, More is Less series.
            JAMA Internal Medicine, published online do:10.1001/ jamainternmed.2022.1262 April 25, 2022.

            Grundy SM, et. al.
            2018 AHA/ACC/AACVPR/ AAPA/ABC/ACPM/ADA/AGS/APhA/ASPCINLA/PCNA Guideline on the Management of Blood Cholesterol.
            JACC. 2019;73:285-e350

            DeFilipis AP, et. al.
            An Analysis of Calibration and Discrimination Among Multiple Cardiovascular Risk Scores in a Modern Multiethnic Cohort.
            Ann Intern Med. 2015;162:266-275

            Yadlowsky S, et. al.
            Clinical Implications of Revised Pooled Cohort Equations for Estimating Atherosclerotic Cardiovascular Disease Risk.
            Ann Intern Med. 2018;169:20-29

            Hong SJ, et. al.
            Treat-to-Target or High-Intensity Statin in Patients With Coronary Artery Disease.
            JAMA. 2023;329(13):1078-1087

            US Preventive Services Task Force
            Recommendation Statement: Risk Assessment for Cardiovascular Disease With Nontraditional Risk Factors.
            JAMA 2018;320(3):272-280

            Byrne P, et. al.
            Evaluating the Association Between Low-Density Lipoprotein Cholesterol Reduction and Relative and Absolute Effects of Statin Treatment, A Systematic Review and Meta-analysis.
            JAMA Intern Med. 2022;182(5):474-481

            Pederson TR, et. al.
            High-Dose Atorvastatin vs Usual-Dose Simvastatin for Secondary Prevention After Myocardial Infarction.
            JAMA. 2005;294:2437-2445
        """.trimIndent()
    }
    val text: LiveData<String> = _text
}
