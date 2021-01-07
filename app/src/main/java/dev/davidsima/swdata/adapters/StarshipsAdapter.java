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
import dev.davidsima.swdata.models.Starship;
import dev.davidsima.swdata.models.Vehicle;

public class StarshipsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;
    List<Starship> starships;
    ListItemClickListener listener;

    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public StarshipsAdapter(Context ct, ListItemClickListener listener) {
        this.ct = ct;
        this.starships = new ArrayList<>();
        this.listener = listener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int personId);
    }

    public List<Starship> getStarships() {
        return starships;
    }

    public void setStarships(List<Starship> starships) {
        this.starships = starships;
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
        View v1 = inflater.inflate(R.layout.starship_row, parent, false);
        viewHolder = new StarshipVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Starship s = starships.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                StarshipVH starshipVH = (StarshipVH) holder;

                starshipVH.name.setText(s.getName());

                starshipVH.itemView.setOnClickListener(new View.OnClickListener() {
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
        return starships == null ? 0 : starships.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == starships.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public Starship getItem(int position) {
        return starships.get(position);
    }

    public void add(Starship s) {
        starships.add(s);
        notifyItemInserted(starships.size() - 1);
    }

    public void addAll(List<Starship> moreStarships) {
        for (Starship s :
                moreStarships) {
            add(s);
        }
    }

    public void remove(Starship s) {
        int position = starships.indexOf(s);
        if(position > -1) {
            starships.remove(position);
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
        add(new Starship());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = starships.size() - 1;
        Starship item = getItem(position);
        if(item != null) {
            starships.remove(position);
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
    protected class StarshipVH extends RecyclerView.ViewHolder {
        private TextView name;

        public StarshipVH(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_view_starship_name);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}