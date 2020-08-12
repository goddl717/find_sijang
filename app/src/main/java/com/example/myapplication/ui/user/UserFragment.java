package com.example.myapplication.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserFragment extends Fragment {

    private UserViewModel userViewModel;
    private static final String TAG = "MainActivity";

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private TextView textivewDelete;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user, container, false);


        //initializing views

        textViewUserEmail = (TextView) root.findViewById(R.id.textviewUserEmail);
//        buttonLogout = (Button) root.findViewById(R.id.buttonLogout);
//        textivewDelete = (TextView) root.findViewById(R.id.textviewDelete);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        //유저가 로그인 하지 않은 상태라면 null 상태이고 이 액티비티를 종료하고 로그인 액티비티를 연다.

        if (firebaseAuth.getCurrentUser() == null) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }

        //유저가 있다면, null이 아니면 계속 진행
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //textViewUserEmail의 내용을 변경해 준다.
        Log.v("jiwon",user.getEmail());
        textViewUserEmail.setText("반갑습니다.\n" + user.getEmail().toString() + "으로 로그인 하였습니다.");

//        //logout button event
//        buttonLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                firebaseAuth.signOut();
//                getActivity().finish();
//                startActivity(new Intent(getActivity(), LoginActivity.class));
//            }
//        });
//        textivewDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getActivity());
//                alert_confirm.setMessage("정말 계정을 삭제 할까요?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                                user.delete()
//                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                Toast.makeText(getActivity(), "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
////                                                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
//                                            }
//                                        });
//                            }
//                        }
//                );
//                alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getActivity(), "취소", Toast.LENGTH_LONG).show();
//                    }
//                });
//                alert_confirm.show();
//            }
//        });


        return root;
    }
}