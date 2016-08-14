package com.example.volks.nanoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Opens Quiz Activity
     * @param v
     */
    public void toHistoryQuiz(View v)
    {
        Intent openquiz = new Intent(MainActivity.this, UecHistoryQuiz.class);
        startActivity(openquiz);
    }

    /**
     * Opens Source and Disclaimer Acitivity
     * @param view
     */
    public void toSources(View view)
    {
        Intent sources = new Intent(MainActivity.this, Sources.class);
        startActivity(sources);
    }
}