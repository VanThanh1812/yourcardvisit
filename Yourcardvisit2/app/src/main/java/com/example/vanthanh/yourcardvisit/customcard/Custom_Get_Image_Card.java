package com.example.vanthanh.yourcardvisit.customcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.vanthanh.yourcardvisit.R;

import java.util.ArrayList;

/**
 * Created by Van Thanh on 7/21/2016.
 */
public class Custom_Get_Image_Card extends ArrayAdapter<ImageView> {
    public Custom_Get_Image_Card(Context context, int resource,ArrayList<ImageView> adapter) {
        super(context, resource, adapter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.yourcard,parent,false);
        }
        return convertView;
    }
}
