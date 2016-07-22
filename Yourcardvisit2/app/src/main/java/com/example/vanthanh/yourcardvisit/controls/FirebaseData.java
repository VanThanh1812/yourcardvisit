package com.example.vanthanh.yourcardvisit.controls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.customcard.Custom_Get_Image_Card;
import com.example.vanthanh.yourcardvisit.customcard.Custom_InfoCard;
import com.example.vanthanh.yourcardvisit.model.Data_Info;
import com.example.vanthanh.yourcardvisit.model.Full_Info_Card;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Van Thanh on 7/11/2016.
 */
public class FirebaseData {
    public static void save_Image_Card(final View view, final Activity activity){
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap=view.getDrawingCache();
        if(bitmap==null) {
            Log.i("90","null rooif");
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();


        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference reference=storage.getReferenceFromUrl(StaticValues.LINKSTORAGE + StaticValues.idfacebook + "/card.jpg");

        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Firebase.setAndroidContext(activity);
                Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_IMAGE+StaticValues.idfacebook);
                root.child("card").setValue(taskSnapshot.getDownloadUrl().toString());
                Log.i("777","oke roi day");
            }
        });
    }
    public static void create_Info_Card(Activity activity,String idFacebook,Data_Info data_info){
        //TODO:lưu thông tin len csdl
        Firebase.setAndroidContext(activity);
        Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA+idFacebook);
        Log.d("abc", idFacebook);
        root.setValue(data_info);
        Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();

        //TODO:lưu link ảnh len csdl

    }
    public static void create_Images_Card(final Activity activity,final String type_image, final String idFacebook,Uri uri){
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference reference=storage.getReferenceFromUrl(StaticValues.LINKSTORAGE + idFacebook + "/");
        StorageReference ref_logo=reference.child(type_image+"/"+uri.getLastPathSegment());
        Log.d(StaticValues.TAG, uri.getLastPathSegment().toString());
        UploadTask uploadTask_logo=ref_logo.putFile(uri);
        uploadTask_logo.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Firebase.setAndroidContext(activity);
                Firebase root = new Firebase(StaticValues.LINKROOT + StaticValues.CHILD_IMAGE + idFacebook);
                root.child(type_image).setValue(taskSnapshot.getDownloadUrl().toString());
            }
        });
    }

    public static void get_ImageCard(final String idFacebook, final ArrayList<String> list, final Custom_Get_Image_Card imgCard,GridView gridView){
        Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_IMAGE+idFacebook);
        root.child("card").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("vttttt", dataSnapshot.toString());
                if (list.size()!=0) list.remove(0);
                list.add(dataSnapshot.getValue().toString());
                imgCard.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
    public static void get_Card_From_Firebase(final String idFacebook, final ArrayList<Full_Info_Card> list, final Custom_InfoCard custom_infoCard,GridView gridView){
        Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA+idFacebook);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("vanthanhabc",dataSnapshot.toString());

                Map<String,Object> map=dataSnapshot.getValue(Map.class);
                final Full_Info_Card full=new Full_Info_Card();
                full.setCard_chucvu(map.get("card_chucvu").toString());
                full.setCard_congty(map.get("card_congty").toString());
                full.setCard_diachi(map.get("card_diachi").toString());
                full.setCard_email(map.get("card_email").toString());
                full.setCard_name(map.get("card_name").toString());
                full.setCard_id(map.get("card_id").toString());
                full.setCard_sodienthoai(map.get("card_sodienthoai").toString());

                Firebase root2=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_IMAGE+idFacebook);
                root2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("vânthanhabc",dataSnapshot.toString());
                        Map<String,Object> map=dataSnapshot.getValue(Map.class);
                        full.setLinkbackground(map.get("background").toString());
                        full.setLinklogo(map.get("logo").toString());
                        list.add(full);
                        Log.i("vanthanhabc",full.getLinkbackground()+"   "+full.getLinklogo());
                        custom_infoCard.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}
