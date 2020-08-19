package com.example.myapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DatabaseControl {
    private DatabaseReference mDatabase;
    public String ret = "";

    public DatabaseControl() {

    }

    // path를 주면 Json을 리턴을 하는 함수
    public String StringJson(final String path1) {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //Post post = dataSnapshot.getValue(Post.class);
                // ...
                Log.v("tag", dataSnapshot.child(path1).toString());

                //여기서 값을 처리하면 되지 않을까?
                ret = dataSnapshot.child(path1).getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("tag", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        return ret;
    }
}
