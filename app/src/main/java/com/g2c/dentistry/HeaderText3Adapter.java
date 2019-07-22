package com.g2c.dentistry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HeaderText3Adapter extends RecyclerView.Adapter<HeaderText3Adapter.MyViewHolder> {

    private List<AllergicModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.question);

        }
    }


    public HeaderText3Adapter(List<AllergicModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public HeaderText3Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alergic_item, parent, false);

        return new HeaderText3Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeaderText3Adapter.MyViewHolder holder, int position) {
        AllergicModel movie = moviesList.get(position);
        holder.title.setText(movie.getA_question());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}