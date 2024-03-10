package com.example.foodpreferencesurvey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {

    private lateinit var startSurveyButton: Button
    private lateinit var surveyTypeRadioGroup: RadioGroup
    private lateinit var foodPreferencesRadioButton: RadioButton
    private lateinit var dietaryHabitsRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startSurveyButton = findViewById(R.id.startSurveyButton)
        surveyTypeRadioGroup = findViewById(R.id.surveyTypeRadioGroup)
        foodPreferencesRadioButton = findViewById(R.id.foodPreferencesRadioButton)
        dietaryHabitsRadioButton = findViewById(R.id.dietaryHabitsRadioButton)

        surveyTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.foodPreferencesRadioButton -> {
                    dietaryHabitsRadioButton.isChecked = false
                }
                R.id.dietaryHabitsRadioButton -> {
                    foodPreferencesRadioButton.isChecked = false
                }
            }
        }

        startSurveyButton.setOnClickListener {
            val surveyType = when {
                foodPreferencesRadioButton.isChecked -> "Food Preferences"
                dietaryHabitsRadioButton.isChecked -> "Dietary Habits"
                else -> ""
            }
            if (surveyType.isNotBlank()) {
                startSurvey(surveyType)
            }
        }
    }

    private fun startSurvey(surveyType: String) {
        val intent = Intent(this, SurveyActivity::class.java).apply {
            putExtra("surveyType", surveyType)
        }
        startActivity(intent)
    }
}


