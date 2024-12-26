package com.example.fishtrack.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fishtrack.R;
import com.example.fishtrack.activities.home.HomePageActivity;
import com.example.fishtrack.httpClient.RetrofitClient;
import com.example.fishtrack.services.login.LoginDTO.LoginResponseDTO;
import com.example.fishtrack.services.login.LoginModal;
import com.example.fishtrack.services.login.LoginService;
import com.jakewharton.threetenabp.AndroidThreeTen;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText emailInput;
    private EditText passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidThreeTen.init(this);
        LoginService user = RetrofitClient.retrofitSingleton().create(LoginService.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailInput = findViewById(R.id.email_input);
                passwordInput = findViewById(R.id.password_input);

                LoginModal loginUser = new LoginModal(emailInput.getText().toString(),passwordInput.getText().toString());
                user.user(loginUser).enqueue(new retrofit2.Callback<LoginResponseDTO>() {
                    @Override
                    public void onResponse(Call<LoginResponseDTO> call, retrofit2.Response<LoginResponseDTO> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            LoginResponseDTO result = response.body();
                            if(result.isExist) {
                                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Falha: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
