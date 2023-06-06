package com.example.navbotdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class forgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Button sendButton = findViewById(R.id.send_button);
        EditText emailEditText = findViewById(R.id.send_email);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();

                if (!email.isEmpty() && isValidEmail(email)) {
                    // El campo de correo electrónico no está vacío y tiene una sintaxis válida, navegar a otra ventana
                    Intent intent = new Intent(forgetPassword.this, verifyCode.class);
                    startActivity(intent);
                } else {
                    // El campo de correo electrónico está vacío o tiene una sintaxis inválida, mostrar un mensaje de error
                    Toast.makeText(forgetPassword.this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
}