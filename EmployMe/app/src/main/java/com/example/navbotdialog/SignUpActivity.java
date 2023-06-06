package com.example.navbotdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.text.Html;


public class SignUpActivity extends AppCompatActivity {
    TextView loginRedirectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        TextView signUpRedirectText = findViewById(R.id.login_RedirectText);
        String text = "¿Ya tienes una cuenta? <b>Inicia sesión</b>";
        signUpRedirectText.setText(Html.fromHtml(text));
        signUpRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

    }


}