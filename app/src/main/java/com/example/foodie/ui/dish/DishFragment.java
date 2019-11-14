//Fragment trang chủ, là danh sách mấy món ăn

package com.example.foodie.ui.dish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodie.DatabaseHelper;
import com.example.foodie.Dish;
import com.example.foodie.DishSender;
import com.example.foodie.R;
import com.example.foodie.ui.combo.ComboViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DishFragment extends Fragment {

    //Mấy ô loại món
    LinearLayout kho, nuoc, chay, bo, ga, ca, heo, rau, tom;

    //Biến đọc db
    private static final String DATABASE_NAME = "DBMonAn.db";
    private static final int DATABASE_VERSION = 1;
    private DatabaseHelper databaseHelper;

    //Cái này do t dùng interface để chuyển dữ liệu fragment, đang lỗi nên comment lại (1)
    //DishSender dishSender;

    //Array lưu DS món ăn
    ArrayList<Dish> dishArrayList;

    //Biến phụ
    int i = 0;
    // int selectedPosition = 0;
    String selectedDish;
    int arrayN = 0;

    //View chính
    View root;
    NestedScrollView scrollView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dish, container, false);
        Mapping();

        //Lỗi (1)
        //dishSender = (DishSender) getActivity();

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

        arrayN = dishArrayList.size(); //kích thước chuỗi món ăn

        //Thêm 5 món ăn đầu tiên (để đầy màn hình), hàm này t giải thích cụ thể bến dưới
        addItem();

        //Lấy cái view chính trong xml
        scrollView = (NestedScrollView) root.findViewById(R.id.dish_fragment_scrollview);

        //Khi nào scroll đến cuối màn hình sẽ add 5 item nữa
        scrollView.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (i < arrayN) {
                            if (scrollView.getChildAt(0).getBottom()
                                    <= (scrollView.getHeight() + scrollView.getScrollY())) {
                                Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
                                addItem();
                            }
                        }
                    }
                });

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

    //Gắn onClick cho mấy cái nút, ...
    private void Mapping(){
        kho = (LinearLayout)root.findViewById(R.id.ll_kho);
        nuoc = (LinearLayout)root.findViewById(R.id.ll_nuoc);
        chay = (LinearLayout)root.findViewById(R.id.ll_chay);
        ga = (LinearLayout)root.findViewById(R.id.ll_ga);
        bo = (LinearLayout)root.findViewById(R.id.ll_bo);
        tom = (LinearLayout)root.findViewById(R.id.ll_tom);
        heo = (LinearLayout)root.findViewById(R.id.ll_heo);
        rau = (LinearLayout)root.findViewById(R.id.ll_rau);
        ca = (LinearLayout)root.findViewById(R.id.ll_ca);

        kho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        nuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        chay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        bo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        heo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        tom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
        rau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Món khô", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Thêm 5 thẻ (file row_dich.xml) món ăn
    public void addItem() {

        //Chỗ này set margin cho từng thẻ để nó cách ra thôi, xóa thì nó sát nhau
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMargins(24, 40, 24, 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Muốn add thêm x thẻ món thì thay 5 thành x
                for (int j = 0; j < 5; j++) {

                    //Hết món
                    if (i == arrayN) break;

                    //Lấy món trong mảng tại vị trí i
                    final Dish tempDish = dishArrayList.get(i);

                    //Lấy row_dish
                    View temp;
                    LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    temp = li.inflate(R.layout.row_dish, null);

                    //Lấy id mấy textView trong row_dish
                    final ImageView holder_thumbnail = (ImageView) temp.findViewById(R.id.row_iv_thumbnail);
                    final TextView holder_name = (TextView) temp.findViewById(R.id.row_tv_name);
                    final TextView holder_type = (TextView) temp.findViewById(R.id.row_tv_type);

                    //Dán thông tin món ăn lên
                    holder_thumbnail.setImageResource(dishArrayList.get(i).getThumbnail());
                    holder_name.setText(dishArrayList.get(i).getName());
                    holder_type.setText(dishArrayList.get(i).getType());

                    //Lấy id của Linear Layout trong màn hình chính
                    LinearLayout my = (LinearLayout) root.findViewById(R.id.dish_linear_layout);

                    //Set margin để cách ra
                    temp.setLayoutParams(params);

                    //Add vô Linear Layout. Vậy là có thêm 1 thẻ món ăn
                    my.addView(temp);

                    //Gắn onClick cho thẻ. T đang bị lỗi chỗ này, không chuyển đc qua fragment_dish_details
                    temp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            selectedDish = holder_name.getText().toString();
                            Toast.makeText(getActivity(), ""+selectedDish, Toast.LENGTH_SHORT).show();
                      //      Bundle bundle = new Bundle();
          //                  bundle.putSerializable(tempDish, "dish_key");
//                            bundle.putString(tempDish.getId(), "dishID");
//                            bundle.putString(tempDish.getName(), "dishName");
//                            bundle.putString(tempDish.getType(), "dishType");


//                            DishDetailFragment dishDetailFragment = new DishDetailFragment();
//                            FragmentManager fm = getActivity().getSupportFragmentManager();
//                            FragmentTransaction transaction = fm.beginTransaction();
//                            fm.beginTransaction()
//                                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                                    .show(dishDetailFragment)
//                                    .commit();
//                            transaction.replace(R.id.dish_fragment, dishDetailFragment);
//                            transaction.addToBackStack(null);
//                            transaction.commit();


                      //      dishSender.SetDishDetails(tempDish);
                            //View tempview = dishDetailFragment.getView();
                            // dishDetailFragment.setInfo(tempDish);
                        }
                    });
                    i++;
                }
            }
        }, 0);
    }


    //Chỗ này t ko nhớ nữa mà đừng xóa :v
    public void getDatabase() {
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity());
//        databaseAccess.open();
//        dishArrayList = databaseAccess.getDishes();
//        databaseAccess.close();
//        File storagePath = 'DBMonAn.db';
//        String myDbPath = storagePath + "/" + DATABASE_NAME;
//        SQLiteDatabase db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
    }

}


