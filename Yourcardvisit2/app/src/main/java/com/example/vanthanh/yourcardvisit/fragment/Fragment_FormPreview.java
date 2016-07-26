package com.example.vanthanh.yourcardvisit.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.Views.TouchImageView;
import com.example.vanthanh.yourcardvisit.controls.FirebaseData;
import com.example.vanthanh.yourcardvisit.controls.Func_fragment;
import com.example.vanthanh.yourcardvisit.customcard.custom_spinnerAdapter;
import com.example.vanthanh.yourcardvisit.model.Data_Info;
import com.example.vanthanh.yourcardvisit.staticvalues.StaticValues;

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
    private GestureDetector gestureDetector;
    //doi chu
    private View.OnClickListener changeText=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final View v=view;
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            View v1=getActivity().getLayoutInflater().inflate(R.layout.activity_change_text,null );
            builder.setView(v1);

            final TextView colorunderA;
            final TextView txt_test;
            Button changeColor;
            String arr[]={"14","8","10","12","14","16","18","20","22","24","26","28","30","32","34"};
            String textfont[]={"DroidSerif-Regular",
                    "bunga belati putih",
                    "CROCHET PATTERN",
                    "croissant sandwich",
                    "DroidSerif-Bold",
                    "DroidSerif-Boldltalic",
                    "DroidSerif-ltalic",
                    "atmostsphere",
                    "FallingSkyBd+Obl",
                    "FallingSky",
                    "FallingSkyCondOu",
                    "FallingSkyCondOuObl",
                    "FallingSkyExBd",
                    "green avocado",
                    "painting the light"};

            Typeface atmostsphere=Typeface.createFromAsset(getActivity().getAssets(),"atmostsphere.ttf");
            Typeface bungabelatiputih=Typeface.createFromAsset(getActivity().getAssets(),"bunga melati putih.ttf");
            Typeface CROCHETPATTERN=Typeface.createFromAsset(getActivity().getAssets(),"CROCHET PATTERN.ttf");
            Typeface croissantsandwich=Typeface.createFromAsset(getActivity().getAssets(),"croissant sandwich.ttf");
            Typeface DroidSerifBold=Typeface.createFromAsset(getActivity().getAssets(),"DroidSerif-Bold.ttf");
            Typeface DroidSerifBoldltalic=Typeface.createFromAsset(getActivity().getAssets(),"DroidSerif-BoldItalic.ttf");
            Typeface DroidSerifltalic=Typeface.createFromAsset(getActivity().getAssets(),"DroidSerif-Italic.ttf");
            Typeface DroidSerifRegular=Typeface.createFromAsset(getActivity().getAssets(),"DroidSerif-Regular.ttf");
            Typeface FallingSkyBdObl=Typeface.createFromAsset(getActivity().getAssets(),"FallingSkyBd+Obl.otf");
            Typeface FallingSky= Typeface.createFromAsset(getActivity().getAssets(),"FallingSky.otf");
            Typeface FallingSkyCondOu=Typeface.createFromAsset(getActivity().getAssets(),"FallingSkyCondOu.otf");
            Typeface FallingSkyCondOuObl=Typeface.createFromAsset(getActivity().getAssets(),"FallingSkyCondOuObl.otf");
            Typeface FallingSkyExBd=Typeface.createFromAsset(getActivity().getAssets(),"FallingSkyExBd.otf");
            Typeface greenavocado=Typeface.createFromAsset(getActivity().getAssets(),"green avocado.ttf");
            Typeface paintingthelight=Typeface.createFromAsset(getActivity().getAssets(),"painting the light.ttf");

            final Typeface myfont[]={DroidSerifRegular,
                    bungabelatiputih,
                    CROCHETPATTERN,
                    croissantsandwich,
                    DroidSerifBold
                    ,DroidSerifBoldltalic,
                    DroidSerifltalic,
                    atmostsphere,
                    FallingSkyBdObl,
                    FallingSky,
                    FallingSkyCondOu,
                    FallingSkyCondOuObl,
                    FallingSkyExBd,
                    greenavocado,
                    paintingthelight};

            colorunderA=(TextView)v1.findViewById(R.id.color_underA);
            txt_test=(TextView)v1.findViewById(R.id.text_modify);
            changeColor=(Button)v1.findViewById(R.id.button_changeColor);
            Spinner spn_textfont=(Spinner)v1.findViewById(R.id.textfont_spinner);
            custom_spinnerAdapter customSpinnerAdapter=new custom_spinnerAdapter(getActivity(),textfont);
            spn_textfont.setAdapter(customSpinnerAdapter);

            txt_test.setText(((TextView)view).getText().toString());

            spn_textfont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    txt_test.setTypeface(myfont[i]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            Spinner spn=(Spinner)v1.findViewById(R.id.textsize_spinner);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arr);
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            spn.setAdapter(adapter);

            spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        txt_test.setTextSize(14);
                    } else
                        txt_test.setTextSize((position + 3) * 2);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {


                }
            });

            changeColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("change color");
                    LayoutInflater inflater = LayoutInflater.from(view.getContext());

                    View v2 = inflater.inflate(R.layout.alertcolor_layout, null);
                    builder.setView(v2);
                    final AlertDialog a = builder.create();
                    Button btn_black = (Button) v2.findViewById(R.id.color_automatic);

                    Button btn_color1 = (Button) v2.findViewById(R.id.color_btn1);
                    btn_color1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn1));
                            txt_test.setTextColor(getResources().getColor(R.color.btn1));
                            a.cancel();
                        }
                    });
                    Button btn_color11 = (Button) v2.findViewById(R.id.color_btn11);
                    btn_color11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn11));
                            txt_test.setTextColor(getResources().getColor(R.color.btn11));
                            a.cancel();
                        }
                    });
                    Button btn_color12 = (Button) v2.findViewById(R.id.color_btn12);
                    btn_color12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn12));
                            txt_test.setTextColor(getResources().getColor(R.color.btn12));
                            a.cancel();
                        }
                    });
                    Button btn_color13 = (Button) v2.findViewById(R.id.color_btn13);
                    btn_color13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn13));
                            txt_test.setTextColor(getResources().getColor(R.color.btn13));
                            a.cancel();
                        }
                    });
                    Button btn_color14 = (Button) v2.findViewById(R.id.color_btn14);
                    btn_color14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn14));
                            txt_test.setTextColor(getResources().getColor(R.color.btn14));
                            a.cancel();
                        }
                    });
                    Button btn_color15 = (Button) v2.findViewById(R.id.color_btn15);
                    btn_color15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn15));
                            txt_test.setTextColor(getResources().getColor(R.color.btn15));
                            a.cancel();
                        }
                    });

                    Button btn_color2 = (Button) v2.findViewById(R.id.color_btn2);
                    btn_color2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn2));
                            txt_test.setTextColor(getResources().getColor(R.color.btn2));
                            a.cancel();
                        }
                    });
                    Button btn_color21 = (Button) v2.findViewById(R.id.color_btn21);
                    btn_color21.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn21));
                            txt_test.setTextColor(getResources().getColor(R.color.btn21));
                            a.cancel();
                        }
                    });
                    Button btn_color22 = (Button) v2.findViewById(R.id.color_btn22);
                    btn_color22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn22));
                            txt_test.setTextColor(getResources().getColor(R.color.btn22));
                            a.cancel();
                        }
                    });
                    Button btn_color23 = (Button) v2.findViewById(R.id.color_btn23);
                    btn_color23.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn23));
                            txt_test.setTextColor(getResources().getColor(R.color.btn23));
                            a.cancel();
                        }
                    });
                    Button btn_color24 = (Button) v2.findViewById(R.id.color_btn24);
                    btn_color24.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn24));
                            txt_test.setTextColor(getResources().getColor(R.color.btn24));
                            a.cancel();
                        }
                    });
                    Button btn_color25 = (Button) v2.findViewById(R.id.color_btn25);
                    btn_color25.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn25));
                            txt_test.setTextColor(getResources().getColor(R.color.btn25));
                            a.cancel();
                        }
                    });

                    Button btn_color3 = (Button) v2.findViewById(R.id.color_btn3);
                    btn_color3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn3));
                            txt_test.setTextColor(getResources().getColor(R.color.btn3));
                            a.cancel();
                        }
                    });
                    Button btn_color31 = (Button) v2.findViewById(R.id.color_btn31);
                    btn_color31.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn31));
                            txt_test.setTextColor(getResources().getColor(R.color.btn31));
                            a.cancel();
                        }
                    });
                    Button btn_color32 = (Button) v2.findViewById(R.id.color_btn32);
                    btn_color32.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn32));
                            txt_test.setTextColor(getResources().getColor(R.color.btn32));
                            a.cancel();
                        }
                    });
                    Button btn_color33 = (Button) v2.findViewById(R.id.color_btn33);
                    btn_color33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn33));
                            txt_test.setTextColor(getResources().getColor(R.color.btn33));
                            a.cancel();
                        }
                    });
                    Button btn_color34 = (Button) v2.findViewById(R.id.color_btn34);
                    btn_color34.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn34));
                            txt_test.setTextColor(getResources().getColor(R.color.btn34));
                            a.cancel();
                        }
                    });
                    Button btn_color35 = (Button) v2.findViewById(R.id.color_btn35);
                    btn_color35.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn35));
                            txt_test.setTextColor(getResources().getColor(R.color.btn35));
                            a.cancel();
                        }
                    });

                    Button btn_color4 = (Button) v2.findViewById(R.id.color_btn4);
                    btn_color4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn4));
                            txt_test.setTextColor(getResources().getColor(R.color.btn4));
                            a.cancel();
                        }
                    });
                    Button btn_color41 = (Button) v2.findViewById(R.id.color_btn41);
                    btn_color41.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn41));
                            txt_test.setTextColor(getResources().getColor(R.color.btn41));
                            a.cancel();
                        }
                    });
                    Button btn_color42 = (Button) v2.findViewById(R.id.color_btn42);
                    btn_color42.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn42));
                            txt_test.setTextColor(getResources().getColor(R.color.btn42));
                            a.cancel();
                        }
                    });
                    Button btn_color43 = (Button) v2.findViewById(R.id.color_btn43);
                    btn_color43.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn43));
                            txt_test.setTextColor(getResources().getColor(R.color.btn43));
                            a.cancel();
                        }
                    });
                    Button btn_color44 = (Button) v2.findViewById(R.id.color_btn44);
                    btn_color44.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn44));
                            txt_test.setTextColor(getResources().getColor(R.color.btn44));
                            a.cancel();
                        }
                    });
                    Button btn_color45 = (Button) v2.findViewById(R.id.color_btn45);
                    btn_color45.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn45));
                            txt_test.setTextColor(getResources().getColor(R.color.btn45));
                            a.cancel();
                        }
                    });

                    Button btn_color5 = (Button) v2.findViewById(R.id.color_btn5);
                    btn_color5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn5));
                            txt_test.setTextColor(getResources().getColor(R.color.btn5));
                            a.cancel();
                        }
                    });
                    Button btn_color51 = (Button) v2.findViewById(R.id.color_btn51);
                    btn_color51.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn51));
                            txt_test.setTextColor(getResources().getColor(R.color.btn51));
                            a.cancel();
                        }
                    });
                    Button btn_color52 = (Button) v2.findViewById(R.id.color_btn52);
                    btn_color52.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn52));
                            txt_test.setTextColor(getResources().getColor(R.color.btn52));
                            a.cancel();
                        }
                    });
                    Button btn_color53 = (Button) v2.findViewById(R.id.color_btn53);
                    btn_color53.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn53));
                            txt_test.setTextColor(getResources().getColor(R.color.btn53));
                            a.cancel();
                        }
                    });
                    Button btn_color54 = (Button) v2.findViewById(R.id.color_btn54);
                    btn_color54.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn54));
                            txt_test.setTextColor(getResources().getColor(R.color.btn54));
                            a.cancel();
                        }
                    });
                    Button btn_color55 = (Button) v2.findViewById(R.id.color_btn55);
                    btn_color55.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.btn55));
                            txt_test.setTextColor(getResources().getColor(R.color.btn55));
                            a.cancel();
                        }
                    });


                    btn_black.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            colorunderA.setBackgroundColor(getResources().getColor(R.color.automatic));
                            txt_test.setTextColor(getResources().getColor(R.color.automatic));
                            a.cancel();
                        }
                    });

                    a.show();

                }
            });
            builder.setTitle("thay đổi định dạng chữ");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((TextView) v).setTextColor(txt_test.getTextColors());
                    ((TextView) v).setTextSize(txt_test.getTextSize());
                    ((TextView) v).setTypeface(txt_test.getTypeface());
                }
            });

            AlertDialog a = builder.create();
            a.show();
        }
    };


    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_createcard,null);
        v.setLongClickable(true);
        gestureDetector = new GestureDetector(getActivity(), new SingleTapConfirm());
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
                Toast.makeText(getActivity(),"Chạm và giữ để chỉnh sửa lại",Toast.LENGTH_LONG).show();
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
        builder.setMessage("Hãy chọn ID cho card của bạn ");
        final ArrayList<Integer> finalIntegers = integers;
        builder.setNegativeButton(txtHoten.getText().toString() + finalIntegers.get(0).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Data_Info data_info=new Data_Info(finalIntegers.get(0).toString(),txtHoten.getText().toString(),txtCongty.getText().toString(),txtSodienthoai.getText().toString(),txtDiachi.getText().toString(),txtChucvu.getText().toString(),txtEmail.getText().toString());
                FirebaseData.create_Info_Card(getActivity(), StaticValues.idfacebook, data_info);
                FirebaseData.save_Image_Card(layout,getActivity());
            }
        });
        builder.setNeutralButton(txtHoten.getText().toString() + finalIntegers.get(1).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Data_Info data_info=new Data_Info(finalIntegers.get(1).toString(),txtHoten.getText().toString(),txtCongty.getText().toString(),txtSodienthoai.getText().toString(),txtDiachi.getText().toString(),txtChucvu.getText().toString(),txtEmail.getText().toString());
                FirebaseData.create_Info_Card(getActivity(), StaticValues.idfacebook, data_info);
                FirebaseData.save_Image_Card(layout, getActivity());
            }
        });
        builder.setPositiveButton(txtHoten.getText().toString() + finalIntegers.get(2).toString(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Data_Info data_info=new Data_Info(finalIntegers.get(2).toString(),txtHoten.getText().toString(),txtCongty.getText().toString(),txtSodienthoai.getText().toString(),txtDiachi.getText().toString(),txtChucvu.getText().toString(),txtEmail.getText().toString());
                FirebaseData.create_Info_Card(getActivity(), StaticValues.idfacebook, data_info);
                FirebaseData.save_Image_Card(layout, getActivity());
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

        txtChucvu.setOnTouchListener(this);

        txtHoten.setOnLongClickListener(event);

        txtHoten.setOnTouchListener(this);

        txtCongty.setOnLongClickListener(event);

        txtCongty.setOnTouchListener(this);

        txtDiachi.setOnLongClickListener(event);

        txtDiachi.setOnTouchListener(this);

        txtSodienthoai.setOnLongClickListener(event);

        txtSodienthoai.setOnTouchListener(this);

        txtEmail.setOnLongClickListener(event);
  
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
                    String picturePath = Func_fragment.getPath(getActivity(), uri);

//                    String[] filePathColumn = { MediaStore.Images.Media.DATA,MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Thumbnails.DATA };
//                    Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    String picturePath = cursor.getString(columnIndex);
//                    if (cursor.equals(null)){
//
//                        Toast.makeText(getActivity(), "Kiểm tra quyền truy cập", Toast.LENGTH_SHORT).show();
//
//                    }else {
//                        //cursor.close();
                        Log.i("vt100", picturePath);
                        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                        //Drawable drawable=new BitmapDrawable(getResources(),bitmap);
                        //txtLinkAva.setText(picturePath);
                        if (bitmap != null) {
                            imgLogo.setImageBitmap(bitmap);
                        } else Log.i("null", "null");
                    }
                    //
//                        int index= lastIndexOf(path, '/');
//                        int length=path.length();
//                        String filechoose=substring(path, index, length);
                    //txtLinkAva.setText(filechoose);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload

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
                    String picturePath = Func_fragment.getPath(getActivity(), uri);
//                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                    Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    String picturePath = cursor.getString(columnIndex);
//                    cursor.close();

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
        if (gestureDetector.onTouchEvent(event)) {
            // single tap
            changeText.onClick(v);
            return true;
        } else {
            // your code for move and drag
            moveView(v, event);
        }

        return false;

    }
    private class SingleTapConfirm extends SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            return true;
        }
    }

    public void moveView(View v, MotionEvent event){

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
    }
}