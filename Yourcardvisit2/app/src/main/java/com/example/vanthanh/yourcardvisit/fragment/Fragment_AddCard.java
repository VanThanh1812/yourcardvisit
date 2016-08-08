package com.example.vanthanh.yourcardvisit.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.controls.Functions_Add_Card;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Map;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class Fragment_AddCard extends Fragment {
    ImageView imgPreview;
    TextView txt_add_key,txt_add_name,txt_add_congty,txt_add_chucvu;
    Button btnRequest;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_addcard,null);
        findID(v);
        setValuePreview();
        return v;
    }
    private void findID(View v){
        txt_add_key=(TextView)v.findViewById(R.id.txt_add_Key);
//        txt_add_chucvu=(TextView)v.findViewById(R.id.txt_add_chucvu);
//        txt_add_congty=(TextView)v.findViewById(R.id.txt_add_congty);
//        txt_add_name=(TextView)v.findViewById(R.id.txt_add_name);
        imgPreview = (ImageView) v.findViewById(R.id.card_preview);
        btnRequest = (Button) v.findViewById(R.id.btnAddtoList);

    }
    private void setValuePreview(){
        final Bundle b=getArguments();
        txt_add_key.setText(b.getString("add_keyID"));
        getLogo(txt_add_key.getText().toString());

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functions_Add_Card.createRequest(getActivity(), StaticValues.idfacebook, b.getString("add_keyID").toString() );
            }
        });
//        txt_add_name.setText(b.getString("add_name"));
//        txt_add_chucvu.setText(b.getString("add_chucvu"));
//        txt_add_congty.setText(b.getString("add_congty"));
    }
    private void getLogo(String key){
        Firebase.setAndroidContext(getActivity());
        // đang tính ko cần request nữa , cho n hiện luôn cho lành
        Firebase root= new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_IMAGE+ key );
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("thanh1111",dataSnapshot.toString());
                Map<String,Object> map=dataSnapshot.getValue(Map.class);
                Picasso.with(getActivity()).load(map.get("card_preview").toString()).placeholder(android.R.drawable.ic_dialog_info).into(imgPreview, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (StaticValues.PROGRESS_DIALOG.isShowing()) StaticValues.PROGRESS_DIALOG.dismiss();
                    }

                    @Override
                    public void onError() {

                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
