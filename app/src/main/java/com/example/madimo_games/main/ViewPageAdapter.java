package com.example.madimo_games.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.madimo_games.main.BreakOutFragment;
import com.example.madimo_games.main.GatoFragment;
import com.example.madimo_games.main.OrdenFragment;

public class ViewPageAdapter extends FragmentStateAdapter {

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){

            case 0:
                return new OrdenFragment();
            case 1:
                return new GatoFragment();
            case 2:
                return new BreakOutFragment();
            default:
                return new GatoFragment();
        }

    }

    @Override
    public int getItemCount(){
        return 3;

    }
}
