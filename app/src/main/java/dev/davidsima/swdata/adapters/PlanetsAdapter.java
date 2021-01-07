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
import dev.davidsima.swdata.models.Planet;

public class PlanetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;
    List<Planet> planets;
    ListItemClickListener listener;

    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public PlanetsAdapter(Context ct, ListItemClickListener listener) {
        this.ct = ct;
        this.planets = new ArrayList<>();
        this.listener = listener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int personId);
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
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
        View v1 = inflater.inflate(R.layout.planet_row, parent, false);
        viewHolder = new PlanetsVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Planet p = planets.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                PlanetsVH planetsVH = (PlanetsVH) holder;

                planetsVH.name.setText(p.getName());

                planetsVH.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onListItemClick(p.getId());
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
        return planets == null ? 0 : planets.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == planets.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public Planet getItem(int position) {
        return planets.get(position);
    }

    public void add(Planet p) {
        planets.add(p);
        notifyItemInserted(planets.size() - 1);
    }

    public void addAll(List<Planet> morePlanets) {
        for (Planet p :
                morePlanets) {
            add(p);
        }
    }

    public void remove(Planet p) {
        int position = planets.indexOf(p);
        if(position > -1) {
            planets.remove(position);
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
        add(new Planet());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = planets.size() - 1;
        Planet item = getItem(position);
        if(item != null) {
            planets.remove(position);
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
    protected class PlanetsVH extends RecyclerView.ViewHolder {
        private TextView name;

        public PlanetsVH(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_view_planet_name);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}