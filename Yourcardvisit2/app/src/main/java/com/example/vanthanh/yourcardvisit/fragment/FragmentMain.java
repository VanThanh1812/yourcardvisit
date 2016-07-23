package com.example.vanthanh.yourcardvisit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.controls.Func_fragment;
import com.example.vanthanh.yourcardvisit.model.selectedCard;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class FragmentMain extends android.app.Fragment {
    Button btnAddcard;
    Button btnListcard;
    Button btnCreateCard,btnMyCard;
    EditText edtKey;
    ArrayList<selectedCard> listCard =new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_main,null);
        btnAddcard=(Button)v.findViewById(R.id.btnAddCard);
        btnAddcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Func_fragment.setFragment(getActivity(), StaticValues.TAG_FRAGMENTADDCARD);
            }
        });
        btnListcard=(Button)v.findViewById(R.id.btnViewListCard);
        btnListcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Func_fragment.setFragment(getActivity(),StaticValues.TAG_FRAGMENT_LISTCARD);
                searchCard();
            }
        });
        btnCreateCard=(Button)v.findViewById(R.id.btnCreatCard);
        btnCreateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Func_fragment.setFragment(getActivity(),StaticValues.TAG_FRAGMENT_CREATE);
            }
        });
        btnMyCard=(Button)v.findViewById(R.id.btnMyCard);
        btnMyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Func_fragment.setFragment(getActivity(),StaticValues.TAG_FRAGMENT_MYCARD);
            }
        });
        edtKey=(EditText)v.findViewById(R.id.edtKey);

        return v;
    }

    public void searchCard(){
        listCard.clear();
        Firebase.setAndroidContext(getActivity());
        final Firebase myFirebaseRef = new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA);
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String,Object> map=dataSnapshot.getValue(Map.class);
                if(map.get("card_name").toString().toLowerCase().contains(edtKey.getText().toString().toLowerCase())){
                   listCard.add(new selectedCard(dataSnapshot.getKey().toString(), map.get("card_name").toString()) );
                }
                if(listCard.size()!=0)
                    Log.d("Nguyenvulong: ", ""+listCard.get(0).getName().toString());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}
