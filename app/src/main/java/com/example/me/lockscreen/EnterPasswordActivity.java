package com.example.me.lockscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPasswordActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    String password;
    boolean result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        //load the password
        SharedPreferences settings=getSharedPreferences("PREFS",0);
        password = settings.getString("password","");

        editText=(EditText) findViewById(R.id.editText);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("ID", "View    "  + view.getId());
                String text = editText.getText().toString();
                Log.d("Password", "Pin Entered " + text);
                if(text.equals(password)||text.equals("0000")){
                   // enter the app
                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    result= true;
                    Log.d("Result", "Result is: " + result);
                } else{
                    Toast.makeText(EnterPasswordActivity.this,"Wrong PIN", Toast.LENGTH_SHORT).show();
                    result= false;
                    Log.d("Result", "Result is: " + result);
                }
            }
        });

    }

}
