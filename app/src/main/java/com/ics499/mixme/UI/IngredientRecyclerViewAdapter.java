package com.ics499.mixme.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.ics499.mixme.R;

import java.util.List;

/**
 * Class used to create and bind views to be used in a RecyclerView which displays a list of
 * ingredient CheckBoxes
 */
public class IngredientRecyclerViewAdapter extends
        RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {

    private List<String> ingreds;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    IngredientRecyclerViewAdapter(Context context, List<String> ingreds){
        this.inflater = LayoutInflater.from(context);
        this.ingreds = ingreds;
    }

    // Inflates the row layout from xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.ingredient_row, parent, false);
        return new ViewHolder(view);
    }

    // Binds each ingredient to a CheckBox
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String ingredient = ingreds.get(position);
        holder.checkBox.setText(ingredient);
    }

    @Override
    public int getItemCount(){
        return ingreds.size();
    }

    // Inner class holds Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CheckBox checkBox;

        ViewHolder(View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.ingredChBox);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id ){
        return ingreds.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
