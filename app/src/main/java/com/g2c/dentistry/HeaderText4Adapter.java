package com.g2c.dentistry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class HeaderText4Adapter extends RecyclerView.Adapter<HeaderText4Adapter.MyViewHolder> {

    private List<DoYouHaveModel> moviesList;

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


    public HeaderText4Adapter(List<DoYouHaveModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public HeaderText4Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doyouhave_item_list, parent, false);

        return new HeaderText4Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeaderText4Adapter.MyViewHolder holder, int position) {
        DoYouHaveModel movie = moviesList.get(position);
        holder.title.setText(movie.getD_question());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}