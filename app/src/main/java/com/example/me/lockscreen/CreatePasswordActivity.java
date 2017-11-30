package com.example.me.lockscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePasswordActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button button;
    private static int userId=0;



    public static int getUserID() {
        return userId;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        button=(Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1= editText1.getText().toString();
                String text2= editText2.getText().toString();
                if(text1.equals("")|| text2.equals("")){
                    // there is no password entered
                    Toast.makeText(CreatePasswordActivity.this, "No PIN entered", Toast.LENGTH_SHORT).show();
                } else{
                    if(text1.equals(text2)){
                        // save the password
                        ++userId;
                        SharedPreferences settings= getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor= settings.edit();
                        editor.putString("password", text1);
                        editor.putString("UserID", ""+ userId);
                        editor.apply();

                        Logger.writeFile("Creating Password");
                        Logger.writeFile("UserID: " + userId);
                        Logger.writeFile("PIN created is : " + text1);
                        Logger.writeFile("Password for user " + userId + " has been created");
                        Logger.writeFile("------------------");
                        //enter the app
                        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                     }else {
                        //Entered PINs do not match
                        Toast.makeText(CreatePasswordActivity.this, "PINs does not match", Toast.LENGTH_SHORT).show();


                    }



                }
            }
        });


    }
}
