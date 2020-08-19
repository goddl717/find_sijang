package com.example.myapplication.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AddressActivity;
import com.example.myapplication.R;
import com.example.myapplication.UserPost;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {


    private TextView text_address;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private SharedPreferences sharedPreferences;
    ArrayList<Store> InfoArrayList;
    StoreAdapter marketAdapter;
    StoreAdapter marketAdapter2;
    private DatabaseReference mFirebaseDatabaseReference;

    private FirebaseRecyclerAdapter<UserPost,RecyclerView.ViewHolder> mFirebaseAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        text_address = (TextView) root.findViewById(R.id.current_address);

        text_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddressActivity.class);
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
            }
        });

        // 기기에 주소 정보 저장
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String current_address = sharedPreferences.getString("address","주소 입력");
        text_address.setText(current_address);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_market);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

         InfoArrayList = new ArrayList<Store>();
         DatabaseReference mDatabase;

         //users 대신 시장에 대한 정보 3개나 2개 넣고.
        Query query;
        //Log.v("tag1",query.toString());
        //Log.v("tag1",query.endAt(100).toString());


        query = FirebaseDatabase.getInstance().getReference().child("시장/");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get Post object and use the values to update the UI

                Log.v("tag",dataSnapshot.toString());
                /*
                String json = dataSnapshot.child("users").getValue().toString();
                Log.v("tag",json);
                */

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    //Address add = snapshot.getValue(Address.class);
                    Market mar = snapshot.getValue(Market.class);
                    Log.v("tag",mar.getDetail());
                    //Log.v("tag",mar.getAddress());
                    //Log.v("tag",mar.getDetail());

                    Store sto = mar.getStore();

                    Log.v("tag",sto.getCansold());
                    Log.v("tag",sto.getItem());
                    Log.v("tag",sto.getUid());

                    // 만들어뒀던 User 객체에 데이터를 담는다.
                    InfoArrayList.add(sto); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }

                //리스트에 추가 하는데 여기서 파싱한 값을 가져와서
                //list.add(파싱한 값.);
                marketAdapter.notifyDataSetChanged();
                marketAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        query.addValueEventListener(postListener);



        marketAdapter = new StoreAdapter(InfoArrayList);
        recyclerView.setAdapter(marketAdapter);
        RecyclerView recyclerView2 = (RecyclerView) root.findViewById(R.id.recyclerview_market2);

        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);
         marketAdapter2 = new StoreAdapter(InfoArrayList);
        recyclerView2.setAdapter(marketAdapter2);


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
}


