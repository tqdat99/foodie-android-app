package com.example.foodie.ui.combo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.foodie.R;
//import com.example.myapplication.DishAdapter;


public class ComboFragment extends ListFragment {


    private ComboViewModel comboViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_combo, container, false);



        return root;
    }

}