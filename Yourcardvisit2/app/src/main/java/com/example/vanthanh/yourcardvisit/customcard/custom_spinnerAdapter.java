package com.example.vanthanh.yourcardvisit.customcard;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.vanthanh.yourcardvisit.R;
import com.example.vanthanh.yourcardvisit.activity.MainActivity;

/**
 * Created by DELL on 7/20/2016.
 */
public class custom_spinnerAdapter extends BaseAdapter {
    Context context;
    String[] textfont;
    Typeface[] myfont;
    LayoutInflater inflater;

    public custom_spinnerAdapter(Context context, String[] textfont) {
        this.context = context;
        this.textfont = textfont;
        this.myfont = myfont;
        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return textfont.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.customspinner,null);
        TextView text=(TextView)view.findViewById(R.id.customspinner_textView);
        text.setText(textfont[i]);

        switch (i){
            case 0:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"DroidSerif-Regular.ttf"));
                break;
            case 1:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"bunga melati putih.ttf"));
                break;
            case 2:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"CROCHET PATTERN.ttf"));
                break;
            case 3:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"croissant sandwich.ttf"));
                break;
            case 4:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"DroidSerif-Bold.ttf"));
                break;
            case 5:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"DroidSerif-BoldItalic.ttf"));
                break;
            case 6:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"DroidSerif-Italic.ttf"));
                break;
            case 7:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"atmostsphere.ttf"));
                break;
            case 8:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"FallingSkyBd+Obl.otf"));
                break;
            case 9:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"FallingSky.otf"));
                break;
            case 10:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"FallingSkyCondOu.otf"));
                break;
            case 11:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"FallingSkyCondOuObl.otf"));
                break;
            case 12:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"FallingSkyExBd.otf"));
                break;
            case 13:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"green avocado.ttf"));
                break;
            case 14:
                text.setTypeface(Typeface.createFromAsset(context.getAssets(),"painting the light.ttf"));
                break;

        }


        return view;
    }
}
