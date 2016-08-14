package com.example.volks.nanoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Sources extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        setTextViews();
    }

    /**
     * Populates All text view with Strings
     * on sources and disclaimer Activity
     */
    public void setTextViews()
    {
        TextView history = (TextView) findViewById(R.id.history_View);
        TextView ships = (TextView) findViewById(R.id.ship_view);
        TextView game = (TextView) findViewById(R.id.game_info_view);
        TextView disclaimer = (TextView) findViewById(R.id.disclaimer);

        StringBuilder temp = new StringBuilder(64);

        temp.append(getString(R.string.history_source) + " "+getString(R.string.history_source_url) + " "+getString(R.string.visit_history));
        history.setText(temp);
        temp.setLength(0);

        temp.append(getString(R.string.ship_sources)+ " "+getString(R.string.ship_source_url) + " "+getString(R.string.visit_ships));
        ships.setText(temp);
        temp.setLength(0);

        temp.append(getString(R.string.game_info)+ " " + getString(R.string.game_info_url));
        game.setText(temp);
        temp.setLength(0);

        disclaimer.setText(getString(R.string.discplaimer));
    }
}