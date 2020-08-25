package com.example.myapplication.navfragment.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Store;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResisterStoreActivity extends AppCompatActivity {
    private static final int GALLEY_CODE = 10;
    private EditText editTextStoreName;
    private EditText editTextMarketName;
    private EditText editTextDetail;
    private EditText editTextCategory;
    private ImageView imageViewStore;
    private Button buttonResister;
    private Button buttonBack;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private String imagePath;
    // market dialog
    List<Map<String, Object>> dialogItemList;
    private static final String TAG_TEXT = "text";
    String[] text = {"동문시장", "남문시장", "신매시장", "북문시장", "감문시장", "서리시장", "동문시장", "남문시장"
//            ,"동문시장", "남문시장", "신매시장", "북문시장", "감문시장", "서리시장", "동문시장", "남문시장",
//            "동문시장", "남문시장", "신매시장", "북문시장", "감문시장", "서리시장", "동문시장", "남문시장",
//            "동문시장", "남문시장", "신매시장", "북문시장", "감문시장", "서리시장", "동문시장", "남문시장",
//            "동문시장", "남문시장", "신매시장", "북문시장", "감문시장", "서리시장", "동문시장", "남문시장"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister_store);
        editTextStoreName = (EditText) findViewById(R.id.editTextStoreName);
        editTextMarketName = (EditText) findViewById(R.id.editTextMarketName);
        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        editTextDetail = (EditText) findViewById(R.id.editTextDetail);
        imageViewStore = (ImageView) findViewById(R.id.imageViewStore);
        buttonResister = (Button) findViewById(R.id.buttonResister);
        buttonBack = (Button) findViewById(R.id.backButton);
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        /*권한*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }
        imageViewStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, GALLEY_CODE);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonResister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload(imagePath);
                Toast.makeText(ResisterStoreActivity.this, "가게 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
        //market dialog
        editTextMarketName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        dialogItemList = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put(TAG_TEXT, text[i]);
            dialogItemList.add(itemMap);
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResisterStoreActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.market_dialog, null);
        builder.setView(view);

        final ListView listview = (ListView) view.findViewById(R.id.listview_alterdialog_list);
        final AlertDialog dialog = builder.create();

        //리사이클러뷰로 교체!!! - 데이터가 많아지면 레이아웃이 깨짐
        SimpleAdapter simpleAdapter = new SimpleAdapter(ResisterStoreActivity.this, dialogItemList,
                R.layout.market_dialog_row,
                new String[]{TAG_TEXT},
                new int[]{R.id.alertDialogItemTextView});

        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                editTextMarketName.setText(text[position]);
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLEY_CODE) {
//            imageViewStore.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageViewStore.setImageURI(data.getData());
//          이미지 불러오기
            imagePath = getPath(data.getData());
            File f = new File(imagePath);
            imageViewStore.setImageURI(Uri.fromFile(f));

        }
    }
    public String getPath(Uri uri){
        String [] proj = { MediaStore.Images.Media.DATA };
        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);

        Cursor cursor = cursorLoader.loadInBackground ();
        int index = cursor.getColumnIndexOrThrow (MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);
    }

    private void upload(String uri){
        final StorageReference storageRef = storage.getReference();;

        Uri file = Uri.fromFile(new File(uri));
        StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.v("jiwon","fail");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                String marketName = editTextMarketName.getText().toString().trim();
                Store store = new Store();
                store.setUserId(auth.getCurrentUser().getUid());
                store.setStoreName(editTextStoreName.getText().toString().trim());
                store.setImageUrl(downloadUrl.toString());
                store.setCategory(editTextCategory.getText().toString().trim());
                store.setDetail(editTextDetail.getText().toString().trim());
                database.getReference().child("시장").child(marketName).child("store").push().setValue(store);
                Log.v("jiwon","succes");
            }
        });
    }
}
