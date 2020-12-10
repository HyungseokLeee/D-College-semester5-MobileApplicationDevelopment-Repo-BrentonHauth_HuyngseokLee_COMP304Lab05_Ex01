package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.landmarkTypesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LandmarkTypeAdapter());
    }

//    public void onLandmarkTypeClick(View v) {
//        LandmarkType type = Landmark.getTypeFromButton(v.getId());
//        if (type == null) {
//            Toast.makeText(this,
//                "Something went wrong.",
//                Toast.LENGTH_SHORT).show();
//        } else {
//            Intent in = new Intent(this, LandmarksActivity.class);
//            in.putExtra(Landmark.TYPE_EXTRA, type.toString());
//            startActivity(in);
//        }
//    }
}