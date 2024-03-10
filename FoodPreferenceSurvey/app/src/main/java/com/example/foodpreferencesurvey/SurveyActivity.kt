package com.example.foodpreferencesurvey
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SurveyActivity : AppCompatActivity() {

    private lateinit var surveyType: String
    private lateinit var surveyTitleTextView: TextView
    private lateinit var question1TextView: TextView
    private lateinit var question1RadioGroup: RadioGroup
    private lateinit var question2TextView: TextView
    private lateinit var question2RadioGroup: RadioGroup
    private lateinit var question3TextView: TextView
    private lateinit var question3RadioGroup: RadioGroup
    private lateinit var question4TextView: TextView
    private lateinit var question4RadioGroup: RadioGroup
    private lateinit var question5TextView: TextView
    private lateinit var question5RadioGroup: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var answersTextView: TextView
    private val answersList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        surveyType = intent.getStringExtra("surveyType") ?: ""

        surveyTitleTextView = findViewById(R.id.surveyTitleTextView)
        surveyTitleTextView.text = surveyType;
        question1TextView = findViewById(R.id.question1TextView)
        question1RadioGroup = findViewById(R.id.question1RadioGroup)
        question2TextView = findViewById(R.id.question2TextView)
        question2RadioGroup = findViewById(R.id.question2RadioGroup)


        question3TextView = findViewById(R.id.question3TextView)
        question3RadioGroup = findViewById(R.id.question3RadioGroup)
        question4TextView = findViewById(R.id.question4TextView)
        question4RadioGroup = findViewById(R.id.question4RadioGroup)
        question5TextView = findViewById(R.id.question5TextView)
        question5RadioGroup = findViewById(R.id.question5RadioGroup)

        submitButton = findViewById(R.id.submitButton)
        answersTextView = findViewById(R.id.answersTextView)
 setupSurvey()
        submitButton.setOnClickListener {
            if (validateSurvey()) {
                collectAnswers()
                displayAnswers()
            } else {
                Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateSurvey(): Boolean {
        return isQuestionAnswered(question1RadioGroup) &&
                isQuestionAnswered(question2RadioGroup) &&
                isQuestionAnswered(question3RadioGroup) &&
                isQuestionAnswered(question4RadioGroup) &&
                isQuestionAnswered(question5RadioGroup)
    }

    private fun isQuestionAnswered(radioGroup: RadioGroup): Boolean {
        return radioGroup.checkedRadioButtonId != -1
    }

    private fun setupSurvey() {


        if (surveyType == "Food Preferences") {
            question1TextView.text = "What is your favorite cuisine?"
            setupRadioGroup(question1RadioGroup, arrayOf("Chinese", "French", "Italian", "Indian", "Japanese", "Thai", "Turkish"))

            question2TextView.text = "How often do you eat out?"
            setupRadioGroup(question2RadioGroup, arrayOf("Never", "Rarely", "Sometimes", "Frequently"))

            question3TextView.text = "Do you prefer spicy food?"
            setupRadioGroup(question3RadioGroup, arrayOf("Yes", "No"))

            question4TextView.text = "Do you prefer vegetarian food?"
            setupRadioGroup(question4RadioGroup, arrayOf("Yes", "No"))

            question5TextView.text = "Do you like seafood?"
            setupRadioGroup(question5RadioGroup, arrayOf("Yes", "No"))
        } else if (surveyType == "Dietary Habits") {
            question1TextView.text = "Are you vegetarian?"
            setupRadioGroup(question1RadioGroup, arrayOf("Yes", "No"))

            question2TextView.text = "Do you prefer organic food?"
            setupRadioGroup(question2RadioGroup, arrayOf("Yes", "No"))

            question3TextView.text = "Do you consume dairy products?"
            setupRadioGroup(question3RadioGroup, arrayOf("Yes", "No"))

            question4TextView.text = "Do you eat fast food?"
            setupRadioGroup(question4RadioGroup, arrayOf("Yes", "No"))

            question5TextView.text = "Do you have any food allergies?"
            setupRadioGroup(question5RadioGroup, arrayOf("Yes", "No"))
        }


    }

    private fun setupRadioGroup(radioGroup: RadioGroup, options: Array<String>) {
        for (option in options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }
    }

    private fun getSelectedOption(radioGroup: RadioGroup): String {
        val radioButtonId = radioGroup.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(radioButtonId)
        return radioButton.text.toString()
    }

    private fun collectAnswers() {
        answersList.clear()
        val question1TextView = findViewById<TextView>(R.id.question1TextView)
        val question1RadioGroup = findViewById<RadioGroup>(R.id.question1RadioGroup)
        val answer1 = "${question1TextView.text}: ${getSelectedOption(question1RadioGroup)}"
        answersList.add(answer1)

        val question2TextView = findViewById<TextView>(R.id.question2TextView)
        val question2RadioGroup = findViewById<RadioGroup>(R.id.question2RadioGroup)
        val answer2 = "${question2TextView.text}: ${getSelectedOption(question2RadioGroup)}"
        answersList.add(answer2)

        val question3TextView = findViewById<TextView>(R.id.question3TextView)
        val question3RadioGroup = findViewById<RadioGroup>(R.id.question3RadioGroup)
        val answer3 = "${question3TextView.text}: ${getSelectedOption(question3RadioGroup)}"
        answersList.add(answer3)

        val question4TextView = findViewById<TextView>(R.id.question4TextView)
        val question4RadioGroup = findViewById<RadioGroup>(R.id.question4RadioGroup)
        val answer4 = "${question4TextView.text}: ${getSelectedOption(question4RadioGroup)}"
        answersList.add(answer4)

        val question5TextView = findViewById<TextView>(R.id.question5TextView)
        val question5RadioGroup = findViewById<RadioGroup>(R.id.question5RadioGroup)
        val answer5 = "${question5TextView.text}: ${getSelectedOption(question5RadioGroup)}"
        answersList.add(answer5)

    }

    private fun displayAnswers() {
        val answersText = StringBuilder()
        for (answer in answersList) {
            answersText.append(answer).append("\n")
        }
        answersTextView.text = answersText.toString()
    }
}



