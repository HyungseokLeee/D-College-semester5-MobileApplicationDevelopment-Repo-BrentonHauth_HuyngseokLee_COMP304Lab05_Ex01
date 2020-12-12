package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LandmarkAdapter extends RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder> {

    private final Landmark[] landmarks;

    public LandmarkAdapter(@NonNull Landmark[] data) {
        landmarks = data;
    }

    @NonNull
    @Override
    public LandmarkHolder onCreateViewHolder(@NonNull ViewGroup p, int type) {
        LayoutInflater in = LayoutInflater.from(p.getContext());
        // inflates the view layout
        View view = in.inflate(R.layout.landmark_view, p, false);
        return new LandmarkAdapter.LandmarkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandmarkHolder holder, int i) {
        Log.d("onBindViewHolder", landmarks[i].getName());
        holder.setLandmark(landmarks[i]);
    }

    @Override
    public int getItemCount() {
        return landmarks.length;
    }

    public static class LandmarkHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Landmark landmark;

        private final TextView nameTextView, typeTextView, addressTextView;

        public LandmarkHolder(@NonNull View view) {
            super(view);
            nameTextView = view.findViewById(R.id.landmarkNameTextView);
            typeTextView = view.findViewById(R.id.landmarkTypeTextView);
            addressTextView = view.findViewById(R.id.landmarkAddressTextView);

            view.setOnClickListener(this);
        }

        public void setLandmark(Landmark landmark) {
            this.landmark = landmark;
            nameTextView.setText(landmark.getName());
            addressTextView.setText(landmark.getAddress());

            LandmarkType type = landmark.getType();
            typeTextView.setText(String.format("%s", type.format(false)));
            typeTextView.setTextColor(type.getColorFrom(itemView));
        }

        @Override
        public void onClick(View v) {
            // TODO: Implement activity change for landmark clicked
            Toast.makeText(itemView.getContext(),
                "Clicked " + landmark.getName(),
                Toast.LENGTH_SHORT).show();
        }
    }
}
