package com.example.root.projectfsoft.service.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by root on 29/12/2016.
 */

public class ToolsActivity {
    ProgressDialog mLoading;
    public static  boolean isNetworkOnline(Context context) {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
    }

    public void showLoading(Context context){
        mLoading = ProgressDialog.show(context,"","Loading",true);
    }

    public void dismissLoading(Context context){
        mLoading.dismiss();
    }


}
