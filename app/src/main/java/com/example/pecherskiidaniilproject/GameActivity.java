package com.example.pecherskiidaniilproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class GameActivity extends AppCompatActivity {
    Bundle arguments;
    int keyOfLevel;
    TextView level;
    Drawlines drawlines;
    String drawType = "";
    ArrayList<Float> coordinatx = new ArrayList<>();
    ArrayList<Float> coordinaty = new ArrayList<>();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            drawType = lineTest(drawlines.coordinaty, drawlines.coordinatx);
            if (questArray.size() != 0) {
                if (drawType == "vertical" && questArray.get(0) == "|") {
                    questArray.remove(0);
                    questPrint(questArray);
                }
                if (drawType == "horizontal" && questArray.get(0) == "-") {
                    questArray.remove(0);
                    questPrint(questArray);
                }
            } else {
                questText.setText("You win");
            }
            coordinatx.clear();
            coordinaty.clear();
        }
        coordinatx.add(event.getX());
        coordinaty.add(event.getY());

        return super.onTouchEvent(event);
    }

    public String lineTest(ArrayList<Float> coordinaty, ArrayList<Float> coordinatx) {
        int verticalLineCounter = 0;
        float endy = coordinaty.get(coordinaty.size() - 1);
        float beginy = coordinaty.get(1);
        float endx = coordinatx.get(coordinatx.size() - 1);
        float beginx = coordinatx.get(1);
        int horizontalLineCounter = 0;
        boolean equalCoordinats;
        String codeError = "";

        if (abs(endy - beginy) > 100 && abs(endx - beginx) < 100) {
            if (coordinaty.get(1) > coordinaty.get(coordinaty.size() - 1)) {
                equalCoordinats = true;
            } else {
                equalCoordinats = false;
            }
            for (int i = 2; i < coordinatx.size() - 1; i++) {
                if ((abs(coordinatx.get(coordinatx.size() - 1) - coordinatx.get(i)) < 100
                        && abs(coordinatx.get(1) - coordinatx.get(i)) < 100)) {
                    if (abs(coordinatx.get(1) - coordinatx.get(coordinatx.size() - 1)) < 100)
                        if (coordinaty.get(i - 1) > coordinaty.get(i) == equalCoordinats) {
                            verticalLineCounter++;
                        } else {
                            verticalLineCounter = -coordinatx.size();
                            codeError = "1";
                        }
                }
            }
        }
        if (abs(endx - beginx) > 100 && abs(endy - beginy) < 100) {
            if (coordinatx.get(1) > coordinatx.get(coordinatx.size() - 1)) {
                equalCoordinats = true;
            } else {
                equalCoordinats = false;
            }
            for (int i = 2; i < coordinatx.size() - 1; i++) {
                if ((abs(coordinaty.get(coordinaty.size() - 1) - coordinaty.get(i)) < 100
                        && abs(coordinaty.get(1) - coordinaty.get(i)) < 100)) {
                    if (abs(coordinaty.get(1) - coordinaty.get(coordinaty.size() - 1)) < 100)
                        if (coordinatx.get(i - 1) > coordinatx.get(i) == equalCoordinats) {
                            horizontalLineCounter++;
                        } else {
                            horizontalLineCounter = -coordinatx.size();
                            codeError = "2";
                        }
                }
            }
        }
        if (verticalLineCounter > coordinaty.size() / 2) {
            return "vertical";
        } else if (horizontalLineCounter > coordinatx.size() / 2) {
            return "horizontal";
        } else {
            return "untitled" + " " + codeError;
        }
    }
    TextView questText;
    public ArrayList<String> questArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random random = new Random();
        setContentView(R.layout.activity_game);
        drawlines=findViewById(R.id.draw);
        questArray = new ArrayList<>();
        arguments = getIntent().getExtras();
        questText = findViewById(R.id.questText);
        keyOfLevel = arguments.getInt("keyLevel");
        level = findViewById(R.id.level);
        level.setText("Сложность  " + (keyOfLevel + 1));
        for (int i = 0; i < 10 + 5 * keyOfLevel; i++) {
            if (random.nextBoolean()) {
                questArray.add("|");
            } else {
                questArray.add("-");
            }
        }
        questPrint(questArray);

    }


    public void questPrint(ArrayList<String> questArray) {
        questText.setText("");
        for (int i = 0; i < questArray.size(); i++) {
            questText.setText(questText.getText() + " " + questArray.get(i));
        }

    }

}