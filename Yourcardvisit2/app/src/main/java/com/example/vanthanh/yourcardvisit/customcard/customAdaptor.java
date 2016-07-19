package com.example.vanthanh.yourcardvisit.customcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.model.textDisplay;

import java.util.ArrayList;

/**
 * Created by DELL on 7/17/2016.
 */
public class customAdaptor extends ArrayAdapter<textDisplay>{
    public customAdaptor(Context context, ArrayList<textDisplay> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.customspinner, parent, false);
        }
        TextView txt1=(TextView)convertView.findViewById(R.id.custom_textView);

        textDisplay textDisplay=getItem(position);
        txt1.setText(textDisplay.getText1().toString());


        return convertView;
    }
}

