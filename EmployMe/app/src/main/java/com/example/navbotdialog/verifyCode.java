package com.example.navbotdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class verifyCode extends AppCompatActivity {
    private TextView forgetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        forgetText = findViewById(R.id.forgetText);

        // Obtener el correo electrónico enviado desde el backend
        String email = getIntent().getStringExtra("email");

        // Construir el texto de verificación con el correo electrónico
        String forgetTextC = "Se ha enviado un código de verificación al correo electrónico: <b>" + email + "</b>";

        // Mostrar el texto en el TextView
        forgetText.setText(Html.fromHtml(forgetTextC));
    }
}



