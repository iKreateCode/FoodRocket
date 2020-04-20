package com.example.foodrocket;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    EditText userName, userEmail, firstPassword, confirmPassword;
    Button btnRegister;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        userEmail = (EditText) findViewById(R.id.emailField);
        firstPassword =(EditText) findViewById(R.id.passwordField);
        confirmPassword=(EditText) findViewById(R.id.confirmPasswordField);
        btnRegister=(Button) findViewById(R.id.registerButton);
        checkBox = (CheckBox) findViewById(R.id.showPassword);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    firstPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    firstPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void  registerUser(){
        final String email =userEmail.getText().toString().trim();
        final String password = firstPassword.getText().toString().trim();
        final String confirmpassword =confirmPassword.getText().toString().trim();


        // Validation
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            userEmail.setError("Invalid Email address");
        } else if (TextUtils.isEmpty(email)){
            userEmail.setError("Please enter the email");
        } else if(TextUtils.isEmpty(password)){
            firstPassword.setError("Please enter the password");
        } else if(!password.equals(confirmpassword)){
            confirmPassword.setError("Passwords do not match");
        } else{
            // Databse insertion goes here
        }
    }

    private void Cleartxt(){
        userName.setText("");
        userEmail.setText("");
        firstPassword.setText("");
        confirmPassword.setText("");
    }
}

