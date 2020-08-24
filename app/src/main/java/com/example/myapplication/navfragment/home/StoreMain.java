package com.example.myapplication.navfragment.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.Item;
import com.example.myapplication.model.Store;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StoreMain extends AppCompatActivity {
    FirebaseDatabase database;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);

        Intent intent = getIntent();
        Log.v("tag1",intent.getStringExtra("marketname"));
        database = FirebaseDatabase.getInstance();
        String marketName = intent.getStringExtra("marketname");
        String key = intent.getStringExtra("key");
        Log.v("tag1",key);

        TextView store_name = (TextView)findViewById(R.id.store_name);
        store_name.setText(intent.getStringExtra("storename"));

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference imagesRef = storageRef.child("/img/"+key+".jpg");
        final ImageView ivGlide = (ImageView)findViewById(R.id.imageView);

        imagesRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    // Glide 이용하여 이미지뷰에 로딩
                    Glide.with(getApplicationContext())
                            .load(task.getResult())
                            //.override(0b10000000000, 1024)
                            .into(ivGlide);
                } else {
                    // URL을 가져오지 못하면 토스트 메세지
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        Glide.with(this)
                .load(imagesRef)
                .into(ivGlide);
        */
        //액션바 제거.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_item);

        // 리사이클러뷰의 notify()처럼 데이터가 변했을 때 성능을 높일 때 사용한다.
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final ArrayList<Item> itemlist = new ArrayList<Item>();
        //Item temp = new Item("sdf","불고기","sdf","가격은 1000원 ");
        //Item temp2 = new Item("sdf","돼지고기 ","sdf","가격은 2000");
        //itemlist.add(temp);
        //itemlist.add(temp2);


        // 어댑터 할당, 어댑터는 기본 어댑터를 확장한 커스텀 어댑터를 사용할 것이다.
        final ItemAdapter adapter = new ItemAdapter(itemlist);
        recyclerView.setAdapter(adapter);
        //인턴트 불러오기.
        //store의 모든 값을 다 가져오면,
        //Log.v("tag1",marketName+key);

        database.getReference().child("시장").child(marketName).child("store").child(key).child("item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("tag", dataSnapshot.toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    Item itm = snapshot.getValue(Item.class);
                    itemlist.add(itm);
                }
                adapter.notifyDataSetChanged();
                //Address add = snapshot.getValue(Address.class);
                //Market market = snapshot.getValue(Market.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        //연결은 마지막에 하면 될거같은데.

    }
}