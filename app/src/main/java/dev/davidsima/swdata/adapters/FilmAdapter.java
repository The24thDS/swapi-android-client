package dev.davidsima.swdata.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.models.Film;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    Context ct;
    List<Film> films;
    int[] images = {
            R.drawable.episode1,
            R.drawable.episode2,
            R.drawable.episode3,
            R.drawable.episode4,
            R.drawable.episode5,
            R.drawable.episode6,
    };
    ListItemClickListener listener;

    public FilmAdapter(Context ct, List<Film> films, ListItemClickListener listener) {
        this.ct = ct;
        this.films = films;
        this.listener = listener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.film_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.episode_id.append(String.valueOf(films.get(position).getEpisode_id()));
        holder.title.setText(films.get(position).getTitle());
        holder.img.setImageResource(images[films.get(position).getEpisode_id()-1]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films == null ? 0 : films.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView episode_id, title;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            episode_id = itemView.findViewById(R.id.text_view_episode_id);
            title = itemView.findViewById(R.id.text_view_title);
            img = itemView.findViewById(R.id.film_image);
        }
    }
}