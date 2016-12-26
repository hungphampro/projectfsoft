package com.example.root.projectfsoft.dataBase;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import io.realm.Realm;

/**
 * Created by root on 23/12/2016.
 */

public  class RealmController {
    public static RealmController realmController;
    private static Realm realm;
    public  RealmController(Application app){
        if(realm==null){
            realm=Realm.getDefaultInstance();
        }
    }
    public static RealmController with(Activity activity){
        if(realmController==null){
            realmController=new RealmController(activity.getApplication());
        }
        return realmController;
    }
    public static RealmController with(Fragment fragment){
        if(realmController==null){
            realmController=new RealmController(fragment.getActivity().getApplication());
        }
        return realmController;
    }
    public static RealmController with(Context mContext){
        if(realmController==null){
            Activity activity= (Activity) mContext;
            realmController=new RealmController(activity.getApplication());
        }
        return realmController;
    }
    public Realm getRealm(){
        return realm;
    }
    public RealmController getInstance(){
        return realmController;
    }
    public void refesh(){
        realm.refresh();
    }
}
