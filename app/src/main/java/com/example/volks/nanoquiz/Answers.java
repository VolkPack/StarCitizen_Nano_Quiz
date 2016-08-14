package com.example.volks.nanoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Answers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        setAnswers();
    }

    /**
     * Displays Correct Answers in the TextView
     */
    private void setAnswers()
    {
        StringBuilder answers = new StringBuilder(256);
        String[] correctAnswers = getResources().getStringArray(R.array.correct_answers);
        TextView answer_view = (TextView) findViewById(R.id.answers);
        for(int i = 0 ; i < correctAnswers.length; i++)
        {
            answers.append(correctAnswers[i]);
        }
        answer_view.setText(answers.toString());
    }
}