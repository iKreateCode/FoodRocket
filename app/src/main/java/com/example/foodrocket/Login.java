package com.example.foodrocket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView register;
    private Button btnLogin, btnGuest;
    private CheckBox checkBox;

    final String SHARED_PREFS = "sharedPrefs";
    final String USER_TOKEN = "user_token";

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
            } else{
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
            logIn(email.getText().toString(), password.getText().toString());
            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Navigation.class);
                startActivity(intent);
            }
        });
    }

    private void logIn(final String email, final String password) {
        ApiLoginRequest login_api = new ApiLoginRequest();
        try {
            String response = login_api.execute(email, password).get();
            JsonParser parser = new JsonParser();
            JsonObject json_response = parser.parse(response).getAsJsonObject();

            if (json_response.has("success")) { // Valid credentials :)
                String token = json_response.getAsJsonObject("success").get("token").toString();
                token = token.substring(1, token.length() - 1);

                SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_TOKEN, token);
                editor.apply();

                Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), Navigation.class);
                startActivity(intent);
            } else if (json_response.has("error")) { // Invalid credentials
                String error = json_response.get("error").toString();
                Toast.makeText(Login.this, error,Toast.LENGTH_LONG).show();
            } else { // Some other error :(
                Toast.makeText(Login.this, response,Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ApiLoginRequest extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            HashMap<String,String> credentials = new HashMap<>();
            credentials.put("email",params[0]);
            credentials.put("password",params[1]);

            GetRequestHandler ruc = new GetRequestHandler();

            String response = ruc.sendPostRequest("https://foodrocket.herokuapp.com/api/v1/user/login", credentials);
            return response;
        }
    }
}

