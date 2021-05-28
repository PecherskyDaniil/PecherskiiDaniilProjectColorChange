package com.example.pecherskiidaniilproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button beginPlay,beginOptions;
    Intent play,options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginOptions=findViewById(R.id.optionsbutton);
        beginPlay=findViewById(R.id.beginPlay);
        options=new Intent(MainActivity.this,OptionsActivity.class);
        play=new Intent(MainActivity.this,LevelsActivity.class);
        beginPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(play);
            }
        });
        beginOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(options);
            }
        });


    }
}