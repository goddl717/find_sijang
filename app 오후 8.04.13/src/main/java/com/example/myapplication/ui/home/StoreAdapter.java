package com.example.myapplication.ui.home;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.UserPost;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int images[] = {R.drawable.shop,R.drawable.shop,R.drawable.shop,R.drawable.shop,R.drawable.shop,R.drawable.shop};
    ArrayList<String> texts;



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                // [END_EXCLUDE]
                Log.v("tag",dataSnapshot.child("/users").toString());

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("tag", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                //      Toast.makeText(PostDetailActivity.this, "Failed to load post.",
                //            Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mDatabase.addValueEventListener(postListener);


//        int width = parent.getResources().getDisplayMetrics().widthPixels/3;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_item,parent,false);
//        view.setLayoutParams(new LinearLayoutCompat.LayoutParams(width, width));
        return new RowCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {




        ((RowCell)holder).imageView.setImageResource(images[position]);
        ((RowCell)holder).textView.setText(texts.get(position));





    }

    @Override
    public int getItemCount() {
        return images.length;
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
    private ArrayList<UserPost> jsonParsing(String json)
    {
        ArrayList<UserPost> temp = null;


        try{
            JSONObject jsonObject = new JSONObject(json);

            String temp1 = jsonObject.getString("DataSnapShot");
            Log.v("tag1",temp1);


        }catch (JSONException e) {
            e.printStackTrace();
        }

        return temp;

    }
}
