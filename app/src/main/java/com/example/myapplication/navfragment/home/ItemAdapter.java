package com.example.myapplication.navfragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    // 이 데이터들을 가지고 각 뷰 홀더에 들어갈 텍스트 뷰에 연결할 것
    private ArrayList<Item> itemlist = new ArrayList<Item>();


    // 생성자
    public ItemAdapter(ArrayList<Item> itemlist){
       this.itemlist = itemlist;

    }

    // 리사이클러뷰에 들어갈 뷰 홀더, 그리고 그 뷰 홀더에 들어갈 아이템들을 지정
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView_item_name;
        public TextView textView_item_price;

        public MyViewHolder(View view){
            super(view);
            this.imageView = view.findViewById(R.id.item_image);

            this.textView_item_name = view.findViewById(R.id.item_name);
            this.textView_item_price = view.findViewById(R.id.item_price);

        }
    }

    // 어댑터 클래스 상속시 구현해야할 함수 3가지 : onCreateViewHolder, onBindViewHolder, getItemCount
    // 리사이클러뷰에 들어갈 뷰 홀더를 할당하는 함수, 뷰 홀더는 실제 레이아웃 파일과 매핑되어야하며, extends의 Adater<>에서 <>안에들어가는 타입을 따른다.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View holderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.store_item_list, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;

    }

    // 실제 각 뷰 홀더에 데이터를 연결해주는 함수
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView_item_name.setText(this.itemlist.get(i).getName());
        myViewHolder.textView_item_price.setText(this.itemlist.get(i).getPrice());
        //myViewHolder.imageView.setBackgroundResource(this.imgSet[i]);
    }

    // iOS의 numberOfRows, 리사이클러뷰안에 들어갈 뷰 홀더의 개수
    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}
