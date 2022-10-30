package com.electsofte.parser.users.get_delete_update;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 24.10.2022 15:05
 */
public interface UserServiseDEL {
    @DELETE("yourPath")
    Call<UserResponseGET> deleteUser(@Path("id") int userId);
}
