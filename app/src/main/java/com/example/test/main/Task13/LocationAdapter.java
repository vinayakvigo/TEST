package com.example.test.main.Task13;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test.R;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<LocationEntity> locations;

    public LocationAdapter(List<LocationEntity> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item, parent, false);
        return new LocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        LocationEntity location = locations.get(position);
        holder.latitudeTextView.setText("Lat " + location.getLatitude());
        holder.longitudeTextView.setText("Lon " + location.getLongitude());
        holder.timestampTextView.setText("TS: " + location.getTimestamp());
        holder.IndexTextView.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView latitudeTextView;
        TextView longitudeTextView;
        TextView timestampTextView;
        TextView IndexTextView;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            latitudeTextView = itemView.findViewById(R.id.latitudeTextView);
            longitudeTextView = itemView.findViewById(R.id.longitudeTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
            IndexTextView = itemView.findViewById(R.id.indexTextView);
        }
    }
}

