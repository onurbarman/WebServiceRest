package com.example.onurbarman.webservice.network;

import com.example.onurbarman.webservice.model.UserList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by OnurBarman on 8.07.2018.
 */

public interface GetUserDataService {
    @GET(".")
    Call<UserList> getUserData();
}
