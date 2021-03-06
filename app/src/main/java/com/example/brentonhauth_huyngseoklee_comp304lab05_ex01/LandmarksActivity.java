package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


public class LandmarksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LandmarkType landmarkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmarks);

        getTypeFromIntent();
    }

    private void getTypeFromIntent() {
        Intent in = getIntent();
        String str = in.getStringExtra(Landmark.TYPE_EXTRA);
        if (str == null) finish();
        landmarkType = LandmarkType.valueOf(str);
        setTitle(landmarkType.format());
        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.landmarksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LandmarkAdapter adapter = new LandmarkAdapter(
                Landmark.getLandmarksByType(landmarkType, true));
        recyclerView.setAdapter(adapter);
    }
}