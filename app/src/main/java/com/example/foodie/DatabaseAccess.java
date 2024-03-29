package com.example.foodie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public ArrayList<Dish> getDishes() {
        ArrayList<Dish> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM MonAn", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //list.add(new Dish(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            list.add(new Dish(R.drawable.bo, cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
