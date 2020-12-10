package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LandmarkTypeAdapter
        extends RecyclerView.Adapter<LandmarkTypeAdapter.LandmarkTypeHolder> {

    private final LandmarkType[] landmarkTypes;

    public LandmarkTypeAdapter() {
        landmarkTypes = LandmarkType.values();
    }

    @NonNull
    @Override
    public LandmarkTypeHolder onCreateViewHolder(@NonNull ViewGroup p, int type) {
        LayoutInflater in = LayoutInflater.from(p.getContext());
        // inflates the view layout
        View view = in.inflate(R.layout.landmark_type_view, p, false);
        return new LandmarkTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandmarkTypeHolder holder, int i) {
        holder.setType(landmarkTypes[i]);
    }

    @Override
    public int getItemCount() { return landmarkTypes.length; }

    public static class LandmarkTypeHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private LandmarkType type;
        private final Button landmarkTypeButton;

        public LandmarkTypeHolder(@NonNull View view) {
            super(view);
            landmarkTypeButton = view.findViewById(R.id.landmarkTypeButton);
            landmarkTypeButton.setOnClickListener(this);
        }

        public void setType(LandmarkType type) {
            this.type = type;
            landmarkTypeButton.setText(type.format());

            int c = Landmark.getTypeColor(type);
            c = itemView.getResources().getInteger(c);
            landmarkTypeButton.setBackgroundColor(c);
        }

        @Override
        public void onClick(View v) {
            Context ctx = v.getContext();
            Intent in = new Intent(ctx, LandmarksActivity.class);
            in.putExtra(Landmark.TYPE_EXTRA, type.toString());
            ctx.startActivity(in);
        }
    }
}

