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


        beginPlay=findViewById(R.id.beginPlay);
        beginOptions=findViewById(R.id.options);

        play=new Intent(MainActivity.this,LevelsActivity.class);




        beginPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(play);
            }
        });


    }
}