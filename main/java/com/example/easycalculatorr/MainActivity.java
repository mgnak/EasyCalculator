package com.example.easycalculatorr;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resulttv, soltv;
    MaterialButton buttonC, buttonBOpen, buttonBClose;
    MaterialButton buttonDiv, buttonMul, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resulttv = findViewById(R.id.result_tv);
        soltv = findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.button_c);
        assignId(buttonAC, R.id.button_ac);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonDiv, R.id.button_divide);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonMul, R.id.button_mul);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonEquals, R.id.button_equal);
        assignId(buttonBClose, R.id.button_closeB);
        assignId(buttonBOpen, R.id.button_openB);
    }


    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = soltv.getText().toString();

        if(buttonText.equals("AC")){
            soltv.setText("");
            resulttv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            soltv.setText(resulttv.getText());
            return;
        }
        if(buttonText.equals("C")){
            //dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            if (!dataToCalculate.isEmpty()) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }

        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        soltv.setText(dataToCalculate);

        if (dataToCalculate.isEmpty()) {
            resulttv.setText("");
        } else {
            String finalResult = getResult(dataToCalculate);
            if (!finalResult.equals("Err")) {
                resulttv.setText(finalResult);
            }
        }
    }
    String getResult(String data){
        try{
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}