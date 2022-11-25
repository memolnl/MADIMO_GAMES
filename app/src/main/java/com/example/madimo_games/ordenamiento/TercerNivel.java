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

public class TercerNivel extends AppCompatActivity {
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
        setContentView(R.layout.activity_tercer_nivel);



        ArrayList<Button> listado = new ArrayList<Button>();
        final ArrayList numeros = new ArrayList();

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


        final TextView texto = (TextView)findViewById(R.id.texto);

        for (final Button bt:listado) {
            int num = (int) (Math.random() * 12) + 1;
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



        Button auto =(Button)findViewById(R.id.autocompletar);

        auto.setVisibility(View.INVISIBLE);


        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(numeros);
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




        TextView numOrdenados;
        numOrdenados = (TextView)findViewById(R.id.numerosordenados);
        Button mostrar=findViewById(R.id.mostrar);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(numeros,Collections.reverseOrder());
                for (Object num: numeros){
                    auto.setVisibility(View.VISIBLE);
                    numOrdenados.setText(numOrdenados.getText().toString()+(int)num+" - ");
                }

            }
        });


        Button validar2 =(Button)findViewById(R.id.btnValidar);

        validar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarContenido(texto, numeros);}
        });



    }

    public void sonidomoneda(){
        if (sonidomoneda==null){
            sonidomoneda=MediaPlayer.create(this,R.raw.coin);
        }
        sonidomoneda.start();

    }

    public void validarContenido(TextView texto, ArrayList numeros){
        Collections.sort(numeros,Collections.reverseOrder());
        String cadena="";
        for (Object num: numeros){
            cadena+=(int)num+"";
        }
        String cadena2 = texto.getText().toString().replaceAll(" ","");
        String mensaje;
        if(cadena.equals(cadena2)) {
            mensaje = "OK";
            Intent in = new Intent(this,cuartonivel.class);
            Bundle b = new Bundle();
            b.putString("mensaje", mensaje);
            in.putExtras(b);
            startActivity(in);

        }else{

            mensaje="fail";
            finish();
            startActivity(getIntent());




        }

    }
}