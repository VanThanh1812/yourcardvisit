package com.example.vanthanh.yourcardvisit.customcard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Van Thanh on 7/21/2016.
 */
public class Custom_Get_Image_Card extends ArrayAdapter<String> {
    public Custom_Get_Image_Card(Context context, int resource,ArrayList<String> adapter) {
        super(context, resource, adapter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_image_card ,parent,false);
        }
        //convertView= LayoutInflater.from(getContext()).inflate(R.layout.yourcard,null);
        final String url=getItem(position);
        final String arr[]=url.split("vs");
        ImageView img=(ImageView)convertView.findViewById(R.id.imageView);
        StaticValues.PROGRESS_DIALOG=new ProgressDialog(convertView.getContext());
        StaticValues.PROGRESS_DIALOG.show();
        Picasso.with(getContext()).load(arr[0]).placeholder(R.mipmap.ic_launcher).into(img, new Callback() {
            @Override
            public void onSuccess() {
                if (StaticValues.PROGRESS_DIALOG.isShowing()) StaticValues.PROGRESS_DIALOG.dismiss();
            }

            @Override
            public void onError() {
                if (StaticValues.PROGRESS_DIALOG.isShowing()) StaticValues.PROGRESS_DIALOG.dismiss();
                Toast.makeText(getContext(),"Fail loading",Toast.LENGTH_SHORT).show();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("id", arr[1].toString());
                Log.i("linkimage", arr[0].toString());
                Log.i("all", url.toString());

                AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
                aBuilder.setTitle("Select");
                View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_contact, null);
                Button btn_phone = (Button) v.findViewById(R.id.btn_phone);
                Button btn_sendemail = (Button) v.findViewById(R.id.btn_send_email);
                Button btn_sendmessage = (Button) v.findViewById(R.id.btn_send_message);
                Button btn_chat = (Button) v.findViewById(R.id.btn_chat);
                aBuilder.setView(v);
                select(btn_phone, arr[1].toString());
                select(btn_chat, arr[1].toString());
                select(btn_sendemail, arr[1].toString());
                select(btn_sendmessage, arr[1].toString());

                aBuilder.show();
            }
        });
        return convertView;
    }
    private void select(final Button btn, String idfacebook){
        Firebase root=new Firebase(StaticValues.LINKROOT+StaticValues.CHILD_DATA+idfacebook);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.i("sdt", dataSnapshot.getValue().toString());
                final Map<String,Object> map=dataSnapshot.getValue(Map.class);
                switch (btn.getId()) {
                    case R.id.btn_phone:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (map.get("card_sodienthoai")!=null)
                                Toast.makeText(getContext(),"Gọi đến "+map.get("card_sodienthoai").toString(),Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getContext(), "Không có số", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.id.btn_chat:

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getContext(), "Chưa có gì", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.id.btn_send_email:

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (map.get("card_email")!=null)
                                    Toast.makeText(getContext(),"Gửi email "+map.get("card_email").toString(),Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getContext(), "Không có email", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    default:
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (map.get("card_sodienthoai")!=null)
                                    Toast.makeText(getContext(),"Gửi tin nhắn "+map.get("card_sodienthoai").toString(),Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getContext(), "Không có số", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
