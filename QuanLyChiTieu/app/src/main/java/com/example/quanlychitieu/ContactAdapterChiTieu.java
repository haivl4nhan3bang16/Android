package com.example.quanlychitieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapterChiTieu extends ArrayAdapter<ModelChiTieu> {
    private Context mContext;
    private int mResources;
    private List<ModelChiTieu> mList;
    public ContactAdapterChiTieu( Context context, int resource, List<ModelChiTieu> listphone) {
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
            viewHolder.txtSoTien = convertView.findViewById(R.id.txtSoTien);
            viewHolder.txtLoaiTien = convertView.findViewById(R.id.txtLoaiTien);
            viewHolder.txtDiaDiem = convertView.findViewById(R.id.txtDiaDiemTieu);
            viewHolder.txtThoiGian = convertView.findViewById(R.id.txtThoiGianTieu);
            viewHolder.txtLyDo = convertView.findViewById(R.id.txtLyDoChi);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final ModelChiTieu modelChiTieu = mList.get(position);
        viewHolder.txtSoTien.setText(String.valueOf(modelChiTieu.getSoTien()));
        viewHolder.txtLoaiTien.setText(String.valueOf(modelChiTieu.getLoaiTien()));
        viewHolder.txtDiaDiem.setText(String.valueOf(modelChiTieu.getDiaDiemChi()));
        viewHolder.txtThoiGian.setText(String.valueOf(modelChiTieu.getThoiGianChi()));
        viewHolder.txtLyDo.setText(String.valueOf(modelChiTieu.getLyDoChi()));

        return convertView;
    }
    public class ViewHolder{
        TextView txtSoTien;
        TextView txtLoaiTien;
        TextView txtDiaDiem;
        TextView txtThoiGian;
        TextView txtLyDo;



    }
}
