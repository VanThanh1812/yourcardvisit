package com.example.vanthanh.yourcardvisit.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.controls.FirebaseData;
import com.example.vanthanh.yourcardvisit.customcard.Custom_Get_Image_Card;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class Fragment_ListCard extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_listcard,null);
        Firebase.setAndroidContext(getActivity());
        ArrayList<String> list_img=new ArrayList<>();
        Custom_Get_Image_Card image_card=new Custom_Get_Image_Card(getActivity(),android.R.layout.simple_list_item_1,list_img);
        GridView gridView=(GridView)v.findViewById(R.id.gridViewListCard);
        gridView.setAdapter(image_card);
        FirebaseData.get_List_Card_Friends(StaticValues.idfacebook, list_img, image_card);
        return v;
    }
    private void onGridViewItemClick(GridView gridView){

    }
}
