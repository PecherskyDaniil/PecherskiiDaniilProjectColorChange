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
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.abs;

public class GameActivity extends AppCompatActivity {
    Bundle arguments;
    int keyOfLevel;
    long startTime=60*1000;
    long intervalTime=1*1000;
    TextView level;
    Drawlines drawlines;
    String drawType = "";
    ArrayList<Float> coordinatx = new ArrayList<>();
    ArrayList<Float> coordinaty = new ArrayList<>();
    ArrayList<Integer> colorArray=new ArrayList<>();
    Button acceptTurn;
    ImageButton redButton,blueButton,greenButton;
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
        for (int i = 0; i < 10 + 5 * (keyOfLevel + 1); i++) {
            if (random.nextInt(3)==1){
                colorArray.add(Color.BLUE);
            }else if(random.nextInt(3)==2){
                colorArray.add(Color.GREEN);
            }else{
                colorArray.add(Color.RED);
            }
        }
        questPrint(questArray,colorArray);
        TextView timerText=findViewById(R.id.timer);
        final GameCountDownTimer gameCountDownTimer=new GameCountDownTimer(timerText,intervalTime,startTime/(keyOfLevel+1));
        gameCountDownTimer.start();
        acceptTurn=findViewById(R.id.acceptTurn);
        acceptTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameCountDownTimer.isTimeEnd()) {
                    if (drawlines.coordinatx.size() != 0 && colorArray.size()!=0) {
                        drawType = lineTest(drawlines.coordinaty, drawlines.coordinatx);
                        if (questArray.size() != 0) {
                            if (drawType == "vertical" && questArray.get(0) == "|" && drawlines.colorLine==colorArray.get(0)) {
                                questArray.remove(0);
                                colorArray.remove(0);
                                questPrint(questArray,colorArray);
                            }
                            if (drawType == "horizontal" && questArray.get(0) == "-" && drawlines.colorLine==colorArray.get(0)) {
                                questArray.remove(0);
                                colorArray.remove(0);
                                questPrint(questArray,colorArray);
                            }
                            if (drawlines.coordinatx.size()==0){
                                questText.setText("You win");
                                gameCountDownTimer.cancel();
                            }
                        } else {
                            questText.setText("You win");
                            gameCountDownTimer.cancel();
                        }
                        drawlines.clearCanvas = true;
                        drawlines.startLine = true;
                        drawlines.coordinaty.clear();
                        drawlines.coordinatx.clear();
                        drawlines.invalidate();
                    }else{
                        if (drawlines.coordinatx.size()==0){
                            questText.setText("You win");
                            gameCountDownTimer.cancel();
                        }
                    }
                }else{
                    questText.setText("You lose");
                    questArray.clear();
                }
            }
        });
        drawlines=findViewById(R.id.draw);
        redButton=findViewById(R.id.red);
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawlines.colorLine=Color.RED;
            }
        });
        blueButton=findViewById(R.id.blue);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawlines.colorLine=Color.BLUE;
            }
        });
        greenButton=findViewById(R.id.green);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawlines.colorLine=Color.GREEN;
            }
        });

    }


    public void questPrint(ArrayList<String> questArray,ArrayList<Integer> colorArray) {
        if (questArray.size()!=0) {
            questText.setText(questArray.get(0));
            questText.setTextColor(colorArray.get(0));
        }else{
            questText.setText("");
        }
    }

}
class GameCountDownTimer extends CountDownTimer{
    TextView timerText;
    int intervalTime;
    float startTime;
    boolean timeEnd=true;
    float timeProcess;
    public GameCountDownTimer(TextView timerText, long intervalTime, long startTime) {
        super(startTime, intervalTime);
        this.timerText = timerText;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timerText.setText("Осталось "+(millisUntilFinished/1000)+" секунд");
    }

    public boolean isTimeEnd() {
        return timeEnd;
    }

    @Override
    public void onFinish() {
        timerText.setText("Время на исходе!");
        timeEnd=false;
    }
}