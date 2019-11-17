package com.example.foodie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodie.Dish;
import com.example.foodie.R;

import java.util.List;

public class DishAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Dish> dishList;

    public DishAdapter(Context context, int layout, List<Dish> dishList) {
        this.context = context;
        this.layout = layout;
        this.dishList = dishList;
    }

    @Override
    public int getCount() {
        return dishList.size();
    }

    @Override
    public Object getItem(int i) {
        return dishList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        ImageView holder_thumbnail;
        TextView holder_name, holder_type, holder_ingredient;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.holder_thumbnail = (ImageView) view.findViewById(R.id.row_iv_thumbnail);
            holder.holder_name = (TextView) view.findViewById(R.id.row_tv_name);
            holder.holder_type = (TextView) view.findViewById(R.id.row_tv_type);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Dish dish = dishList.get(i);

        holder.holder_thumbnail.setImageResource(dish.getThumbnail());
        holder.holder_name.setText(dish.getName());
        holder.holder_type.setText(dish.getType());

        return view;
    }
}
