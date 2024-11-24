package com.example.fishtrack;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Vinculando o layout XML

        //BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configuração do BottomNavigationView
       // bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Lógica para alterar fragments
        //    return true;
     //   });
    }
}
