package com.example.foodrocket;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView register;
    private Button btnLogin, btnGuest;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        email = (EditText) findViewById(R.id.emailField);
        password = (EditText) findViewById(R.id.confirmPasswordField);
        register = (TextView) findViewById(R.id.registerTextview);
        checkBox = (CheckBox) findViewById(R.id.showPassword);
        btnLogin = (Button) findViewById(R.id.loginButton);
        btnGuest = (Button) findViewById(R.id.guestButton);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Navigation.class);
                startActivity(intent);
                //logIn(email.getText().toString(), password.getText().toString());
            }
        });

//        btnGuest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Buy.class);
//                intent.putExtra("guestUsername", "guest");
//                startActivity(intent);
//            }
//        });
    }

    private void logIn(final String email,final String password) {
        // User login
        if (!email.isEmpty()) {
            Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Login.this,"Email cannot be left empty",Toast.LENGTH_LONG).show();
        }

    }
}

