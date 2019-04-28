package com.example.thigiuakyasynctask;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.ViewHolder> {
    List<ModelPhone> models;
    int mResource;
    Context mContext;
    public MyViewAdapter(Context context,int resource, List<ModelPhone> objects){
        this.mContext = context;
        this.mResource = resource;
        this.models = objects;
    }
    @NonNull
    @Override
    public MyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter.ViewHolder viewHolder, int i) {
        final ModelPhone model = models.get(i);
        viewHolder.txtName.setText(model.getProductName());
        viewHolder.txtPrice.setText(String.valueOf(model.getPrice()));
        viewHolder.txtDescrip.setText(model.getDescription());
        viewHolder.txtProducer.setText(model.getProducer());
        final int position = i;

        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, PhoneDetail.class);
                i.putExtra("phone", models.get(position));
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtPrice;
        TextView txtDescrip;
        TextView txtProducer;
        Button btnDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtName = itemView.findViewById(R.id.txtNameProduct);
            this.txtPrice = itemView.findViewById(R.id.txtPrice);
            this.txtDescrip = itemView.findViewById(R.id.txtDescription);
            this.txtProducer = itemView.findViewById(R.id.txtProducer);
            this.btnDetail = itemView.findViewById(R.id.btndetail);
        }


    }
}
