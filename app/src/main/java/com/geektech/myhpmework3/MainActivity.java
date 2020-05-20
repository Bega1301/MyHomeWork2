package com.geektech.myhpmework3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView resultField;
    TextView operationField;
    EditText numberField;
    Double operand=null;
    String lastOperation="=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultField=(TextView)findViewById(R.id.resultField);
        numberField=(EditText)findViewById(R.id.numberField);
        operationField=(TextView)findViewById(R.id.operationField);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null) {
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);}
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation=savedInstanceState.getString("OPERATION");
        operand=savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }
    public void onNumberClick (View view){

        Button button= (Button)view;
        numberField.append(button.getText());
        if (lastOperation.equals("=")&& operand!=null) {
            operand=null;
        }


    }
    public void onOperationClick1 (View view) {
        Button button1=(Button) view;
        String op1= button1.getText().toString();
        resultField.setText(" ");

    }
    public void onOperationClick (View view) {
        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }

        lastOperation = op;
        operationField.setText(lastOperation);


    }

    public void performOperation (Double number, String operation){
        if (operand ==null) {
            operand=number;
        }
        else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation){
            case "=":
                operand=number;
                break;


            case "/":
                    if(number==0){
                        operand=0.0;
                    }
                    else{
                        operand /=number;
                    }
                 break;
            case "*":
                operand *=number;
                break;
            case "-":
                operand -=number;
                break;
            case "+":
                operand +=number;
                break;

            }

        }
        resultField.setText(operand.toString().replace('.',','));
        numberField.setText(" ");
    }
}

