package com.example.prihartadi.dsscompspecs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by prihartadi on 22/07/2017.
 */

public class H_CustomAdapter extends BaseAdapter {

    private  List<C_Item> item_list;

    private LayoutInflater mInflater;
    Context mContext;

    public H_CustomAdapter(Context context, List<C_Item> result){
        mContext = context;
        item_list = result;
        mInflater = LayoutInflater.from(context);
    }

    public void updateListCustomAdapter(List<C_Item> list){
//        item_list.clear();
//        item_list.addAll(list);
        item_list = list;
        this.notifyDataSetChanged();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.txtPart = (TextView) convertView.findViewById(R.id.part);
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtDetail = (TextView) convertView.findViewById(R.id.detail);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.price);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtPart.setText(item_list.get(position).getPart());
        holder.txtName.setText(item_list.get(position).getName());
        holder.txtDetail.setText(item_list.get(position).getDetail());
        holder.txtPrice.setText(item_list.get(position).getPrice());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, "Test Content "+item_list.get(position).getName(), Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("https://www.google.com/search?tbm=isch&q="+item_list.get(position).getName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtPart;
        TextView txtName;
        TextView txtDetail;
        TextView txtPrice;
    }

}
