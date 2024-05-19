package com.example.pahamotang_multicalculator;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution,answer;
    MaterialButton C,openBracket,closeBracket;
    MaterialButton buttondivide,buttonMultiply,buttonAdd,buttonSubtract,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttondot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solution = findViewById(R.id.solutionView);
        answer = findViewById(R.id.answerView);

        assignId(C,R.id.buttonC);
        assignId(openBracket,R.id.openBracket_btn);
        assignId(closeBracket,R.id.closeBracket_btn);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonAdd,R.id.button_add);
        assignId(buttonSubtract,R.id.button_subtract);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.number0);
        assignId(button1,R.id.number1);
        assignId(button2,R.id.number2);
        assignId(button3,R.id.number3);
        assignId(button4,R.id.number4);
        assignId(button5,R.id.number5);
        assignId(button6,R.id.number6);
        assignId(button7,R.id.number7);
        assignId(button8,R.id.number8);
        assignId(button9,R.id.number9);
        assignId(buttonAC,R.id.clear);
        assignId(buttondot,R.id.point);


    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            answer.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(answer.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            answer.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
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