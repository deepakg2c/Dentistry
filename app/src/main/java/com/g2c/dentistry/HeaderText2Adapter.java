package com.g2c.dentistry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HeaderText2Adapter extends RecyclerView.Adapter<HeaderText2Adapter.MyViewHolder> {

    private List<WomenModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public RadioButton yes,no;
        public RadioGroup rg;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.question);
            yes=(RadioButton)view.findViewById(R.id.radioButton1);
            no=(RadioButton)view.findViewById(R.id.radioButton2);
            rg=(RadioGroup)view.findViewById(R.id.rg);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    if(yes.isChecked()){
                        no.setChecked(false);
                    }
                    if(no.isChecked()){
                        yes.setChecked(false);
                    }
                }
            });


        }
    }


    public HeaderText2Adapter(List<WomenModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public HeaderText2Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_volunteer_item, parent, false);

        return new HeaderText2Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeaderText2Adapter.MyViewHolder holder, int position) {
        WomenModel movie = moviesList.get(position);
        holder.title.setText(movie.getW_question());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}