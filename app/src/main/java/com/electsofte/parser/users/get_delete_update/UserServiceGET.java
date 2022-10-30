package com.electsofte.parser.users.get_delete_update;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 17.10.2022 5:06
 */
public interface UserServiceGET {
    @GET("yourPath")
    Call<UserResponseGET> getAllUsers();


}
