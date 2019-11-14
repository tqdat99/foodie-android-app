package com.example.foodie.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodie.R;

public class StoreFragment extends Fragment {

    private StoreViewModel storeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storeViewModel =
                ViewModelProviders.of(this).get(StoreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_store, container, false);

        return root;
    }
}