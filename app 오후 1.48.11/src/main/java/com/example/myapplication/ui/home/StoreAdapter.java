package com.example.myapplication.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //int images[] = {R.drawable.shop,R.drawable.shop,R.drawable.shop,R.drawable.shop,R.drawable.shop,R.drawable.shop};
    //String texts[] = {"지원이네","영행이네","철구네","지원이네","영행이네","철구네"};
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        int width = parent.getResources().getDisplayMetrics().widthPixels/3;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_item,parent,false);
//        view.setLayoutParams(new LinearLayoutCompat.LayoutParams(width, width));
        return new RowCell(view);
    }

    private ArrayList<Store> userInforArrayList;
    //초기화 작업.

    public StoreAdapter(ArrayList<Store> userInforArrayList){
        this.userInforArrayList = userInforArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RowCell)holder).imageView.setImageResource(R.drawable.shop);
        ((RowCell)holder).textView.setText(userInforArrayList.get(position).getItem());
    }

    @Override
    public int getItemCount() {
        return userInforArrayList.size();
    }
    private static class RowCell extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RowCell(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.store_id);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView = (TextView)view.findViewById(R.id.store_title);
        }
    }
}
