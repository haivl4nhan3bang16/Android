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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        return new ViewHolder(LayoutInflater.from(mContext).inflate(mResource,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.ViewHolder viewHolder, final int position) {
        viewHolder.txt_subcode.setText(models.get(position).getSubject_code());
        viewHolder.txt_subname.setText(models.get(position).getSubject_name());
        viewHolder.txt_credits.setText(String.valueOf(models.get(position).getCredits()));
        viewHolder.txt_descrip.setText(models.get(position).getDescription());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailProduct.class);
                i.putExtra("code", models.get(position).getSubject_code());
                i.putExtra("name", models.get(position).getSubject_name());
                i.putExtra("credits", String.valueOf(models.get(position).getCredits()));
                i.putExtra("description", models.get(position).getDescription());
                mContext.startActivity(i);
            }
        });
        viewHolder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase;
                mDatabase = FirebaseDatabase.getInstance().getReference("AdvancedAndroidFinalTest");
                mDatabase.child(viewHolder.txt_subcode.getText().toString()).removeValue() ;
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView txt_subcode;
        TextView txt_subname;
        TextView txt_credits;
        TextView txt_descrip;
        Button btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.place_delete);
            txt_subcode = itemView.findViewById(R.id.txt_subcode);
            txt_subname = itemView.findViewById(R.id.txt_subname);
            txt_credits = itemView.findViewById(R.id.txt_credit);
            txt_descrip = itemView.findViewById(R.id.txt_descrip);
            btnDel = itemView.findViewById(R.id.btnDel);


        }


    }
}
