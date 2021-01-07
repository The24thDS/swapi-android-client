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
import dev.davidsima.swdata.models.Species;

public class SpeciesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;
    List<Species> species;
    ListItemClickListener listener;

    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public SpeciesAdapter(Context ct, ListItemClickListener listener) {
        this.ct = ct;
        this.species = new ArrayList<>();
        this.listener = listener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int personId);
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
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
        View v1 = inflater.inflate(R.layout.species_row, parent, false);
        viewHolder = new SpeciesVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Species s = species.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                SpeciesVH speciesVH = (SpeciesVH) holder;

                speciesVH.name.setText(s.getName());

                speciesVH.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onListItemClick(s.getId());
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
        return species == null ? 0 : species.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == species.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public Species getItem(int position) {
        return species.get(position);
    }

    public void add(Species s) {
        species.add(s);
        notifyItemInserted(species.size() - 1);
    }

    public void addAll(List<Species> moreSpecies) {
        for (Species s :
                moreSpecies) {
            add(s);
        }
    }

    public void remove(Species s) {
        int position = species.indexOf(s);
        if(position > -1) {
            species.remove(position);
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
        add(new Species());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = species.size() - 1;
        Species item = getItem(position);
        if(item != null) {
            species.remove(position);
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
    protected class SpeciesVH extends RecyclerView.ViewHolder {
        private TextView name;

        public SpeciesVH(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_view_species_name);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}