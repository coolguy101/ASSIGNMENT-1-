package com.example1.wocao;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
public class cutadapter extends ArrayAdapter<counter>{
	private ArrayList<counter> entries;
    private Activity activity;
 
    public cutadapter(Activity a, int textViewResourceId, ArrayList<counter> entries) {
        super(a, textViewResourceId, entries);
        this.entries = entries;
        this.activity = a;
    }
    
    public static class ViewHolder{
        public TextView item1;
        public TextView item2;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater vi =
                (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listlayout, null);
            holder = new ViewHolder();
            holder.item1 = (TextView) v.findViewById(R.id.big);
            holder.item2 = (TextView) v.findViewById(R.id.small);
            v.setTag(holder);
        }
        else
            holder=(ViewHolder)v.getTag();
 
        final counter custom = entries.get(position);
        if (custom != null) {
            holder.item1.setText(custom.name1);
            holder.item2.setText(custom.count+"");
        }
        return v;
    }


	}
    
    
 
    


