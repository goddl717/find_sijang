package com.example.myapplication.navfragment.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Store;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //초기화가 필요하다.
    public static ArrayList<Store> StoreList = new ArrayList<Store>();
    private  Context mContext;
    private static String marketName = new String();
    private static ArrayList<String> StoreKey = new ArrayList<String>();



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //int width = parent.getResources().getDisplayMetrics().widthPixels/3;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_item,parent,false);
        //view.setLayoutParams(new LinearLayoutCompat.LayoutParams(width, width));


        return new RowCell(view);
    }
    public StoreAdapter(Context mContext){
        this.mContext = mContext;
    }
    //어뎁터 생성자.
    public StoreAdapter(ArrayList<Store> userInfoArrayList, ArrayList<String> StoreKey,String marketName){
        this.StoreKey = StoreKey;
        this.marketName = marketName;
        this.StoreList = userInfoArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RowCell)holder).imageView.setImageResource(R.drawable.shop);
        //StroeList.get(position).getStoreName();

        // intent 보내는걸 key 값을 보내는 걸로 설정하고.
        // store 설정하고 이미지 처리를 하고,
        //

        ((RowCell)holder).textView.setText(StoreList.get(position).getStoreName());


    }


    @Override
    public int getItemCount() {
        return StoreList.size();
    }

    private static class RowCell extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout itemView;

        public RowCell(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.store_id);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView = (TextView)view.findViewById(R.id.store_title);

            itemView = (LinearLayout)view.findViewById(R.id.store_item);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : process click event.
                    Log.v("tag1","click event");
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조.
                        Store sto = (Store)StoreList.get(pos);
                        String temp = StoreKey.get(pos);

                        Log.v("tag1",sto.getDetail());
                        Context context = v.getContext();
                        Intent intent =  new Intent(v.getContext(),StoreMain.class);
                        intent.putExtra("key",temp);
                        intent.putExtra("marketname",marketName);
                        intent.putExtra("userid",sto.getUserId());
                        intent.putExtra("storename",sto.getStoreName());
                        intent.putExtra("imageurl",sto.getImageUrl());

                        intent.putExtra("userId",sto.getUserId());

                        //intent 에 store 정보를 모두 보낸다.
                        context.startActivity(intent);
                        Toast.makeText(context, pos +"store id"+temp +"부모주소"+marketName, Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}