package com.example.mycalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txt_Input, txt_Result;
    MaterialButton btn_C, btn_Open_brac, btn_Close_brac;
    MaterialButton btn_Devide, btn_Multi, btn_Minus, btn_Plus, btn_Equal;
    MaterialButton btn_7, btn_8, btn_9, btn_4, btn_5, btn_6, btn_1, btn_2, btn_3, btn_Ac, btn_0, btn_Dot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_Input = findViewById(R.id.txt_Input);
        txt_Result = findViewById(R.id.txt_Result);
        assignId(btn_C,R.id.btn_C);
        assignId(btn_Open_brac,R.id.btn_Open_brac);
        assignId(btn_Close_brac,R.id.btn_Close_brac);
        assignId(btn_Devide,R.id.btn_Devide);
        assignId(btn_Multi,R.id.btn_Multi);
        assignId(btn_Plus,R.id.btn_Plus);
        assignId(btn_Minus,R.id.btn_Minus);
        assignId(btn_Equal,R.id.btn_Equal);
        assignId(btn_0,R.id.btn_0);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);
        assignId(btn_Ac,R.id.btn_Ac);
        assignId(btn_Dot,R.id.btn_Dot);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = txt_Input.getText().toString();

        if (buttonText.equals("AC")){
            txt_Input.setText("");
            txt_Result.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            txt_Input.setText(txt_Result.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        txt_Input.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            txt_Result.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}