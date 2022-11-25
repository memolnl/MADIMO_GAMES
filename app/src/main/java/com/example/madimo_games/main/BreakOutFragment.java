package com.example.madimo_games.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.madimo_games.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.madimo_games.breakout.MainBreakOut;

public class BreakOutFragment extends Fragment{

        private FirebaseAuth mAuth;
        private DatabaseReference mDatabase;
        private TextView textPuntaje;

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            View v = inflater.inflate(R.layout.activity_break_out_fragment, container, false);

            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();
            textPuntaje = v.findViewById(R.id.nombrePuntaje);

            getUserInfo();

            Button btnJugar = (Button) v.findViewById(R.id.btnJugar);
            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(getActivity(), MainBreakOut.class);
                    //in.putExtra("algo", "Cosas");
                    startActivity(in);
                }
            });

            return v;
        }



        private void getUserInfo() {
            String id = mAuth.getCurrentUser().getUid();
            mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String name = snapshot.child("name").getValue().toString();

                        textPuntaje.setText(name);


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
