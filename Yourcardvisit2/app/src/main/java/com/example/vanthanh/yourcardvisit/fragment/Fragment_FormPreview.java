package com.example.vanthanh.yourcardvisit.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.Views.TouchImageView;
import com.example.vanthanh.yourcardvisit.controls.FirebaseData;
import com.example.vanthanh.yourcardvisit.controls.Func_fragment;
import com.example.vanthanh.yourcardvisit.model.Data_Info;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Van Thanh on 7/10/2016.
 */
public class Fragment_FormPreview extends Fragment implements View.OnTouchListener {
    TextView txtHoten,txtCongty,txtDiachi,txtEmail,txtSodienthoai,txtChucvu;
    EditText edtHoten,edtCongty,edtDiachi,edtEmail,edtSodienthoai,edtChucvu;
    private int _xDelta;
    private int _yDelta;
    public static final int RELATIVE_MARGIN_TOP=440;
    public static final int RELATIVE_MARGIN_LEFT=700;
    TouchImageView imgLogo;
    RelativeLayout layout;
    Button btnCreate;
    View v;
    String link_logo=null;
    String link_background=null;

    //doi chu
    private View.OnClickListener changeText=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Func_fragment.setFragment(getActivity(), StaticValues.TAG_FRAGMENT_CHANGETEXT);

        }
    };
    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_createcard,null);
        Firebase.setAndroidContext(getActivity());
        v.setLongClickable(true);
        Connect();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View popup=getActivity().getLayoutInflater().inflate(R.layout.dialog_forminfo,null);
        connectPopup(popup);
        builder.setView(popup);
        builder.setTitle("Điền thông tin tại đây");
        builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txtChucvu.setText(edtChucvu.getText().toString());
                txtHoten.setText(edtHoten.getText().toString());
                txtSodienthoai.setText(edtSodienthoai.getText().toString());
                txtCongty.setText(edtCongty.getText().toString());
                txtEmail.setText(edtEmail.getText().toString());
                txtDiachi.setText(edtEmail.getText().toString());
                Toast.makeText(getActivity(),"Chạm và giữ để chỉnh sửa lại",Toast.LENGTH_LONG);
            }
        });
        builder.show();
        return v;
    }
    View.OnClickListener create=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            creatCard();
        }
    };

    public void creatCard(){
        FragmentMain Data_Info=new FragmentMain();
        ArrayList<com.example.vanthanh.yourcardvisit.model.Data_Info> listCard=Data_Info.searchCard(txtHoten.getText().toString().trim().toLowerCase());
        ArrayList<Integer> integers=new ArrayList<>();

        if(listCard.size()==0){
            integers=randomNumber();
        }
        if(listCard.size()!=0){
            boolean check;
            ArrayList<Integer> integers1=new ArrayList<>();

            for(int i=0;i<listCard.size();i++){
                integers1.add(Integer.parseInt(listCard.get(i).getCard_id()));
            }
            do {
                integers=randomNumber();
                check=checkInterges(integers1,integers);
            }while (check==false);
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Xin moi ban chon ID cho Card: ");
        final ArrayList<Integer> finalIntegers = integers;
        builder.setNegativeButton(txtHoten.getText().toString() + finalIntegers.get(0).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Data_Info data_info=new Data_Info(finalIntegers.get(0).toString(),txtHoten.getText().toString(),txtCongty.getText().toString(),txtSodienthoai.getText().toString(),txtDiachi.getText().toString(),txtChucvu.getText().toString(),txtEmail.getText().toString());
                FirebaseData.create_Info_Card(getActivity(), StaticValues.idfacebook, data_info);
            }
        });
        builder.setNeutralButton(txtHoten.getText().toString() + finalIntegers.get(1).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Data_Info data_info=new Data_Info(finalIntegers.get(1).toString(),txtHoten.getText().toString(),txtCongty.getText().toString(),txtSodienthoai.getText().toString(),txtDiachi.getText().toString(),txtChucvu.getText().toString(),txtEmail.getText().toString());
                FirebaseData.create_Info_Card(getActivity(), StaticValues.idfacebook, data_info);
            }
        });
        builder.setPositiveButton(txtHoten.getText().toString() + finalIntegers.get(2).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Data_Info data_info=new Data_Info(finalIntegers.get(2).toString(),txtHoten.getText().toString(),txtCongty.getText().toString(),txtSodienthoai.getText().toString(),txtDiachi.getText().toString(),txtChucvu.getText().toString(),txtEmail.getText().toString());
                FirebaseData.create_Info_Card(getActivity(), StaticValues.idfacebook, data_info);
            }
        });
        builder.show();


    }

    public ArrayList<Integer> randomNumber(){
        ArrayList<Integer> integers=new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=101; i<999; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<3; i++) {
            integers.add(list.get(i));
        }
        return integers;
    }

    public boolean checkInterges(ArrayList<Integer> a, ArrayList<Integer> b){
        boolean check=true;
        for(int i=0;i<a.size();i++){
            for(int j=0;j<b.size();j++){
                if(a.get(i)==b.get(j)){
                    check=false;
                }
            }
        }
        return check;
    }



    View.OnLongClickListener event=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            final TextView txt=(TextView)view;
            final EditText editText=new EditText(getActivity());
            editText.setText(txt.getText().toString());
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("Chỉnh sửa");
            builder.setView(editText);
            builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    txt.setText(editText.getText().toString());
                }
            });
            builder.show();
            return true;
        }
    };
    //kết nối và đặt các sự kiện
    private void Connect(){
        txtChucvu=(TextView)v.findViewById(R.id.txtChucvu);
        txtCongty=(TextView)v.findViewById(R.id.txtCongty);
        txtDiachi=(TextView)v.findViewById(R.id.txtDiachi);
        txtEmail=(TextView)v.findViewById(R.id.txtEmail);
        txtSodienthoai=(TextView)v.findViewById(R.id.txtSodienthoai);
        txtHoten=(TextView)v.findViewById(R.id.txtHoTen);
        imgLogo=(TouchImageView)v.findViewById(R.id.imgLogo);
        btnCreate=(Button)v.findViewById(R.id.btnCreate);
        Drawable marker = getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark);
        imgLogo.setImageDrawable(marker);
        txtChucvu.setOnLongClickListener(event);
        txtChucvu.setOnClickListener(changeText);
        txtChucvu.setOnTouchListener(this);

        txtHoten.setOnLongClickListener(event);
        txtHoten.setOnClickListener(changeText);
        txtHoten.setOnTouchListener(this);

        txtCongty.setOnLongClickListener(event);
        txtCongty.setOnClickListener(changeText);
        txtCongty.setOnTouchListener(this);

        txtDiachi.setOnLongClickListener(event);
        txtDiachi.setOnClickListener(changeText);
        txtDiachi.setOnTouchListener(this);

        txtSodienthoai.setOnLongClickListener(event);
        txtSodienthoai.setOnClickListener(changeText);
        txtSodienthoai.setOnTouchListener(this);

        txtEmail.setOnLongClickListener(event);
        txtEmail.setOnClickListener(changeText);
        txtEmail.setOnTouchListener(this);

        //click ảnh
        imgLogo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showFileChooser(StaticValues.IMAGE_CONGTY_FILECHOOSE);
                return true;
            }
        });
        imgLogo.setOnTouchListener(this);


        //click nền
        layout=(RelativeLayout)v.findViewById(R.id.yourcard);
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showFileChooser(StaticValues.IMAGE_BACKGROUND_FILECHOOSE);
                return true;
            }
        });

        //click button
        btnCreate.setOnClickListener(create);

    }
    //pop up hiện form điền dữ liệu
    private void connectPopup(View popup){
        edtHoten=(EditText)popup.findViewById(R.id.edtName);
        edtCongty=(EditText)popup.findViewById(R.id.edtCongty);
        edtChucvu=(EditText)popup.findViewById(R.id.edtChucvu);
        edtDiachi=(EditText)popup.findViewById(R.id.edtDiachi);
        edtEmail=(EditText)popup.findViewById(R.id.edtEmail);
        edtSodienthoai=(EditText)popup.findViewById(R.id.edtSodienthoai);
    }
    private void showFileChooser(int NUMBER) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),NUMBER);

        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getActivity(), "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        return super.onContextItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case StaticValues.IMAGE_CONGTY_FILECHOOSE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    FirebaseData.create_Images_Card(getActivity(),StaticValues.TYPE_LOGO,StaticValues.idfacebook,uri);
                    //HienthiView.uri_congty=uri;
                    //url=uri.toString();
                    Log.d("vthanh", "File Uri: " + uri.toString());

                    // Get the path
                    //String path = getPath(this, uri);

                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    Log.i("vt100",picturePath);
                    Bitmap bitmap= BitmapFactory.decodeFile(picturePath);
                    Drawable drawable=new BitmapDrawable(getResources(),bitmap);
                    //txtLinkAva.setText(picturePath);
                    if (bitmap!=null) {
                        imgLogo.setImageBitmap(bitmap);
                    }else Log.i("null","null");
                    //
//                        int index= lastIndexOf(path, '/');
//                        int length=path.length();
//                        String filechoose=substring(path, index, length);
                    //txtLinkAva.setText(filechoose);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
            case StaticValues.IMAGE_BACKGROUND_FILECHOOSE:
                if (resultCode == getActivity().RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    FirebaseData.create_Images_Card(getActivity(),StaticValues.TYPE_BACKGROUND,StaticValues.idfacebook,uri);
                    //HienthiView.uri_background=uri;
                    //url=uri.toString();
                    Log.d("vthanh", "File Uri: " + uri.toString());

                    // Get the path
                    //String path = getPath(this, uri);
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    //
                    //txtLinkBackground.setText(picturePath);
                    Bitmap bitmap= BitmapFactory.decodeFile(picturePath);
                    Drawable drawable=new BitmapDrawable(getResources(),bitmap);
                    //
                    layout.setBackground(drawable);
                    //
//                        int index= lastIndexOf(path, '/');
//                        int length=path.length();
//                        String filechoose=substring(path, index, length);
//                        txtLinkAva.setText(filechoose);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();

                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;

                if (Y-_yDelta<=0){
                    layoutParams.topMargin=0;
                }
                if(Y+(v.getHeight()-_yDelta)>=RELATIVE_MARGIN_TOP){
                    layoutParams.topMargin=RELATIVE_MARGIN_TOP-v.getHeight();
                }
                if(X-_xDelta<=0){
                    layoutParams.leftMargin=0;
                }
                if(X+(v.getWidth()-_xDelta)>=RELATIVE_MARGIN_LEFT){
                    layoutParams.leftMargin=RELATIVE_MARGIN_LEFT-v.getWidth();
                }
//                        Toast.makeText(getActivity(), ""+z, Toast.LENGTH_SHORT).show();
                v.setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}
