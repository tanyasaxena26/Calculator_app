package com.tanya.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView result_t,solution_t;
    MaterialButton button_c, button_open_bracket,button_close_bracket, button_divide,button_7,button_8,button_9,button_multiply,button_6,button_5,button_4,button_add,button_3,button_2,button_1,button_subtract,button_AC,button_0,button_dot,button_equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_t=findViewById(R.id.result_t);
        solution_t=findViewById(R.id.solution_t);

        assignId(button_c,R.id.button_c);
        assignId(button_open_bracket,R.id.button_open_bracket);
        assignId(button_close_bracket,R.id.button_close_bracket);
        assignId(button_divide,R.id.button_divide);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_multiply,R.id.button_multiply);
        assignId(button_6,R.id.button_6);
        assignId(button_5,R.id.button_5);
        assignId(button_4,R.id.button_4);
        assignId(button_add,R.id.button_add);
        assignId(button_3,R.id.button_3);
        assignId(button_2,R.id.button_2);
        assignId(button_1,R.id.button_1);
        assignId(button_subtract,R.id.button_subtract);
        assignId(button_AC,R.id.button_AC);
        assignId(button_0,R.id.button_0);
        assignId(button_dot,R.id.button_dot);
        assignId(button_equals,R.id.button_equals);


    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solution_t.getText().toString();
        if(buttonText.equals("AC")){
            solution_t.setText("");
            result_t.setText("0");
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate=dataToCalculate+buttonText;
        }
        if(buttonText.equals("=")){
            solution_t.setText(result_t.getText());
            return;
         }
        solution_t.setText(dataToCalculate);
       String finalRes=getRes(dataToCalculate);
       if(!finalRes.equals("Error")){
          result_t.setText(finalRes);
}

}
    String getRes(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalRes=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalRes.endsWith(".0")){
               finalRes=finalRes.replace(".0","");
           }
            return finalRes;
        }catch(Exception e){
            return "Error";
        }
    }
}