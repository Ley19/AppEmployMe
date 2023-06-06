package com.example.navbotdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class verifyCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        TextView forgetText = findViewById(R.id.forgetText);
        String forgetTextC = "Se ha enviado un código de verificación al correo electronico: <b> correo@correo.com</b>";
        forgetText.setText(Html.fromHtml(forgetTextC));
    }
}