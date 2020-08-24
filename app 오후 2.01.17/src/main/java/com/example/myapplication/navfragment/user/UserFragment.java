package com.example.myapplication.navfragment.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.myapplication.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFragment extends Fragment {


    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects

    private Button buttonLogout;
    private TextView textivewDelete;
    String currentUser;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        buttonLogout = (Button) root.findViewById(R.id.buttonLogout);
        textivewDelete = (TextView) root.findViewById(R.id.textviewDelete);

        if (firebaseAuth.getCurrentUser() != null) {
            buttonLogout.setVisibility(View.VISIBLE);
            textivewDelete.setVisibility(View.VISIBLE);
        } else {
            buttonLogout.setVisibility(View.GONE);
            textivewDelete.setVisibility(View.GONE);
        }

        //logout button event
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Fragment childFragment = new LogoutFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_container, childFragment).commit();
                buttonLogout.setVisibility(View.GONE);
                textivewDelete.setVisibility(View.GONE);
            }
        });
        textivewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
                alert_confirm.setMessage("정말 계정을 삭제 할까요?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                currentUser = firebaseAuth.getUid();
                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getActivity(), "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                                // 데이터 베이스 삭제
                                                DatabaseReference userDBRef = FirebaseDatabase.getInstance().getReference("users/"+currentUser);
                                                userDBRef.removeValue();
                                                Fragment childFragment = new LogoutFragment();
                                                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                                                transaction.replace(R.id.child_fragment_container, childFragment).commit();
                                                buttonLogout.setVisibility(View.GONE);
                                                textivewDelete.setVisibility(View.GONE);

                                            }
                                        });
                            }
                        }
                );
                alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "취소", Toast.LENGTH_LONG).show();
                    }
                });
                alert_confirm.show();
            }
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //유저가 로그인 하지 않은 상태라면 null 상태이고 이 액티비티를 종료하고 로그인 액티비티를 연다.
        if (firebaseAuth.getCurrentUser() == null) {
            Fragment childFragment = new LogoutFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.child_fragment_container, childFragment).commit();
        } else {
            Fragment childFragment = new LoginFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.child_fragment_container, childFragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //유저가 로그인 하지 않은 상태라면 null 상태이고 이 액티비티를 종료하고 로그인 액티비티를 연다.
        if (firebaseAuth.getCurrentUser() == null) {
            Fragment childFragment = new LogoutFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.child_fragment_container, childFragment).commit();
            buttonLogout.setVisibility(View.GONE);
            textivewDelete.setVisibility(View.GONE);

        } else {
            Fragment childFragment = new LoginFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.child_fragment_container, childFragment).commit();
            buttonLogout.setVisibility(View.VISIBLE);
            textivewDelete.setVisibility(View.VISIBLE);
        }
    }
}