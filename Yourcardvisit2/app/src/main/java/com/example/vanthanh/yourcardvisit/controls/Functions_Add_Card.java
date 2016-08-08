package com.example.vanthanh.yourcardvisit.controls;

import android.app.Activity;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by Van Thanh on 8/3/2016.
 */
public class Functions_Add_Card {
    public static void createRequest(final Activity activity,String from_card,String to_card){
        Firebase.setAndroidContext(activity);
        Firebase root=new Firebase(StaticValues.LINKROOT);
        Firebase child = root.child(StaticValues.DATA_REQUEST).child(to_card);
        //set values : id - id
        child.child(from_card).setValue(from_card, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Toast.makeText(activity, "Đã gửi yêu cầu thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
