package com.example.madimo_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.madimo_games.breakout.MainBreakOut;
import com.example.madimo_games.gato.MainGato;
import com.example.madimo_games.main.AltosPuntajes;
import com.example.madimo_games.main.LeaderBoardScreen;
import com.example.madimo_games.main.LoginScreen;
import com.example.madimo_games.main.MainScreen;
import com.example.madimo_games.main.ProfileScreen;
import com.example.madimo_games.main.RegisterScreen;
import com.example.madimo_games.ordenamiento.MainOrdenamiento;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent in = new Intent(this, MainScreen.class);
        startActivity(in);
    }
}