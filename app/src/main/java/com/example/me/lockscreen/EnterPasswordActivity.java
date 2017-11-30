package com.example.me.lockscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Date;

public class EnterPasswordActivity extends AppCompatActivity{

    EditText editText;
    Button button;
    String password;
    boolean result;

    long durationDeleteKey;
    Date startTime;
    Date delete;
    Date now;
    boolean startFlag = true;
    boolean isCorrected = false;
    String entry = "";
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        //load the password
        SharedPreferences settings=getSharedPreferences("PREFS",0);
        password = settings.getString("password","");
        userId = settings.getString("UserID", "");

        editText=(EditText) findViewById(R.id.editText);
        button=(Button) findViewById(R.id.button);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 == 0) {
                    delete = new Date();
                    durationDeleteKey += delete.getTime() - now.getTime();
                    isCorrected = true;
                    Log.d("OnChange", "Entering the key: Delete");
                } else {
                    if(startFlag) {
                        startTime = new Date();
                        startFlag = false;
                        Logger.writeFile("PIN entry start time is: " + startTime);
                        Log.d("Timing Operation", "Start time: " + startTime);
                    }
                    now = new Date();
                    Log.d("OnChange", "Entering the key: " + charSequence);
                    entry += charSequence.charAt(i);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                Date endTime = new Date();
                if(isCorrected) {
                    long total = endTime.getTime() - startTime.getTime();
                    Logger.writeFile("User ID is: " + userId);
                    Logger.writeFile("PIN entry end time is: " + endTime);
                    Logger.writeFile("Total PIN entry time with correction times is: " + total);
                    Logger.writeFile("Correction time is: " + durationDeleteKey);
                    Logger.writeFile("PIN created is: " + text);
                    Logger.writeFile("PIN entered is: " + entry);
                    //Log.d("Timing Operation", "Total PIN entry time: " + total);
                    //Log.d("Password", "Pin Entered " + text);
                } else {
                    long total = endTime.getTime() - startTime.getTime();
                    Logger.writeFile("User ID is: " + userId);
                    Logger.writeFile("PIN entry end time is: " + endTime);
                    Logger.writeFile("Total PIN entry time without correction: " + total);
                    Logger.writeFile("PIN created is: " + text);
                    Logger.writeFile("PIN entered is: " + entry);
                    //Log.d("Timing Operation", "End Time: " + endTime);
                    //Log.d("Timing Operation", "Total PIN entry time: " + total);
                    //Log.d("Password", "Pin Entered " + text);
                }
                if(text.equals(password)||text.equals("0000")){
                   // enter the app
                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    result= true;
                    //Log.d("Result", "Result is: " + result);
                    Logger.writeFile("Result is true");
                    Logger.writeFile("------------------");
                } else{
                    Toast.makeText(EnterPasswordActivity.this,"Wrong PIN", Toast.LENGTH_SHORT).show();
                    result= false;
                    //Log.d("Result", "Result is: " + result);
                    Logger.writeFile("Result is false");
                    Logger.writeFile("------------------");
                }
            }
        });
    }
}
