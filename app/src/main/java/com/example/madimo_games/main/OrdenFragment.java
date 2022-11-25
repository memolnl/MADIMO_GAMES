package com.example.madimo_games.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madimo_games.R;
import com.example.madimo_games.ordenamiento.MainOrdenamiento;

public class OrdenFragment extends Fragment {

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            View v = inflater.inflate(R.layout.activity_orden_fragment, container, false);

            Button btnJugar = (Button) v.findViewById(R.id.btnJugar);
            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(getActivity(), MainOrdenamiento.class);
                    //in.putExtra("algo", "Cosas");
                    startActivity(in);
                }
            });

            return v;
        }

    }

