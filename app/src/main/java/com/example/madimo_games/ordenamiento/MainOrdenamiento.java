package com.example.madimo_games.ordenamiento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.madimo_games.R;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class MainOrdenamiento extends AppCompatActivity implements View.OnClickListener{
    //botones de cronometro
    Button start, stop, reset;
    boolean isOn=true;
    TextView crono;
    Thread cronos;
    int mili=0, seg=0, minutos=0;
    Handler h=new Handler();

    //botones de sonido
    MediaPlayer sonidomoneda;
    Button play;
    SoundPool sp;
    int sonidoreproduccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ordenamiento);
        play=(Button)findViewById(R.id.mostrar);
        sp = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        sonidoreproduccion = sp.load(this, R.raw.coin,1);


        sonidomoneda=MediaPlayer.create(this,R.raw.coin);


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

        final TextView texto = (TextView) findViewById(R.id.texto);

        for (final Button btn : listado) {
            int num = (int) (Math.random() * 12) + 1;
            numeros.add(num);
            btn.setText(num + "");
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    sonidomoneda();
                    texto.setText(texto.getText() + " " + btn.getText());
                    btn.setVisibility(View.INVISIBLE);
                }

            });

        }
        Button validar = (Button) findViewById(R.id.btnValidar);

        Button auto = findViewById(R.id.autocompletar);
        auto.setVisibility(View.INVISIBLE);
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(numeros);
                if(numeros.equals(numeros)) {

                    Intent intent = new Intent (v.getContext(), SegundoNivel.class);
                    startActivityForResult(intent, 0);
                    startActivity(intent);
                    finish();
                }else{

                    finish();
                    startActivity(getIntent());

                }

                Intent intent = new Intent (v.getContext(), SegundoNivel.class);
                startActivityForResult(intent, 0);
            }
        });
        TextView numOrdenados;
        numOrdenados = (TextView)findViewById(R.id.numerosordenados);
        Button mostrar=findViewById(R.id.mostrar);
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(numeros);
                for (Object num: numeros){
                    auto.setVisibility(View.VISIBLE);
                    start.setVisibility(View.VISIBLE);
                    stop.setVisibility(View.VISIBLE);
                    reset.setVisibility(View.VISIBLE);
                    numOrdenados.setText(numOrdenados.getText().toString()+(int)num+" - ");
                }

            }
        });



        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarContenido(texto, numeros);}

        });

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        crono = (TextView) findViewById(R.id.crono);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);

        start.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);


        cronos = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isOn) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mili++;
                        if (mili == 999) {
                            seg++;
                            mili = 0;
                        }
                        if (seg == 59) {
                            minutos++;
                            seg = 0;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                String m = "", s = "", mi = "";
                                if (mili < 10) {
                                    m = "00" + mili;
                                } else if (mili < 100) {
                                    m = "0" + mili;
                                } else {
                                    m = "" + mili;
                                }
                                if (seg < 10) {
                                    s = "0" + seg;
                                } else {
                                    s = "" + seg;
                                }
                                if (minutos < 10) {
                                    mi = "0" + minutos;
                                } else {
                                    mi = "" + minutos;
                                }
                                crono.setText(mi + ":" + s+":" + m);
                            }
                        });
                    }
                }
            }

        });
        cronos.start();
    }
    public void sonidomoneda(){
        if (sonidomoneda==null){
            sonidomoneda=MediaPlayer.create(this,R.raw.coin);
        }
        sonidomoneda.start();

    }
    public void coin(View view){
        sp.play(sonidoreproduccion,1,1,1,0,0);

    }

    public void audiocoin(View view){
        MediaPlayer mp = MediaPlayer.create(this,R.raw.coin);
        mp.start();
    }

    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.start:
                isOn = true;
                break;
            case R.id.stop:
                isOn = false;
                break;
            case R.id.reset:
                isOn = false;
                mili = 0;
                seg = 0;
                minutos = 0;
                crono.setText("00:00:000");
                break;
        }

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
            Intent in = new Intent(this,SegundoNivel.class);
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

   /* public class Cronometro extends AppCompatActivity implements View.OnClickListener{
    Button start, stop, reset;
    boolean isOn=false;
    TextView crono;
    Thread cronos;
    int mili=0, seg=0, minutos=0;
    Handler h=new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        reset = (Button) findViewById(R.id.reset);
        crono = (TextView) findViewById(R.id.crono);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        reset.setOnClickListener(this);

        cronos = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isOn) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mili++;
                        if (mili == 999) {
                            seg++;
                            mili = 0;
                        }
                        if (seg == 59) {
                            minutos++;
                            seg = 0;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                String m = "", s = "", mi = "";
                                if (mili < 10) {
                                    m = "00" + mili;
                                } else if (mili < 100) {
                                    m = "0" + mili;
                                } else {
                                    m = "" + mili;
                                }
                                if (seg < 10) {
                                    s = "0" + seg;
                                } else {
                                    s = "" + seg;
                                }
                                if (minutos < 10) {
                                    mi = "0" + minutos;
                                } else {
                                    mi = "" + minutos;
                                }
                                crono.setText(mi + ":" + s+":" + m);
                            }
                        });
                    }
                }
            }

        });
        cronos.start();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                isOn = true;
                break;
            case R.id.stop:
                isOn = false;
                break;
            case R.id.reset:
                isOn = false;
                mili = 0;
                seg = 0;
                minutos = 0;
                crono.setText("00:00:000");
                break;
        }

    }


    }*/


}