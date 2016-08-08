package com.example.vanthanh.yourcardvisit.controls;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.customcard.Custom_Get_Image_Card;
import com.example.vanthanh.yourcardvisit.customcard.Custom_InfoCard;
import com.example.vanthanh.yourcardvisit.model.Data_Info;
import com.example.vanthanh.yourcardvisit.model.Full_Info_Card;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Van Thanh on 7/11/2016.
 */
public class FirebaseData {
    public static void save_Image_Card(final View view, final Activity activity){
        //TODO: chụp lại card đã chỉnh sửa các thuộc tính
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap=view.getDrawingCache();
        if(bitmap==null) {
            Log.i("90","null rooif");
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StaticValues.PROGRESS_DIALOG=new ProgressDialog(activity);
        StaticValues.PROGRESS_DIALOG.setTitle("Đang tạo card");
        StaticValues.PROGRESS_DIALOG.show();


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

                Firebase.setAndroidContext(activity);
                Firebase root = new Firebase(StaticValues.LINKROOT + StaticValues.CHILD_IMAGE + StaticValues.idfacebook);
                root.child("card").setValue(taskSnapshot.getDownloadUrl().toString(), new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        Toast.makeText(activity, "Tạo card thành công", Toast.LENGTH_SHORT).show();
                        if (StaticValues.PROGRESS_DIALOG.isShowing()) StaticValues.PROGRESS_DIALOG.dismiss();
                    }
                });
            }
        });
    }
    public static void create_Info_Card(final Activity activity,String idFacebook,Data_Info data_info){
        //TODO:lưu thông tin len csdl
        Firebase.setAndroidContext(activity);
        Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA+idFacebook);
//        Log.d("abc", idFacebook);
        root.setValue(data_info, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {

            }
        });

    }
    public static void create_Images_Card(final Activity activity,final String type_image, final String idFacebook,Uri uri){
        //TODO: lưu ảnh logo và background
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
    public static void get_List_Card_Friends(final Activity activity,final String idFacebook, final ArrayList<String> list, final Custom_Get_Image_Card imgCard){
        StaticValues.PROGRESS_DIALOG=new ProgressDialog(activity);
        StaticValues.PROGRESS_DIALOG.show();
        Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA+idFacebook);
        root.child("listcard/").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.equals(null)) {
                    if (StaticValues.PROGRESS_DIALOG.isShowing())
                        StaticValues.PROGRESS_DIALOG.dismiss();
                    Toast.makeText(activity, "Fail loading...", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("abbaa", dataSnapshot.toString());
                final Firebase root = new Firebase(StaticValues.LINKROOT + StaticValues.CHILD_IMAGE + dataSnapshot.getValue().toString());
                root.child("card").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        Log.i("vttttt", data.toString());
                        if (data.equals(null)) {
                            list.add("vs" + dataSnapshot.getValue().toString());
                        } else {
                            if (StaticValues.PROGRESS_DIALOG.isShowing())
                                StaticValues.PROGRESS_DIALOG.dismiss();
                            list.add(data.getValue().toString() + "vs" + dataSnapshot.getValue().toString());
                            imgCard.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(activity, "Fail loading", Toast.LENGTH_SHORT).show();
                    }
                });
                //get_ImageCard(dataSnapshot.getValue().toString(),list,imgCard,null);
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
    public static void get_MyCard (final Activity activity,final String idFacebook, final ImageView img){
        Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_IMAGE+idFacebook);
        root.child("card").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("vttttt", dataSnapshot.toString());
                Picasso.with(activity).load(dataSnapshot.getValue().toString()).placeholder(android.R.drawable.ic_dialog_info).into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (StaticValues.PROGRESS_DIALOG.isShowing())
                            StaticValues.PROGRESS_DIALOG.dismiss();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(activity, "Fail loading", Toast.LENGTH_SHORT).show();
                    }
                });
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

    public static ArrayList<Integer> getIDall(){

        final ArrayList<Integer> integers=new ArrayList<>();
        final Firebase myFirebaseRef = new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_KEYID);
        myFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Integer integer=Integer.parseInt(dataSnapshot.getValue().toString());
                integers.add(integer);
                Log.d("NguyenLong", ""+integers.size());
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
        return integers;
    }

}
