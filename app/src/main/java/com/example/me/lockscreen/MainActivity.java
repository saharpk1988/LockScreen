package com.example.me.lockscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_Enter;
    Button button_Create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_Enter=(Button) findViewById(R.id.button_Enter);
        button_Create=(Button) findViewById(R.id.button_Create);
        button_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Enter PIN
                Intent intent= new Intent(getApplicationContext(), EnterPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Create PIN
                Intent intent= new Intent(getApplicationContext(), CreatePasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
