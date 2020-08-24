package com.example.myapplication.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.AddressActivity;
import com.example.myapplication.R;
import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private TextView text_address;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    private SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //여기서 리사이클링 뷰를 동적으로 제어해 보자.


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

        //리사이커뷰.
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_market);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        StoreAdapter marketAdapter = new StoreAdapter();
        recyclerView.setAdapter(marketAdapter);


        RecyclerView recyclerView2 = (RecyclerView) root.findViewById(R.id.recyclerview_market2);

        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);
        StoreAdapter marketAdapter2 = new StoreAdapter();
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


