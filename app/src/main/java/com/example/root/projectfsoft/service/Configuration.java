package com.example.root.projectfsoft.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 21/12/2016.
 */

public final class Configuration {
    public Configuration(){

    }
    public static Retrofit retrofit=null;
    public static String Base_Url="https://maps.googleapis.com/maps/api/place/";

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create(createGson()))
                    .build();
        }
        return retrofit;
    }
    private static Gson createGson(){
        return new GsonBuilder().setLenient()
                   .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
