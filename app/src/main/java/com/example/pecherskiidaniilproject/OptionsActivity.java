package com.example.pecherskiidaniilproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();
        final String[] colors = new String[3];
        Spinner colorChoice1,colorChoice2,colorChoice3;
        Button buttonSave=findViewById(R.id.save);
        colorChoice1=findViewById(R.id.colorchoice1);
        colorChoice2=findViewById(R.id.colorchoice2);
        colorChoice3=findViewById(R.id.colorchoice3);
        ArrayAdapter<?> arrayAdapterColor=ArrayAdapter.createFromResource(this,R.array.colorArray,R.layout.support_simple_spinner_dropdown_item);
        arrayAdapterColor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        colorChoice1.setAdapter(arrayAdapterColor);
        colorChoice2.setAdapter(arrayAdapterColor);
        colorChoice3.setAdapter(arrayAdapterColor);
        colorChoice1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] arrayString=getResources().getStringArray(R.array.colorArray);
                colors[0] =arrayString[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                colors[0]="red";
            }
        });
        colorChoice2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] arrayString=getResources().getStringArray(R.array.colorArray);
                colors[1] =arrayString[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                colors[1]="blue";
            }
        });
        colorChoice3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] arrayString=getResources().getStringArray(R.array.colorArray);
                colors[2] =arrayString[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                colors[2]="green";
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(colors[0].equals("")) && !(colors[1].equals("")) && !(colors[2].equals(""))) {
                    if (!(colors[0].equals(colors[1])) && !(colors[1].equals(colors[2])) && !(colors[0].equals(colors[2]))) {
                        editor.putString("color1", colors[0]);
                        editor.putString("color2", colors[1]);
                        editor.putString("color3", colors[2]);//InputString: from the EditText
                        editor.commit();
                    }else{
                        editor.putString("color1", "blue");
                        editor.putString("color2", "green");
                        editor.putString("color3", "red");//InputString: from the EditText
                        editor.commit();
                    }
                }else{
                    editor.putString("color1", "blue");
                    editor.putString("color2", "green");
                    editor.putString("color3", "red");//InputString: from the EditText
                    editor.commit();
                }
            }
        });
    }
}