package com.ics499.mixme.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ics499.mixme.R;

import java.util.List;

public class DrinkRecyclerViewAdapter extends RecyclerView.Adapter<DrinkRecyclerViewAdapter.ViewHolder> {
    private List<String> drinks;
    private List<String> percents;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    DrinkRecyclerViewAdapter(Context context, List<String> drinks){
        this.inflater = LayoutInflater.from(context);
        this.drinks = drinks;
    }

    // Inflates the row layout from xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.drink_row, parent, false);
        return new ViewHolder(view);
    }

    // Binds each drink to a button
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String drink = drinks.get(position);
        holder.button.setText(drink);
        // not sure if needed
        //holder.bind(position);
    }

    @Override
    public int getItemCount(){
        return drinks.size();
    }

    // Inner class holds Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Button button;

        ViewHolder(View itemView){
            super(itemView);
            button = itemView.findViewById(R.id.drinkBtn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id ){
        return drinks.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
