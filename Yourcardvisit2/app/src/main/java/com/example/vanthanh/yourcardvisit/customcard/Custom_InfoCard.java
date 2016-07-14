package com.example.vanthanh.yourcardvisit.customcard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.model.Full_Info_Card;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Van Thanh on 7/11/2016.
 */
public class Custom_InfoCard extends ArrayAdapter<Full_Info_Card> {
    public Custom_InfoCard(Context context,int re,ArrayList<Full_Info_Card> full_info_cards ) {
        super(context, re, full_info_cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.yourcard,parent,false);
        }
        Full_Info_Card data_info=getItem(position);
        if (data_info==null) Log.i("abc","nullllll");

        TextView txtHoten,txtCongty,txtDiachi,txtEmail,txtSodienthoai,txtChucvu;
        ImageView imgLogo=(ImageView)convertView.findViewById(R.id.your_imgCongty);
        txtChucvu=(TextView)convertView.findViewById(R.id.your_chucvu);
        txtCongty=(TextView)convertView.findViewById(R.id.your_congty);
        txtDiachi=(TextView)convertView.findViewById(R.id.your_diachi);
        txtEmail=(TextView)convertView.findViewById(R.id.your_email);
        txtSodienthoai=(TextView)convertView.findViewById(R.id.your_sodienthoai);
        txtHoten=(TextView)convertView.findViewById(R.id.your_name);

        if (data_info.getCard_chucvu()==null) Log.i("abc","nullll");
        txtChucvu.setText(data_info.getCard_chucvu());
        txtCongty.setText(data_info.getCard_congty());
        txtDiachi.setText(data_info.getCard_diachi());
        txtEmail.setText(data_info.getCard_email());
        txtSodienthoai.setText(data_info.getCard_sodienthoai());
        txtHoten.setText(data_info.getCard_name());

         Picasso.with(getContext()).load(data_info.getLinkbackground()).resize(356,220).centerCrop().into(imgLogo);
        convertView.setBackground(imgLogo.getDrawable());
        Picasso.with(getContext()).load(data_info.getLinklogo()).resize(60, 60).centerCrop().error(R.drawable.ms9_spot_shadow_z5_x1).into(imgLogo);

        return convertView;
    };

}
