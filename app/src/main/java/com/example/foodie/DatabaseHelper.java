package com.example.foodie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
   public static final String DBNAME = "DBMonAn.db";
   public static final String DBLOCATION = "data/data/com.example.foodie/mydatabase";
   private Context mContext;
   private SQLiteDatabase mDatabase;

   public DatabaseHelper(Context context){
       super(context, DBNAME, null, 1);
       this.mContext = context;
   }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){
       String dpPath = DBLOCATION + DBNAME;
       if (mDatabase != null && mDatabase.isOpen()){
           return;
       }
       mDatabase = SQLiteDatabase.openDatabase(dpPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    public void closeDatabase(){
       if (mDatabase != null){
           mDatabase.close();
       }
    }

    public ArrayList<Dish> getDishList(){
       Dish dish = null;
       ArrayList<Dish> list = new ArrayList<>();
       openDatabase();
       Cursor cursor = mDatabase.rawQuery("SELECT * FROM MonAn", null);
       cursor.moveToPosition(0);
       while(cursor.getString(0)!=null){
           dish = new Dish(R.drawable.bo, cursor.getString(0), cursor.getString(1));
           list.add(dish);
           cursor.moveToNext();
       }
       cursor.close();
       closeDatabase();
       return list;
    }

    public ArrayList<String> getDishInfo(String id) {
        ArrayList<String> info = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM MonAn", null);
        cursor.moveToPosition(0);
        while (cursor.getString(0) != null) {
            if (cursor.getString(0).equals(id)) {
                info.add(cursor.getString(3));
                info.add(cursor.getString(4));
                break;
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return info;
    }
}