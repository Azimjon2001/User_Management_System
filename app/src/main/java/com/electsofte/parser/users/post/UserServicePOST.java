package com.electsofte.parser.users.post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 30.09.2022 5:14
 */
public interface UserServicePOST {

     @POST("yourPath")
       Call<UserResponse> saveUser(@Body UserRequest userRequest);


}
