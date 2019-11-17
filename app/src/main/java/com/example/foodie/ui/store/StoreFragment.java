package com.example.foodie.ui.store;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodie.DatabaseHelper;
import com.example.foodie.Dish;
import com.example.foodie.DishAdapter;
import com.example.foodie.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class StoreFragment extends ListFragment {

    View root;
    ArrayList<Dish> dishArrayList;
    DishAdapter adapter;

    //Biến đọc db
    private static final String DATABASE_NAME = "DBMonAn.db";
    private static final int DATABASE_VERSION = 1;
    private DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_store, container, false);

        dishArrayList = new ArrayList<>();

        //Chỗ này đọc file db trong asset r copy vô database (nghĩa là copy cái file db trong asset rồi tạo 1 cái y chang vô folder của ứng dụng)
        databaseHelper = new DatabaseHelper(getActivity());
        File database = getActivity().getApplication().getDatabasePath(DatabaseHelper.DBNAME);
        if (database.exists() == false){
            databaseHelper.getReadableDatabase();
            if (copyDatabase(getActivity())){
                Toast.makeText(getActivity(), "Copy success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        }

        //Lấy ra list món ăn từ db
        dishArrayList = new ArrayList<>();
        dishArrayList = databaseHelper.getDishList();

        //Toast ra số món lấy đc (để test thôi)
        Toast.makeText(getActivity(), "" + dishArrayList.size(), Toast.LENGTH_SHORT).show();

        adapter = new DishAdapter(getActivity(), R.layout.row_dish, dishArrayList);
        setListAdapter(adapter);


        return root;
    }

    //Copy db từ trong asset vô ứng dụng
    private boolean copyDatabase(Context context){
        try{
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0){
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB Copied");
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}