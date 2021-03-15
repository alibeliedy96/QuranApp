package com.example.quranapp.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.R;


public class QuranSurasAdapter extends RecyclerView.Adapter<QuranSurasAdapter.ViewHolder> {

    String[]  names ;
    OnItemClickListener onItemClickListener;

    public QuranSurasAdapter(String[] names) {
        this.names = names;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item_sura,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.name.setText(names[position]);
        if(onItemClickListener!=null){
            viewHolder.itemView
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(position,names[position]);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
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
