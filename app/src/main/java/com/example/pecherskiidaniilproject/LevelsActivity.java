package com.example.pecherskiidaniilproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelsActivity extends AppCompatActivity {
    int availableLevel,unavailableLevel,keyOfLevel,levelNumbers;
    Button backToMenu;

    Intent toMenu,toGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Resources resources=getResources();
        levelNumbers=3;
        final Boolean[] accessLevels=new Boolean[levelNumbers];
        Button[] onLevels=new Button[levelNumbers];
        accessLevels[0]=true;
        for (int i = 1; i < accessLevels.length; i++) {
            accessLevels[i]=true;
        }

        availableLevel= resources.getColor(R.color.colorAvailable);
        unavailableLevel=resources.getColor(R.color.colorUnavailable);
        onLevels[0]=findViewById(R.id.level1);
        onLevels[1]=findViewById(R.id.level2);
        onLevels[2]=findViewById(R.id.level3);
        backToMenu=findViewById(R.id.backToMenu);
        toMenu=new Intent(LevelsActivity.this,MainActivity.class);
        toGame = new Intent(LevelsActivity.this,GameActivity.class);

        for (int i = 0; i < accessLevels.length; i++) {
            if (accessLevels[i]){
                onLevels[i].setTextColor(availableLevel);
            }else{
                onLevels[i].setTextColor(unavailableLevel);
            }

        }
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toMenu);
            }
        });

        for (int i = 0; i < onLevels.length; i++) {
            final int finalI = i;
            onLevels[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (accessLevels[finalI]){
                        keyOfLevel=finalI;
                        toGame.putExtra("keyLevel",keyOfLevel);
                        startActivity(toGame);
                    }
                }
            });
        }
    }
}