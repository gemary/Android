package com.example.finalexam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ImageView textView,tv_sync;
    RecyclerView listview_show;
    public static List<Model> modelList;
    public static MyAdapter adapter;
    public static   FirebaseDatabase database;
    String TAG = "fireBase Connect";
    public static DatabaseReference myRef;
    String googleId ;
    Model model ;
    public static DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();


        ref =  database.getReference();
        myRef = ref.child("AdvancedAndroidFinalTest");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              if (  dataSnapshot.hasChildren())
              {
//                  Toast.makeText(MainActivity.this, "Have Data", Toast.LENGTH_SHORT).show();
              }
              else {
                  // call api
                  Intent intent = getIntent();
                  googleId = intent.getStringExtra("id");
                  Map<String, String> map = new HashMap<>();
                  map.put("google_id", googleId);
                  map.put("firebase_url", "https://final-1557897430549.firebaseio.com/");
                  new Myasyntask(map).execute("http://vidoandroid.vidophp.tk/api/FireBase/PushData");
                  //
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        myRef = database.getReference("AdvancedAndroidFinalTest");
        Init();


        listview_show.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager  grm = new GridLayoutManager(this,2);
        listview_show.setLayoutManager(grm);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });
        tv_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call api
                Intent intent = getIntent();
                googleId = intent.getStringExtra("id");
                Map<String, String> map = new HashMap<>();
                map.put("google_id", googleId);
                map.put("firebase_url", "https://final-1557897430549.firebaseio.com/");
                new Myasyntask(map).execute("http://vidoandroid.vidophp.tk/api/FireBase/PushData");
                Toast.makeText(MainActivity.this, "Sync Success", Toast.LENGTH_SHORT).show();
                //
            }
        });


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    model = ds.getValue(Model.class);
                    model.setKey(ds.getKey());
                    modelList.add(model);
                }
                adapter = new MyAdapter(MainActivity.this,R.layout.item,modelList);
                listview_show.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    model = ds.getValue(Model.class);
                    model.setKey(ds.getKey());
                    modelList.add(model);
                }
                adapter = new MyAdapter(MainActivity.this,R.layout.item,modelList);
                listview_show.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot ds : dataSnapshot.getChildren())
//                {
//
//                    model = ds.getValue(Model.class);
//                    model.setKey(ds.getKey());
//                    modelList.add(model);
//                }
//                adapter = new MyAdapter(MainActivity.this,R.layout.item,modelList);
//                listview_show.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }



    private void Init() {
        modelList = new ArrayList<>();
        listview_show = (RecyclerView) findViewById(R.id.listview_show);
        textView = (ImageView)findViewById(R.id.tv_show);
        tv_sync =(ImageView)findViewById(R.id.tv_sync);
    }
}
