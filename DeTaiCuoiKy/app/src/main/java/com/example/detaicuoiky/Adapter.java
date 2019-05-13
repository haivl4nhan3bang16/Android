package com.example.detaicuoiky;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    List<Model> models;
    int mResource;
    Context mContext;
    public Adapter(Context context,int resource, List<Model> models){
        this.mContext = context;
        this.mResource = resource;
        this.models = models;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, final int position) {
        final Model model = models.get(position);
        viewHolder.id_product.setText(model.getProductID());
        viewHolder.name_product.setText(model.getProductName());
        viewHolder.price_product.setText(model.getPrice() + " Đồng");
        viewHolder.origin_product.setText(model.getOrigin());
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailProduct.class);
                i.putExtra("id_product", model.getProductID());
                i.putExtra("name_product", model.getProductName());
                i.putExtra("price_product", model.getPrice());
                i.putExtra("id_origin", model.getOrigin());
                mContext.startActivity(i);
            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_product;
        TextView name_product;
        TextView price_product;
        TextView origin_product;
        LinearLayout linearLayout;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.place_delete);
            this.id_product = itemView.findViewById(R.id.txt_idproduct);
            this.name_product = itemView.findViewById(R.id.txt_nameproduct);
            this.price_product = itemView.findViewById(R.id.txt_price);
            this.origin_product = itemView.findViewById(R.id.txt_xuatxu);
            this.image = itemView.findViewById(R.id.img_product);

        }


    }
}
