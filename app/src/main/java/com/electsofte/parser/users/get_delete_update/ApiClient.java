package com.electsofte.parser.users.get_delete_update;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 17.10.2022 5:05
 */
public class ApiClient {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("url")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return  retrofit;
        }

    public static UserServiceGET getUserService(){
        UserServiceGET userService = getRetrofit().create(UserServiceGET.class);
        return userService;
    }
 public static UserServiseDEL deleteUserService(){
        UserServiseDEL userServiseDEL = getRetrofit().create(UserServiseDEL.class);
        return userServiseDEL;
 }
  public  static UserServicePUT updateUserService(){
        UserServicePUT userServicePUT = getRetrofit().create(UserServicePUT.class);
        return userServicePUT;
  }

}
