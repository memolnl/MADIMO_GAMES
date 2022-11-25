package com.example.madimo_games.ordenamiento;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madimo_games.R;

import java.util.ArrayList;
import java.util.Collections;

public class cuartonivel extends AppCompatActivity {
    Button start, stop, reset;
    boolean isOn=true;
    TextView crono;
    Thread cronos;
    int mili=0, seg=0, minutos=0;
    Handler h=new Handler();
    MediaPlayer sonidomoneda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuartonivel);

        try{
            Bundle recibido = this.getIntent().getExtras();

            final String mensaje = recibido.getString("mensaje");
        }
        catch (Exception e)
        {}

        ArrayList<Button> listado = new ArrayList<Button>();

        listado.add((Button) findViewById(R.id.btn1));
        listado.add((Button) findViewById(R.id.btn2));
        listado.add((Button) findViewById(R.id.btn3));
        listado.add((Button) findViewById(R.id.btn4));
        listado.add((Button) findViewById(R.id.btn5));
        listado.add((Button) findViewById(R.id.btn6));
        listado.add((Button) findViewById(R.id.btn7));
        listado.add((Button) findViewById(R.id.btn8));
        listado.add((Button) findViewById(R.id.btn9));
        listado.add((Button) findViewById(R.id.btn10));
        listado.add((Button) findViewById(R.id.btn11));
        listado.add((Button) findViewById(R.id.btn12));
        listado.add((Button) findViewById(R.id.btn13));
        listado.add((Button) findViewById(R.id.btn14));
        listado.add((Button) findViewById(R.id.btn15));
        listado.add((Button) findViewById(R.id.btn16));
        listado.add((Button) findViewById(R.id.btn17));
        listado.add((Button) findViewById(R.id.btn18));
        listado.add((Button) findViewById(R.id.btn19));
        listado.add((Button) findViewById(R.id.btn20));
        listado.add((Button) findViewById(R.id.btn21));
        listado.add((Button) findViewById(R.id.btn22));
        listado.add((Button) findViewById(R.id.btn23));
        listado.add((Button) findViewById(R.id.btn24));

        final TextView texto = (TextView)findViewById(R.id.texto);


        final ArrayList numeros = new ArrayList();

        for (final Button bt:listado) {
            int num = (int) (Math.random() * 24) + 1;
            numeros.add(num);
            bt.setText(num + "");
            bt.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view) {
                    sonidomoneda();
                    texto.setText(texto.getText() + " " + bt.getText());
                    bt.setVisibility(View.INVISIBLE);
                }
            });
        }

        Button validar2 =(Button)findViewById(R.id.btnValidar);

        validar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                validarContenido(texto, numeros);}
        });
        Button auto =(Button)findViewById(R.id.autocompletar);

        auto.setVisibility(View.INVISIBLE);


        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(numeros,Collections.reverseOrder());
                if(numeros.equals(numeros)) {

                    Intent intent = new Intent (v.getContext(),cuartonivel.class);
                    startActivityForResult(intent, 0);
                    startActivity(intent);
                    finish();
                }else{

                    finish();
                    startActivity(getIntent());

                }


            }
        });


    }
    public void sonidomoneda(){
        if (sonidomoneda==null){
            sonidomoneda=MediaPlayer.create(this,R.raw.coin);
        }
        sonidomoneda.start();

    }
    private void validarContenido(TextView texto, ArrayList numeros){
        Collections.sort(numeros);
        String cadena="";
        for (Object num: numeros){
            cadena+=(int)num+"";
        }
        String cadena2 = texto.getText().toString().replaceAll(" ","");
        String mensaje;
        if(cadena.equals(cadena2)) {
            mensaje = "OK";
            Intent in = new Intent(this,infoActivity.class);
            Bundle b = new Bundle();
            b.putString("mensaje", mensaje);
            in.putExtras(b);
            startActivity(in);
        }else{

            Intent in = new Intent(this,infoActivity.class);
            mensaje="fail";
            Bundle b = new Bundle();
            b.putString("mensaje",mensaje);
            in.putExtras(b);
            startActivity(in);




        }

    }
}