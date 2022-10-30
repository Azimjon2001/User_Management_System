package com.electsofte.parser.users.post;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 30.09.2022 4:56
 */
public class ApiClient {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
         httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("url")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return  retrofit;
    }
    public  static UserServicePOST getuserService(){
        UserServicePOST userService=getRetrofit().create(UserServicePOST.class);
        return userService;
    }
}
