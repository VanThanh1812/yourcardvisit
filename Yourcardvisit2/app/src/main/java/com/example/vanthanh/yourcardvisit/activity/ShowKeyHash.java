package com.example.vanthanh.yourcardvisit.activity;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by DELL on 7/15/2016.
 */
public class ShowKeyHash extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        prinHasykey();
    }
    public void prinHasykey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.vanthanh.yourcardvisit.activity",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
