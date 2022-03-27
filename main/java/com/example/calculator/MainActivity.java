package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int count;
    int secondCount;

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("count", count);
        intent.putExtra("secondCount", secondCount);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText firstTermInput = (EditText) findViewById(R.id.editTextNumber);
        EditText secondTermInput = (EditText) findViewById(R.id.editTextNumber2);

        firstTermInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    firstTermInput.setText("");
                }
            }
        });
        secondTermInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    secondTermInput.setText("");
                }
            }
        });
    }
    public void onClick(View view) {
        TextView firstTerm = findViewById(R.id.editTextNumber);
        TextView secondTerm = findViewById(R.id.editTextNumber2);
        try {
            count = Integer.parseInt(String.valueOf(firstTerm.getText()));
            secondCount = Integer.parseInt(String.valueOf(secondTerm.getText()));
            openActivity2();
        } catch (Exception ex) {
            AlertDialog.Builder message = new AlertDialog.Builder(MainActivity.this);
            message.setTitle("Ошибка");
            message.setMessage("Неверные входные данные, введите еще раз");
            message.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            message.show();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("nums", new int[]{count, secondCount});
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("nums")) {
            int[] nums = savedInstanceState.getIntArray("nums");
            count = nums[0];
            secondCount = nums[1];
            if (!(count == 0 && secondCount == 0)) {
                resetUI();
            }
        }
    }
    protected void resetUI() {
        TextView countScore = findViewById(R.id.editTextNumber);
        countScore.setText(String.valueOf(count));
        TextView secondScore = findViewById(R.id.editTextNumber2);
        secondScore.setText(String.valueOf(secondCount));
    }
}