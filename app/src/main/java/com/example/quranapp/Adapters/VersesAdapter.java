package com.example.quranapp.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.R;

import java.util.ArrayList;


public class VersesAdapter extends RecyclerView.Adapter<VersesAdapter.ViewHolder> {

    ArrayList<String> content;
    OnItemClickListener onItemClickListener;

    public VersesAdapter(ArrayList<String> content) {
        this.content = content;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item_verse,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.name.setText(content.get(position));
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(View view){
            super(view);
            name=view.findViewById(R.id.name);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos, String name);
    }
}
