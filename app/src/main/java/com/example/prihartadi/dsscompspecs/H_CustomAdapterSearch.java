package com.example.prihartadi.dsscompspecs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by prihartadi on 24/07/2017.
 */

public class H_CustomAdapterSearch extends BaseAdapter {
    private static List<C_UnItem> item_list;
    private ArrayList<C_UnItem> arraylist;

    private LayoutInflater mInflater;
    Context mContext;

    public H_CustomAdapterSearch(Context context, List<C_UnItem> result){
        mContext = context;
        item_list = result;
        mInflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<C_UnItem>();
        this.arraylist.addAll(item_list);
    }

    public int getCount() {
        return item_list.size();
    }

    public Object getItem(int position) {
        return item_list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        H_CustomAdapterSearch.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_search, null);
            holder = new H_CustomAdapterSearch.ViewHolder();
            holder.txtId = (TextView) convertView.findViewById(R.id.idid);
            holder.txtName = (TextView) convertView.findViewById(R.id.nmnm);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.prpr);

            convertView.setTag(holder);
        } else {
            holder = (H_CustomAdapterSearch.ViewHolder) convertView.getTag();
        }

        holder.txtId.setText(item_list.get(position).getId()+"");
        holder.txtName.setText(item_list.get(position).getName());
        holder.txtPrice.setText(item_list.get(position).getPrice()+"");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Part Selection "+item_list.get(position).getName(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext, A_Result.class);
                Intent intent = new Intent();
                intent.putExtra("id",(item_list.get(position).getId()));
                intent.putExtra("name",(item_list.get(position).getName()));
                intent.putExtra("price",(item_list.get(position).getPrice()));
                ((Activity)mContext).setResult(1,intent);
//                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtPrice;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        item_list.clear();
        if (charText.length() == 0) {
            item_list.addAll(arraylist);
        }
        else
        {
            for (C_UnItem i : arraylist)
            {
                if (i.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    item_list.add(i);
                }
            }
        }
        notifyDataSetChanged();
    }
}
