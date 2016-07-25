package com.example.vanthanh.yourcardvisit.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.controls.FirebaseData;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.Firebase;

/**
 * Created by Van Thanh on 7/12/2016.
 */
public class Fragment_Mycard extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_mycard,null);
        Firebase.setAndroidContext(getActivity());
        ImageView imgView=(ImageView)v.findViewById(R.id.gridView2);
        StaticValues.PROGRESS_DIALOG=new ProgressDialog(getActivity());
        StaticValues.PROGRESS_DIALOG.show();
        FirebaseData.get_MyCard(getActivity(), StaticValues.idfacebook, imgView);
        return v;
    }
}
