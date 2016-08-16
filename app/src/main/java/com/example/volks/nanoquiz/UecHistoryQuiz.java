package com.example.volks.nanoquiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class UecHistoryQuiz extends AppCompatActivity {

    private String[] userAnswers = new String[4]; //User Input answers
    private boolean graded = false;

    /**
     * onCreate populates Radiobuttons, TextViews and CheckBox with text
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uec_history_quiz);
        popQuestions();
    }

    public void seeAnswers(View view)
    {
        Intent seeAnswers = new Intent(UecHistoryQuiz.this, Answers.class);
        startActivity(seeAnswers);
    }
    /**
     * IF Answres have been graded Opens an Email App
     * In the Body Included are the UserAnswers
     * as well as the correct answers for the first three questions
     * IF answers have not been Graded Shows a toast Reminder to grade the Quiz
     * @param view
     */
    public void emailAnswers(View view)
    {
        if(graded)
        {
            //Creates a String using UserInput Answers
            //Preset Text
            //Correct Answers
            StringBuilder answers = new StringBuilder(256);
            String[] correctAnswers = getResources().getStringArray(R.array.correct_answers);
            answers.append("Quantum Core Engine Was Patented in ");
            answers.append(userAnswers[0] + "\n\n");
            answers.append("Major Even in 2232 was the ");
            answers.append(userAnswers[1] + "\n\n");
            answers.append("Ships Designed For Exploration are: ");
            answers.append(userAnswers[2] + "\n\n");
            answers.append("My Desired profession in the \'verse is/are ");
            answers.append(userAnswers[3] + "\n\n");
            answers.append("THE CORRECT ANSWERS ARE BELOW\n\n");

            for(int i = 0 ; i < correctAnswers.length; i++)
            {
                answers.append(correctAnswers[i]);
            }
            //Open Email By Intent
            //Populate Body with User Answers and Correct Answers
            //Populate Subject
            Intent ShareEmail = new Intent(Intent.ACTION_SEND);
            ShareEmail.setType("text/html");
            ShareEmail.putExtra(Intent.EXTRA_SUBJECT, "StarCitizen Nano Quiz Answers");
            ShareEmail.putExtra(Intent.EXTRA_TEXT, answers.toString());
            try
            {
                startActivity(Intent.createChooser(ShareEmail, "CHOOSE EMAIL CLIENT"));
            }
            catch (android.content.ActivityNotFoundException ex)
            {
                Toast.makeText(getApplicationContext(), "NO APP TO HANDLE THIS", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Quiz Not Graded Yet", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Checks if all Questions Have been answered
     * @return TRUE if all questions have been answered FALSE otherwise
     */
    public boolean validateInput()
    {
        RadioGroup firstSet = (RadioGroup) findViewById(R.id.first_group);
        RadioGroup secondSet = (RadioGroup) findViewById(R.id.second_group);
        boolean flag;
        if(firstSet.getCheckedRadioButtonId() == -1 ||
                secondSet.getCheckedRadioButtonId() ==-1)
        {
            flag = false; //not all Question Have been Answered
        }
        else
        {
            flag = true; //All questions have been answered
        }
        return flag;
    }
    /**
     * Sets All Answer Results to INCORRECT and In RED
     * Checks each answer if Correct
     * If Correct Changes color to GREEN and Result to CORRECT
     * Records User Input Answers into global var userAnswers
     * @param view
     */
    public void checkAnswers(View view)
    {
        //Variables and view declaration and initialization
        RadioButton userChoice;
        RadioButton answer1 = (RadioButton) findViewById(R.id.q1a2);
        RadioButton answer2 = (RadioButton) findViewById(R.id.q2a3);

        CheckBox correctanswer1 = (CheckBox) findViewById(R.id.q3a1);
        CheckBox correctanswer2 = (CheckBox) findViewById(R.id.q3a3);
        CheckBox wronganswer1 = (CheckBox) findViewById(R.id.q3a2);
        CheckBox wronganswer2 = (CheckBox) findViewById(R.id.q3a4);

        EditText userInput = (EditText) findViewById(R.id.free_answer);

        TextView result1 = (TextView) findViewById(R.id.q1Result);
        TextView result2 = (TextView) findViewById(R.id.q2Result);
        TextView result3 = (TextView) findViewById(R.id.q3Result);

        CheckBox userCheck;
        StringBuilder checkTemp = new StringBuilder();

        if(validateInput()) //checks if questions were answered
        {
            graded = true;
            //Sets ALL Answer Results to Incorrect and changes Color to Red
            result1.setText(R.string.incorrect);
            result2.setText(R.string.incorrect);
            result3.setText(R.string.incorrect);
            result1.setTextColor(getResources().getColor(R.color.incorrect));
            result2.setTextColor(getResources().getColor(R.color.incorrect));
            result3.setTextColor(getResources().getColor(R.color.incorrect));

            //Checks if the The First answer is Correct and if True
            //Changes Color to Green and Text To Correct
            if (answer1.isChecked()) {
                result1.setText(R.string.correct);
                result1.setTextColor(getResources().getColor(R.color.correct));
            }

            //Checks if the The second answer is Correct and if True
            //Changes Color to Green and Text To Correct
            if (answer2.isChecked()) {
                result2.setText(R.string.correct);
                result2.setTextColor(getResources().getColor(R.color.correct));
            }

            //Checks if the The third answer is Correct and contains both answers
            //If True Changes Color to Green and Text To Correct
            if (correctanswer1.isChecked() && correctanswer2.isChecked() && !wronganswer1.isChecked() && !wronganswer2.isChecked()) {
                result3.setText(R.string.correct);
                result3.setTextColor(getResources().getColor(R.color.correct));
            }

            //Gets Users Answer for the First Question
            //Adds that Answer to UserAnswer Array
            RadioGroup firstSet = (RadioGroup) findViewById(R.id.first_group);
            userChoice = (RadioButton) findViewById(firstSet.getCheckedRadioButtonId());
            userAnswers[0] = userChoice.getText().toString();

            //Gets Users Answer for the Second Question
            //Adds that Answer to UserAnswer Array
            RadioGroup secondSet = (RadioGroup) findViewById(R.id.second_group);
            userChoice = (RadioButton) findViewById(secondSet.getCheckedRadioButtonId());
            userAnswers[1] = userChoice.getText().toString();

            //Check Which boxes are checked for the Third Question
            //Adds Text from Checked boxes to UserAnswer Array
            userCheck = (CheckBox) findViewById(R.id.q3a1);
            if (userCheck.isChecked()) {
                checkTemp.append(userCheck.getText().toString());
            }
            userCheck = (CheckBox) findViewById(R.id.q3a2);
            if (userCheck.isChecked()) {
                checkTemp.append(" " + userCheck.getText().toString());
            }
            userCheck = (CheckBox) findViewById(R.id.q3a3);
            if (userCheck.isChecked()) {
                checkTemp.append(" " + userCheck.getText().toString());
            }
            userCheck = (CheckBox) findViewById(R.id.q3a4);
            if (userCheck.isChecked()) {
                checkTemp.append(" " + userCheck.getText().toString());
            }
            userAnswers[2] = checkTemp.toString();

            //Checks if The User Free Answer is Null if Not Adds it to User Answers Array
            if (userInput.getText().toString() != null)
            {
                userAnswers[3] = userInput.getText().toString();
            }
            if (userInput.getText().toString().equals(""))
            {
                CharSequence reminder_string = getString(R.string.reminder);
                Toast.makeText(getApplicationContext(), reminder_string, Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            CharSequence warning_string = getString(R.string.warning);
            Toast.makeText(getApplicationContext(), warning_string, Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Populates all RadioButtons, TextView and CheckBox with Text from resources
     */
    public void popQuestions()
    {
        String[] questionList = getResources().getStringArray(R.array.question_One);
        String[] questionList2 = getResources().getStringArray(R.array.question_Two);
        String[] questionList3 = getResources().getStringArray(R.array.question_Three);

        TextView question;
        RadioButton q1;
        RadioButton q2;
        CheckBox q3;

        question = (TextView) findViewById(R.id.question1);
        question.setText(questionList[0]);
        q1 = (RadioButton) findViewById(R.id.q1a1);
        q1.setText(questionList[1]);
        q1 = (RadioButton) findViewById(R.id.q1a2);
        q1.setText(questionList[2]);
        q1 = (RadioButton) findViewById(R.id.q1a3);
        q1.setText(questionList[3]);
        q1 = (RadioButton) findViewById(R.id.q1a4);
        q1.setText(questionList[4]);

        question = (TextView) findViewById(R.id.question2);
        question.setText(questionList2[0]);
        q2=(RadioButton) findViewById(R.id.q2a1);
        q2.setText(questionList2[1]);
        q2=(RadioButton) findViewById(R.id.q2a2);
        q2.setText(questionList2[2]);
        q2 = (RadioButton) findViewById(R.id.q2a3);
        q2.setText(questionList2[3]);
        q2 = (RadioButton) findViewById(R.id.q2a4);
        q2.setText(questionList2[4]);

        question = (TextView) findViewById(R.id.question3);
        question.setText(questionList3[0]);
        q3 = (CheckBox) findViewById(R.id.q3a1);
        q3.setText(questionList3[1]);
        q3 = (CheckBox) findViewById(R.id.q3a2);
        q3.setText(questionList3[2]);
        q3 = (CheckBox) findViewById(R.id.q3a3);
        q3.setText(questionList3[3]);
        q3 = (CheckBox) findViewById(R.id.q3a4);
        q3.setText(questionList3[4]);

        question = (TextView) findViewById(R.id.question4);
        question.setText(R.string.question_four);
    }
}