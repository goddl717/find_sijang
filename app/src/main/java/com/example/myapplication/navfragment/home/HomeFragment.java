package com.example.myapplication.navfragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AddressActivity;
import com.example.myapplication.R;

import com.example.myapplication.model.Market;
import com.example.myapplication.model.Pair_temp;
import com.example.myapplication.model.Store;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements View.OnClickListener{



    private TextView text_address;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    StoreAdapter storeAdapter;
    StoreAdapter sotreAdapter2;
    private ArrayList<Store> storeArrayList = new ArrayList<Store>();
    private ArrayList<String> storeKey = new ArrayList<String>();


    //  floating actionbar
    private Context mContext;
    private FloatingActionButton fab_main, fab_sub1, fab_sub2;
    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;
    //private Geocoder geocoder = new Geocoder();
    private List<Address> mListAddress;
    Address mAddress;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        text_address = (TextView) root.findViewById(R.id.current_address);
        final Geocoder geocoder = new Geocoder(this.getContext());

        text_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivityForResult(intent, SEARCH_ADDRESS_ACTIVITY);
            }
        });
        // 기기에 주소 정보 저장
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String current_address = sharedPreferences.getString("address", "주소 입력");
        text_address.setText(current_address);
        final ArrayList<Pair_temp> addlist = new ArrayList<Pair_temp>();


        if (current_address!= null) {
            Log.v("tag1", current_address);
            database = FirebaseDatabase.getInstance();
            database.getReference().child("시장").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.v("tag1",snapshot.toString());
                    for (DataSnapshot snap : snapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                        Market mar = snap.getValue(Market.class);
                        String temp = snap.getKey();
                        Log.v("tag1 = marketname",temp);

                        String add = mar.getAddress();
                        Log.v("tag1 = marketaddress",add);
                        //Address add = snapshot.getValue(Address.class);
                        Pair_temp pa = new Pair_temp(temp,add);
                        addlist.add(pa);
                    }

                    // 시장에 대한 정보를 임시 저장. 이 테이블에서 계산식을 구해서 가장 작은 것을 갱신해서 사용한다.
                    for(int i = 0 ; i < addlist.size();i++) {
                        Log.v("tag1", addlist.get(i).getFirst() + addlist.get(i).getSecond());
                        //String result = SearchLocation(addlist.get(i).getSecond());
                        //Log.v("tag",result);
                    }
                    //위도 경도를 가지고 가장 가까운 위치 2개를 뽑는다 ?
                    try {
                        List<Address> temp = geocoder.getFromLocationName("대구광역시 수성구 달구벌대로 3310",5);
                        Address addtemp = temp.get(0);
                        Log.v("tag", String.valueOf(addtemp.getLatitude()));
                        Log.v("tag", String.valueOf(addtemp.getLongitude()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
        //구글 api를 불러와서 일단은 주소를 변환.




        database = FirebaseDatabase.getInstance();
        String marketName = database.getReference().child("시장").child("서문시장").getKey();

        database.getReference().child("시장").child("서문시장").child("store").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                storeArrayList.clear();
                Log.v("tag", dataSnapshot.toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    Store sto = snapshot.getValue(Store.class);
                    String temp = snapshot.getKey();
                    Log.v("tag1 = storekey",temp);

                    storeKey.add(temp);
                    storeArrayList.add(sto);
                    //Address add = snapshot.getValue(Address.class);
                    //Market market = snapshot.getValue(Market.class);
                }
                storeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });



        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_market);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        storeAdapter = new StoreAdapter(storeArrayList,storeKey,marketName);
        recyclerView.setAdapter(storeAdapter);
//        RecyclerView recyclerView2 = (RecyclerView) root.findViewById(R.id.recyclerview_market2);
//        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView2.setLayoutManager(horizontalLayoutManager2);
//         sotreAdapter2 = new StoreAdapter(InfoArrayList);
//        recyclerView2.setAdapter(sotreAdapter2);

        // floatingActionButton
        mContext = getActivity();
        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);
        fab_main = (FloatingActionButton) root.findViewById(R.id.fab_main);
        fab_sub1 = (FloatingActionButton) root.findViewById(R.id.fab_sub1);
        fab_sub2 = (FloatingActionButton) root.findViewById(R.id.fab_sub2);
        fab_main.setOnClickListener(this);
        fab_sub1.setOnClickListener(this);
        fab_sub2.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            fab_main.hide();
        }

        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null)
                        text_address.setText(data);
                }
                break;
        }
    }
    // floatingActionButton 동작
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main:
                toggleFab();
                break;
            case R.id.fab_sub1:
                toggleFab();
                startActivity(new Intent(getActivity(), ResisterStoreActivity.class));
                break;
            case R.id.fab_sub2:
                toggleFab();
                Toast.makeText(getActivity(), "Map Open-!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void toggleFab() {

        if (isFabOpen) {
            fab_main.setImageResource(R.drawable.ic_add);
            fab_sub1.startAnimation(fab_close);
            fab_sub2.startAnimation(fab_close);
            fab_sub1.setClickable(false);
            fab_sub2.setClickable(false);
            isFabOpen = false;

        } else {
            fab_main.setImageResource(R.drawable.ic_close);
            fab_sub1.startAnimation(fab_open);
            fab_sub2.startAnimation(fab_open);
            fab_sub1.setClickable(true);
            fab_sub2.setClickable(true);
            isFabOpen = true;
        }

    }
}
