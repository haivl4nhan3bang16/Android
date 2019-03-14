package com.example.ngoquocduy_1706020017_11th1c;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<OOPPHONE> {
    private Context mContext;
    private int mResources;
    private List<OOPPHONE> mList;
    public ContactAdapter( Context context, int resource, List<OOPPHONE> listphone) {
        super(context, resource, listphone);
        this.mContext = context;
        this.mResources = resource;
        this.mList = listphone;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;

        if (viewHolder == null){
            convertView = LayoutInflater.from(mContext).inflate(mResources,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtNamePhone = convertView.findViewById(R.id.txtNamePhone);
            viewHolder.txtMaDT = convertView.findViewById(R.id.txtMaDT);
            viewHolder.txtPrice = convertView.findViewById(R.id.PricePhone);
            viewHolder.btnInfo = convertView.findViewById(R.id.btnInfo);

        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final OOPPHONE oopphone = mList.get(position);
        viewHolder.txtNamePhone.setText(String.valueOf(oopphone.getNameSubject()));
        viewHolder.txtMaDT.setText(String.valueOf(oopphone.getMaDT()));
        viewHolder.txtPrice.setText(String.valueOf(oopphone.getPricePhone()));
        viewHolder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, InformationContact.class);
                i.putExtra("name", oopphone.getNameSubject());
                i.putExtra("ma", oopphone.getMaDT());
                i.putExtra("price", oopphone.getPricePhone());
                i.putExtra("descrip", oopphone.getDescription());
                mContext.startActivity(i);
            }
        });

        return convertView;
    }
    public class ViewHolder{
        TextView txtNamePhone;
        TextView txtMaDT;
        TextView txtPrice;
        Button btnInfo;

    }
}
