package com.example.patrickcordero.tipyourhat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting listeners to Five buttons.
        Button calculateTotal = (Button)findViewById(R.id.calculate_Button);
        calculateTotal.setOnClickListener(this);

        Button addPerson = (Button) findViewById(R.id.add_people_button);
        addPerson.setOnClickListener(this);

        Button subPerson = (Button) findViewById(R.id.sub_people_button);
        subPerson.setOnClickListener(this);

        Button fifteenPercent = (Button)findViewById(R.id.fifteen_button);
        fifteenPercent.setOnClickListener(this);

        Button twentyPercent = (Button)findViewById(R.id.twenty_button);
        twentyPercent.setOnClickListener(this);


        //Set the two EditText fields and five TextView fields to variables
        /*EditText totalInput = (EditText)findViewById(R.id.total_input);
        EditText tipInput = (EditText)findViewById(R.id.tip_input);
        TextView totalDisplay = (TextView)findViewById(R.id.total_display);
        TextView tipDisplay = (TextView)findViewById(R.id.tip_display);
        TextView splitDisplay = (TextView)findViewById(R.id.split_display);
        TextView splitNumber = (TextView)findViewById(R.id.split_number);*/

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calculate_Button: {
                EditText totalInput = (EditText)findViewById(R.id.total_input);
                EditText tipInput = (EditText)findViewById(R.id.tip_input);
                TextView totalDisplay = (TextView)findViewById(R.id.total_display);
                TextView tipDisplay = (TextView)findViewById(R.id.tip_display);
                TextView splitDisplay = (TextView)findViewById(R.id.split_display);

                if(TextUtils.isEmpty(totalInput.getText()))
                {
                    totalInput.setError("Cannot enter 0.");
                }
                else if(TextUtils.isEmpty(tipInput.getText()))
                {
                    tipInput.setError("Cannot enter 0.");
                }
                else
                {
                    float billTotal = Float.parseFloat(totalInput.getText().toString());

                    float tipPercent = Float.parseFloat(tipInput.getText().toString());

                    float grandTotal = calculateTotal(billTotal,tipPercent);
                    float tipTotal = calculateTips(billTotal,tipPercent);
                    float splitTotal = grandTotal;

                    tipDisplay.setText(String.format("%.2f",tipTotal));
                    totalDisplay.setText(String.format("%.2f",grandTotal));
                    splitDisplay.setText(String.format("%.2f",splitTotal));
                    break;

                }

                break;
            }
            case R.id.add_people_button: {
                TextView splitDisplay = (TextView)findViewById(R.id.split_display);
                TextView splitNumber = (TextView)findViewById(R.id.split_number);
                TextView totalDisplay = (TextView)findViewById(R.id.total_display);

                if(TextUtils.isEmpty(totalDisplay.getText()))
                {
                    totalDisplay.setError("Enter amount and tip.");
                }
                else{
                    //String totalDisplayed = totalDisplay.getText().toString();
                    double totalConvert = Double.parseDouble(totalDisplay.getText().toString());

                    //String splitNum = splitNumber.getText().toString();
                    int splitConvert = Integer.parseInt(splitNumber.getText().toString());

                    int addSplit = addPeople(splitConvert);
                    double splitTotal = calculateSplitAdd(totalConvert, addSplit);

                    splitNumber.setText(String.format("%d",addSplit));
                    splitDisplay.setText(String.format("%.2f",splitTotal));
                    break;

                }
                break;


            }
            case R.id.sub_people_button: {

                TextView splitDisplay = (TextView)findViewById(R.id.split_display);
                TextView splitNumber = (TextView)findViewById(R.id.split_number);
                TextView totalDisplay = (TextView)findViewById(R.id.total_display);

                if(TextUtils.isEmpty(totalDisplay.getText()))
                {
                    totalDisplay.setError("Enter amount and tip.");
                }
                else
                {
                    //String totalDisplayed = totalDisplay.getText().toString();
                    double totalConvert = Double.parseDouble(totalDisplay.getText().toString());

                    //String splitNum = splitNumber.getText().toString();
                    int splitConvert = Integer.parseInt(splitNumber.getText().toString());

                    int subSplit = subPeople(splitConvert);
                    double splitTotal = calculateSplitSub(totalConvert, subSplit);

                    splitNumber.setText(String.format("%d",subSplit));
                    splitDisplay.setText(String.format("%.2f",splitTotal));

                    break;
                }

                break;
            }
            case R.id.fifteen_button: {
                EditText totalInput = (EditText)findViewById(R.id.total_input);
                EditText tipInput = (EditText)findViewById(R.id.tip_input);
                TextView totalDisplay = (TextView)findViewById(R.id.total_display);
                TextView tipDisplay = (TextView)findViewById(R.id.tip_display);
                TextView splitDisplay = (TextView)findViewById(R.id.split_display);

                if(TextUtils.isEmpty(totalInput.getText()))
                {
                    totalInput.setError("Cannot enter 0.");
                }
                else{
                    float billTotal = Float.parseFloat(totalInput.getText().toString());
                    float tipPercent = 15;

                    float grandTotal = calculateTotal(billTotal,tipPercent);
                    float tipTotal = calculateTips(billTotal,tipPercent);
                    float splitTotal = grandTotal;

                    tipInput.setText("15", TextView.BufferType.EDITABLE);
                    tipDisplay.setText(String.format("%.2f",tipTotal));
                    totalDisplay.setText(String.format("%.2f",grandTotal));
                    splitDisplay.setText(String.format("%.2f",splitTotal));
                    break;

                }
                break;

            }
            case R.id.twenty_button: {
                EditText totalInput = (EditText)findViewById(R.id.total_input);
                EditText tipInput = (EditText)findViewById(R.id.tip_input);
                TextView totalDisplay = (TextView)findViewById(R.id.total_display);
                TextView tipDisplay = (TextView)findViewById(R.id.tip_display);
                TextView splitDisplay = (TextView)findViewById(R.id.split_display);

                if(TextUtils.isEmpty(totalInput.getText()))
                {
                    totalInput.setError("Cannot enter 0.");
                }
                else {
                    float billTotal = Float.parseFloat(totalInput.getText().toString());
                    float tipPercent = 20;

                    float grandTotal = calculateTotal(billTotal, tipPercent);
                    float tipTotal = calculateTips(billTotal, tipPercent);
                    float splitTotal = grandTotal;

                    tipInput.setText("20", TextView.BufferType.EDITABLE);
                    tipDisplay.setText(String.format("%.2f", tipTotal));
                    totalDisplay.setText(String.format("%.2f", grandTotal));
                    splitDisplay.setText(String.format("%.2f",splitTotal));
                    break;
                }
                break;
            }
        }
    }

    public float calculateTotal(float billTotal, float tipPercent)
    {
        float grandTotal;
        float tipCost;

        tipCost = billTotal *(tipPercent/100);
        grandTotal = billTotal + tipCost;
        return grandTotal;
    }

    public float calculateTips(float billTotal, float tipPercent)
    {
        float tipCost;
        tipCost = billTotal * (tipPercent/100);
        return tipCost;
    }

    public double calculateSplitAdd(double totalConvert, int addSplit)
    {
        double splitTotal = totalConvert /addSplit;
        return splitTotal;

    }
    public double calculateSplitSub(double totalConvert,int subSplit)
    {
        double splitTotal = totalConvert /subSplit;
        return splitTotal;
    }

    public int addPeople(int splitConvert)
    {
        int addPerson = splitConvert + 1;
        return addPerson;
    }

    public int subPeople(int splitConvert)
    {
        if(splitConvert == 1)
        {
            return splitConvert;
        }
        else
            return splitConvert - 1;
    }


}
