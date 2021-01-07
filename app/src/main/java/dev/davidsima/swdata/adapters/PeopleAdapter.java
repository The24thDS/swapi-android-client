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
import dev.davidsima.swdata.models.Person;

public class PeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ct;
    List<Person> people;
    ListItemClickListener listener;

    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public PeopleAdapter(Context ct, ListItemClickListener listener) {
        this.ct = ct;
        this.people = new ArrayList<>();
        this.listener = listener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int personId);
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
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
        View v1 = inflater.inflate(R.layout.person_row, parent, false);
        viewHolder = new PeopleVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Person p = people.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                PeopleVH peopleVH = (PeopleVH) holder;

                peopleVH.name.setText(p.getName());

                peopleVH.itemView.setOnClickListener(new View.OnClickListener() {
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
        return people == null ? 0 : people.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == people.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public Person getItem(int position) {
        return people.get(position);
    }

    public void add(Person p) {
        people.add(p);
        notifyItemInserted(people.size() - 1);
    }

    public void addAll(List<Person> morePeople) {
        for (Person p :
                morePeople) {
            add(p);
        }
    }

    public void remove(Person p) {
        int position = people.indexOf(p);
        if(position > -1) {
            people.remove(position);
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
        add(new Person());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = people.size() - 1;
        Person item = getItem(position);
        if(item != null) {
            people.remove(position);
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
    protected class PeopleVH extends RecyclerView.ViewHolder {
        private TextView name;

        public PeopleVH(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view_person_name);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}