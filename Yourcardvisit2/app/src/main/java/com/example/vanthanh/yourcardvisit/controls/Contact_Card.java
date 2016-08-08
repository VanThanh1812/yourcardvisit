package com.example.vanthanh.yourcardvisit.controls;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.fragment.Fragment_AddCard;
import com.example.vanthanh.yourcardvisit.model.Data_Info;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Van Thanh on 7/23/2016.
 */
public class Contact_Card {
    public static void contact_Card_Select(){

    }
    public static ArrayList<Data_Info> searchCardtoCreate(final String nameCard){
        final ArrayList<Data_Info> listCard=new ArrayList<>();
        final Firebase myFirebaseRef = new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA);
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.i("77777", dataSnapshot.toString());
                Map<String, Object> map = dataSnapshot.getValue(Map.class);
                if (map.get("card_id").toString().contains(nameCard)) {
                    Data_Info data_info = new Data_Info(map.get("card_id").toString(), map.get("card_name").toString(), map.get("card_congty").toString(),
                            map.get("card_sodienthoai").toString(), map.get("card_diachi").toString(),
                            map.get("card_chucvu").toString(), map.get("card_email").toString());
                    listCard.add(data_info);

                }
                if (listCard.size() != 0)
                    Log.d("Nguyenvulong: ", "" + listCard.get(0).getCard_name().toString());

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
        return listCard;
    }

    public static void searchCard(final Activity activity,String tag_fragment,final String nameCard, final ArrayList<Data_Info> listCard){

        StaticValues.PROGRESS_DIALOG.show();
        final Firebase myFirebaseRef = new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA);
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.i("77777",dataSnapshot.toString());
                Map<String,Object> map=dataSnapshot.getValue(Map.class);
                if((map.get("card_id").toString().equals(nameCard)) && ("a"=="a")){
                    Data_Info data_info=new Data_Info(map.get("card_id").toString(), map.get("card_name").toString(), map.get("card_congty").toString(),
                            map.get("card_sodienthoai").toString(), map.get("card_diachi").toString(),
                            map.get("card_chucvu").toString(), map.get("card_email").toString());
                    listCard.add(data_info);
                    FragmentManager fragmentManager=activity.getFragmentManager() ;
                    android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    Fragment_AddCard addCard=new Fragment_AddCard();
                    Bundle bundle=new Bundle();
                    bundle.putString("add_keyID",dataSnapshot.getKey());
                    bundle.putString("add_name",map.get("card_name").toString());
                    bundle.putString("add_congty",map.get("card_congty").toString());
                    bundle.putString("add_chucvu",map.get("card_chucvu").toString());
                    addCard.setArguments(bundle);
                    fragmentTransaction.replace(R.id.framelayoutmain,addCard,StaticValues.TAG_FRAGMENTADDCARD).addToBackStack(null);
                    Log.d("vanthanh1812",String.valueOf(listCard.size()));
                    fragmentTransaction.commit();
                }
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
