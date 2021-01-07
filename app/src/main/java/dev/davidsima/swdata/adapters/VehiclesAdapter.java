package dev.davidsima.swdata.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.models.Species;
import dev.davidsima.swdata.models.Vehicle;

public class VehiclesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;
    List<Vehicle> vehicles;
    ListItemClickListener listener;

    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public VehiclesAdapter(Context ct, ListItemClickListener listener) {
        this.ct = ct;
        this.vehicles = new ArrayList<>();
        this.listener = listener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int personId);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(ct);

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.progress_row, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.vehicle_row, parent, false);
        viewHolder = new VehicleVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Vehicle v = vehicles.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                VehicleVH vehicleVH = (VehicleVH) holder;

                vehicleVH.name.setText(v.getName());

                vehicleVH.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onListItemClick(v.getId());
                    }
                });
                break;
            case LOADING:
//                Do nothing
                break;
        }


    }

    @Override
    public int getItemCount() {
        return vehicles == null ? 0 : vehicles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == vehicles.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public Vehicle getItem(int position) {
        return vehicles.get(position);
    }

    public void add(Vehicle v) {
        vehicles.add(v);
        notifyItemInserted(vehicles.size() - 1);
    }

    public void addAll(List<Vehicle> moreVehicles) {
        for (Vehicle v :
                moreVehicles) {
            add(v);
        }
    }

    public void remove(Vehicle v) {
        int position = vehicles.indexOf(v);
        if(position > -1) {
            vehicles.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Vehicle());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = vehicles.size() - 1;
        Vehicle item = getItem(position);
        if(item != null) {
            vehicles.remove(position);
            notifyItemRemoved(position);
        }
    }

     /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class VehicleVH extends RecyclerView.ViewHolder {
        private TextView name;

        public VehicleVH(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_view_vehicle_name);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}