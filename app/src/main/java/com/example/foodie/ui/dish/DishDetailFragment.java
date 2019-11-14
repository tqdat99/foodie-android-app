package com.example.foodie.ui.dish;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodie.DatabaseHelper;
import com.example.foodie.Dish;
import com.example.foodie.DishSender;
import com.example.foodie.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DishDetailFragment extends Fragment {

    ImageView favorite;
    View root;
    DatabaseHelper databaseHelper;
    ImageView img;
    TextView name, type, ingredients, cook;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dish_details, container, false);
        Mapping();

        //Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
        return root;
    }

    public void Mapping(){
        favorite = (ImageView) root.findViewById(R.id.favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite.setImageResource(R.drawable.favorite_set);
            }
        });

        img = (ImageView) root.findViewById(R.id.iv_dish);
        name = (TextView) root.findViewById(R.id.tv_title_name);
        type = (TextView) root.findViewById(R.id.tv_type);
        ingredients = (TextView) root.findViewById(R.id.tv_ingredient_list);
        cook = (TextView) root.findViewById(R.id.tv_cook);
    }

    public void setInfo(Dish dish){

        //name.setText("đạt");
        databaseHelper = new DatabaseHelper(getActivity());
        String id = dish.getId();
        ArrayList<String> dishInfo = new ArrayList<>();
        dishInfo = databaseHelper.getDishInfo(id);

        img.setImageResource(R.drawable.bo);
        name.setText(dish.getName());
        type.setText(dish.getType());
        ingredients.setText(dishInfo.get(0));
        cook.setText(dishInfo.get(1));
    }


}
