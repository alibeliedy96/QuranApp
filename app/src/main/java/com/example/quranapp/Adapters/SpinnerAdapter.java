package com.example.quranapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quranapp.R;

import java.util.ArrayList;


public class SpinnerAdapter extends BaseAdapter {

    ArrayList<String> suraNames;
   public SpinnerAdapter(ArrayList<String> names){
        suraNames=names;
    }

    @Override
    public int getCount() {
        return suraNames.size();
    }

    @Override
    public Object getItem(int position) {
        return suraNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
       ViewHolder viewHolder =null;
       if(convertView==null){
           convertView = LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.sura_item,parent,false);
           viewHolder = new ViewHolder();
           viewHolder.name = convertView.findViewById(R.id.name);
           convertView.setTag(viewHolder);

       }else {
           viewHolder = (ViewHolder) convertView.getTag();
       }
        String suraname = suraNames.get(position);
        viewHolder.name.setText(suraname);
        return convertView;
    }

    class ViewHolder{
       TextView name;
    }

}
