package com.electsofte.parser.users.get_delete_update;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 26.10.2022 4:15
 */
public interface UserServicePUT {
    @PUT("yourPath")
    Call<UserResponseGET> updateUser(@Body UserRequestPUT userRequestPUT, @Path("id") int id);
}
