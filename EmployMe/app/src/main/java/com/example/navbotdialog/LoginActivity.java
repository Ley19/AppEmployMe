package com.example.navbotdialog;
import static android.content.ContentValues.TAG;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    boolean passwordVisible = false;
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    TextView forgetPassword;
    LinearLayout enviar_a_Registro;
    EditText editTextPassword;
    ImageView buttonPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        forgetPassword = findViewById(R.id.forgetPassword);
        enviar_a_Registro = findViewById(R.id.enviar_a_Registro);

        editTextPassword = findViewById(R.id.login_password);
        buttonPassword = findViewById(R.id.passwordIcon);

        TextView singUpRedirectedText = findViewById(R.id.forgetPassword);
        String text = "Olvidaste tu contraseña";
        singUpRedirectedText.setText(Html.fromHtml(text));

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

        enviar_a_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Definir las animaciones
                ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat( enviar_a_Registro, "scaleX", 1f, 0.5f, 1f);
                scaleXAnimation.setDuration(500);

                ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat( enviar_a_Registro, "scaleY", 1f, 0.5f, 1f);
                scaleYAnimation.setDuration(500);

                // Crear un conjunto de animaciones
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleXAnimation, scaleYAnimation);

                // Iniciar las animaciones
                animatorSet.start();

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Definir las animaciones
                ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(forgetPassword, "scaleX", 1f, 0.5f, 1f);
                scaleXAnimation.setDuration(500);

                ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(forgetPassword, "scaleY", 1f, 0.5f, 1f);
                scaleYAnimation.setDuration(500);

                // Crear un conjunto de animaciones
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleXAnimation, scaleYAnimation);

                // Iniciar las animaciones
                animatorSet.start();

                Intent intent = new Intent(LoginActivity.this, forgetPassword.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Definir las animaciones
                ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(loginButton, "scaleX", 1f, 0.5f, 1f);
                scaleXAnimation.setDuration(500);

                ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(loginButton, "scaleY", 1f, 0.5f, 1f);
                scaleYAnimation.setDuration(500);

                // Crear un conjunto de animaciones
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(scaleXAnimation, scaleYAnimation);

                // Iniciar las animaciones
                animatorSet.start();

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Faltan ingresar campos", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Falta ingresar el correo electrónico", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Falta ingresar la contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    // Realizar la acción deseada cuando los campos no estén vacíos
                    iniciarSesion(email, password);
                }
            }
        });

    }

    private void iniciarSesion(String email, String password) {

        String url = "http://192.168.0.111:3000/login";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // La solicitud fue exitosa y se recibió una respuesta del servidor
                        // Procesar la respuesta del servidor
                        try {
                            String message = response.getString("message");
                            JSONObject userObject = response.optJSONObject("user");

                            if (userObject != null) {
                                int userId = userObject.optInt("id", 0);
                                String userName = userObject.optString("name", "");
                                String userEmail = userObject.optString("email", "");

                                Log.d("LoginActivity", message);
                                Log.d("LoginActivity", "User ID: " + userId);
                                Log.d("LoginActivity", "User Name: " + userName);
                                Log.d("LoginActivity", "User Email: " + userEmail);

                                if (message.equals("Inicio de sesión exitoso")) {
                                    // La conexión fue exitosa y el usuario está autenticado
                                    // Redireccionar a MainActivity
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish(); // Opcionalmente, finalizar la actividad actual
                                } else {
                                    Toast.makeText(LoginActivity.this, "Contraseña o usuario incorrecto", Toast.LENGTH_SHORT).show();
                                    // La conexión fue exitosa, pero hubo un error en la autenticación
                                    // Puedes mostrar un mensaje de error o realizar otras acciones
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                                // El campo "user" es nulo en el objeto JSON
                                // Manejar este caso según tus necesidades
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Manejar la excepción JSONException aquí
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Ocurrió un error en la solicitud
                        // Registrar el error en los registros de la aplicación
                        Log.e("LoginActivity", "Error en la solicitud HTTP: " + error.toString());
                        Toast.makeText(LoginActivity.this, "Lo sentimos. Problemas con el servidor", Toast.LENGTH_SHORT).show();

                    }
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

}
