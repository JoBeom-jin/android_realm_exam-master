package com.example.realmexam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.realmexam.models.Student;

import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private EditText mAgeEditText;
    private RecyclerView mRecyclerView;
    private ImageView immm;
    private String a;

    private Realm realm = Realm.getDefaultInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.name_edit);
        mAgeEditText = findViewById(R.id.age_edit);
        mRecyclerView = findViewById(R.id.recycler_view);

        immm = findViewById(R.id.immm);
        immm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });


        // 쿼리
        RealmResults<Student> results = realm.where(Student.class)
                .sort("age", Sort.DESCENDING).findAll();

        StudentRecyclerAdapter adapter = new StudentRecyclerAdapter(results);
        mRecyclerView.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                int age = Integer.parseInt(mAgeEditText.getText().toString());


                // DB에 저장
                realm.beginTransaction();
                Student student = realm.createObject(Student.class);
                student.setName(name);
                student.setAge(age);
                student.setUu(a);
                realm.commitTransaction();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    immm.setImageBitmap(img);
                    a = data.getData().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}