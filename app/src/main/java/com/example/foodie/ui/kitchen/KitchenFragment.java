package com.example.foodie.ui.kitchen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodie.R;

public class KitchenFragment extends Fragment {

    private KitchenViewModel kitchenViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kitchenViewModel =
                ViewModelProviders.of(this).get(KitchenViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kitchen, container, false);

        return root;
    }
}

