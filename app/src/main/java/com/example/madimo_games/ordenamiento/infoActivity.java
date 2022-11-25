package com.example.madimo_games.ordenamiento;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madimo_games.R;

public class infoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle recibido = this.getIntent().getExtras();

        final String mensaje = recibido.getString("mensaje");

        ImageButton im = (ImageButton)findViewById(R.id.btnImagen);
        if(mensaje.equals("OK")) {
            im.setImageResource(R.drawable.feliz);
        }else{
            im.setImageResource(R.drawable.triste);
        }
    }
}