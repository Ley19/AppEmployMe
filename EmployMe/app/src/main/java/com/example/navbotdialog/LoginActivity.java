package com.example.navbotdialog;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    TextView forgetPassword;
    LinearLayout enviar_a_Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        forgetPassword = findViewById(R.id.forgetPassword);
        enviar_a_Registro = findViewById(R.id.enviar_a_Registro);

        //TextView singUpRedirectedText = findViewById(R.id.forgetPassword);
        String text = "Olvidaste tu contraseña";
        forgetPassword.setText(Html.fromHtml(text));

        enviar_a_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, forgetPassword.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                //Validar campos llenos
                boolean isFormValid = true;
                if(email.isEmpty()){
                    emailEditText.setError("Campo obligatorio");
                    isFormValid = false;
                }
                if(password.isEmpty()){
                    passwordEditText.setError("Campo obligatorio");
                    isFormValid = false;
                }
                if (isFormValid){
                    iniciarSesion(email, password);
                }else {
                    Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void iniciarSesion(String email, String password) {
        String url = APIUtils.getFullUrl("login");

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
                                }
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
                    }
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
