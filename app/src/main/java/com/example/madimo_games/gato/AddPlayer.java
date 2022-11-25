package com.example.madimo_games.gato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madimo_games.R;

public class AddPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        final EditText jugador1 = findViewById(R.id.nombreJugador1);
        final EditText jugador2 = findViewById(R.id.nombreJugador2);
        final Button btnComenzar = findViewById(R.id.btnComenzar);
        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getJugador1 = jugador1.getText().toString();
                final String getJugador2 = jugador2.getText().toString();
                if(getJugador1.isEmpty() || getJugador2.isEmpty()){
                    Toast.makeText(AddPlayer.this, "Ingrese el jugador 1", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(AddPlayer.this, MainGato.class);
                    intent.putExtra("jugador1", getJugador1);
                    intent.putExtra("jugador2", getJugador2);
                    startActivity(intent);
                }

            }
        });
    }
}