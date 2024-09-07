package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(v->{
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            }else{

                if(username.equals("admin") && password.equals("admin")){
                    Intent intent = new Intent(MainActivity.this, AdminHomeActivity.class); // Assuming HomeActivity is the activity after login
                    startActivity(intent);
                }
                else {
                    DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                    boolean result = dbHelper.checkUserByUsername(username, password);
                    if(result){
                        Toast.makeText(MainActivity.this, "Welcome valid user!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ProductsDisplay.class); // Assuming HomeActivity is the activity after login
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Username and password!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}