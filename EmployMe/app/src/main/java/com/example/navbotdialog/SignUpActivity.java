package com.example.navbotdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.Html;


public class SignUpActivity extends AppCompatActivity {
    TextView loginRedirectText;
    boolean passwordVisible = false;
    boolean passwordVisibleConfirmar = false;
    EditText editTextPassword, editTextPasswordConfirmar;
    ImageView buttonPassword, buttonPasswordConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextPassword = findViewById(R.id.passwordET);
        buttonPassword = findViewById(R.id.passwordIcon);
        editTextPasswordConfirmar = findViewById(R.id.conpassword);
        buttonPasswordConfirmar = findViewById(R.id.conpasswordIcon);

        buttonPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (passwordVisible) {
                    // Si la contraseña es visible, cambia el tipo de entrada a 'textPassword'
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    buttonPassword.setImageResource(R.drawable.ico_visibility_off);
                    passwordVisible = false;
                } else {
                    // Si la contraseña está oculta, cambia el tipo de entrada a 'text'
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    buttonPassword.setImageResource(R.drawable.ico_visibility);
                    passwordVisible = true;
                }

                // Mueve el cursor al final del texto de la contraseña
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
        });

        buttonPasswordConfirmar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (passwordVisibleConfirmar) {
                    // Si la contraseña es visible, cambia el tipo de entrada a 'textPassword'
                    editTextPasswordConfirmar.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    buttonPasswordConfirmar.setImageResource(R.drawable.ico_visibility_off);
                    passwordVisibleConfirmar = false;
                } else {
                    // Si la contraseña está oculta, cambia el tipo de entrada a 'text'
                    editTextPasswordConfirmar.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    buttonPasswordConfirmar.setImageResource(R.drawable.ico_visibility);
                    passwordVisibleConfirmar = true;
                }

                // Mueve el cursor al final del texto de la contraseña
                editTextPasswordConfirmar.setSelection(editTextPasswordConfirmar.getText().length());
            }
        });

        TextView signUpRedirectText = findViewById(R.id.login_RedirectText);
        String text = "¿Ya tienes una cuenta? <b>Inicia sesión</b>";
        signUpRedirectText.setText(Html.fromHtml(text));

        signUpRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Definir las animaciones
                ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(signUpRedirectText, "scaleX", 1f, 0.5f, 1f);
                scaleXAnimation.setDuration(500);

                ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(signUpRedirectText, "scaleY", 1f, 0.5f, 1f);
                scaleYAnimation.setDuration(500);

                // Crear un conjunto de animaciones
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleXAnimation, scaleYAnimation);

                // Iniciar las animaciones
                animatorSet.start();


                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });



    }


}