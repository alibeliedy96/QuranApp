package com.example.quranapp.Adapters;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.API.Model.RadioResponse.RadiosItem;
import com.example.quranapp.API.Model.RecitersResponse.RecitersItem;
import com.example.quranapp.R;

import java.util.List;


public class RecitersAdapter extends RecyclerView.Adapter<RecitersAdapter.ViewHolder> {

    List<RecitersItem> recitersItems;
    RecitersAdapter.OnItemClickListener onPlayClickListener;
    RecitersAdapter.OnItemClickListener onStopClickListener;

    public RecitersAdapter(List<RecitersItem> recitersItems) {
        this.recitersItems = recitersItems;
    }

    public void setOnPlayClickListener(OnItemClickListener onPlayClickListener) {
        this.onPlayClickListener = onPlayClickListener;
    }

    public void setOnStopClickListener(OnItemClickListener onStopClickListener) {
        this.onStopClickListener = onStopClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_reciters,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
       RecitersItem reciters=recitersItems.get(position);
        viewHolder.name.setText(reciters.getName());
        viewHolder.rewaya.setText(reciters.getRewaya());
        if(onPlayClickListener!=null){
            viewHolder.play
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onPlayClickListener.onItemClick(position,reciters);
                        }
                    });
        }
        if(onStopClickListener!=null){
            viewHolder.stop
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onStopClickListener.onItemClick(position,reciters);
                        }
                    });
        }

    }
    //using this method if data changed
    public void changeData(List<RecitersItem> items){
        this.recitersItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(recitersItems==null)return 0;
        return recitersItems.size();
    }
    //using this method to get item position
    public RecitersItem getItemAtPosition(int  pos){
       return recitersItems.get(pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView rewaya;
        ImageView play;
        ImageView stop;


        public ViewHolder(View view){
            super(view);

            name=view.findViewById(R.id.reciters_name);
            rewaya=view.findViewById(R.id.rewaya);
            play=view.findViewById(R.id.playy);
            stop=view.findViewById(R.id.stopp);




        }
    }
    public interface OnItemClickListener{
        void onItemClick(int pos, RecitersItem reciters);
    }


}
