package com.example.quanlychitieu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CustumDialog extends DialogFragment {
    EditText edtTienChi, edtLoaiChi, edtDiaDiem, edtThoiGian, edtLydo;
    EditText edtadd_sotien;
    EditText spinner_add_loaitien;
    EditText edtadd_diadiem;
    EditText edtadd_thoigian;
    EditText edtadd_lydo;
    String mSoTien;
    String mLoaiTien;
    String mDiaDiem;
    String mThoiGian;
    String mLyDo;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.activity_add_new_khoan_chi_tieu, null);
        builder.setView(customLayout);
        builder.setTitle("Đăng nhập");

        edtTienChi = customLayout.findViewById(R.id.edtSoTienChi);
        edtLoaiChi = customLayout.findViewById(R.id.edtLoaiChiTieu);
        edtDiaDiem = customLayout.findViewById(R.id.edtDiaDiemChi);
        edtThoiGian = customLayout.findViewById(R.id.edtThoiGianChi);
        edtLydo = customLayout.findViewById(R.id.edtLyDoChi);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    private boolean onValidateForm()
    {
        mSoTien = edtadd_sotien.getText().toString();
        mLoaiTien = spinner_add_loaitien.getText().toString();
        mDiaDiem = edtadd_diadiem.getText().toString();
        mThoiGian = edtadd_thoigian.getText().toString();
        mLyDo = edtadd_lydo.getText().toString();
        if(mSoTien.trim().equals(""))
        {
            edtadd_sotien.setError("Can be null");
            return false;
        }
        if(mDiaDiem.trim().equals(""))
        {
            edtadd_diadiem.setError("Can be null");
            return false;
        }
        if(mThoiGian.trim().equals(""))
        {
            edtadd_thoigian.setError("Can be null");
            return false;
        }
        if(mLyDo.trim().equals(""))
        {
            edtadd_lydo.setError("Can be null");
            return false;
        }
        return true;
    }
}
